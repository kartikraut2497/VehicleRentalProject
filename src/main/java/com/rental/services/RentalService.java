package com.rental.services;

import com.rental.models.Booking;
import com.rental.models.Branch;
import com.rental.models.VehicleTypeEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalService {

    BookingService bookingService = new BookingService();
    Map<String, Branch> branchMap = new HashMap<>();

    VehicleTypeEnum checkEnum(String vehicleType){
        for(VehicleTypeEnum vehicleTypeEnum: VehicleTypeEnum.values()){
            if(vehicleTypeEnum.name().equalsIgnoreCase(vehicleType)){
                return vehicleTypeEnum;
            }
        }
        return null;
    }

    boolean isVehicleTypeValid(List<String> vehicleTypes){
        boolean isValid = true;

        for(String vehicleType: vehicleTypes){
            VehicleTypeEnum vehicleEnum = checkEnum(vehicleType);
            if(vehicleEnum == null){
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    boolean isVehicleTypeValid(String vehicleType){
        boolean isValid = true;
        VehicleTypeEnum vehicleEnum = checkEnum(vehicleType);
        if(vehicleEnum == null){
            isValid = false;
        }

        return isValid;
    }

    public boolean addBranch(String branchId, List<String> vehicleTypes){
        boolean isVehicleTypeValid = isVehicleTypeValid(vehicleTypes);
        if(null != branchId && !branchId.isBlank() && isVehicleTypeValid){
            Branch branch = new Branch(branchId, branchId, vehicleTypes);
            branchMap.put(branchId, branch);
            return true;
        }
        return false;
    }

    public boolean addVehicle(String branchId, String vehicleType, String vehicleId, double price){
        Branch currentBranch = branchMap.getOrDefault(branchId, null);
        boolean isVehicleTypeValid = isVehicleTypeValid(vehicleType);
        if(null != currentBranch && isVehicleTypeValid){
            return currentBranch.addVehicle(vehicleType, vehicleId, price);
        }
        return false;
    }

    public double bookVehicle(String branchId, String vehicleType, int start, int end){
        Branch currentBranch = branchMap.getOrDefault(branchId, null);
        boolean isVehicleTypeValid = isVehicleTypeValid(vehicleType);
        if(null != currentBranch && isVehicleTypeValid){
            return this.bookingService.bookVehicle(currentBranch, vehicleType, start, end);
        }
        return -1;
    }

    public void displayVehicles(String branchId, int start, int end){
        Branch currentBranch = branchMap.getOrDefault(branchId, null);
        if(null != currentBranch){
            Map<String, List<Booking>> currentBookingsMap = this.bookingService.getCurrentBookingsMap();
            BranchService branchService = new BranchService();
            branchService.setCurrentBookingsMap(currentBookingsMap);
            List<String> availableVehicleIds = branchService.getAvailableVehicles(currentBranch, start, end);
            if(availableVehicleIds.size() == 0){
                System.out.println("No Vehicles availabled between " + start + " " + end);
            }
            else{
                System.out.println("Available Vehicles: " + availableVehicleIds);
            }
        }
        else{
            System.out.println("Please check the Branch Id");
        }
    }
}
