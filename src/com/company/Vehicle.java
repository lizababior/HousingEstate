package com.company;

public class Vehicle extends Content {

    public Vehicle(String name, double volume) {
        super(name, volume);
        Content.setVehicles(this);
    }

    public Vehicle(String name, double length, double width, double height) {
        super(name, length, width, height);
        Content.setVehicles(this);
    }

    @Override
    public String toString() {
        return "\nVehicle " + this.getName() + " (volume:" + this.getVolume() + ")";
    }
}
