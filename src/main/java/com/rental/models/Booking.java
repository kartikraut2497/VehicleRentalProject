package com.rental.models;

public class Booking {

    String vehicleId;
    String branchId;
    VehicleTypeEnum vehicleTypeEnum;
    int start, end;
    double totalPrice;

    public Booking(String vehicleId, String branchId, VehicleTypeEnum vehicleTypeEnum, int start, int end, double totalPrice) {
        this.vehicleId = vehicleId;
        this.branchId = branchId;
        this.vehicleTypeEnum = vehicleTypeEnum;
        this.start = start;
        this.end = end;
        this.totalPrice = totalPrice;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleTypeEnum getVehicleTypeEnum() {
        return vehicleTypeEnum;
    }

    public void setVehicleTypeEnum(VehicleTypeEnum vehicleTypeEnum) {
        this.vehicleTypeEnum = vehicleTypeEnum;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vehicleTypeEnum=" + vehicleTypeEnum +
                ", start=" + start +
                ", end=" + end +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
