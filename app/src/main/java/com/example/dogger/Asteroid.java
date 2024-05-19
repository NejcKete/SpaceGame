package com.example.dogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Asteroid {

    private final AsteroidSheet asteroidSheet;
    private final Rect rect;

    public Asteroid(AsteroidSheet asteroidSheet, Rect rect){
        this.asteroidSheet = asteroidSheet;
        this.rect = rect;
    }

    public void draw(Canvas canvas,int x,int y){
        canvas.drawBitmap(asteroidSheet.getBitmap(),rect,new Rect(x,y,x+100,y+100),null);
    }


}
