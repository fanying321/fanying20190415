package com.bwei.fanying20190415.mvp.presenter;

import com.bwei.fanying20190415.mvp.model.Modle;
import com.bwei.fanying20190415.mvp.view.BaseView;

import okhttp3.FormBody;

/**
 * @Author：莹
 * @E-mail： 2016906034@qq.com
 * @Date：2019/4/13 10:36
 * @Description：描述信息
 */
public class BasePresenter implements Presenter, Modle.MyCallBack {

    private Modle modle;
    private BaseView baseView;
    public BasePresenter(Modle modle, BaseView baseView){
        this.modle = modle;
        this.baseView = baseView;
    }

    @Override
    public void doGet(int type, String url) {
        modle.showGet(type,url,this);
    }

    @Override
    public void doPost(int type, String url, FormBody.Builder builder) {
        modle.showPost(type,url,builder,this);
    }

    @Override
    public void success(int type, String data) {
        baseView.success(type,data);
    }

    @Override
    public void fail(String error) {
        baseView.fail(error);
    }
    //销毁
    public void destory(){
        if (modle != null){
            modle = null;
        }
        if (baseView != null){
            baseView = null;
        }
        System.gc();
    }
}
