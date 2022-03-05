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

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public List<Vehicle> getVehiclesList() {
        return vehiclesList;
    }

    public void setVehiclesList(List<Vehicle> vehiclesList) {
        this.vehiclesList = vehiclesList;
    }

    public Set<VehicleTypeEnum> getVehicleTypeEnumSet() {
        return vehicleTypeEnumSet;
    }

    public void setVehicleTypeEnumSet(Set<VehicleTypeEnum> vehicleTypeEnumSet) {
        this.vehicleTypeEnumSet = vehicleTypeEnumSet;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public boolean addVehicle(String vehicleType, String vehicleId, int price){
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
        Collections.sort(this.vehiclesList, (v1, v2) -> v1.getPrice() - v2.getPrice());
        return true;
    }
}
