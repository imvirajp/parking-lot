package com.step.parking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {

    private ParkingLot parkingLot;
    private TestVehicle vehicle;

    private class TestVehicle implements Vehicle {
        public TestVehicle() {
        }
    }

    @Before
    public void setUp() {
        parkingLot = new ParkingLot(5);
        vehicle = new TestVehicle();
    }

    @Test
    public void shouldBeAbleToParkTheVehicle() throws AlreadyParkedException, FullLotException {
        Object token = parkingLot.park(vehicle);
        assertNotNull(token);
    }

    @Test
    public void shouldBeAbleToCheckoutTheVehicle() throws InvalidCheckoutException, AlreadyParkedException, FullLotException {
        Object token = parkingLot.park(vehicle);
        Object myCar = this.parkingLot.checkout(token);
        assertEquals(myCar, vehicle);
        assertFalse(parkingLot.hasVehicleFor(token));
    }

    @Test(expected = AlreadyParkedException.class)
    public void shouldNotBeAbleToParkTheSameVehicleTwice() throws AlreadyParkedException, FullLotException {
        parkingLot.park(vehicle);
        parkingLot.park(vehicle);
    }

    @Test(expected = InvalidCheckoutException.class)
    public void shouldNotBeAbleToCheckoutSameVehicleTwice() throws InvalidCheckoutException, AlreadyParkedException, FullLotException {
        Object token = parkingLot.park(vehicle);
        parkingLot.checkout(token);
        parkingLot.checkout(token);
    }

    @Test
    public void shouldCheckoutSpecificVehicle() throws InvalidCheckoutException, AlreadyParkedException, FullLotException {
        Object token = parkingLot.park(vehicle);
        TestVehicle anotherCar = new TestVehicle();
        Object anotherToken = parkingLot.park(anotherCar);
        assertEquals(parkingLot.checkout(token), vehicle);
        assertTrue(parkingLot.hasVehicleFor(anotherToken));
    }

    @Test
    public void shouldAssertFalseForNotFullParkingLot() {
        assertFalse(parkingLot.isFull());
    }

    @Test
    public void shouldAssertTrueWhenParkingLotIsFull() throws AlreadyParkedException, FullLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(vehicle);
        assertTrue(parkingLot.isFull());
    }

    @Test
    public void shouldAssertTrueWhenLotIsFullAndAfterCheckoutLotShouldNotBeFull() throws AlreadyParkedException, FullLotException, InvalidCheckoutException {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(vehicle);
        Object anotherToken = parkingLot.park(new TestVehicle());
        assertTrue(parkingLot.isFull());
        parkingLot.checkout(anotherToken);
        assertFalse(parkingLot.isFull());
    }

    @Test(expected = FullLotException.class)
    public void shouldNotAllowToParkVehicleWhenLotIsFull() throws AlreadyParkedException, FullLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(vehicle);
        parkingLot.park(new TestVehicle());
        parkingLot.park(new TestVehicle());
    }
}