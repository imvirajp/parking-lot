package com.step.parking;

import java.util.HashMap;
import java.util.Objects;

public class ParkingLot {
    private HashMap<Object,Object> parkedCars;

    public ParkingLot() {
        this.parkedCars = new HashMap<>();
    }

    public Object park(Object car) {
        Object token = new Object();
        parkedCars.put(token,car);
        return token;
    }

    private boolean hasCar(Object token) {
        return parkedCars.containsKey(token);
    }

    public Object checkout(Object token) throws CarNotInLotException {
        if(!hasCar(token)) throw new CarNotInLotException();
        return parkedCars.remove(token);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return Objects.equals(parkedCars, that.parkedCars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkedCars);
    }
}