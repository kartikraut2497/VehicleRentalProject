package com.rental.services;

import com.rental.models.*;

public class VehicleFactory {

    public Vehicle getVehicle(String vehicleType, String vehicleId, int price){
        VehicleTypeEnum vehicleTypeEnum = VehicleTypeEnum.valueOf(vehicleType);
        if(VehicleTypeEnum.BIKE.equals(vehicleTypeEnum)){
            return new Bike(vehicleId, price, VehicleTypeEnum.BIKE);
        }
        else if(VehicleTypeEnum.CAR.equals(vehicleTypeEnum)){
            return new Car(vehicleId, price, VehicleTypeEnum.CAR);
        }
        else if(VehicleTypeEnum.VAN.equals(vehicleTypeEnum)){
            return new Van(vehicleId, price, VehicleTypeEnum.VAN);
        }
        else if(VehicleTypeEnum.BUS.equals(vehicleTypeEnum)){
            return new Bus(vehicleId, price, VehicleTypeEnum.BUS);
        }

        return null;
    }

}
