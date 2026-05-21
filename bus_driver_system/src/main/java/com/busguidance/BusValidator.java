//contains bus validation rule 
package com.busguidance;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class BusValidator {

    //B1 - validating bus ID
    public static boolean isValidBusID(String id) {
        //all 8 characters must be digit and contains exactly 8 
        return id.matches("\\d{8}");
    }

    //B3 - Driver age restriction 
    public static boolean canDriverUseBus(Driver driver, Bus bus){

        int age = calculateAge(driver.getBirthdate()); 

        //Drivers older than 50 cannot drive buses with capacity >=50 
        if (age > 50 && bus.getCapacity() >= 50){
            return false;
        }

        //B4 - Electric buses require 5 years experience 
        if (bus.getFuelType().equalsIgnoreCase("Electricity")) {

            if(driver.getExperienceYears() < 5) {
                return false; 
            }
        }

        //B5- Electric and Hybrid require Heavy/Public transport 
        if(bus.getFuelType().equalsIgnoreCase("Electricity") || bus.getFuelType().equalsIgnoreCase("Hybrid")) {

            String license = driver.getLicenseType();

            if(!(license.equalsIgnoreCase("Heavy") || license.equalsIgnoreCase("Public Transport"))){
                return false; 
            }
        }

        return true; 

    }

    //helper method to calculate age 
    public static int calculateAge(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //changing localDate into dd-MM-yyyy format. 
        LocalDate dob = LocalDate.parse(birthdate, formatter); 

        //gives difference between the bus driver birthdate and current local date to get the Age in years. 
        return Period.between(dob, LocalDate.now()).getYears(); 
    }


    
}
