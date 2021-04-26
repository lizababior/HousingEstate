package com.company;

public class CityCar extends Vehicle{

    private int numOfDoors;

    public CityCar(String name, double volume, int numOfDoors) {
        super(name, volume);
        this.numOfDoors = numOfDoors;
    }

    public CityCar(String name, double length, double width, double height, int numOfDoors) {
        super(name, length, width, height);
        this.numOfDoors = numOfDoors;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }

    @Override
    public String toString() {
        return "\nCity car " + getName() + ", number of doors=" + numOfDoors + " (volume:" + getVolume() + ')';
    }
}
