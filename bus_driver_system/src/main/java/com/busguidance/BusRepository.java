package com.busguidance;
import java.io.*;
import java.util.ArrayList;

public class BusRepository {
    //declaring array list of type Bus
    private ArrayList<Bus> buses = new ArrayList<>();

    private final String FILE_NAME = "buses.txt";

     //constructor loads buses from file 
    public BusRepository(){
        loadBusesFromFile(); 
    }

    //Add Bus
    public boolean addBus(Bus bus) {

        //validate bus ID
        if(!BusValidator.isValidBusID(bus.getBusID())){
            return false;
        }

        //checking duplicate ID for bus - B1
        //loop to check id against all bus in buses arrayList 
        for (Bus b : buses){
            if(b.getBusID().equals(bus.getBusID())){
                return false; 
            }
            
        }
        //adding to arrayList
        buses.add(bus);
        
        saveBusesToFile();

        return true; 
    }

    //update bus 
    public boolean updateBus(String id, Bus updatedBus){
        
        Bus existingBus = retrieveBus(id);

        //if no bus return false
        if(existingBus == null){
            return false; 
        }

        //B2 - capacity cannot increase
        if(updatedBus.getCapacity() > existingBus.getCapacity()){
            return false; 
        }
        //setting the updated bus attribute values to the existing bus
        existingBus.setCapacity(updatedBus.getCapacity());
        existingBus.setFuelLevel(updatedBus.getFuelLevel());
        existingBus.setFuelType(updatedBus.getFuelType());

        //saving 
        saveBusesToFile();

        return true; 

    }

    //retreive bus
    public Bus retrieveBus(String id){
        //loop to check id against all bus in buses arrayList 
        for(Bus b : buses){
            
            if(b.getBusID().equals(id)){
                return b; 
            }
        }
         //else return null meaning not found
        return null; 
    }

    //count buses
    public int countBuses() {
        return buses.size();
    }

    //save to txt file 
    private void saveBusesToFile() {
        
        try {

            //writing it for every driver in drivers
            PrintWriter writer = new PrintWriter(FILE_NAME);

            for (Bus b : buses){
                writer.println(b);
            }

            writer.close();
        } catch(Exception e) {

            System.out.println("Error saving. buses.");
        }
    }

    //load buses from txt file
    private void loadBusesFromFile() {

        try{
            File file = new File(FILE_NAME);

            if(!file.exists()){
                return;
            }
            //if file doesnt exist return 
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            //looping/reading while line is not null
            while((line = reader.readLine()) != null) {
                //spliting data based on commas
                String [] data = line.split(",");

                //creating a driver object with the data
                Bus bus = new Bus (
                    data[0],
                    Integer.parseInt(data[1]),
                    Double.parseDouble(data[2]),
                    data[3]
                );

                buses.add(bus);
            }
            
            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading buses.");
        }
    }
}
