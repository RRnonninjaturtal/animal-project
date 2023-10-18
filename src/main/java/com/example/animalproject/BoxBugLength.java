package com.example.animalproject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoxBugLength extends BoxBug{


    public BoxBugLength(String name,  World myWorld, int row, int col, int boxBugLength)  throws FileNotFoundException {
        super(name, myWorld, row, col);
        setImage(new Image(new FileInputStream("C:\\Users\\rjrivera24\\IdeaProjects\\animal project\\src\\main\\resources\\com\\example\\animalproject\\boxbugFixed.png")));
        setDir(Location.EAST);
        this.boxBugLength = boxBugLength;
    }
    private int boxBugLength;
    private int counter = 0;

    @Override
    public void act(){
        Location nextLoc = getMyLoc().getLocInDirection(getDir());


        if (counter <= 5) {
            if (getMyWorld().isValid((nextLoc)) && getMyWorld().getActor(nextLoc) == null) {
                super.act();
                counter++;
            } else {
                counter =0 ;
                setDir((getDir() + 2) % 8);

            }

        }
        else {
            setDir((getDir() + 2) % 8);
            counter = 0;
        }

    }
}
