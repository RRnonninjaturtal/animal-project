package com.example.animalproject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Rocks extends Actor{
    public Rocks(String name,  World myWorld, int row, int col)  throws FileNotFoundException {
        super(name, myWorld, row, col);
        setImage(new Image(new FileInputStream("C:\\Users\\rjrivera24\\IdeaProjects\\animal project\\src\\main\\resources\\com\\example\\animalproject\\tinywow_rock_34219105.png")));
    }


    @Override
    public void act(){
        //empty
    }
}
