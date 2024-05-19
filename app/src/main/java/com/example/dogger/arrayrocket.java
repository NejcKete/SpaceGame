package com.example.dogger;

import android.graphics.Canvas;

import java.util.ArrayList;

public class arrayrocket {
    private ArrayList<Rocket> rockets;

    public arrayrocket(){
        rockets= new ArrayList<>();
    }
    public  void drawRocket(int i,Canvas canvas){
        rockets.get(i).draw(canvas);
    }
    public void rocketBoom(int i){
        rockets.remove(i);
    }
}
