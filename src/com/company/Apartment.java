package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Apartment extends Room {
    private int idNum;
    private double rentalFee;
    private ArrayList<Person> people;
    private ParkingSpace parkingSpace;
    private static int counter = 1;
    //private static ArrayList<Apartment> allApartments = new ArrayList<>();

    public Apartment(double volume, double rentalFee, Person tenant, LocalDate startDate, LocalDate endDate, ParkingSpace parkingSpace) {
        super(volume, tenant, startDate, endDate);
        this.rentalFee = rentalFee;
        this.parkingSpace = parkingSpace;
        this.people = new ArrayList<>();
        this.idNum = counter++;
        //this.allApartments.add(this);
    }

    public Apartment(double length, double width, double height, Person tenant, LocalDate startDate, LocalDate endDate, double rentalFee, ParkingSpace parkingSpace) {
        super(length, width, height, tenant, startDate, endDate);
        this.rentalFee = rentalFee;
        this.parkingSpace = parkingSpace;
        this.people = new ArrayList<>();
        this.idNum = counter++;
        //this.allApartments.add(this);
    }

    public Apartment(double volume, double rentalFee) {
        super(volume);
        this.rentalFee = rentalFee;
        this.people = new ArrayList<>();
        this.idNum = counter++;
        //this.allApartments.add(this);
    }

    public Apartment(double length, double width, double height, double rentalFee) {
        super(length, width, height);
        this.rentalFee = rentalFee;
        this.people = new ArrayList<>();
        this.idNum = counter++;
        //this.allApartments.add(this);
    }

    public int getIdNum() {
        return idNum;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void checkIn(Person tenant, Person person) {
        if (this.getTenant().equals(tenant)) {
            people.add(person);
            System.out.println(person + " was checked in" + this);
        } else {
            System.out.println("Tenant of this apartment is " + this.getTenant());
            System.out.println("Only tenant allowed to check in people");
        }
    }

    public void checkIn(Person tenant, List<Person> people) {
        if (this.getTenant().equals(tenant)) {
            this.people.addAll(people);
            System.out.println(people + " were checked in" + this);
        } else {
            System.out.println("Tenant of this apartment is " + this.getTenant());
            System.out.println("Only tenant allowed to check in people");
        }
    }

    public void checkOut(Person tenant, Person person) {
        if (this.getTenant().equals(tenant)) {
            people.remove(person);
        } else {
            System.out.println("Tenant of this apartment is " + this.getTenant());
            System.out.println("Only tenant allowed to check out people");
        }
    }

    public void evictPeople() {
        this.people.clear();
    }

    public void addParkingSpace(ParkingSpace parkingSpace, LocalDate startDate, LocalDate endDate) {
        if (this.parkingSpace == null) {
            this.parkingSpace = parkingSpace;
        } else {
            System.out.println("Apartment already has parking space");
        }
    }

    @Override
    public String toString() {
        return "\nApartment " + idNum + " (volume:" + this.getVolume() + ")";
    }
}
