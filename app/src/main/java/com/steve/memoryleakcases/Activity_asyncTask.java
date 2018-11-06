package com.steve.memoryleakcases;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Activity_asyncTask extends AppCompatActivity {

    public static String[] fdyImages = {
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/Z3QjilA.jpg"
    };

    public static Bitmap[] thumbs = new Bitmap[fdyImages.length];
    picassoLoadImageAsyncTask asyncTask;
    boolean usePicasso = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        GlideApp.with(this)
                .load(R.drawable.loading)
                .override(200,200)
                .into((ImageView)findViewById(R.id.picaImage));

        /*
            This method can also apply to most of the background processors such as:
            handler, broadcast receiver, AdMob, listener...
         */
        asyncTask = new picassoLoadImageAsyncTask(getBaseContext(), (ImageView) findViewById(R.id.picaImage));
        asyncTask.execute(0);
    }
    @Override
    public void onStop() {
        // 3. Stop the running asyncTask if this activity is going to be destroyed.
        // if (asyncTask != null)
        //    asyncTask.cancel(true);
        super.onStop();
    }

}