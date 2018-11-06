package com.steve.memoryleakcases;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityStatic extends AppCompatActivity {

    private static TextView textView;  // remove the static key word
    private static Context context;  // remove the static key word
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);

        textView = findViewById(R.id.textViewS);
        context = getBaseContext();

        // your code goes here.
    }
}
