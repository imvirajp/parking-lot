package com.step.parking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingLotAttendantTest {

    private ParkingLotAttendant attendant;

    class TestVehicle implements Vehicle{
    }

    @Before
    public void setUp() {
        attendant = new ParkingLotAttendant();
        attendant.add(new ParkingLot(2));
    }

    @Test
    public void shouldParkVehicleIntoParkingLot() throws UnableToParkException {
        TestVehicle car = new TestVehicle();
        assertNotNull(attendant.park(car));
    }

    @Test
    public void shouldBeAbleToParkVehicleWhenFirstLotIsFull() throws UnableToParkException {
        attendant.add(new ParkingLot(2));
        Vehicle car = new TestVehicle();
        attendant.park(new TestVehicle());
        attendant.park(new TestVehicle());
        assertNotNull(attendant.park(car));
    }

    @Test(expected = UnableToParkException.class)
    public void shouldNotBeAbleToParkVehicleIfBothLotsAreFull() throws UnableToParkException {
        attendant.add(new ParkingLot(2));
        attendant.park(new TestVehicle());
        attendant.park(new TestVehicle());
        attendant.park(new TestVehicle());
        attendant.park(new TestVehicle());
        attendant.park(new TestVehicle());
    }

    @Test
    public void shouldBeAbleToCheckoutVehicle() throws UnableToParkException, InvalidCheckoutException {
        TestVehicle vehicle = new TestVehicle();
        Object token = attendant.park(vehicle);
        assertEquals(attendant.checkout(token),vehicle);
    }
}
