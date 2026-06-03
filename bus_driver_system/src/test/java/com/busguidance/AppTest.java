package com.busguidance;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

/**
* Unit test for simple App.
*/
public class AppTest
{
   @BeforeEach //delete the older files
   void cleanUp(){
       new File("drivers.txt").delete();
       new File("buses.txt").delete();

   }
  /**
   * Rigorous Test :-)
   */
  /**
   D1 test
   */
  @Test
   void D1_validDriverID(){
      assertTrue(DriverValidator.isValidDriverID("45@#abCDXY"));
  }


  @Test
   void D1_invalidDriverID(){
      assertFalse(DriverValidator.isValidDriverID("45@#abCD"));


   }


   @Test
   void D1_noSpecialChars(){
      assertFalse(DriverValidator.isValidDriverID("45abCDXY"));


   }


   @Test
   void D2_verifyAddress(){
      assertTrue(DriverValidator.isValidAddress("12|King St|Melbourne|VIC|Australia"));


   }


   @Test
   void D2_incorrectAddress(){
      assertFalse(DriverValidator.isValidAddress("12 King St Melbourne VIC Australia"));
   }
 
   @Test
   void D2_missingVal(){
      assertFalse(DriverValidator.isValidAddress("12|King St|Melbourne |Australia"));


   }


   @Test
   void D3_verifyBirthday(){
      assertTrue(DriverValidator.isValidBirthdate("15-08-1985"));


   }


   @Test
   void D3_incorrectBD(){
      assertFalse(DriverValidator.isValidBirthdate("15/08/1985"));


   }


   @Test
   void D3_checkNewinput(){
      assertTrue(DriverValidator.isValidBirthdate("07-09-1980"));


   }


   @Test
   void D4_updatedLicense(){
      DriverRepository repo = new DriverRepository();
      Driver driver = new Driver("45@#abCDXY", "Alice Brown", 5, "Medium", "12|King St|Melbourne|VIC|Australia" , "15-08-1985");
      repo.addDriver(driver);
      Driver updated = new Driver("45@#abCDXY", "Alice Brown", 5, "Heavy", "12|King St|Melbourne|VIC|Australia" , "15-08-1985");
      assertTrue(repo.updateDriver("45@#abCDXY", updated));
   }


   @Test
   void D4_updatedType(){
      DriverRepository repo = new DriverRepository();
      Driver driver = new Driver("45@#abCDXY", "Alice Brown", 5, "Heavy", "12|King St|Melbourne|VIC|Australia" , "15-08-1985");
      repo.addDriver(driver);
      Driver updated = new Driver("45@#abCDXY", "Alice Brown", 5, "PublicTransport", "12|King St|Melbourne|VIC|Australia" , "15-08-1985");
      assertTrue(repo.updateDriver("45@#abCDXY", updated));
   }


   @Test
   void D4_invalidUpdateLicense(){


      DriverRepository repo = new DriverRepository();
      Driver driver = new Driver("45@#abCDXY", "Alice Brown", 14, "Medium", "12|King St|Melbourne|VIC|Australia" , "15-08-1985");
      repo.addDriver(driver);
      Driver updated = new Driver("45@#abCDXY", "Alice Brown", 14, "Heavy", "12|King St|Melbourne|VIC|Australia" , "15-08-1985");
      assertFalse(repo.updateDriver("45@#abCDXY", updated));
   }


   @Test
   void D5_DriverIDModified(){
      boolean hasSetID = false;
      String[] methodName = new String[Driver.class.getMethods().length];
      for(int i=0;i < methodName.length;i++){
          methodName[i] = Driver.class.getMethods()[i].getName();
          if(methodName[i].equals("setDriverID")){
              hasSetID = true;
              break;
          }
      }
      assertFalse(hasSetID);


   }
    @Test
   void D5_DriverNameModified(){
      boolean hasSetName = false;
      String[] methodName = new String[Driver.class.getMethods().length];
      for(int i=0;i < methodName.length;i++){
          methodName[i] = Driver.class.getMethods()[i].getName();
          if(methodName[i].equals("setName")){
              hasSetName = true;
              break;
          }
      }
      assertFalse(hasSetName);
  }


  @Test
  void D5_addressUpdate(){
      DriverRepository repo =new DriverRepository();
      Driver driver = new Driver ("45@#abCDXY", "Dave Green",  5, "Heavy", "12|King St|Melbourne|VIC|Australia", "15-08-1985");
      repo.addDriver(driver);
      Driver update = new Driver ("45@#abCDXY", "Dave Green",  5, "Heavy", "15|King St|Melbourne|VIC|Australia", "15-08-1985");
      assertTrue(repo.updateDriver("45@#abCDXY", update));
    
  }






