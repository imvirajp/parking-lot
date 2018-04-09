package com.step.parking;

public class CarCannotBeParkedException extends Throwable {
  public CarCannotBeParkedException() {
    super("Car is already parked !!");
  }
}
