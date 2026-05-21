//will store and manage drivers 
package com.busguidance;
import java.io.*;
import java.util.ArrayList;

public class DriverRepository {
    //declaring array list of type Driver
    private ArrayList<Driver> drivers =  new ArrayList<>(); 

    private final String FILE_NAME = "drivers.txt"; 

    //constructor loads drivers from file 
    public DriverRepository() {
        loadDriversFromFile();
    }

    //Add Driver
    public boolean addDriver(Driver driver) {

        //validating driver ID
        if(!DriverValidator.isValidDriverID(driver.getDriverID())){
            return false; 
        }

        //checking for duplicate IDs - D1 
        //loop to check id against all driver in drivers arrayList 
        for(Driver d: drivers) {
            if(d.getDriverID().equals(driver.getDriverID())){
                return false; 
            }
        }

        //validate address
        if(!DriverValidator.isValidAddress(driver.getAddress())){
            return false;
        }

        //validate birthdate 
        if(!DriverValidator.isValidBirthdate(driver.getBirthdate())){
            return false;
        }

        //adding driver to drivers arrayList
        drivers.add(driver);

        //saving to file
        saveDriversToFile(); 

        return true; 

    }

    //update driver 
    public boolean updateDriver(String id, Driver updatedDriver){

        Driver existingDriver = retreiveDriver(id);

        //D4 - license cannot change if experience > 10
        if(existingDriver.getExperienceYears() > 10){
            if(!existingDriver.getLicenseType().equals(updatedDriver.getLicenseType())){
                return false; 
            }
        }

        //setting the updated driver attribute values to the existing driver
        existingDriver.setExperienceYears(updatedDriver.getExperienceYears());
        existingDriver.setLicenseType(updatedDriver.getLicenseType());
        existingDriver.setAddress(updatedDriver.getAddress());
        existingDriver.setBirthdate(updatedDriver.getBirthdate());

        //saving
        saveDriversToFile();

        return true;
    }

    //Retrieve driver
    public Driver retreiveDriver(String id){

        //loop to check id against all driver in drivers arrayList 
        for(Driver d: drivers){
            if(d.getDriverID().equals(id)){
                return d; 
            }
        }

        //else return null meaning not found
        return null; 
    }

    //count drivers
    public int countDrivers() {
        return drivers.size(); 
    }

    //save to txt file 
    private void saveDriversToFile() {
        try{
            PrintWriter writer = new PrintWriter(FILE_NAME);

            //writing it for every driver in drivers
            for(Driver d : drivers){
                writer.println(d);
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving drivers.");
        }
    }

    //Load from txt file
    private void loadDriversFromFile(){
        try{
            File file = new File(FILE_NAME);

            //if file doesnt exist return 
            if(!file.exists()){
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            
            //looping/readding while line is not null
            while((line = reader.readLine()) != null){
                //spliting data based on commas
                String[] data = line.split(",");

                //creating a driver object with the data
                Driver driver = new Driver(
                    data[0],
                    data[1],
                    Integer.parseInt(data[2]),
                    data[3],
                    data[4],
                    data[5]

                );
                drivers.add(driver);

                
            } 
            
            reader.close();
            
            } catch (Exception e) {
                System.out.println("Error loading drivers.");

            }
    }

    
}
