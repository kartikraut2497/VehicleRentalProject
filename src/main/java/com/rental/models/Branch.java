package com.rental.models;

import com.rental.services.VehicleFactory;

import java.util.*;

public class Branch {

    String branchId;
    String branchName;
    List<Vehicle> vehiclesList = new ArrayList<>();
    Set<String> vehicleIdsSet = new HashSet<>();
    Set<VehicleTypeEnum> vehicleTypeEnumSet = new HashSet<>();
    private VehicleFactory vehicleFactory = new VehicleFactory();

    public Branch(){}

    public Branch(String branchId, String branchName,  List<String> vehicleTypeEnumsList){
        this.branchId = branchId;
        this.branchName = branchName;
        for(String vehicleType: vehicleTypeEnumsList){
            this.vehicleTypeEnumSet.add(VehicleTypeEnum.valueOf(vehicleType));
        }
    }

    public String getBranchId() {
        return branchId;
    }

    public List<Vehicle> getVehiclesList() {
        return vehiclesList;
    }

    public Set<VehicleTypeEnum> getVehicleTypeEnumSet() {
        return vehicleTypeEnumSet;
    }

    public boolean addVehicle(String vehicleType, String vehicleId, double price){
        if(!this.vehicleTypeEnumSet.contains(VehicleTypeEnum.valueOf(vehicleType))){
            System.out.println("Vehicle Type - " + vehicleType + " is not allowed in current Branch.");
            return false;
        }

        if(this.vehicleIdsSet.contains(vehicleId)){
            System.out.println("Vehicle Id : " + vehicleId + " already exists.");
            return false;
        }

        Vehicle vehicle = vehicleFactory.getVehicle(vehicleType, vehicleId, price);
        this.vehiclesList.add(vehicle);
        this.vehicleIdsSet.add(vehicleId);
        Collections.sort(this.vehiclesList, Comparator.comparingDouble(Vehicle::getPrice));
        return true;
    }
}
