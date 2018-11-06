package com.steve.memoryleakcases;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_innerClass extends AppCompatActivity {
    TextView textView;
    AsyncTask bgTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        textView = (TextView) findViewById(R.id.textView);
        bgTask = new BackgroundTask().execute();
    }

    private class BackgroundTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                return "interrupted";
            }
            return "finished";
        }

        @Override
        protected void onPostExecute(String result) {
            textView.setText(result);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // Solution: uncomment following two lines will eliminate memory leak.
        if (bgTask != null)
            bgTask.cancel(true); // cancel the AsyncTask will prevent memory leak.
    }
}
