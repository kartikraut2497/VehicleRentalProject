package com.rental.services;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


class RentalServiceTest {

    RentalService rentalService = null;
    List<String> vehicleTypes;

    @BeforeEach
    void setUp() {
        rentalService = new RentalService();
        vehicleTypes = new ArrayList<>();
        vehicleTypes.add("CAR");
        vehicleTypes.add("BUS");
        rentalService.addBranch("B1", vehicleTypes);
    }

    @Test
    void addBranchSuccess() {
        boolean addBranch = rentalService.addBranch("B2", vehicleTypes);
        Assert.assertTrue(addBranch);
    }

    @Test
    void addBranchFail_InvalidVehicleType() {
        vehicleTypes.add("CAN");
        boolean addBranch = rentalService.addBranch("B3", vehicleTypes);
        Assert.assertFalse(addBranch);
    }

    @Test
    void addVehicleSuccess() {
        boolean addVehicle = rentalService.addVehicle("B1", "CAR", "V1", 500);
        Assert.assertTrue(addVehicle);
    }

    @Test
    void addVehicleFail_InvalidVehicleType() {
        boolean addVehicle = rentalService.addVehicle("B1", "CART", "V2", 500);
        Assert.assertFalse(addVehicle);
    }

    @Test
    void addVehicleFail_DuplicateVehicleId() {
        boolean addVehicle = rentalService.addVehicle("B1", "CAR", "V1", 500);
        boolean addVehicle2 = rentalService.addVehicle("B1", "CAR", "V1", 1000);
        Assert.assertFalse(addVehicle2);
    }

    @Test
    void bookVehicle() {
        boolean addVehicle = rentalService.addVehicle("B1", "CAR", "V1", 500);
        boolean addVehicle2 = rentalService.addVehicle("B1", "CAR", "V2", 1000);
        int bookingPrice = rentalService.bookVehicle("B1", "CAR", 1, 2);
        int bookingPrice2 = rentalService.bookVehicle("B1", "CAR", 3, 6);
        int bookingPrice3 = rentalService.bookVehicle("B1", "CAR", 4, 5);
        Assert.assertEquals(bookingPrice, 500);
        Assert.assertEquals(bookingPrice2, 1500);
        Assert.assertEquals(bookingPrice3, 1000);
    }

    @Test
    void bookVehicle_Fail() {
        boolean addVehicle = rentalService.addVehicle("B1", "CAR", "V1", 500);
        boolean addVehicle2 = rentalService.addVehicle("B1", "CAR", "V2", 1000);
        int bookingPrice = rentalService.bookVehicle("B1", "BUS", 1, 2);
        Assert.assertEquals(bookingPrice, -1);
    }

    @Test
    void bookVehicle_InvalidBranchId() {
        int bookingPrice = rentalService.bookVehicle("B2", "BUS", 1, 2);
        Assert.assertEquals(bookingPrice, -1);
    }
}