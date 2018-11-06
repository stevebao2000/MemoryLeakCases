package com.steve.memoryleakcases;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.io.IOException;

import static com.steve.memoryleakcases.Activity_asyncTask.fdyImages;

public class picassoLoadImageAsyncTask extends AsyncTask<Integer, Integer, Bitmap>{
    private Context parentContext;
    private ImageView iv;

       public picassoLoadImageAsyncTask(Context context, ImageView view) {
        //// passing context or view object will potentially cause memory leak!
         parentContext = context;
         iv = view;

        //1. Use weak reference to avoid memory leak.
        //   parentContext = new WeakReference<>(context).get();
        //   iv = new WeakReference<>(view).get();
    }

    @Override
    protected Bitmap doInBackground(Integer... ints) {
        int index=ints[0];
        try {
            Bitmap bitmap =  Bitmap.createBitmap(Picasso.with(parentContext).load(fdyImages[index]).get());
            try {
                Thread.sleep(8000); // make this process runing longer time.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;
        } catch (IOException e) {
            Toast.makeText(parentContext, "Image loading failure.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return null;
    }

    // This function running on parent(UI) thread. You can update UI here!
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (iv != null )
            iv.setImageBitmap(bitmap);
        Toast.makeText(parentContext, "Images load completed.", Toast.LENGTH_SHORT).show();
        // 2. Call cancel(true) to stop the process.
        // cancel(true); // Stop the process to prevent from memory leak.
        return;
    }
}
