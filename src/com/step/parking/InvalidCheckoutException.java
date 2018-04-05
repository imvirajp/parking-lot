package com.step.parking;

public class InvalidCheckoutException extends Throwable {
    public InvalidCheckoutException() {
        super("Parking lot don't have given car");
    }
}
