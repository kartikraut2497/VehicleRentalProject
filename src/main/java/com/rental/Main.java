package com.rental;

import com.rental.services.RentalService;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

//    public static void main(String[] args) {
//        List<String> vehicleTypes = List.of("CAR", "BUS");
//
//        RentalService rentalService = new RentalService();
//        boolean branch1 = rentalService.addBranch("B1", vehicleTypes);
//        System.out.println("Branch1 = " + branch1);
//
//        boolean add1 = rentalService.addVehicle("B1", "CAR", "V1", 5000);
//        System.out.println("Add1 = " + add1);
//        boolean add2 = rentalService.addVehicle("B1", "CAR", "V2", 1000);
//        System.out.println("Add2 = " + add2);
//        boolean add3 = rentalService.addVehicle("B1", "CAR", "V3", 2000);
//        System.out.println("Add3 = " + add3);
//        boolean add4 = rentalService.addVehicle("B1", "BUS", "V4", 500);
//        System.out.println("Add4 = " + add4);
//        boolean add5 = rentalService.addVehicle("B1", "VAN", "V5", 500);
//        System.out.println("Add5 = " + add5);
//        boolean add6 = rentalService.addVehicle("B2", "BUS", "V6", 500);
//        System.out.println("Add6 = " + add6);
//        boolean add7 = rentalService.addVehicle("B1", "CAR", "V4", 5500);
//        System.out.println("Add7 = " + add7);
//
//        int booking1 = rentalService.bookVehicle("B1", "VAN", 1, 5);
//        int booking2 = rentalService.bookVehicle("B1", "CAR",1, 3);
//        int booking3 = rentalService.bookVehicle("B1", "CAR", 2, 4);
//        int booking4 = rentalService.bookVehicle("B1", "CAR", 3, 4);
//
//        System.out.println("Booking1 = " + booking1);
//        System.out.println("Booking2 = " + booking2);
//        System.out.println("Booking3 = " + booking3);
//        System.out.println("Booking4 = " + booking4);
//
//        rentalService.displayVehicles("B1", 1, 5);
//    }

    public static void main(String[] args){

        RentalService rentalService = new RentalService();
        System.out.println("Enter file path:");
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();

        //Read File
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                String[] commandsArr = line.split("\\s+");
                if(commandsArr.length >= 3) {
                    String command = commandsArr[0];
                    String branchId = commandsArr[1];

                    if (Constants.ADD_BRANCH.equalsIgnoreCase(command)) {
                        String vehicleTypes = commandsArr[2];
                        List<String> vehicleTypesList = Arrays.asList(vehicleTypes.split(","));
                        boolean addBranch = rentalService.addBranch(branchId, vehicleTypesList);
                        System.out.println("ADD BRANCH: " + addBranch);
                    } else if (Constants.ADD_VEHICLE.equalsIgnoreCase(command)) {
                        String vehicleType = commandsArr[2];
                        String vehicleId = commandsArr[3];
                        int price = Integer.valueOf(commandsArr[4]);

                        boolean addVehicle = rentalService.addVehicle(branchId, vehicleType, vehicleId, price);
                        System.out.println("ADD VEHICLE: " + addVehicle);
                    } else if (Constants.BOOK.equalsIgnoreCase(command)) {
                        String vehicleType = commandsArr[2];
                        int start = Integer.valueOf(commandsArr[3]);
                        int end = Integer.valueOf(commandsArr[4]);

                        int bookingPrice = rentalService.bookVehicle(branchId, vehicleType, start, end);
                        System.out.println("BOOK: " + bookingPrice);
                    } else if (Constants.DISPLAY_VEHICLES.equalsIgnoreCase(command)) {
                        int start = Integer.valueOf(commandsArr[2]);
                        int end = Integer.valueOf(commandsArr[3]);

                        rentalService.displayVehicles(branchId, start, end);
                    } else {
                        System.out.println("Please Enter Correct Statements");
                    }
                }
                else{
                    System.out.println("Please Enter Correct Statements");
                }
            }
            fr.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
