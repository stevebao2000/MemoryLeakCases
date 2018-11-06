package com.steve.memoryleakcases;

import android.content.Context;

public class SingletonSample {
    private Context context;
    private static SingletonSample instance;

    private SingletonSample(Context context) {
        this.context = context;
    }

    public synchronized static SingletonSample getInstance(Context context) {
        if (instance == null) instance = new SingletonSample(context);
        return instance;
    }

    public void onDestroy() {
        if(context != null) {
            context = null;
        }
    }

}
