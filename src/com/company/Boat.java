package com.company;

public class Boat extends Vehicle{

    private int peopleCapacity;

    public Boat(String name, double volume, int peopleCapacity) {
        super(name, volume);
        this.peopleCapacity = peopleCapacity;
    }

    public Boat(String name, double length, double width, double height, int peopleCapacity) {
        super(name, length, width, height);
        this.peopleCapacity = peopleCapacity;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    @Override
    public String toString() {
        return "\nBoat " + getName() + ", people capacity=" + peopleCapacity + " (volume:" + getVolume() + ')';
    }
}
