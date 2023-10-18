package com.example.animalproject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class World {
    Actor[][] world;
    Image defaultTile;
    private ArrayList<Actor> actorList = new  ArrayList<>();



    public World (int numRows, int numCols){
        world = new Actor[numRows][numCols];

        try {
            loadTiles();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void step() {
        for (int row =0; row < world.length; row++){
            for (int col =0; col < world[row].length; col++){
                //grab the Actor
                Actor a = world[row][col];
                //if a is an actual actor and it has not alreadt acted
                if( a != null && !a.hasActed()){
                    a.act();
                    a.setHasActed(true);
                }//end if
            }//end inner for
        }//end outer for
        clearActedFlagsForAllActors();
    }//end step

    public void clearActedFlagsForAllActors(){
        for (int row =0; row < world.length; row++){
            for (int col =0; col < world[row].length; col++){
             //grab the Actor
                Actor a = world[row][col];
                //if a is an actual actor and it has not alreadt acted
               if( a != null){
                   a.setHasActed(false);
               }//end if
            }//end inn for
        }//end outer for
    }


    public ArrayList<Actor> getActorsOfType(String className) {
        ArrayList<Actor> actorsOfType = new ArrayList<>();
        for(Actor a: actorList) {
            String toString = a.getClass().toString();
            //contains does toString have className?
            if(toString.contains(className)) {
                actorsOfType.add(a);
            }
        }
        return actorsOfType;
    }



    public Actor getRandomActor(String className){//problem
        ArrayList<Actor> actors = getActorsOfType(className);
        if(actors.size() == 0){
            return null;
        }
        Actor actor = actors.get((int)(Math.random()*actors.size()));
        System.out.println(actor);
        return actor;
    }



    /*
    returns an ArrayList of Locations that contain other actors around a given location loc in four directions NORTH SOUTH EAST WEST
    */
    public ArrayList<Location> getFourNeighbors(Location loc) {
        ArrayList<Location> neighbors = new ArrayList<>();
        if(isValid(loc.getNorth()) && getActor(loc.getNorth()) == null)
            neighbors.add(loc.getNorth());
        if(isValid(loc.getSouth()) && getActor(loc.getSouth()) == null)
            neighbors.add(loc.getSouth());
        if(isValid(loc.getWest()) && getActor(loc.getWest()) == null)
            neighbors.add(loc.getWest());
        if(isValid(loc.getEast()) && getActor(loc.getEast()) == null)
            neighbors.add(loc.getEast());

        return neighbors;
    }
    public ArrayList<Location> getEightNeighbors(Location loc) {
        ArrayList<Location> neighbors = new ArrayList<>();
        for(int row = loc.getRow()-1; row <=loc.getRow()+1; row++){
            for(int col = loc.getCol()-1; row <=loc.getCol()+1; row++){
                Location tempLoc = new Location(row, col);
                if(isValid(tempLoc) && getActor(tempLoc) != null){
                    neighbors.add(tempLoc);
                }
            }
        }
        neighbors.remove(loc);

        return neighbors;
    }



    public Actor getActor(Location loc){
        return world[loc.getRow()][loc.getCol()];
    }

    public void loadTiles() throws FileNotFoundException {
        defaultTile = new Image(new FileInputStream("C:\\Users\\rjrivera24\\IdeaProjects\\animal project\\src\\main\\resources\\com\\example\\animalproject\\floorTile.jpg"));
    }

    public void draw(GraphicsContext gc){
        for(int row = 0; row < world.length; row++){
            for(int col = 0; col < world[row].length; col++){
                gc.drawImage(defaultTile, col*HelloApplication.TILE_SIZE, row*HelloApplication.TILE_SIZE);
                if (world[row][col] != null){
                    world[row][col].draw(gc);
                }
            }
        }
    }

    public boolean addActor(Actor a){
        if (world[a.getMyLoc().getRow()][a.getMyLoc().getCol()] != null) {
            return false;
        }
        world[a.getMyLoc().getRow()][a.getMyLoc().getCol()] = a;
        actorList.add(a);
        return true;
    }

    //returns new location in the given direction from the given loc
    //return null if there is no valid location in that direction

    public boolean isValid(Location loc) {//problem here
        return loc.getRow() > 0 && loc.getRow() < world.length && loc.getCol() > 0 && loc.getCol() < world[loc.getRow()].length-1;
    }

    public void clearLoc(Location loc) {
        if(isValid(loc)){
            Actor a = world[loc.getRow()][loc.getCol()];
            world[loc.getRow()][loc.getCol()] = null;
            //if we grabbed an actor, also remove from actors list
        }//end if
    }//end clear loc

}//end World