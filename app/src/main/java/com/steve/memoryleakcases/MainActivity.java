package com.steve.memoryleakcases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.squareup.leakcanary.LeakCanary;

public class MainActivity extends AppCompatActivity {

    static boolean LeakInstalled = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (LeakCanary.isInAnalyzerProcess(getApplication())) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return; // if there is a memory leak, stop the app.
        }
        if (!LeakInstalled) {
            LeakCanary.install(getApplication());
            LeakInstalled = true;
        }

        setContentView(R.layout.activity_main);

        Button btnView = (Button)findViewById(R.id.innerClass);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Activity_innerClass.class);
                startActivity(intent);
            }
        });

        Button btnAsync = (Button)findViewById(R.id.asyncTask);
        btnAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Activity_asyncTask.class);
                startActivity(intent);
            }
        });

        Button btnService = (Button)findViewById(R.id.RegService);
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LocationActivity.class);
                startActivity(intent);
            }
        });

        Button btnStatic = (Button)findViewById(R.id.staticRef);
        btnStatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ActivityStatic.class);
                startActivity(intent);
            }
        });

        Button btnSingleton = (Button)findViewById(R.id.singleton);
        btnSingleton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Activity_Singleton.class);
                startActivity(intent);
            }
        });
    }

}
