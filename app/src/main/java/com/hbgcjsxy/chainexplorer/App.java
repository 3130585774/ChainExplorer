package com.hbgcjsxy.chainexplorer;

import android.app.Application;

import org.xutils.x;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //TODO :2023年3月28日 全局性的配置代码
        //配置xutils
        x.Ext.init(this);
        x.Ext.setDebug(Constant.isDebug);
    }
}
