package com.rental.models;

public abstract class Vehicle {

    String vehicleId;
    int price;
    VehicleTypeEnum vehicleTypeEnum;

    public Vehicle(String vehicleId, int price, VehicleTypeEnum vehicleTypeEnum) {
        this.vehicleId = vehicleId;
        this.price = price;
        this.vehicleTypeEnum = vehicleTypeEnum;
    }

    public Vehicle() {}

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public VehicleTypeEnum getVehicleTypeEnum() {
        return vehicleTypeEnum;
    }

    public void setVehicleTypeEnum(VehicleTypeEnum vehicleTypeEnum) {
        this.vehicleTypeEnum = vehicleTypeEnum;
    }

}
