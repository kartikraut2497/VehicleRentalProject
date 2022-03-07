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
        double bookingPrice = rentalService.bookVehicle("B1", "CAR", 1, 2);
        double bookingPrice2 = rentalService.bookVehicle("B1", "CAR", 3, 6);
        double bookingPrice3 = rentalService.bookVehicle("B1", "CAR", 4, 5);
        System.out.println(bookingPrice);
        System.out.println(bookingPrice2);
        System.out.println(bookingPrice3);

        Assert.assertEquals(bookingPrice, 500, 0);
        Assert.assertEquals(bookingPrice2, 1500, 0);
        Assert.assertEquals(bookingPrice3, 1000, 0);
    }

    @Test
    void bookVehicle2() {
        boolean addVehicle = rentalService.addVehicle("B1", "CAR", "V1", 500);
        boolean addVehicle2 = rentalService.addVehicle("B1", "CAR", "V2", 1000);
        double bookingPrice = rentalService.bookVehicle("B1", "CAR", 1, 2);
        double bookingPrice2 = rentalService.bookVehicle("B1", "CAR", 4, 5);
        double bookingPrice3 = rentalService.bookVehicle("B1", "CAR", 3, 6);
        double bookingPrice4 = rentalService.bookVehicle("B1", "CAR", 4, 6);

        Assert.assertEquals(bookingPrice, 500, 0);
        Assert.assertEquals(bookingPrice2, 500, 0);
        Assert.assertEquals(bookingPrice3, 3000, 0);
        Assert.assertEquals(bookingPrice4, -1, 0);
    }

    @Test
    void bookVehicleDynamicPricing() {
        rentalService.addVehicle("B1", "CAR", "V1", 500);
        rentalService.addVehicle("B1", "CAR", "V2", 1000);
        rentalService.addVehicle("B1", "CAR", "V3", 1500);
        rentalService.addVehicle("B1", "CAR", "V4", 2000);
        rentalService.addVehicle("B1", "CAR", "V5", 2500);
        rentalService.addVehicle("B1", "CAR", "V6", 3000);
        double bookingPrice = rentalService.bookVehicle("B1", "CAR", 1, 5);
        double bookingPrice2 = rentalService.bookVehicle("B1", "CAR", 4, 6);
        double bookingPrice3 = rentalService.bookVehicle("B1", "CAR", 3, 5);
        double bookingPrice4 = rentalService.bookVehicle("B1", "CAR", 2, 6);
        double bookingPrice5 = rentalService.bookVehicle("B1", "CAR", 3, 5);
        double bookingPrice6 = rentalService.bookVehicle("B1", "CAR", 4, 5);

        Assert.assertEquals(bookingPrice, 2000, 0);
        Assert.assertEquals(bookingPrice2, 2000, 0);
        Assert.assertEquals(bookingPrice3, 3000, 0);
        Assert.assertEquals(bookingPrice4, 8000, 0);
        Assert.assertEquals(bookingPrice5, 5000, 0);
        Assert.assertEquals(bookingPrice6, 3300, 0.0000001);
    }

    @Test
    void bookVehicle_Fail() {
        boolean addVehicle = rentalService.addVehicle("B1", "CAR", "V1", 500);
        boolean addVehicle2 = rentalService.addVehicle("B1", "CAR", "V2", 1000);
        double bookingPrice = rentalService.bookVehicle("B1", "BUS", 1, 2);
        Assert.assertEquals(bookingPrice, -1, 0);
    }

    @Test
    void bookVehicle_InvalidBranchId() {
        double bookingPrice = rentalService.bookVehicle("B2", "BUS", 1, 2);
        Assert.assertEquals(bookingPrice, -1, 0);
    }
}