package com.company;

public class Amphibious extends Vehicle{

    private int numOfAxles;

    public Amphibious(String name, double volume, int numOfAxles) {
        super(name, volume);
        this.numOfAxles = numOfAxles;
    }

    public Amphibious(String name, double length, double width, double height, int numOfAxles) {
        super(name, length, width, height);
        this.numOfAxles = numOfAxles;
    }

    public int getNumOfAxles() {
        return numOfAxles;
    }

    @Override
    public String toString() {
        return "\nAmphibious " + getName() + ", number of axles=" + numOfAxles + " (volume:" + getVolume() + ")";
    }
}
