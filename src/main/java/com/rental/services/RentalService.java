package com.rental.services;

import com.rental.models.Branch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalService {

    BookingService bookingService = new BookingService();
    Map<String, Branch> branchMap = new HashMap<>();

    public boolean addBranch(String branchId, List<String> vehicleTypes){
        if(null != branchId && !branchId.isBlank()){
            Branch branch = new Branch(branchId, branchId, vehicleTypes);
            branchMap.put(branchId, branch);
            return true;
        }
        return false;
    }

    public boolean addVehicle(String branchId, String vehicleType, String vehicleId, int price){
        Branch currentBranch = branchMap.getOrDefault(branchId, null);
        if(null != currentBranch){
            return currentBranch.addVehicle(vehicleType, vehicleId, price);
        }
        return false;
    }

    public int bookVehicle(String branchId, String vehicleType, int start, int end){
        Branch currentBranch = branchMap.getOrDefault(branchId, null);
        if(null != currentBranch){
            return this.bookingService.bookVehicle(currentBranch, vehicleType, start, end);
        }
        return -1;
    }

    public void displayVehicles(String branchId, int start, int end){
        Branch currentBranch = branchMap.getOrDefault(branchId, null);
        if(null != currentBranch){
            this.bookingService.displayAvailableVehicles(currentBranch, start, end);
        }
        else{
            System.out.println("Please check the Branch Id");
        }
    }
}
