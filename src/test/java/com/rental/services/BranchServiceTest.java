package com.rental.services;

import com.rental.models.Booking;
import com.rental.models.Branch;
import com.rental.models.VehicleTypeEnum;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BranchServiceTest {

    BranchService branchService = null;
    Map<String, List<Booking>> bookingsMap = new HashMap<>();

    @BeforeEach
    void setUp() {
        branchService = new BranchService();
        Booking booking1 = new Booking("V1", "B1", VehicleTypeEnum.CAR, 1,3, 1000);
        Booking booking2 = new Booking("V1", "B1", VehicleTypeEnum.CAR, 4,5, 500);
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking1);
        bookingList.add(booking2);
        bookingsMap.put("V1", bookingList);
        branchService.setCurrentBookingsMap(bookingsMap);
    }

    @Test
    void getAvailableVehicles() {
        List<String> vehicleTypes = new ArrayList<>();
        vehicleTypes.add("CAR");
        Branch B1 = new Branch("B1", "B1", vehicleTypes);
        B1.addVehicle("CAR", "V1", 500);
        B1.addVehicle("CAR", "V2", 1000);
        List<String> availableVehicles = branchService.getAvailableVehicles(B1, 6, 7);
        String[] availableVehiclesArr = availableVehicles.toArray(new String[0]);
        List<String> expectedAvailableVehicles = new ArrayList<>();
        expectedAvailableVehicles.add("V1");
        expectedAvailableVehicles.add("V2");
        String[] expectedAvailableVehiclesArr = expectedAvailableVehicles.toArray(new String[0]);
        System.out.println(availableVehicles);
        Assert.assertArrayEquals(availableVehiclesArr, expectedAvailableVehiclesArr);
    }

    @Test
    void getAvailableVehiclesWithMergedIntervals() {
        List<String> vehicleTypes = new ArrayList<>();
        vehicleTypes.add("CAR");
        Branch B1 = new Branch("B1", "B1", vehicleTypes);
        B1.addVehicle("CAR", "V1", 500);
        B1.addVehicle("CAR", "V2", 1000);
        List<String> availableVehicles = branchService.getAvailableVehicles(B1, 4, 6);
        String[] availableVehiclesArr = availableVehicles.toArray(new String[0]);
        List<String> expectedAvailableVehicles = new ArrayList<>();
        expectedAvailableVehicles.add("V2");
        String[] expectedAvailableVehiclesArr = expectedAvailableVehicles.toArray(new String[0]);
        System.out.println(availableVehicles);
        Assert.assertArrayEquals(availableVehiclesArr, expectedAvailableVehiclesArr);
    }
}