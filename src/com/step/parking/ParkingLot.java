package com.step.parking;

import java.util.HashMap;
import java.util.Objects;

public class ParkingLot {
  private final HashMap <Object,Vehicle> vehicles;
  private int capacity;
  private final MyEvents events;


  public ParkingLot(int capacity,MyEvents events) {
    this.capacity = capacity;
    this.vehicles = new HashMap<> ( );
    this.events = events;
  }

  public Object park(Vehicle car) throws CarCannotBeParkedException {
    if (isFull())
      throw new CarCannotBeParkedException ();
    if (vehicles.containsValue ( car ))
      throw new CarCannotBeParkedException ();
    Object token = new Object ();
    vehicles.put(token,car);
    if(isFull()){
      this.events.fireFullEvent();
    }
    return token;
  }

  public Vehicle unParkCar(Object token) throws CarNotFoundException {
    if (!hasCar (token))
      throw new CarNotFoundException ();
    if (isFull()){
      this.events.fireHasSpaceEvent();
    }
    Vehicle car = vehicles.remove (token);
    return car;
  }

  public boolean hasCar(Object token) {
    return vehicles.containsKey ( token );
  }

  @Override
  public String toString() {
    return "ParkingLot{" +
            "vehicles=" + vehicles +
            '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash ( vehicles , capacity );
  }

  public boolean isFull() {
    return capacity == vehicles.size ();
  }

  public void addListener(ParkingLotListener parkingLotListener) {
    events.addListener(parkingLotListener);
  }
}
