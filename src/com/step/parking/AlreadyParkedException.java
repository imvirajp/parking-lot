package com.step.parking;

public class AlreadyParkedException extends Throwable {
    public AlreadyParkedException() {
        super("Vehicle is already in parking lot!");
    }
}
