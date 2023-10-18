package com.example.animalproject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tiger extends BoxBug{
    public Tiger(String name,  World myWorld, int row, int col, int tigerLength)  throws FileNotFoundException {
        super(name, myWorld, row, col);
        setImage(new Image(new FileInputStream("C:\\Users\\rjrivera24\\IdeaProjects\\animal project\\src\\main\\resources\\com\\example\\animalproject\\tiger2.png")));
        setDir(Location.SOUTH);
        this.tigerLength = tigerLength;
    }
    private int tigerLength;
    private int counter = 0;
    private int lifeSpan = 0;

    @Override
    public void act(){
        Location nextLoc = getMyLoc().getLocInDirection(getDir());
        lifeSpan++;

        if (counter <= tigerLength) {
            if (getMyWorld().isValid((nextLoc)) && getMyWorld().getActor(nextLoc) == null) {
                super.act();
                counter++;
            } else {
                counter =0 ;
                setDir((getDir() + 4) % 8);

            }


        }
        else {
            setDir((getDir() + 4) % 8);
            counter = 0;
        }

        if(lifeSpan == 70){
            getMyWorld().clearLoc(getMyLoc());
        }



    }

}
