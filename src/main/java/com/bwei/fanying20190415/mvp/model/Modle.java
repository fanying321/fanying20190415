package com.bwei.fanying20190415.mvp.model;

import okhttp3.FormBody;

/**
 * @Author：莹
 * @E-mail： 2016906034@qq.com
 * @Date：2019/4/13 9:29
 * @Description：描述信息
 */
public interface Modle {
    public  interface MyCallBack{
        void success(int type, String data);
        void fail(String error);
    }
    void showGet(int type, String url, MyCallBack callBack);
    void showPost(int type, String url, FormBody.Builder builder, MyCallBack callBack);
        }
