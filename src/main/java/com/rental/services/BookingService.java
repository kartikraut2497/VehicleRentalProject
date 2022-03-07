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

    public double bookVehicle(Branch branch, String vehicleType, int start, int end){
        Set<VehicleTypeEnum> allowedVehicleTypes = branch.getVehicleTypeEnumSet();
        if(!allowedVehicleTypes.contains(VehicleTypeEnum.valueOf(vehicleType))){
            return -1;
        }

        double totalPrice = -1;
        List<Vehicle> vehicleList = branch.getVehiclesList();
        VehicleTypeEnum currVehicleTypeEnum = VehicleTypeEnum.valueOf(vehicleType);
        Booking booking = null;

        for(int i=0; i<vehicleList.size(); i++){
            Vehicle currentVehicle = vehicleList.get(i);
            String vehicleId = currentVehicle.getVehicleId();
            double price = currentVehicle.getPrice();
            if(currentVehicle.getVehicleTypeEnum().equals(currVehicleTypeEnum)){
                boolean isBookingAvailable = isBookingAvailable(vehicleId, start, end);
                if(isBookingAvailable){
                    boolean isDynamicPricing = isDynamicPricing(currVehicleTypeEnum, vehicleList, start, end);
                    List<Booking> currentBookings = currentBookingsMap.getOrDefault(vehicleId, new ArrayList<>());
                    if(isDynamicPricing){
                        price = (1.1)*price;
                    }
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

    private boolean isDynamicPricing(VehicleTypeEnum currVehicleTypeEnum, List<Vehicle> vehicleList, int start, int end) {
        // totalVehicles - Total Number of vehicles of current vehicle type, bookedVehicles - number of booked vehicles of current vehicle type
        int totalVehicles = 0, bookedVehicles = 0;
        boolean isDynamicPricing = false;

        for(int i=0; i<vehicleList.size(); i++){
            Vehicle currentVehicle = vehicleList.get(i);
            if(currentVehicle.getVehicleTypeEnum().equals(currVehicleTypeEnum)){
                totalVehicles++;
                if(!isBookingAvailable(currentVehicle.getVehicleId(),start, end)){
                    bookedVehicles++;
                }
            }
        }

        double bookedPercent = ((double) bookedVehicles*100)/((double) totalVehicles);
        if(bookedPercent >= 80){
            isDynamicPricing = true;
        }
//        System.out.println("Booked = " + bookedPercent);
        return isDynamicPricing;
    }
}
