package com.step.parking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {

    private ParkingLot parkingLot;
    private Object car;

    @Before
    public void setUp() {
        parkingLot = new ParkingLot();
        car = new Object();
    }

    @Test
    public void shouldParkCarInParkingLot() {
        Object token = parkingLot.park(car);
        assertNotNull(token);
    }

    @Test
    public void shouldGiveBackCarAfterCheckout() throws CarNotInLotException {
        Object token = parkingLot.park(car);
        Object myCar = this.parkingLot.checkout(token);
        assertEquals(myCar,car);
    }

    @Test(expected = CarNotInLotException.class)
    public void shouldThrowExceptionIfCarIsNotParked() throws CarNotInLotException {
        parkingLot.checkout(1);
    }
}