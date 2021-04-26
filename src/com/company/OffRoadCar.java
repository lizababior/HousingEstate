package com.company;

public class OffRoadCar extends Vehicle{

    private String engineType;

    public OffRoadCar(String name, double volume, String engineType) {
        super(name, volume);
        this.engineType = engineType;
    }

    public OffRoadCar(String name, double length, double width, double height, String engineType) {
        super(name, length, width, height);
        this.engineType = engineType;
    }

    public String getEngineType() {
        return engineType;
    }

    @Override
    public String toString() {
        return "\nOff-road car " + getName() + ", engine type=" + engineType + " (volume:" + getVolume() + ')';
    }
}
