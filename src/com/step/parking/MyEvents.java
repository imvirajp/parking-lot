package com.step.parking;

import java.util.ArrayList;
import java.util.EventListener;

public class MyEvents implements EventListener {
  private final ArrayList<ParkingLotListener> parkingLotListeners;

  public MyEvents() {
    this.parkingLotListeners = new ArrayList<>();
  }

  public void addListener (ParkingLotListener parkingLotListener) {
    parkingLotListeners.add(parkingLotListener);
  }

  public void fireFullEvent() {
    for (ParkingLotListener parkingLotListener : parkingLotListeners) {
      parkingLotListener.full();
    }
  }

  public void fireHasSpaceEvent() {
    for (ParkingLotListener parkingLotListener : parkingLotListeners) {
      parkingLotListener.hasSpace();
    }
  }
}
