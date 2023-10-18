package com.example.animalproject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.NetworkChannel;

public class BoxBug extends Actor {

    public BoxBug(String name,  World myWorld, int row, int col)  throws FileNotFoundException {
        super(name, myWorld, row, col);
        setImage(new Image(new FileInputStream("C:\\Users\\rjrivera24\\IdeaProjects\\animal project\\src\\main\\resources\\com\\example\\animalproject\\boxbugFixed.png")));
        setDir(Location.EAST);
    }
    @Override
    public void act(){
        Location newLoc = chooseMove(getPossibleMoves());

        if(getMyWorld().isValid(newLoc) && getMyWorld().getActor(newLoc) == null){
            getMyWorld().clearLoc(getMyLoc());
            setMyLoc(newLoc);
            getMyWorld().addActor(this);
        }
        else { //location is either invalid or already occupied
            setDir((getDir()+2) % 8); //turn 90 degrees clockwise
            getMyWorld().clearLoc(getMyLoc());
            setMyLoc(newLoc);
        }
    }

    @Override
    public String toString() {
        return "BoxBug " + getName();
    }
}
