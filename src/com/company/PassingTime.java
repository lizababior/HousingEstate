package com.company;

import java.io.IOException;

public class PassingTime extends Thread {

    int miliseconds;
    int daysToPass = 1;

    PassingTime(int miliseconds) {
        this.miliseconds = miliseconds;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Thread.sleep(miliseconds);
                Main.currentDate = Main.currentDate.plusDays(daysToPass);
                for (Room r : Room.getAllRooms()) {
                    try {
                        if (!r.checkIfAvailable()) {
                            r.checkRent();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    public void pauseTimePassing() {
        daysToPass = 0;
    }

    public void resumeTimePassing() {
        daysToPass = 1;
    }
}
