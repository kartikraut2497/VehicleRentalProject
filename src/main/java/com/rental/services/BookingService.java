package com.rental.services;

import com.rental.models.Booking;
import com.rental.models.Branch;
import com.rental.models.Vehicle;
import com.rental.models.VehicleTypeEnum;

import java.util.*;

public class BookingService {

    // This Map contains bookings of all vehicles with key as VehicleId
    Map<String, List<Booking>> currentBookingsMap = new HashMap<>();

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
//                if((bookingStart <= start && bookingEnd > start) || (bookingStart <= end && bookingEnd >= end) || (start < bookingStart && end > bookingEnd)){
//                    isBookingAvailable = false;
//                }
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

    public void displayAvailableVehicles(Branch branch, int start, int end){
        List<Vehicle> vehicleList = branch.getVehiclesList();
        List<String> availableVehicles = new ArrayList<>();

        for(int i=0; i<vehicleList.size(); i++){
            String vehicleId = vehicleList.get(i).getVehicleId();
            if(isBookingAvailable(vehicleId, start, end)){
                availableVehicles.add(vehicleId);
//                System.out.println(vehicleId);
            }
        }

        if(availableVehicles.size() == 0){
            System.out.println("No Vehicles availabled between " + start + " " + end);
        }
        else{
            System.out.println("Available Vehicles: " + availableVehicles);
        }
    }

    public void displayBookings(){
        for(Map.Entry<String, List<Booking>> bookingEntry : currentBookingsMap.entrySet()){
            System.out.println("Vehicle Id: " + bookingEntry.getKey());
            System.out.println("Bookings: ");
            for(Booking booking: bookingEntry.getValue()){
                System.out.println(booking.toString());
            }
        }
    }

}
