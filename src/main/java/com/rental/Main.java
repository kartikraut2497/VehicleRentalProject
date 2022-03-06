package com.rental;

import com.rental.services.RentalService;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

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
