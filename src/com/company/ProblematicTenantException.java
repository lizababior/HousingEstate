package com.company;

public class ProblematicTenantException extends Exception {

    public ProblematicTenantException(Person person) {
        super("Person " + person.toString() + " had already renting rooms: "
                + person.getRentedRooms().toString().replaceAll("[\\[\\]]", ""));
    }
}

