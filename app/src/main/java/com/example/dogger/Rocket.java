package com.example.dogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Rocket{

    private int x;
    private int y;
    private int radius;


    private Asteroid asteroid;

    Rocket(int x,int y, int radius,Asteroid asteroid) {
        this.x = x;
        this.y = y;
        this.radius = radius;

        this.asteroid=asteroid;


    }

    /*Setter*/
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    /*Getter*/
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    /*Method*/
    public void draw(Canvas canvas) {
        asteroid.draw(canvas,x-50,y-50);
    }

    public boolean moveRocket(int screenWidth,int screenHeight){
        if (screenHeight+radius < y){
            this.y=0-radius;
            this.x = (int) (Math.random() * (screenWidth));
            return true;
        }
        else{
            setY(y+5);
            return false;
        }
    }

}
