package com.company;

import java.util.*;

public class HousingEstate {
    private String name;
    private ArrayList<Room> allRooms;
    private ArrayList<Apartment> apartments;
    private ArrayList<ParkingSpace> parkingSpaces;

    public HousingEstate(String name) {
        this.name = name;
        this.apartments = Room.getAllApartments();
        this.parkingSpaces = Room.getAllParkings();
        //this.allRooms.addAll(Room.getAllApartments());
        //this.allRooms.addAll(Room.getAllParkings());
    }

    public String getName() {
        return name;
    }

    public void setRooms(Room room) {
        if (room instanceof Apartment) {
            this.apartments.add((Apartment) room);
        }
        else {
            this.parkingSpaces.add((ParkingSpace) room);
        }
    }

    public ArrayList<Apartment> getApartments() {
        return apartments;
    }

    public Apartment getApartmentById(int number) {
        for (Apartment apartment : this.getApartments()) {
            if (apartment.getIdNum() == number) {
                return apartment;
            }
        }
        return null;
    }

    public ParkingSpace getParkingById(int number) {
        for (ParkingSpace parkingSpace : this.getParkingSpaces()) {
            if (parkingSpace.getIdNum() == number) {
                return parkingSpace;
            }
        }
        return null;
    }

    public ArrayList<ParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public ArrayList<Apartment> getRentedApartments() {
        ArrayList<Apartment> rented = new ArrayList<>();
        for (Apartment a : apartments) {
            if (a.getTenant() != null) {
                rented.add(a);
            }
        }
        return rented;
    }

    public ArrayList<ParkingSpace> getRentedParkings() {
        ArrayList<ParkingSpace> rented = new ArrayList<>();
        for (ParkingSpace p : parkingSpaces) {
            if (p.getTenant() != null) {
                rented.add(p);
            }
        }
        return rented;
    }

    public ArrayList<Room> getRentedRooms() {
        ArrayList<Room> rentedRooms = new ArrayList<>();
        rentedRooms.addAll(getRentedApartments());
        rentedRooms.addAll(getRentedParkings());
        return rentedRooms;
    }

    public ArrayList<Apartment> getFreeApartments() {
        ArrayList<Apartment> list = new ArrayList<>();
        for (Apartment a : apartments) {
            if (a.getTenant() == null) {
                list.add(a);
            }
        }
        return list;
    }

    public ArrayList<ParkingSpace> getFreeParkings() {
        ArrayList<ParkingSpace> list = new ArrayList<>();
        for (ParkingSpace p : parkingSpaces) {
            if (p.getTenant() == null) {
                list.add(p);
            }
        }
        return list;
    }

    public Apartment getFreeApartmentById(int number) {
        for (Apartment apartment : this.getFreeApartments()) {
            if (apartment.getIdNum() == number) {
                return apartment;
            }
        }
        return null;
    }

    public ParkingSpace getFreeParkingById(int number) {
        for (ParkingSpace parking : this.getFreeParkings()) {
            if (parking.getIdNum() == number) {
                return parking;
            }
        }
        return null;
    }

    public Set<Person> getTenants() {
        Set<Person> tenants = new HashSet<Person>();
        for (Apartment apartment : this.getApartments()) {
            if (apartment.getTenant() != null) {
                tenants.add(apartment.getTenant());
            }
        }
        return tenants;
    }


    @Override
    public String toString() {
        return "HousingEstate{" + name + '}';
    }
}
