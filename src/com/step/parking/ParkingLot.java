package com.step.parking;

import java.util.HashMap;
import java.util.Objects;

public class ParkingLot {
    private final int capacity;
    private HashMap<Object,Vehicle> vehicles;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.vehicles = new HashMap<>();
    }

    public Object park(Vehicle vehicle) throws AlreadyParkedException, FullLotException {
        if(vehicles.containsValue(vehicle)) throw new AlreadyParkedException();
        if (isFull()) throw new FullLotException();
        Object token = new Object();
        vehicles.put(token,vehicle);
        return token;
    }

    public boolean isFull() {
        return vehicles.size() == capacity;
    }

    public Vehicle checkout(Object token) throws InvalidCheckoutException {
        if(!hasVehicleFor(token)) throw new InvalidCheckoutException();
        return vehicles.remove(token);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return Objects.equals(vehicles, that.vehicles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicles);
    }

    public boolean hasVehicleFor(Object token) {
        return vehicles.containsKey(token);
    }
}