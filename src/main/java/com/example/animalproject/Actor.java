package com.example.animalproject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class Actor {
    private String name;
    private Image image;
    private Location myLoc;
    private World myWorld;
    private boolean hasActed;
    private int dir;//default is 0 -> North(direction

    int count = 0;

    public Actor(String name,  World myWorld)  throws FileNotFoundException{
        this.name = name;
        this.myWorld = myWorld;
        this.image = new Image(new FileInputStream("C:\\Users\\rjrivera24\\IdeaProjects\\animal project\\src\\main\\resources\\com\\example\\animalproject\\happyface.png"));
        myLoc = new Location(0,0);

    }



    protected ArrayList<Location> getPossibleMoves(){
        ArrayList<Location> locs = new ArrayList<>();

        Location above = myLoc.getNorth();
        Location below = myLoc.getSouth();
        Location right = myLoc.getEast();
        Location left = myLoc.getWest();

        if(myWorld.isValid(above) && myWorld.getActor(above) == null)
            locs.add(above);
        if(myWorld.isValid(below) && myWorld.getActor(below) == null)
            locs.add(below);
        if(myWorld.isValid(right) && myWorld.getActor(right) == null)
            locs.add(right);
        if(myWorld.isValid(left) && myWorld.getActor(left) == null)
            locs.add(left);
        return locs;
    }

    protected Location chooseMove(ArrayList<Location> locs){
       int randomIndex = (int)(Math.random()* locs.size());
       return locs.get(randomIndex);
    }





    public Actor(String name,  World myWorld, int row, int col)  throws FileNotFoundException{
        this.name = name;
        this.myWorld = myWorld;
        this.image = new Image(new FileInputStream("C:\\Users\\rjrivera24\\IdeaProjects\\animal project\\src\\main\\resources\\com\\example\\animalproject\\happyface.png"));
        myLoc = new Location(row,col);

    }

    public Actor(String name,  World myWorld, int row, int col, Image image)  throws FileNotFoundException{
        this.name = name;
        this.myWorld = myWorld;
        this.image = image;
        myLoc = new Location(row,col);

    }

    //the act method will do EXACTLY 1 action
    public void act(){
        Location newLoc = chooseMove(getPossibleMoves());

        myWorld.clearLoc(myLoc);
        myLoc = newLoc;
        myWorld.addActor(this);
    }

    public void draw(GraphicsContext gc){
        gc.save();
        Rotate r = new Rotate(dir*45,
                myLoc.getCol()*HelloApplication.TILE_SIZE + HelloApplication.TILE_SIZE/2,
                myLoc.getRow()*HelloApplication.TILE_SIZE+ HelloApplication.TILE_SIZE/2);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

        gc.drawImage(image, myLoc.getCol()*HelloApplication.TILE_SIZE,
                myLoc.getRow()*HelloApplication.TILE_SIZE);
        gc.restore();
    }


    public boolean isHasActed() {
        return hasActed;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public Actor(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                '}';
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Location getMyLoc() {
        return myLoc;
    }

    public void setMyLoc(Location myLoc) {
        this.myLoc = myLoc;
    }

    public World getMyWorld() {
        return myWorld;
    }

    public void setMyWorld(World myWorld) {
        this.myWorld = myWorld;
    }

    public boolean hasActed() {
        return hasActed;
    }

    public void setHasActed(boolean hasActed) {
        this.hasActed = hasActed;
    }
}
