package com.company;

public class TenantLetter {

    private Room room;
    private StringBuilder content;

    public TenantLetter(Room room, StringBuilder content) {
        this.room = room;
        this.content = content;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "\nTenantLetter " + room + ". " + content;
    }
}
