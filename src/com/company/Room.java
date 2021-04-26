package com.company;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Room implements Comparable<Room> {

    private double volume;
    private Person tenant;
    private LocalDate startDate;
    private LocalDate endDate;
    private static ArrayList<Room> allRooms = new ArrayList<>();


    public Room(double volume, Person tenant, LocalDate startDate, LocalDate endDate) {
        this.volume = volume;
        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
        allRooms.add(this);
    }

    public Room(double length, double width, double height, Person tenant, LocalDate startDate, LocalDate endDate) {
        this.volume = Volume.calculateVolume(length, width, height);
        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
        allRooms.add(this);
    }

    public Room(double volume) {
        this.volume = volume;
        allRooms.add(this);
    }

    public Room(double length, double width, double height) {
        this.volume = Volume.calculateVolume(length, width, height);
        allRooms.add(this);
    }

    public double getVolume() {
        return volume;
    }

    public Person getTenant() {
        return tenant;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public static ArrayList<Room> getAllRooms() {
        return allRooms;
    }

    public static ArrayList<Apartment> getAllApartments() {
        ArrayList<Apartment> ap = new ArrayList<>();
        for (Room r : allRooms) {
            if (r instanceof Apartment) {
                ap.add((Apartment) r);
            }
        }
        return ap;
    }

    public static ArrayList<ParkingSpace> getAllParkings() {
        ArrayList<ParkingSpace> ps = new ArrayList<>();
        for (Room r : allRooms) {
            if (r instanceof ParkingSpace) {
                ps.add((ParkingSpace) r);
            }
        }
        return ps;
    }

    public void setTenant(Person tenant) {
        this.tenant = tenant;
    }

    public void deleteTenant() {
        this.tenant = null;
        this.startDate = null;
        this.endDate = null;
    }

    public boolean checkIfAvailable() {
        return this.tenant == null;
    }

    public void checkRent() throws IOException {
        if (Main.currentDate.isAfter(this.endDate)) {
            StringBuilder str = new StringBuilder();
            str.append("Rent expired: " + this.getEndDate() + "; checking day: " + Main.currentDate);
            this.getTenant().addLetter(this, str);

            Period period = Period.between(this.getEndDate(), Main.currentDate);
            if (period.getDays() >= 30) {
                if (this instanceof Apartment) {
                    System.err.println("Rent expired more than 30 days ago " + this.tenant.getName()
                            + " no longer a tenant of apartment ID: " + ((Apartment) this).getIdNum());
                    ((Apartment) this).evictPeople();
                } else if (this instanceof ParkingSpace) {
                    System.err.println("Rent expired more than 30 days ago " + this.tenant.getName()
                            + " no longer a tenant of parking space ID: " + ((ParkingSpace) this).getIdNum());
                    ((ParkingSpace) this).utilizeAll();
                }
                deleteTenant();
            }
        }
    }

    public void startRent(Person person, LocalDate start, LocalDate end) {
        try {
            if (checkIfAvailable()) {
                if (person.getLetters().size() < 3) {
                    if (person.getRentedRooms().size() < 5) {
                        if (this instanceof Apartment) {
                            this.tenant = person;
                            this.startDate = start;
                            this.endDate = end;
                            person.setRentedRooms(this);
                            System.out.println("Rent has successfully started" + this + " by" + person + " from: " + start + " to: " + end);
                        } else {
                            if (person.getRentedRooms().size() > 0) {
                                System.out.print("Rented apartments:");
                                System.out.println(Arrays.toString(person.getRentedApartments().
                                        toArray()).replaceAll("[\\[\\]]", ""));
                                System.out.println("Enter apartment ID to add parking space:");
                                Scanner scanner = new Scanner(System.in);
                                int id = scanner.nextInt();
                                person.getRentedApartmentById(id).addParkingSpace((ParkingSpace) this, start, end);
                                this.tenant = person;
                                this.startDate = start;
                                this.endDate = end;
                                person.setRentedRooms(this);
                                System.out.println("Rent has successfully started" + this + " by" + person + " from: " + start + " to: " + end);
                            } else {
                                System.out.println(person + " has no rented apartments");
                            }
                        }
                    } else {
                        System.out.println("Person already has " + person.getRentedRooms().size() + " rooms rented");
                    }
                } else {
                    throw new ProblematicTenantException(person);
                }
            } else {
                System.out.println("Room is being rented");
            }
        } catch (ProblematicTenantException e) {
            e.printStackTrace();
        }
    }

    public void renewRent(LocalDate endDate) {
        Period period = Period.between(this.getEndDate(), Main.currentDate);
        if (period.getDays() <= 30 || period.getMonths() <= 1) {
            System.out.println("Rent was renewed, last end:" + this.endDate + ", current end:" + endDate);
            this.endDate = endDate;
            this.tenant.removeLetter(this);
        } else {
            System.out.println("Rent can't be renewed, it's been " + period.getDays() + " days after expiring");
        }
    }

    public void cancelRent() {
        Period period = Period.between(this.getEndDate(), Main.currentDate);
        if (period.getDays() <= 30 && period.getMonths() < 1) {
            this.tenant.removeLetter(this);
            deleteTenant();
            System.out.println("Rent rent is canceled");
        } else {
            deleteTenant();
            System.out.println("Rent rent is canceled, but tenant letter remains");
        }
    }

    @Override
    public int compareTo(Room room) {
        return (int) (getVolume() - room.getVolume());
    }
}
