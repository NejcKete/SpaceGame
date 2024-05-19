package com.example.dogger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class ShipSheet {
    private Bitmap bitmap;
    public ShipSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid_sheet,bitmapOptions);
    }
    public Ship getShip(){
        return new Ship(this,new Rect(160,0,320,160));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
