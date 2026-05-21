//main.java file
package com.busguidance;


public class App 
{
    public static void main( String[] args )
    {   
        
        DriverRepository driverRepo = new DriverRepository();
        BusRepository busRepo = new BusRepository(); 

        //creating driver
        Driver d1 = new Driver(
                "45@#abCDXY",
                "John Smith",
                8,
                "Heavy",
                "12|King St|Melbourne|VIC|Australia",
                "15-08-1985"
        );
    
        //create bus 
        Bus b1 = new Bus(
            "12345678",
            45,
            90.5,
            "Hybrid"
        );

       

        //add driver
        boolean driverAdded = driverRepo.addDriver(d1);
        //add bus
        boolean busAdded = busRepo.addBus(b1);

        System.out.println("Driver added: " + driverAdded);
        System.out.println("Bus added: " + busAdded);

        //check compatibility
        boolean allowed = BusValidator.canDriverUseBus(d1, b1);
        System.out.println("Diver allowed to drive bus: " + allowed);

        //count 
        System.out.println("Driver count: " + driverRepo.countDrivers());
        System.out.println("Bus count: " + busRepo.countBuses());

        //retreiving driver
        Driver driverRetreived = driverRepo.retreiveDriver(d1.getDriverID()); 
        System.out.println("Retreived Driver Info: " + driverRetreived);
        
        //retreiving bus
        Bus busRetreived = busRepo.retrieveBus(b1.getBusID()); 
        System.out.println("Retreived Bus Info: " + busRetreived);


    }
}
