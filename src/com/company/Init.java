package com.company;

import java.time.LocalDate;
import java.util.Arrays;

public class Init {

    public static HousingEstate housingEstate;

    public static void create() throws TooManyThingsException {
        Person t1 = new Person("Liza", "Babior", "Snov");
        Person t2 = new Person("Karyna", "Bartashevich", "Minsk");
        Person t3 = new Person("Oliver", "Smith", "Warsaw");
        Person t4 = new Person("Jack", "Jones", "Chicago");
        Person t5 = new Person("Harry", "Williams", "London");

        Person r1 = new Person("Victor", "Trippel", "Innsbruck");
        Person r2 = new Person("Anastasia", "Key", "Ljubljana");
        Person r3 = new Person("Whitney", "Goodman", "Manchester");
        Person r4 = new Person("Tiernan", "Stevenson", "Berlin");
        Person r5 = new Person("Zaynab", "Duggan", "Istanbul");

        Vehicle v1 = new Vehicle("Audi A6", 15);
        Vehicle v2 = new Vehicle("Audi A5", 16);
        Vehicle v3 = new Vehicle("Range Rover Sport", 17);
        Vehicle v4 = new Vehicle("Mercedes CLA250", 15);
        Vehicle v5 = new Vehicle("BMW X5", 16);

        Item i1 = new Item("Cardboard", 5);
        Item i2 = new Item("Piano", 10);
        Item i3 = new Item("Instruments", 3);
        Item i4 = new Item("Wheels", 6);
        Item i5 = new Item("Toys", 2);

        OffRoadCar ofc = new OffRoadCar("Land Rover Defender", 10, "Four cylinders");
        CityCar cc = new CityCar("Audi A6", 8, 5);
        Boat b = new Boat("Caparral", 13, 8);
        Motorcycle m = new Motorcycle("Kawasaki", 6, 300);
        Amphibious a = new Amphibious("Sherp N", 20, 4);

        ParkingSpace ps1 = new ParkingSpace(50, t1, LocalDate.parse("2021-01-10"), LocalDate.parse("2021-06-05"));
        t1.setRentedRooms(ps1);
        ParkingSpace ps2 = new ParkingSpace(60, t2, LocalDate.parse("2021-01-03"), LocalDate.parse("2021-03-04"));
        t2.setRentedRooms(ps2);
        ParkingSpace ps3 = new ParkingSpace(10, 7, 3);
        ParkingSpace ps4 = new ParkingSpace(10, 10, 3);
        ParkingSpace ps5 = new ParkingSpace(30);

        ps1.insertContent(t1, ofc);
        ps1.insertContent(t1, cc);
        ps2.insertContent(t2, b);
        ps2.insertContent(t2, m);
        ps2.insertContent(t2, a);

        Apartment ap1 = new Apartment(50, 500, t1, LocalDate.parse("2021-01-10"), LocalDate.parse("2021-06-05"), ps1);
        t1.setRentedRooms(ap1);
        Apartment ap2 = new Apartment(100, 1000, t2, LocalDate.parse("2021-01-03"), LocalDate.parse("2021-03-04"), ps2);
        t2.setRentedRooms(ap2);
        Apartment ap3 = new Apartment(75, 800);
        ap3.startRent(t3, LocalDate.parse("2021-01-02"), LocalDate.parse("2021-03-03"));
        Apartment ap4 = new Apartment(200, 2500);
        ap4.startRent(t3, LocalDate.parse("2021-02-15"), LocalDate.parse("2021-04-15"));
        Apartment ap5 = new Apartment(60, 750);

        ap1.checkIn(t1, Arrays.asList(r1, r2, r3));

        StringBuilder str = new StringBuilder("Apartment");

        TenantLetter l1 = new TenantLetter(ap1, str);
        TenantLetter l2 = new TenantLetter(ap1, str);
        TenantLetter l3 = new TenantLetter(ap1, str);

        t1.setLetters(l1);
        t1.setLetters(l2);
        t1.setLetters(l3);

        housingEstate = new HousingEstate("Sun");


    }

}
