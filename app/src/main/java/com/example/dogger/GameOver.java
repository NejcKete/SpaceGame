package com.example.dogger;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;
/*
*Game Over class Draws Game over text
*/
public class GameOver {



    public void draw(Canvas canvas,Context context){
        String text="Game Over";
        float x=canvas.getWidth()/2;
        float y=canvas.getHeight()/2;
        Paint paint=new Paint();
        int color= ContextCompat.getColor(context,R.color.gameOver);
        paint.setColor(color);
        float textSize=150;
        paint.setTextSize(textSize);
        canvas.drawText(text,x,y,paint);

    }
}
