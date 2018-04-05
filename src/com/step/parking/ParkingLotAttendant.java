package com.step.parking;

import java.util.ArrayList;

public class ParkingLotAttendant {
    private ArrayList<ParkingLot> parkingLots;

    public ParkingLotAttendant() {
        this.parkingLots = new ArrayList<>();
    }

    public void add(ParkingLot lot) {
        parkingLots.add(lot);
    }

    public Object park(Vehicle vehicle) throws UnableToParkException {
        for (int i = 0; i<parkingLots.size();i++){
            try{
                return parkingLots.get(i).park(vehicle);
            } catch (UnableToParkException e){
                return parkingLots.get(i+1).park(vehicle);
            }
        }
        return null;
    }

    public Vehicle checkout(Object token) throws InvalidCheckoutException {
        for (int i = 0; i<parkingLots.size();i++) {
            try {
                return parkingLots.get(i).checkout(token);
            } catch (InvalidCheckoutException e) {
                return parkingLots.get(i+1).checkout(token);
            }
        }
        return null;
    }
}