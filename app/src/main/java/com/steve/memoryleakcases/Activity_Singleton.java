package com.steve.memoryleakcases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_Singleton extends AppCompatActivity {
    private SingletonSample singletonSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);

        // Option 1: Do not pass activity context to the Singleton class. If there are more than one
        // Activities, each one passes different context :-(
        singletonSample = SingletonSample.getInstance(this);
        // 1. Correct way: pass applicationContext
        // singletonSample = SingletonSample.getInstance(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 2: Unregister the singleton class if you passed activity's context.
        // singletonSample.onDestroy();
    }
}
