package com.bwei.fanying20190415;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.fanying20190415.bean.LoginBean;
import com.bwei.fanying20190415.mvp.model.BaseModel;
import com.bwei.fanying20190415.mvp.presenter.BasePresenter;
import com.bwei.fanying20190415.mvp.view.BaseView;
import com.google.gson.Gson;

import okhttp3.FormBody;

public class MainActivity extends AppCompatActivity implements BaseView {

    private EditText Mname,Mpwd;
    private Button Login;
    private  String url = "http://172.17.8.100/small/user/v1/login";
    private BasePresenter presenter;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mname = findViewById(R.id.login_name);
        Mpwd = findViewById(R.id.Login_pwd);
        Login = findViewById(R.id.login);
        //调用presenter层
        presenter = new BasePresenter(new BaseModel(), this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        //点击登录
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取值
                String name = Mname.getText().toString().trim();
                String pwd = Mpwd.getText().toString().trim();
                //非空验证
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(MainActivity.this,"输入不能为空",Toast.LENGTH_LONG).show();
                }
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("phone",name);
                builder.add("pwd",pwd);
                presenter.doPost(1,url,builder);
            }
        });
        findViewById(R.id.Login_region).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegionActivity.class));
            }
        });
    }

    @Override
    public void success(int type, String data) {
        if (type == 1) {
            Gson gson = new Gson();
            LoginBean loginBean = gson.fromJson(data, LoginBean.class);
            Log.i("aaa", loginBean.toString());
            if (loginBean.getStatus().equals("0000")) {
                //创建sp
                SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
                String nickName = loginBean.getResult().getNickName();
                String headPic = loginBean.getResult().getHeadPic();
                config.edit().putString("name", nickName).commit();
                config.edit().putString("pic", headPic).commit();
                config.edit().putInt("id", loginBean.getResult().getUserId()).commit();
               startActivity(new Intent(MainActivity.this, InFoActivity.class));
            } else {
                Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void fail(String error) {

    }
}
