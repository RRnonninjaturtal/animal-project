package com.example.animalproject;

public class Location {
    public int getRow() {
        return row;
    }

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    private int row;
    private int col;
    public static final int NORTH=0, NORTHEAST=1,EAST=2,
            SOUTHEAST=3,SOUTH=4,SOUTHWEST=5,
            WEST=6,NORTHWEST=7;

    public Location getLocInDirection(int dir){//possible problem

        switch(dir){
            case NORTH:
                return getNorth();
            case SOUTH:
                return getSouth();
            case EAST:
                return getEast();
            case WEST:
                return getWest();
            case SOUTHEAST:
                return getSouthEast();
            case SOUTHWEST:
                return getSouthWest();
            case NORTHEAST:
                return getNorthWest();
            case NORTHWEST:
                return getNorthWest();
            default:
                return null;
        }
    }

    public boolean equals(Location other){
        return row == other.row && col == other.row;
    }


    public Location getNorth(){
        return new Location(row-1 , col);
    }

    public Location getSouth(){
        return new Location(row+1 , col);
    }
    public Location getEast(){
        return new Location(row , col+1);
    }
    public Location getWest(){
        return new Location(row , col-1);
    }
    public Location getSouthWest(){
        return new Location(row+1 , col-1);
    }
    public Location getNorthWest(){
        return new Location(row-1 , col-1);
    }
    public Location getSouthEast(){
        return new Location(row+1 , col+1);
    }
    public Location getNorthEast(){
        return new Location(row-1 , col+1);
    }


    /*
    eightway -> true method eutrns direction using 8 nautical
             -> false, method returns direction using 4 nautical (NORTH SOUTH EAST WEST)
     */
    public int getDirectionToLoc(Location dest, boolean eightway) {

        if (!eightway){
            return fourWay(dest);
        }

        else return getEightWay(dest);
    }

    private int fourWay(Location dest) {
        int rowDiff = row - dest.getRow(); // get the difference between rows
        int colDiff = col - dest.getCol(); // get the difference between col
        int x = NORTH;
        if (rowDiff != 0) {
            if (rowDiff > 0) {
                x = NORTH;
            } else {
                x = SOUTH;
            }
        }
        if (colDiff != 0) {
            if (colDiff > 0) {
                x = WEST;
            } else {
                x = EAST;
            }
        }

        return x;
        // FULLY WORKING 4 WAY FUNCTION
        // IF IT REACHES, IT AUTO LOOPS  return x;
    }


    private int getEightWay(Location dest) {
        int rowDiff = row - dest.getRow(); // get the difference between rows
        int colDiff = col - dest.getCol(); // get the difference between col
        if (rowDiff == 0) {
            if (colDiff > 0) {
                return WEST;
            }
            return EAST;
        }
        if (colDiff == 0) {
            if (rowDiff > 0) {
                return NORTH;
            } else {
                return SOUTH;
            }
        }

        if (rowDiff > 0) { // if it is NORTH
            if (colDiff > 0) { // if it is WEST
                return NORTHWEST;

            } else { // if not, it is EAST
                return NORTHEAST;

            }
            // if it is not set to NE or NW, set NORTH
        } else { // if IT IS SOUTH
            if (colDiff < 0) { // it is to the WEST
                return SOUTHEAST;
            } else { // if not, set EAST
                return SOUTHWEST;
            }
            // if not SET, to ANY, set SOUTH
        }
        // end outer if
        //FULLY WORKING 4 WAY FUNCTION // IF IT REACHES, IT AUTO LOOPS} // FINISHED 8-WAY
    }

    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }


}
