package com.example.animalproject;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.channels.NetworkChannel;

public class HelloApplication extends Application {

    public static final int TILE_SIZE = 25;
    private World myWorld;
    public boolean isAnimationRunning() {
        return isAnimationRunning;
    }

    public void setAnimationRunning(boolean animationRunning) {
        isAnimationRunning = animationRunning;
    }

    private boolean isAnimationRunning = false;

    private AnimationTimer animTimer;

    @Override
    public void start(Stage stage) throws IOException {
        /*
        HelloController - both the view and the controller for at least our default view
         */
        HelloController hc = new HelloController(this);
        myWorld = new World((int) (hc.getCanvasHeight() / TILE_SIZE), (int) (hc.getCanvasWidth() / TILE_SIZE));
        //myWorld = new World(5,3);

        //actors
        myWorld.addActor(new Actor("a1", myWorld, 1, 1));
        myWorld.addActor(new BoxBug("jack", myWorld, 5,5));
        myWorld.addActor(new BoxBug("reggie", myWorld, 10, 10));
        myWorld.addActor(new Tiger("reggie", myWorld, 15, 10,2));
        myWorld.addActor(new BoxBug("eric1", myWorld, 11, 10));
        myWorld.addActor(new BoxBug("eric2", myWorld, 12, 10));
        myWorld.addActor(new BoxBug("eric3", myWorld, 13, 10));
        myWorld.addActor(new Grass("g1", myWorld, 15, 15));
        myWorld.addActor(new Scorpion("s1", myWorld, 20, 20));
        myWorld.addActor(new Rocks("r1", myWorld, 30, 30));

        Scene rootScene = new Scene(hc.getPane(), 1280, 920);
        stage.setTitle("Animal Sim");
        stage.setScene(rootScene);

         animTimer = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long currrentNanoTime) {
                if (currrentNanoTime - lastUpdate >= 500_000_000){
                    System.out.println();
                    myWorld.step();
                    myWorld.draw(hc.getGraphicsContext());
                    lastUpdate = currrentNanoTime;
                }
            }
        };
        myWorld.draw(hc.getGraphicsContext());
        stage.show();
    }//end start

        public void startAnimation(){
            animTimer.start();
            isAnimationRunning = true;
        }

        public void stopAnimation() {
            animTimer.stop();
            isAnimationRunning = false;
        }

    public static void main(String[] args){
        launch();
    }
}