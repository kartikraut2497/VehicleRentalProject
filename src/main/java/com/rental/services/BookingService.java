package com.rental.services;

import com.rental.models.Booking;
import com.rental.models.Branch;
import com.rental.models.Vehicle;
import com.rental.models.VehicleTypeEnum;

import java.util.*;

public class BookingService {

    // This Map contains bookings of all vehicles with key as VehicleId
    Map<String, List<Booking>> currentBookingsMap = new HashMap<>();

    public Map<String, List<Booking>> getCurrentBookingsMap() {
        return currentBookingsMap;
    }

    private boolean isBookingAvailable(String vehicleId, int start, int end){
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

    public int bookVehicle(Branch branch, String vehicleType, int start, int end){
        Set<VehicleTypeEnum> allowedVehicleTypes = branch.getVehicleTypeEnumSet();
        if(!allowedVehicleTypes.contains(VehicleTypeEnum.valueOf(vehicleType))){
            return -1;
        }

        int totalPrice = -1;
        List<Vehicle> vehicleList = branch.getVehiclesList();
        VehicleTypeEnum currVehicleTypeEnum = VehicleTypeEnum.valueOf(vehicleType);
        Booking booking = null;

        for(int i=0; i<vehicleList.size(); i++){
            Vehicle currentVehicle = vehicleList.get(i);
            String vehicleId = currentVehicle.getVehicleId();
            int price = currentVehicle.getPrice();
            if(currentVehicle.getVehicleTypeEnum().equals(currVehicleTypeEnum)){
                boolean isBookingAvailable = isBookingAvailable(vehicleId, start, end);
                if(isBookingAvailable){
                    List<Booking> currentBookings = currentBookingsMap.getOrDefault(vehicleId, new ArrayList<>());
                    totalPrice = (end-start)*price;
                    booking = new Booking(vehicleId, branch.getBranchId(), currVehicleTypeEnum, start, end, totalPrice);
                    currentBookings.add(booking);
                    currentBookingsMap.put(vehicleId, currentBookings);
                    break;
                }
            }
        }

        return totalPrice;
    }
}
