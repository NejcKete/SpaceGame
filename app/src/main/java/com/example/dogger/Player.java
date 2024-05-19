package com.example.dogger;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {
    private int x;
    private int y;
    private int radius;


    private int playerHealth;

    private Ship ship;

    public  Player(int x, int y, int radius,int playerHealt,Ship ship){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.playerHealth = playerHealt;
        this.ship=ship;
    }
    /*Getter*/
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getRadius(){
        return radius;
    }
    public int getPlayerHealth() {
        return playerHealth;
    }
    /*Setter*/
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    /*Method*/
    public void draw(Canvas canvas) {

        ship.draw(canvas,x-100,y-100);

    }
}
