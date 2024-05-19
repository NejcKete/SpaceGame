package com.example.dogger;




import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private Player player;

    final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private ArrayList<Rocket> rockets;
    private int updateCounter = 0;

    private Context mContext;

    private AsteroidSheet asteroidSheet;

    private ShipSheet shipSheet;

    private int score;


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    public Game(Context context) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        gameLoop = new GameLoop(this,surfaceHolder);
        setFocusable(true);
        this.asteroidSheet = new AsteroidSheet(context);
        this.shipSheet = new ShipSheet(context);
        player = new Player(500,500,75,5,shipSheet.getShip());
        rockets = new ArrayList<>();
        genRockets(10);
        mContext=context;


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


        for(Rocket i:rockets){
            i.draw(canvas);
        }
        drawScore(canvas);
        player.draw(canvas);

    }
    public void update(){
        if (player.getPlayerHealth() <= 0) {
            mContext.startActivity(new Intent(mContext, GameEnd.class));
            return;
        }
        moveRocket();
        updateCounter++;
        if(updateCounter == 1000){
            genRockets(3);
            updateCounter = 0;
        }
        for(Rocket i:rockets) {
            if (isColliding(i, player)) {
                rockets.remove(i);
                player.setPlayerHealth(player.getPlayerHealth() - 1);
                break;
            }
        }


    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (player.getPlayerHealth() <= 0) {
            return false;
        }
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();

        int playerX = player.getX();
        int playerY = player.getY();
        int playerRadius = player.getRadius();

        int leniency = 50;
        if(touchX <= playerRadius) // Checks if the player is outside of screen edges
            touchX = playerRadius;
        else if(touchX >= screenWidth - playerRadius)
            touchX = screenWidth - playerRadius;
        if(touchY <= playerRadius)
            touchY = playerRadius;
        else if(touchY >= screenHeight - playerRadius)
            touchY = screenWidth - playerRadius;

        if (touchX >= (playerX - playerRadius) - leniency && // Checks if the player is being moved from their previous position
                touchX <= (playerX + playerRadius) + leniency &&
                touchY >= (playerY - playerRadius) - leniency &&
                touchY <= (playerY + playerRadius) + leniency
        ) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    player.setX(touchX);
                    player.setY(touchY);
                    return true;
            }
        }

        return super.onTouchEvent(event);
    }
    public void moveRocket(){
        for(Rocket i:rockets){
            boolean count=i.moveRocket(screenWidth,screenHeight);
            if(count==true){
                score++;
            }
        }
    }
    public void genRockets(int j) {
        for (int i = 0; i < j; i++) {
            int y = (int) (Math.random() * 2500-2500);
            int x = (int) (Math.random() * (screenWidth-200)+100);;
            rockets.add(new Rocket(x, y,50,asteroidSheet.getAsteroid()));
        }
    }
    public static boolean isColliding(Rocket rocket, Player player) {
        double distance = getDistance(rocket.getX(), rocket.getY(), player);
        double radiusDistance = rocket.getRadius()/2 + player.getRadius();
        double distanceToCollision = Math.sqrt(radiusDistance * radiusDistance);
        if(distance < distanceToCollision)
            return true;
        else
            return false;
    }
    public static double getDistance(double bulletX, double bulletY, Player player) {
        double distanceX = bulletX - player.getX();
        double distanceY = bulletY - player.getY();

        return Math.sqrt((distanceX*distanceX) + (distanceY*distanceY));
    }
    public void pause(){
        gameLoop.stopLoop();
    }

    public void drawScore(Canvas canvas) {
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.white);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("Score: " + score, 100, 200,paint);
    }
    public int getScore(){
        return score;
    }



}

