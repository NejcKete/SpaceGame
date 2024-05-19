package com.example.dogger;




import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {

    private boolean isRunning = false;
    private Game game;
    private double averageUPS;
    private double averageFPS;
    private long time;
    private static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;
    private SurfaceHolder surfaceHolder;
    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        this.game = game;

    }

    public void startLoop() {
        isRunning = true;
        start();
    }
    public void stopLoop(){
        isRunning = false;
        try {
            join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        super.run();
        Canvas canvas = null;
        int frameCount = 0;
        int updateCount = 0;
        long startTime;
        long elapsedTime;
        long sleepTime;
        startTime = System.currentTimeMillis();
        long startTime2 = System.currentTimeMillis();
        while (isRunning){
            // Try to update objects from the Game class
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    game.draw(canvas);
                    updateCount++;
                }
            }
            catch(IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            // Pause the game loop to not exceed the target UPS
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long)(updateCount*UPS_PERIOD - elapsedTime);
            if(sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // Skip frames to keep up with the target UPS
            while(sleepTime < 0 && updateCount < MAX_UPS-1) {
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long)(updateCount*UPS_PERIOD - elapsedTime);
            }

            // Calculate the average UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime >= 1000) {
                averageUPS = updateCount / (1E-3 * elapsedTime);
                averageFPS = frameCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
            time = System.currentTimeMillis() - startTime2;
        }
    }
    public double getAverageUPS(){
        return averageUPS;
    }

    public double getAverageFPS(){
        return averageFPS;
    }


}
