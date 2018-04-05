package com.step.parking;

public class FullLotException extends Throwable {
    public FullLotException() {
        super("Parking lot is full!");
    }
}
