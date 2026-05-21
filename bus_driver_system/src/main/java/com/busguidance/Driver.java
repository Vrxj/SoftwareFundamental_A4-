package com.busguidance;
//driver object 
public class Driver {
    
    //declaring attributes
    private String driverID; //id of the driver
    private String name; 
    private int experienceYears; //experience of the driver
    private String licenseType; //type of license the bus driver has 
    private String address;
    private String birthdate;

    //declaring constuctor
    public Driver(String driverID, String name, int experienceYears, String licenseType, String address , String birthdate) {
        this.driverID = driverID; 
        this.name = name; 
        this.experienceYears = experienceYears;
        this.licenseType = licenseType;
        this.address = address;
        this.birthdate = birthdate; 
    }

    //getters
    public String getDriverID() {
        return driverID;
    }

    public String getName() {
        return name; 
    }

    public int getExperienceYears() {
        return experienceYears; 

    }

    public String getLicenseType() {
        return licenseType;
    }
    
    public String getAddress() {
        return address;
    }

    public String getBirthdate(){
        return birthdate;
    }

    //setters 
    //as per assignment driverID and name is immutable hence didnt include (D5)
    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears; 
    }

    public void setAddress(String address) {
        this.address = address; 
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate; 
    }
    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }


    //converting this object into a readable TXT formate for readability 
    @Override
    public String toString() {
        return driverID + "," + name + "," + experienceYears + "," + licenseType + "," 
        + address + "," + birthdate;
     }
}
