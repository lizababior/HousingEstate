package com.company;

import java.util.ArrayList;

public class Content implements Comparable<Content> {
    private String name;
    private double volume;
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static ArrayList<Item> items = new ArrayList<>();

    public Content(String name, double volume) {
        this.name = name;
        this.volume = volume;
    }

    public Content(String name, double length, double width, double height) {
        this.name = name;
        this.volume = Volume.calculateVolume(length, width, height);
    }

    public String getName() {
        return name;
    }

    public double getVolume() {
        return volume;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public static void setItems(Item item) {
        Content.items.add(item);
    }

    public static void setVehicles(Vehicle vehicle) {
        Content.vehicles.add(vehicle);
    }

    public static Item getItemByName(String name) {
        for (Item i : getItems()) {
            if (i.getName().equals(name))
                return i;
        }
        System.out.println("No item with name: " + name);
        return null;
    }

    public static Vehicle getVehicleByName(String name) {
        for (Vehicle v : getVehicles()) {
            if (v.getName().equals(name))
                return v;
        }
        System.out.println("No vehicle with name: " + name);
        return null;
    }

    @Override
    public String toString() {
        return "\n" + name + " (volume:" + volume + ")";
    }

    @Override
    public int compareTo(Content content) {
        int k = (int) (content.getVolume() - this.getVolume());
        if (k != 0) return k;
        return this.name.compareToIgnoreCase(content.name);
    }
}
