package com.company;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    public static void printMenuOptions() {
        System.out.println("\nMAIN MENU:\nChoose the operation you want to perform:");
        System.out.println("1: Print all rooms of housing estate");
        System.out.println("2: Print rented rooms");
        System.out.println("3: Print available rooms");
        System.out.println("4: Print all people");
        System.out.println("5: Rent room");
        System.out.println("6: Cancel room rent");
        System.out.println("7: Renew room rent");
        System.out.println("8: Print residents of apartments");
        System.out.println("9: Print content of parking spaces");
        System.out.println("10: Check in person to apartment");
        System.out.println("11: Check out person from apartment");
        System.out.println("12: Evict people from apartment");
        System.out.println("13: Insert content to parking space"); //not yet
        System.out.println("14: Take out content from parking space"); //not yet
        System.out.println("15: Utilize all from parking space");
        System.out.println("16: Print tenant letters of all people");
        System.out.println("17: Save information about all people to file");
        System.out.println("18: Add new object");
        System.out.println("19: Exit");
    }

    public static void printAllRooms() {
        System.out.print("Apartments:");
        printList(Init.housingEstate.getApartments());
        System.out.print("Parking spaces:");
        printList(Init.housingEstate.getParkingSpaces());
    }

    public static void printRentedRooms() {
        System.out.print("Rented Apartments:");
        printList(Init.housingEstate.getRentedApartments());
        System.out.print("Rented Parking spaces:");
        printList(Init.housingEstate.getRentedParkings());
    }

    public static void printFreeRooms() {
        System.out.print("Available Apartments:");
        printList(Init.housingEstate.getFreeApartments());
        System.out.print("Available Parking spaces:");
        printList(Init.housingEstate.getFreeParkings());
    }

    public static void printAllPeople() {
        System.out.print("People:");
        System.out.println(Arrays.toString(Person.getAllPeople().
                toArray()).replaceAll("[\\[\\]]", ""));
    }

    public static void rentRoom() {
        System.out.println("Choose room type to rent:");
        System.out.println("1: Apartment");
        System.out.println("2: Parking space");
        int command, id;
        ArrayList<LocalDate> dates;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.print("Choose apartment to rent");
                    printList(Init.housingEstate.getFreeApartments());
                    System.out.println("Enter Apartment ID");
                    id = scanner.nextInt();
                    dates = inputRentDates();
                    printAllPeople();
                    System.out.println("Choose tenant");
                    Init.housingEstate.getFreeApartmentById(id).startRent(inputPerson(), dates.get(0), dates.get(1));
                    break;
                case 2:
                    printAllPeople();
                    System.out.println("Choose tenant");
                    Person p = inputPerson();
                    if (p.getRentedApartments().size() > 0) {
                        System.out.println("Choose parking space to rent");
                        printList(Init.housingEstate.getFreeParkings());
                        System.out.println("Enter Parking space ID");
                        id = scanner.nextInt();
                        //dates = inputRentDates();
                        Init.housingEstate.getFreeParkingById(id).startRent(p, LocalDate.parse("2021-04-18"), LocalDate.parse("2021-04-19"));/*dates.get(0), dates.get(1));*/
                    } else {
                        System.out.println("Person has not rented rooms");
                    }
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void cancelRoomRent() {
        System.out.print("Rented rooms:");
        printList(Init.housingEstate.getRentedRooms());
        System.out.println("Choose room type to cancel rent:");
        System.out.println("1: Apartment\n2: Parking space");
        int command, id;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter Apartment ID");
                    id = scanner.nextInt();
                    Init.housingEstate.getApartmentById(id).cancelRent();
                    break;
                case 2:
                    System.out.println("Enter Parking space ID");
                    id = scanner.nextInt();
                    Init.housingEstate.getParkingById(id).cancelRent();
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void renewRoomRent() {
        System.out.print("Rented rooms:");
        printList(Init.housingEstate.getRentedRooms());
        System.out.println("Choose room type to renew rent:");
        System.out.println("1: Apartment\n2: Parking space");
        int command, id;
        String date;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter Apartment ID");
                    id = scanner.nextInt();
                    System.out.println("Enter end date in format yyyy-mm-dd:");
                    date = scanner.next();
                    Init.housingEstate.getApartmentById(id).renewRent(LocalDate.parse(date));
                    break;
                case 2:
                    System.out.println("Enter Parking space ID");
                    id = scanner.nextInt();
                    System.out.println("Enter end date in format yyyy-mm-dd:");
                    date = scanner.next();
                    Init.housingEstate.getParkingById(id).renewRent(LocalDate.parse(date));
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void printResidents() {
        Init.housingEstate.getRentedApartments().forEach((r) -> {
            System.out.print(r);
            printList(r.getPeople());
        });
    }

    public static void printContent() {
        Init.housingEstate.getRentedParkings().forEach((p) -> {
            System.out.print(p + ":");
            printList(p.getContent());
        });
    }

    public static void checkIn() {
        printTenants();
        Person p = inputPerson();
        printList(p.getRentedApartments());
        System.out.println("Enter apartment ID");
        int id = scanner.nextInt();
        printAllPeople();
        p.getRentedApartmentById(id).checkIn(p, inputPerson());
    }

    public static void checkOut() {
        printTenants();
        Person p = inputPerson();
        printList(p.getRentedApartments());
        System.out.println("Enter apartment ID");
        int id = scanner.nextInt();
        System.out.print("Residents:");
        printList(p.getRentedApartmentById(id).getPeople());
        p.getRentedApartmentById(id).checkOut(p, inputPerson());
    }

    public static void evictPeople() {
        printTenants();
        Person p = inputPerson();
        printList(p.getRentedApartments());
        System.out.println("Enter apartment ID");
        int id = scanner.nextInt();
        p.getRentedApartmentById(id).evictPeople();
        System.out.println("All people from" + p.getRentedApartmentById(id) + " were evicted");
    }

    public static void insertItem() throws TooManyThingsException {
        printTenants();
        Person p = inputPerson();
        System.out.print("Rented parking:");
        p.getRentedApartments().forEach((a) -> {
            System.out.println(a.getParkingSpace().toString());
        });
        String content;
        int command, id;
        System.out.println("Enter Parking space ID");
        id = scanner.nextInt();
        System.out.println("What you want to insert?");
        System.out.println("1: Vehicle\n2: Item");
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.print("Enter vehicle name:");
                    printList(Content.getVehicles());
                    scanner.nextLine();
                    content = scanner.nextLine(); //doesnt work
                    Vehicle v = Content.getVehicleByName(content); //doesnt work
                    Init.housingEstate.getParkingById(id).insertContent(p, v);
                    break;
                case 2:
                    System.out.print("Enter item's name:");
                    printList(Content.getItems());
                    scanner.nextLine();
                    content = scanner.nextLine();
                    Init.housingEstate.getParkingById(id).insertContent(p, Content.getItemByName(content));
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void takeOutItem() {
        printTenants();
        Person p = inputPerson();
        System.out.print("Rented parking:");
        p.getRentedApartments().forEach((a) -> {
            System.out.println(a.getParkingSpace().toString());
        });
        String content;
        int command, id;
        System.out.println("Enter Parking space ID");
        id = scanner.nextInt();
        System.out.print("Parking content:");
        printList(Init.housingEstate.getParkingById(id).getContent());
        System.out.println("What you want to take out?");
        System.out.println("1: Vehicle\n2: Item");
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter vehicle name:");
                    scanner.nextLine();
                    content = scanner.nextLine();
                    Content.getVehicleByName(content);
                    Init.housingEstate.getParkingById(id).takeOutContent(p, Content.getVehicleByName(content));
                    break;
                case 2:
                    System.out.println("Enter item's name:");
                    scanner.nextLine();
                    content = scanner.nextLine();
                    Init.housingEstate.getParkingById(id).takeOutContent(p, Content.getItemByName(content));
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void utilize() {
        printTenants();
        Person p = inputPerson();
        System.out.print("Rented parking:");
        p.getRentedApartments().forEach((a) -> {
            System.out.println(a.getParkingSpace().toString());
        });
        System.out.println("Enter parking ID");
        int id = scanner.nextInt();
        Init.housingEstate.getParkingById(id).utilizeAll();
        System.out.println("All items from" + Init.housingEstate.getParkingById(id) + " were utilized");
    }

    public static void printTenantLetters() {
        for (Person p : Person.getAllPeople()) {
            System.out.println(p);
            printList(p.getLetters());
        }
    }

    public static void writeToFile() throws IOException {
        Person.writeToFile();
        System.out.println("Information about about all people was saved to file");
    }

    public static void createObject() {
        System.out.println("Which object you want to create?");
        System.out.println("1: Person\n2: Apartment\n3: Parking space\n4: Vehicle\n5: Item");
        int command;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    createPerson();
                    break;
                case 2:
                    createRoom(true);
                    break;
                case 3:
                    createRoom(false);
                    break;
                case 4:
                    createContent(true);
                    break;
                case 5:
                    createContent(false);
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2 && command != 3 && command != 4 && command != 5);
    }

    public static void createPerson() {
        String name, surname, adress;
        int pesel;
        scanner.nextLine();
        System.out.println("Enter name:");
        name = scanner.nextLine();
        System.out.println("Enter surname:");
        surname = scanner.nextLine();
        System.out.println("Enter pesel number:");
        pesel = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter adress:");
        adress = scanner.nextLine();
        Person p = new Person(name, surname, pesel, adress);
    }

    public static void createRoom(boolean f) {
        double volume, rentaFee, length, width, height;
        scanner.nextLine();
        System.out.println("Choose volume input type:");
        System.out.println("1: Cubic meters\n2: Length, width, height");
        int command;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter volume:");
                    volume = scanner.nextDouble();
                    if (f) {
                        System.out.println("Enter rental fee:");
                        rentaFee = scanner.nextDouble();
                        Init.housingEstate.setRooms(new Apartment(volume, rentaFee));
                    } else {
                        Init.housingEstate.setRooms(new ParkingSpace(volume));
                    }
                    break;
                case 2:
                    System.out.println("Enter length:");
                    length = scanner.nextDouble();
                    System.out.println("Enter width:");
                    width = scanner.nextDouble();
                    System.out.println("Enter height:");
                    height = scanner.nextDouble();
                    if (f) {
                        System.out.println("Enter rental fee:");
                        rentaFee = scanner.nextDouble();
                        Init.housingEstate.setRooms(new Apartment(length, width, height, rentaFee));
                    } else {
                        Init.housingEstate.setRooms(new ParkingSpace(length, width, height));
                    }
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void createContent(boolean f) {
        double volume, length, width, height;
        String name;
        scanner.nextLine();
        System.out.println("Enter name:");
        name = scanner.nextLine();
        System.out.println("Choose volume input type:");
        System.out.println("1: Cubic meters\n2: Length, width, height");
        int command;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter volume:");
                    volume = scanner.nextDouble();
                    if (f) {
                        createVehicle(name, volume);
                    } else {
                        Item i = new Item(name, volume);
                    }
                    break;
                case 2:
                    System.out.println("Enter length:");
                    length = scanner.nextDouble();
                    System.out.println("Enter width:");
                    width = scanner.nextDouble();
                    System.out.println("Enter height:");
                    height = scanner.nextDouble();
                    if (f) {
                        createVehicle(name, length, width, height);
                    } else {
                        Item i = new Item(name, length, width, height);
                    }
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2);
    }

    public static void createVehicle(String name, double volume) {
        System.out.println("Choose vehicle type to create: ");
        System.out.println("1: Off-road car\n2: City car\n3: Boat\n4: Motorcycle\n5: Amphibious");
        String engineType;
        int command, numOfDoors, peopleCapacity, numOfAxles;
        double engineVolume;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter engine type:");
                    scanner.nextLine();
                    engineType = scanner.nextLine();
                    OffRoadCar frc = new OffRoadCar(name, volume, engineType);
                    break;
                case 2:
                    System.out.println("Enter number of doors:");
                    numOfDoors = scanner.nextInt();
                    CityCar cc = new CityCar(name, volume, numOfDoors);
                    break;
                case 3:
                    System.out.println("Enter people capacity of boat:");
                    peopleCapacity = scanner.nextInt();
                    Boat b = new Boat(name, volume, peopleCapacity);
                    break;
                case 4:
                    System.out.println("Enter engine volume:");
                    engineVolume = scanner.nextInt();
                    Motorcycle m = new Motorcycle(name, volume, engineVolume);
                    break;
                case 5:
                    System.out.println("Enter number of axles:");
                    numOfAxles = scanner.nextInt();
                    Amphibious a = new Amphibious(name, volume, numOfAxles);
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2 && command != 3 && command != 4 && command != 5);
    }

    public static void createVehicle(String name, double length, double width, double height) {
        System.out.println("Choose vehicle type to create: ");
        System.out.println("1: Off-road car\n2: City car\n3: Boat\n4: Motorcycle\n5: Amphibious");
        String engineType;
        int command, numOfDoors, peopleCapacity, numOfAxles;
        double engineVolume;
        do {
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Enter engine type:");
                    scanner.nextLine();
                    engineType = scanner.nextLine();
                    OffRoadCar frc = new OffRoadCar(name, length, width, height, engineType);
                    break;
                case 2:
                    System.out.println("Enter number of doors:");
                    numOfDoors = scanner.nextInt();
                    CityCar cc = new CityCar(name, length, width, height, numOfDoors);
                    break;
                case 3:
                    System.out.println("Enter people capacity of boat:");
                    peopleCapacity = scanner.nextInt();
                    Boat b = new Boat(name, length, width, height, peopleCapacity);
                    break;
                case 4:
                    System.out.println("Enter engine volume:");
                    engineVolume = scanner.nextInt();
                    Motorcycle m = new Motorcycle(name, length, width, height, engineVolume);
                    break;
                case 5:
                    System.out.println("Enter number of axles:");
                    numOfAxles = scanner.nextInt();
                    Amphibious a = new Amphibious(name, length, width, height, numOfAxles);
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 1 && command != 2 && command != 3 && command != 4 && command != 5);
    }

    public static ArrayList<LocalDate> inputRentDates() {
        ArrayList<LocalDate> dates = new ArrayList<>();
        System.out.println("Enter start date in format yyyy-mm-dd");
        dates.add(LocalDate.parse(scanner.next()));
        System.out.println("Enter end date in format yyyy-mm-dd:");
        dates.add(LocalDate.parse(scanner.next()));
        return dates;
    }

    public static Person inputPerson() {
        System.out.println("Enter person's PESEL number");
        int pesel = scanner.nextInt();
        return Person.getByPesel(pesel);
    }

    public static void printTenants() {
        System.out.print("Tenants:");
        System.out.println(Arrays.toString(Init.housingEstate.getTenants().
                toArray()).replaceAll("[\\[\\]]", ""));
    }

    public static void printList(ArrayList<?> arrayList) {
        System.out.println(Arrays.toString(arrayList.toArray()).replaceAll("[\\[\\]]", ""));
    }

}