   @Test
   void B1_verifyBusID(){
      assertTrue(BusValidator.isValidBusID("12345678"));
   }


   @Test
   void B1_duplicateBusID(){
      BusRepository repo = new BusRepository();
      Bus bus1 = new Bus("12345678", 50, 80.0, "Diesel");
      Bus bus2 = new Bus("12345678", 40, 60.0, "Hybrid");
      assertTrue(repo.addBus(bus1));
      assertFalse(repo.addBus(bus2));
   }


   @Test
   void B1_invalidBusID(){
      assertFalse(BusValidator.isValidBusID("123AB678"));


   }


   @Test
   void B2_verifyUpdatedcapacity(){
      BusRepository repo = new BusRepository();
      repo.addBus(new Bus("12345678", 50, 80.0, "Diesel"));
      assertTrue(repo.updateBus("12345678",new Bus("12345678", 45, 80.0, "Diesel")));


   }


   @Test
   void B2_invalidUpdatedcapacity(){
      BusRepository repo = new BusRepository();
      repo.addBus(new Bus("12345678", 50, 80.0, "Diesel"));
      assertFalse(repo.updateBus("12345678",new Bus("12345678", 55, 80.0, "Diesel")));


   }


   @Test
   void B2_sameUpdatedcapacity(){
      BusRepository repo = new BusRepository();
      repo.addBus(new Bus("12345678", 50, 80.0, "Diesel"));
      assertTrue(repo.updateBus("12345678",new Bus("12345678", 50, 80.0, "Diesel")));


   }


   @Test
   void B3_checkDriverAge(){
      Driver driver = new Driver("45@#abCDXY", "Young Driver", 10, "Heavy", "1|Main St|Melbourne|VIC|Australia", "01-01-1977");
      Bus bus = new Bus("12345678", 50, 80.0,"Diesel");
      assertTrue(BusValidator.canDriverUseBus(driver, bus));


   }


   @Test
   void B3_ageOver50(){
      Driver driver = new Driver("45@#abCDXY", "Old Driver", 20, "Heavy", "1|Main St|Melbourne|VIC|Australia", "01-01-1970");
      Bus bus = new Bus("12345678", 50, 80.0,"Diesel");
      assertFalse(BusValidator.canDriverUseBus(driver, bus));


   }


   @Test
   void B3_over50DriverSmallBus(){
      Driver driver = new Driver("45@#abCDXY", "Old Driver", 20, "Heavy", "1|Main St|Melbourne|VIC|Australia", "01-01-1977");
      Bus bus = new Bus("12345678", 45, 80.0,"Diesel");
      assertTrue(BusValidator.canDriverUseBus(driver, bus));


   }


   @Test
   void B4_driverEV(){
      Driver driver = new Driver("45@#abCDXY", "Eco Driver", 5, "Heavy", "1|Main St|Melbourne|VIC|Australia", "01-01-1990");
      Bus bus = new Bus("12345678", 30, 100.0,"Electricity");
      assertTrue(BusValidator.canDriverUseBus(driver, bus));


   }


   @Test
   void B4_cannotDriveEV(){
      Driver driver = new Driver("45@#abCDXY", "New Driver", 4, "Heavy", "1|Main St|Melbourne|VIC|Australia", "01-01-1990");
      Bus bus = new Bus("12345678", 30, 100.0,"Electricity");
      assertFalse(BusValidator.canDriverUseBus(driver, bus));
   }


   @Test
   void B4_over5Years(){
      Driver driver = new Driver("45@#abCDXY", "Senior Driver", 9, "Heavy", "1|Main St|Melbourne|VIC|Australia", "01-01-1990");
      Bus bus = new Bus("12345678", 30, 100.0,"Electricity");
      assertTrue(BusValidator.canDriverUseBus(driver, bus));
   }


   @Test
   void B5_heavyLicenseDriveEV(){
      Driver driver = new Driver("45@#abCDXY", "Heavy Driver", 10, "Heavy", "1|Main St|Melbourne|VIC|Australia", "01-01-1985");
      Bus bus = new Bus("12345678", 30, 100.0,"Electricity");
      assertTrue(BusValidator.canDriverUseBus(driver, bus));


   }


   @Test
   void B5_mediumLicenseDriverEV(){
      Driver driver = new Driver("45@#abCDXY", "Medium Driver", 10, "Medium", "1|Main St|Melbourne|VIC|Australia", "01-01-1985");
      Bus bus = new Bus("12345678", 30, 80.0,"Hybrid");
      assertFalse(BusValidator.canDriverUseBus(driver, bus));
   }


