package com.step.parking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ParkingLotTest{
  public class TestCar implements Vehicle {}
  ParkingLot parkingLot;
  Vehicle car;
  MyEvents testListener = mock(MyEvents.class);

  @Before
  public void setUp() {
    parkingLot = new ParkingLot (5,testListener);
    car = new TestCar ();
  }

  @Test
  public void shouldParkCar() throws CarCannotBeParkedException {
    Object token = parkingLot.park (car);
    assertNotNull(token);
  }

  @Test(expected = CarCannotBeParkedException.class)
  public void shouldThrowExceptionIfCarIsAlreadyParked() throws CarCannotBeParkedException {
    parkingLot.park (car);
    parkingLot.park (car);
  }

  @Test
  public void shouldUnParkCar() throws CarNotFoundException, CarCannotBeParkedException {
    Object token = parkingLot.park(car);
    Vehicle myCar = parkingLot.unParkCar(token);
    assertFalse(parkingLot.hasCar(this.car));
    assertEquals(car,myCar);
  }

  @Test
  public void shouldGiveFalseIfCarIsNotPresent() {
    assertFalse(parkingLot.hasCar(new Object()));
  }

  @Test
  public void shouldGiveTrueIfCarIsPresent() throws CarCannotBeParkedException {
    Object token = parkingLot.park(car);
    assertTrue(parkingLot.hasCar(token));
  }

  @Test(expected = CarNotFoundException.class)
  public void shouldThrowExceptionIfCarIsNotInLot() throws CarNotFoundException {
    parkingLot.unParkCar(car);
  }

  @Test
  public void shouldGiveTrueIfParkingLotIsFull() throws CarCannotBeParkedException {
    ParkingLot parkingLot = new ParkingLot(1,testListener);
    parkingLot.park(car);
    assertTrue(parkingLot.isFull());
  }

  @Test
  public void shouldGiveFalseIfParkingLotIsNotFull() {
    ParkingLot parkingLot = new ParkingLot(1,testListener);
    assertFalse(parkingLot.isFull());
  }

  @Test(expected = CarCannotBeParkedException.class)
  public void shouldNotParkCarIfParkingLotIsFull() throws CarCannotBeParkedException {
    ParkingLot parkingLot = new ParkingLot(1,testListener);
    parkingLot.park(car);
    Vehicle anotherCar = new TestCar ();
    parkingLot.park(anotherCar);
  }

  @Test
  public void shouldParkCarIfAnyCarIsUnParked() throws CarCannotBeParkedException, CarNotFoundException {
    ParkingLot parkingLot = new ParkingLot(2,testListener);
    Vehicle anotherCar = new TestCar();
    Object token = parkingLot.park(car);
    parkingLot.park(anotherCar);
    parkingLot.unParkCar(token);
    assertFalse(parkingLot.hasCar(token));
    Object anotherToken = parkingLot.park (car);
    assertTrue(parkingLot.hasCar(anotherToken));
  }

  @Test
  public void name() throws CarCannotBeParkedException {
    ParkingLot parkingLot = new ParkingLot(2, testListener);
    parkingLot.addListener(new Assistant());
    Vehicle car2 = new TestCar();
    parkingLot.park(car);
    parkingLot.park(car2);
    verify(testListener).fireFullEvent();
  }

  @Test
  public void name2() throws CarCannotBeParkedException, CarNotFoundException {
    ParkingLot parkingLot = new ParkingLot(2, testListener);
    parkingLot.addListener(new Assistant());
    Vehicle car2 = new TestCar();
    parkingLot.park(car);
    Object token = parkingLot.park(car2);
    parkingLot.unParkCar(token);
    verify(testListener).fireHasSpaceEvent();
  }

  @Test
  public void name3() throws CarCannotBeParkedException, CarNotFoundException {
    ParkingLot parkingLot = new ParkingLot(2, testListener);
    parkingLot.addListener(new Assistant());
    Vehicle car2 = new TestCar();
    Object token2 = parkingLot.park(car);
    Object token = parkingLot.park(car2);
    parkingLot.unParkCar(token);
    verify(testListener).fireHasSpaceEvent();
    parkingLot.unParkCar(token2);
    verify(testListener) .fireHasSpaceEvent();
  }
}
