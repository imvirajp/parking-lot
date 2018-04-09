package com.step.parking;

import java.util.ArrayList;

public class Attendant implements ParkingLotListener {

  private ArrayList<ParkingLot> parkingLots;
  private final Assistant assistant;

  public Attendant() {
    this.parkingLots = new ArrayList<>();
    this.assistant = new Assistant();
  }

  public Object park(Vehicle car) throws CarCannotBeParkedException {
    for (ParkingLot parkingLot : parkingLots) {
      if(!parkingLot.isFull()) {
        return parkingLot.park(car);
      }
    }
    return null;
  }

  public Vehicle unPark(Object token) throws CarNotFoundException {
    for (ParkingLot parkingLot : parkingLots) {
      if(parkingLot.hasCar(token)){
        return parkingLot.unParkCar(token);
      }
    }
    return null;
  }

  public void add(ParkingLot parkingLot) {
    parkingLots.add(parkingLot);
    parkingLot.addListener(this);
  }

  @Override
  public void full() {
    System.out.println("Full");
  }

  @Override
  public void hasSpace() {
    System.out.println("Not full");

  }
}