   @Test
   void B5_ptLicenseDriverHybrid(){
      Driver driver = new Driver("45@#abCDXY", "PT Driver", 10, "Heavy", "1|Main St|Melbourne|VIC|Australia", "01-01-1985");
      Bus bus = new Bus("12345678", 30, 80.0,"Hybrid");
      assertTrue(BusValidator.canDriverUseBus(driver, bus));
   }


   //Driver integration Tests


   @Test
   void integration_ValidIDStored(){
      DriverRepository repo = new DriverRepository();
      Driver driver = new Driver("45@#abCDXY", "John Smith", 8, "Heavy", "12|King St|Melbourne|VIC|Australia", "15-08-1985");
      assertTrue(repo.addDriver(driver));
      Driver retrived = repo.retreiveDriver("45@#abCDXY");
      assertNotNull(retrived);
      assertEquals("45@#abCDXY", retrived.getDriverID());
      assertEquals("John Smith", retrived.getName());
      assertTrue(new File("drivers.txt").exists());


   }


   @Test
   void integration_InvalidDriver(){
      DriverRepository repo = new DriverRepository();
      Driver driver = new Driver("00@#abCDXY", "Bad Driver", 3, "Light", "12|King St|Melbourne|VIC|Australia", "01-01-1990");
      assertFalse(repo.addDriver(driver));
      assertEquals(0, repo.countDrivers());


   }


   @Test
   void integration_detailUpdates(){
      DriverRepository repo = new DriverRepository();
      Driver driver = new Driver("45@#abCDXY", "Jane Doe",  5, "Heavy", "12|King St|Melbourne|VIC|Australia", "15-08-1985");
      repo.addDriver(driver);
      repo.updateDriver("45@#abCDXY", new Driver("45@#abCDXY", "Jane Doe",  5, "Heavy", "15|King St|Melbourne|VIC|Australia", "15-08-1985"));
      DriverRepository newRepo = new DriverRepository();
      assertEquals("15|King St|Melbourne|VIC|Australia", newRepo.retreiveDriver("45@#abCDXY").getAddress());
   }


   @Test
   void integreation_driverCountUpdate(){
      DriverRepository repo = new DriverRepository();
      repo.addDriver(new Driver("45@#abCDXY", "Driver One",  3, "Light", "1|Street A|Sydney|NSW|Australia", "01-01-1990"));
      assertEquals(1, repo.countDrivers());
      repo.addDriver(new Driver("67@#abCDXY", "Driver Two",  5, "Medium", "2|Street B|Sydney|NSW|Australia", "02-02-1988"));
      assertEquals(2, repo.countDrivers());
      repo.addDriver(new Driver("89@#abCDXY", "Driver Three",  8, "Heavy", "2|Street c|Sydney|NSW|Australia", "03-03-1986"));
      assertEquals(3, repo.countDrivers());
    


    
   }


   //Bus integration


   @Test
   void integration_validBusStored(){
      BusRepository repo = new BusRepository();
      Bus bus = new Bus("12345678", 60, 90.0,"Diesel");
      assertTrue(repo.addBus(bus));
      assertEquals(1, repo.countBuses());
      assertEquals(60, repo.retrieveBus("12345678").getCapacity());
      assertTrue(new File("buses.txt").exists());


   }


   @Test
   void integration_invalidBusStored(){
      BusRepository repo = new BusRepository();
      Bus bus = new Bus("12345AVC", 48, 70.0,"Diesel");
      assertFalse(repo.addBus(bus));
      assertEquals(0, repo.countBuses());


   }


   @Test
   void integration_BusUpdates(){
      BusRepository repo = new BusRepository();
      repo.addBus(new Bus("12345675", 48, 80.0,"Diesel"));
      assertTrue(repo.updateBus("12345675", new Bus("12345675", 40, 80.0,"Diesel")));
      BusRepository Newrepo = new BusRepository();
      assertEquals(40, Newrepo.retrieveBus("12345675").getCapacity());




   }


   @Test
   void integration_BusCountUpdate(){
      BusRepository repo = new BusRepository();
      repo.addBus(new Bus("11111111", 30, 80.0, "Diesel"));
      assertEquals(1, repo.countBuses());
      repo.addBus(new Bus("22222222", 40, 70.0, "Hybrid"));
      assertEquals(2, repo.countBuses());
      repo.addBus(new Bus("33333333", 50, 90.0, "Electricity"));
      assertEquals(3, repo.countBuses());


   }








}
