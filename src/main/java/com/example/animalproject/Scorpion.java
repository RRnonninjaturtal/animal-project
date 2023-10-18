package com.example.animalproject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Scorpion extends BoxBug{
    public Scorpion(String name,  World myWorld, int row, int col)  throws FileNotFoundException {
        super(name, myWorld, row, col);
        setImage(new Image(new FileInputStream("C:\\Users\\rjrivera24\\IdeaProjects\\animal project\\src\\main\\resources\\com\\example\\animalproject\\tinywow_redscorp_34220956.jpg")));
        setDir(Location.NORTH);
    }


    private ArrayList<Actor> targets= getMyWorld().getActorsOfType("BoxBug");
    private Actor target = targets.get(targets.size()-1);
    private int targetsLeft = targets.size()-1;



    private int lifeSpan = 0;

    public void act(){
        Location newLoc = chooseMove(getPossibleMoves());

        System.out.println(newLoc);
        System.out.println("list of targets " + targets);
        System.out.println("current target" + target);//won't change target
        System.out.println();
        int targetsLeft = targets.size();
        System.out.println("target size " + targets.size());

        //moving

            if(getMyWorld().getActor(newLoc) != null){
                if(getMyWorld().getActor(newLoc).toString().contains("BoxBug")){//is being called when its not supposed to

                    getMyWorld().clearLoc(getMyLoc());
                    setMyLoc(newLoc);
                    getMyWorld().clearLoc(newLoc);//removes boxBug
                    getMyWorld().addActor(this);

                    System.out.println("I ate " + target);
                    System.out.println("i ate ");
                    target = targets.get(0);
                }
                else {
                    eat(newLoc);
                }
            }
            else {
                super.act();
            }


        if(lifeSpan == 50){
            getMyWorld().clearLoc(getMyLoc());
        }
    }
    public void eat(Location newLoc){
        getMyWorld().clearLoc(getMyLoc());
        setMyLoc(newLoc);
        getMyWorld().clearLoc(newLoc);//removes boxBug
        getMyWorld().addActor(this);
    }

    @Override
    protected Location chooseMove(ArrayList<Location> locs){
        //error is that scorpion is going out of bounds not going to valid location
        System.out.println("the direction of my " + target +" target is " + getMyLoc().getDirectionToLoc(target.getMyLoc(), true));
        return locs.get(getMyLoc().getDirectionToLoc(target.getMyLoc(), true));//assumes 8 locations when all 8 directions arent availible it makes an error
    }

    @Override
    protected ArrayList<Location> getPossibleMoves(){
        ArrayList<Location> locs = new ArrayList<>();

        Location above = getMyLoc().getNorth();//0
        Location topRight = getMyLoc().getNorthEast();//1
        Location right = getMyLoc().getEast();//2
        Location bottomRight = getMyLoc().getSouthEast();//3
        Location below = getMyLoc().getSouth();//4
        Location bottomLeft = getMyLoc().getSouthWest();//5
        Location left = getMyLoc().getWest();//6
        Location topLeft = getMyLoc().getNorthWest();//7


        if(getMyWorld().isValid(above) )
            locs.add(above);
        if(getMyWorld().isValid(topRight) )
            locs.add(topRight);
        if(getMyWorld().isValid(right))
            locs.add(right);
        if(getMyWorld().isValid(bottomRight))
            locs.add(bottomRight);
        if(getMyWorld().isValid(below))
            locs.add(below);
        if(getMyWorld().isValid(bottomLeft))
            locs.add(bottomLeft);
        if(getMyWorld().isValid(left))
            locs.add(left);
        if(getMyWorld().isValid(topLeft) )
            locs.add(topLeft);

        return locs;
    }

    public ArrayList<Actor> getTargets() {
        return targets;
    }
/*

       @Override
    protected ArrayList<Location> getPossibleMoves(){
        ArrayList<Location> locs = new ArrayList<>();

        Location above = getMyLoc().getNorth();//0
        Location topRight = getMyLoc().getNorthEast();//1
        Location right = getMyLoc().getEast();//2
        Location bottomRight = getMyLoc().getSouthEast();//3
        Location below = getMyLoc().getSouth();//4
        Location bottomLeft = getMyLoc().getSouthWest();//5
        Location left = getMyLoc().getWest();//6
        Location topLeft = getMyLoc().getNorthWest();//7


        if(getMyWorld().isValid(above) && getMyWorld().getActor(above) == null){
            locs.add(above);
        }

        if(getMyWorld().isValid(topRight) && getMyWorld().getActor(topRight) == null){
            locs.add(topRight);
        }
        else{
            locs.add(locs.get(0));
        }

        if(getMyWorld().isValid(right) && getMyWorld().getActor(right) == null){
            locs.add(right);
        }
        else{
            locs.add(locs.get(0));
        }

        if(getMyWorld().isValid(bottomRight) && getMyWorld().getActor(bottomRight) == null){
            locs.add(bottomRight);
        }
        else{
            locs.add(locs.get(0));
        }

        if(getMyWorld().isValid(below) && getMyWorld().getActor(below) == null){
            locs.add(below);
        }
        else{
            locs.add(locs.get(0));
        }

        if(getMyWorld().isValid(bottomLeft) && getMyWorld().getActor(bottomLeft) == null){
            locs.add(bottomLeft);
        }
        else{
            locs.add(locs.get(0));
        }

        if(getMyWorld().isValid(left) && getMyWorld().getActor(left) == null){
            locs.add(left);
        }
        else{
            locs.add(locs.get(0));
        }

        if(getMyWorld().isValid(topLeft) && getMyWorld().getActor(topLeft) == null){
            locs.add(topLeft);
        }
        else{
            locs.add(locs.get(0));
        }


        return locs;
    }

 */


}
