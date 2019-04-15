package com.bwei.fanying20190415.mvp.view;

/**
 * @Author：莹
 * @E-mail： 2016906034@qq.com
 * @Date：2019/4/15 9:27
 * @Description：描述信息
 */
public interface BaseView {
    //成功
    void success(int type,String data);
    //失败
    void fail(String error);
}
