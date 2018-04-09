package com.step.parking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttendantTest {

  private Attendant attendant;
  ParkingLot parkingLot1;
  ParkingLot parkingLot2;
  MyEvents events = new MyEvents();

  public class TestCar implements Vehicle {}

  @Before
  public void setUp() {
    parkingLot1 = new ParkingLot ( 1 ,events);
    parkingLot2 = new ParkingLot ( 2 ,events);
    attendant = new Attendant ();
    attendant.add(parkingLot1);
    attendant.add(parkingLot2);
  }

  @Test
  public void shouldBeAbleToParkCar() throws CarCannotBeParkedException {
    Vehicle car = new TestCar ();
    Object token = attendant.park(car);
    assertNotNull(token);
  }

  @Test
  public void shouldBeAbleToParkCarEvenIfOneParkingLotIsFull() throws CarCannotBeParkedException {
    Vehicle car1 = new TestCar ();
    Vehicle car2 = new TestCar ();
    attendant.park(car1);
    Object token2 = attendant.park(car2);
    assertNotNull(token2);
  }

  @Test
  public void shouldBeAbleToUnParkCar() throws CarCannotBeParkedException, CarNotFoundException {
    Vehicle car = new TestCar ();
    Object token = attendant.park(car);
    Vehicle anotherCar = attendant.unPark(token);
    assertEquals(car,anotherCar);
  }

  @Test
  public void shouldBeAbleToCallTheAssistantToUpdateDisplayWhenLotIsFull() throws CarCannotBeParkedException {
    Vehicle car = new TestCar ();
    Object token = attendant.park(car);
    assertNotNull(token);
  }
}
