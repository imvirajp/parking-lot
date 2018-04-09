package com.step.parking;

public class Assistant implements ParkingLotListener {

  @Override
  public void full() {
    System.out.println("No space!");
  }

  @Override
  public void hasSpace() {
    System.out.println("Has Space");
  }
}
