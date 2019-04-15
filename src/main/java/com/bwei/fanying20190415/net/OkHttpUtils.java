package com.bwei.fanying20190415.net;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Author：莹
 * @E-mail： 2016906034@qq.com
 * @Date：2019/4/15 9:42
 * @Description：描述信息
 */
public class OkHttpUtils {

    public OkHttpUtils get(String url){
        getShow(0,url,null);
        return this;
    }
    public OkHttpUtils post(String url,FormBody.Builder builderBody){
        getShow(1,url,builderBody);
        return this;
    }
    //1.网络请求
    private void getShow(int i, String url,FormBody.Builder builderBody) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return chain.proceed(request);
            }
        }).build();
        //4.
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        //3.判断
        if (i == 0){
            builder.get();
        }else {
            RequestBody body = builderBody.build();
            builder.post(body);
        }
        //2.
        Request request = builder.build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.what = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                message.obj = response.body().string();
                message.what = 1;
                handler.sendMessage(message);
            }
        });
    }
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                String str = (String) msg.obj;
                httpListener.success(str);
            }else {
                httpListener.fail("失败");
            }
        }
    };
    private HttpListener httpListener;
    public interface HttpListener{
        void success(String data);
        void fail(String error);
    }
    public void request(HttpListener httpListener){
        this.httpListener = httpListener;
    }
}
