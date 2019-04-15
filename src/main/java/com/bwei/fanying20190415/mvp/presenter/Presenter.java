package com.bwei.fanying20190415.mvp.presenter;

import okhttp3.FormBody;

/**
 * @Author：莹
 * @E-mail： 2016906034@qq.com
 * @Date：2019/4/13 9:31
 * @Description：描述信息
 */
public interface Presenter {
    void doGet(int type, String url);

    void doPost(int type, String url, FormBody.Builder builder);
}
