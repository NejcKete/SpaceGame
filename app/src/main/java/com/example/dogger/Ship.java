package com.example.dogger;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Ship {
    private final ShipSheet shipSheet;
    private final Rect rect;

    public Ship(ShipSheet shipSheet, Rect rect){
        this.shipSheet = shipSheet;
        this.rect = rect;
    }

    public void draw(Canvas canvas, int x, int y){
        canvas.drawBitmap(shipSheet.getBitmap(),rect,new Rect(x,y,x+200,y+200),null);
    }
}
