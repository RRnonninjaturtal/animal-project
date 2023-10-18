package com.example.animalproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class HelloController {
    private AnchorPane anchorPane;
    private Button startStopSim;
    private Button testButton2;
    private Canvas canvas;
    private GraphicsContext gc;
    private HelloApplication app;

    public HelloController(HelloApplication app){
        anchorPane = new AnchorPane();
        this.app = app;
        createGUI();
        attachListeners();
    }

    private void attachListeners() {
        startStopSim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleButtonClicks(actionEvent);
            }
        });

        testButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleButtonClicks(actionEvent);
            }
        });
    }

    public void handleButtonClicks(ActionEvent actionEvent){
        if(actionEvent.getSource() == startStopSim){
            if(app.isAnimationRunning()) {
                System.out.println("Start Sim");
                app.stopAnimation();
            }
            else{
                app.startAnimation();
                startStopSim.setText("Pause sim");
            }
        }//end if
        else if(actionEvent.getSource() == testButton2){
            System.out.println("Test Button 2 Clicked");
        }
    }

    private void createGUI() {
        startStopSim = new Button("Start Sim");
        startStopSim.setPrefSize(150,70);
        AnchorPane.setTopAnchor(startStopSim, 100.0);
        AnchorPane.setRightAnchor(startStopSim, 50.0);
        anchorPane.getChildren().add(startStopSim);

        testButton2 = new Button("Test Button 2");
        testButton2.setPrefSize(150,70);
        AnchorPane.setTopAnchor(testButton2, 300.0);
        AnchorPane.setRightAnchor(testButton2, 50.0);
        anchorPane.getChildren().add(testButton2);

        canvas = new Canvas(1000,800);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillRect(0,0,1000,800);
        AnchorPane.setLeftAnchor(canvas,10.0);
        AnchorPane.setTopAnchor(canvas, 10.0);
        anchorPane.getChildren().add(canvas);

    }

    public double getCanvasWidth(){
        return canvas.getWidth();
    }

    public double getCanvasHeight(){
        return canvas.getHeight();
    }

    public AnchorPane getPane(){
        return anchorPane;
    }

    public GraphicsContext getGraphicsContext(){
        return gc;
    }

}