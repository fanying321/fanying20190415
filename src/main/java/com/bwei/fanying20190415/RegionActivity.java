package com.bwei.fanying20190415;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.fanying20190415.mvp.model.BaseModel;
import com.bwei.fanying20190415.mvp.presenter.BasePresenter;
import com.bwei.fanying20190415.mvp.view.BaseView;

import okhttp3.FormBody;

public class RegionActivity extends AppCompatActivity implements BaseView {

    private EditText Mname,Mpwsd;
    private Button region;
    private String url = "http://172.17.8.100/small/user/v1/register";
    private BasePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        Mname = findViewById(R.id.region_name);
        Mpwsd = findViewById(R.id.region_pwd);
        region = findViewById(R.id.region);
        presenter = new BasePresenter(new BaseModel(),this);
        //点击注册
        region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //非空验证
                String name = Mname.getText().toString().trim();
                String pwd = Mpwsd.getText().toString().trim();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(RegionActivity.this,"输入的内容不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("phone",name);
                builder.add("pwd",pwd);
                presenter.doPost(1,url,builder);
            }
        });
    }

    @Override
    public void success(int type, String data) {
        if (type == 1){
            Toast.makeText(RegionActivity.this,data,Toast.LENGTH_LONG);
            Log.i("aaa",data);
            finish();
        }
    }

    @Override
    public void fail(String error) {

    }
}
