package com.busguidance;
//bus object
public class Bus {
    //declaring attributes
    private String busID; //id of the bus 
    private int capacity ;  //capacity of the bus 
    private double fuelLevel;   
    private String fuelType;    //desel, petrol, electric, hybrid etc.

    //declaring constructor 
    public Bus(String busID, int capacity, double fuelLevel, String fuelType) {
        this.busID = busID;
        this.capacity = capacity;
        this.fuelLevel = fuelLevel; 
        this.fuelType = fuelType; 
    }

    //getters
    public String getBusID() {
        return busID;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public String getFuelType() {
        return fuelType; 
    }
    //setters
    public void setCapacity(int capacity) {
        this.capacity = capacity; 
    }
    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel; 
    }
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType; 
    }

    //converting this object into a readable TXT formate for readability 
    @Override
    public String toString() {
        return busID + "," + capacity + "," + fuelLevel + "," + fuelType; 
        
    }

}
