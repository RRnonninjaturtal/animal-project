package com.example.animalproject;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Grasseater extends Actor{//finds grass, travel to it, eat it

    public Grasseater(String name,  World myWorld, int row, int col)  throws FileNotFoundException {
        super(name, myWorld, row, col);
        setImage(new Image(new FileInputStream("C:\\Users\\rjrivera24\\IdeaProjects\\animal project\\src\\main\\resources\\com\\example\\animalproject\\happyface.png")));
        setDir(Location.EAST);
    }
    @Override
    public void act(){
        Location newLoc = chooseMove(getPossibleMoves());


        getMyWorld().clearLoc(getMyLoc());
        setMyLoc(newLoc);
        getMyWorld().addActor(this);

        if(getMyWorld().getActor(newLoc) instanceof Scorpion){
            getMyWorld().clearLoc(getMyLoc());
            setMyLoc(newLoc);
            getMyWorld().clearLoc(newLoc);//removes grass
            getMyWorld().addActor(this);
        }

    }
    @Override
    protected Location chooseMove(ArrayList<Location> locs){
        //locs is a list of EMPTY locations around me
        ArrayList<Location> neighbors = getMyWorld().getFourNeighbors(getMyLoc());

        for(Location l: neighbors){
            Actor a = getMyWorld().getActor(l);
            if(a instanceof Grass){
                return l;//returns location containing grass
            }
        }
        return super.chooseMove(locs);
    }//end choose move


}
