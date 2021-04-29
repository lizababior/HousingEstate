package com.company;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;


public class Main {

    public static LocalDate currentDate = LocalDate.now();
    public static final String FILENAME = "output.txt";

    public static void main(String[] args) throws ParseException, TooManyThingsException, IOException {
        Scanner scanner = new Scanner(System.in);
        int command;

        //PassingTime passingTime = new PassingTime(200);
        //passingTime.start();

        //Main menu
        Init.create();
        do {
            Menu.printMenuOptions();
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    Menu.printAllRooms();
                    break;
                case 2:
                    Menu.printRentedRooms();
                    break;
                case 3:
                    Menu.printFreeRooms();
                    break;
                case 4:
                    Menu.printAllPeople();
                    break;
                case 5:
                    Menu.rentRoom();
                    break;
                case 6:
                    Menu.cancelRoomRent();
                    break;
                case 7:
                    Menu.renewRoomRent();
                    break;
                case 8:
                    Menu.printResidents();
                    break;
                case 9:
                    Menu.printContent();
                    break;
                case 10:
                    Menu.checkIn();
                    break;
                case 11:
                    Menu.checkOut();
                    break;
                case 12:
                    Menu.evictPeople();
                    break;
                case 13:
                    Menu.insertItem();
                    break;
                case 14:
                    Menu.takeOutItem();
                    break;
                case 15:
                    Menu.utilize();
                    break;
                case 16:
                    Menu.printTenantLetters();
                    break;
                case 17:
                    Menu.writeToFile();
                    break;
                case 18:
                    Menu.createObject();
                    break;
                default:
                    System.out.println("Command not recognised! Please, try again\n");
            }
        } while (command != 19);
    }
}
