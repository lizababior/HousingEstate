package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Person {

    private String name;
    private String surname;
    private int pesel;
    private String address;
    private ArrayList<TenantLetter> letters;
    private ArrayList<Room> rentedRooms;
    private static ArrayList<Person> allPeople = new ArrayList<>();

    public Person(String name, String surname, int pesel, String address) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.address = address;
        letters = new ArrayList<TenantLetter>();
        rentedRooms = new ArrayList<>();
        allPeople.add(this);
    }

    public Person(String name, String surname, String address) {
        this.name = name;
        this.surname = surname;
        Random r = new Random();
        this.pesel = 10000 + r.nextInt(90000);
        this.address = address;
        letters = new ArrayList<TenantLetter>();
        rentedRooms = new ArrayList<>();
        allPeople.add(this);
    }

    public String getName() {
        return name + " " + surname;
    }

    public int getPesel() {
        return pesel;
    }

    public ArrayList<TenantLetter> getLetters() {
        return letters;
    }

    public ArrayList<Room> getRentedRooms() {
        return rentedRooms;
    }

    public ArrayList<Apartment> getRentedApartments() {
        ArrayList<Apartment> apartments = new ArrayList<>();
        for (Room room : this.getRentedRooms()) {
            if (room instanceof Apartment) {
                apartments.add((Apartment) room);
            }
        }
        return apartments;
    }

    public Apartment getRentedApartmentById(int number) {
        for (Room room : this.getRentedRooms()) {
            if (room instanceof Apartment) {
                if (((Apartment) room).getIdNum() == number) {
                    return (Apartment) room;
                }
            }
        }
        return null;
    }

    public static ArrayList<Person> getAllPeople() {
        return allPeople;
    }

    public void setRentedRooms(Room rentedRoom) {
        this.rentedRooms.add(rentedRoom);
    }

    public void setLetters(TenantLetter letter) {
        this.letters.add(letter);
    }

    public static Person getByPesel(int number) {
        for (Person person : allPeople) {
            if (person.getPesel() == number) {
                return person;
            }
        }
        System.out.println("There are no person with pesel " + number);
        return null;
    }

    public void addLetter(Room room, StringBuilder str) {
        this.letters.add(new TenantLetter(room, str));
    }

    public void removeLetter(Room room) {
        for (TenantLetter l : letters) {
            if (l.getRoom().equals(room)) {
                this.letters.remove(l);
                break;
            }
        }
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(Main.FILENAME, false);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Person p : allPeople) {
            bw.write(p.toString() + " rooms:");
            Collections.sort(p.getRentedRooms());
            for (Room r : p.getRentedRooms()) {
                bw.write(r.toString() + " content:");
                if (r instanceof Apartment && ((Apartment) r).getPeople().size() != 0) {
                    bw.write(Arrays.toString(((Apartment) r).getPeople().toArray()).replaceAll("[\\[\\]]", ""));
                } else if (r instanceof ParkingSpace && ((ParkingSpace) r).getContent().size() != 0) {
                    Collections.sort(((ParkingSpace) r).getContent());
                    bw.write(Arrays.toString(((ParkingSpace) r).getContent().toArray()).replaceAll("[\\[\\]]", ""));
                }
                bw.newLine();
            }
            if (p.getLetters().size() != 0) {
                bw.write("\nLetters:");
                bw.write(Arrays.toString(p.getLetters().toArray()).replaceAll("[\\[\\]]", ""));
            }
            bw.newLine();
            bw.newLine();
            bw.newLine();
        }
        bw.close();
    }

    @Override
    public String toString() {
        return "\n" + name + " " + surname + " (" + pesel + ")";
    }


}
