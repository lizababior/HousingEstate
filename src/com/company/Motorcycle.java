package com.company;

public class Motorcycle extends Vehicle{
    private double engineVolume;

    public Motorcycle(String name, double volume, double engineVolume) {
        super(name, volume);
        this.engineVolume = engineVolume;
    }

    public Motorcycle(String name, double length, double width, double height, double engineVolume) {
        super(name, length, width, height);
        this.engineVolume = engineVolume;
    }

    public double isSideSeat() {
        return engineVolume;
    }

    @Override
    public String toString() {
        return "\nMotorcycle " + getName() + ", engine volume=" + engineVolume + " (volume:" + getVolume() + ")";
    }
}
