package com.company;

public class TooManyThingsException extends Exception {

    public TooManyThingsException() {
        super("Remove some old items to insert a new item");
    }

}
