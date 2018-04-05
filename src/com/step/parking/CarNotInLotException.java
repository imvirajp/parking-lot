package com.step.parking;

public class CarNotInLotException extends Throwable {
    public CarNotInLotException() {
        super("Parking lot don't have given car");
    }
}
