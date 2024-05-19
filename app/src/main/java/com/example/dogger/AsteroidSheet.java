package com.example.dogger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;


public class AsteroidSheet {
    private Bitmap bitmap;
    public AsteroidSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid_sheet,bitmapOptions);
    }
    public Asteroid getAsteroid(){
        return new Asteroid(this,new Rect(0,0,160,160));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
