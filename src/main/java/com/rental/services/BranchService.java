package com.rental.services;

import com.rental.models.Booking;
import com.rental.models.Branch;
import com.rental.models.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BranchService {

    Map<String, List<Booking>> currentBookingsMap = new HashMap<>();

    public Map<String, List<Booking>> getCurrentBookingsMap() {
        return currentBookingsMap;
    }

    public void setCurrentBookingsMap(Map<String, List<Booking>> currentBookingsMap) {
        this.currentBookingsMap = currentBookingsMap;
    }

    private boolean isVehicleAvailable(String vehicleId, int start, int end){
        boolean isBookingAvailable = false;

        if(!currentBookingsMap.containsKey(vehicleId)){
            isBookingAvailable = true;
        }
        else{
            isBookingAvailable = true;
            List<Booking> currentBookings = currentBookingsMap.get(vehicleId);
            for(int j=0; j<currentBookings.size() && isBookingAvailable; j++){
                int bookingStart = currentBookings.get(j).getStart();
                int bookingEnd = currentBookings.get(j).getEnd();
                if(!((start < bookingStart && end <= bookingStart) || (start >= bookingEnd && end > bookingEnd))){
                    isBookingAvailable = false;
                }
            }
        }

        return isBookingAvailable;
    }

    public List<String> getAvailableVehicles(Branch branch, int start, int end){
        List<Vehicle> vehicleList = branch.getVehiclesList();
        List<String> availableVehicles = new ArrayList<>();

        for(int i=0; i<vehicleList.size(); i++){
            String vehicleId = vehicleList.get(i).getVehicleId();
            if(isVehicleAvailable(vehicleId, start, end)){
                availableVehicles.add(vehicleId);
            }
        }

        return availableVehicles;
    }

}
