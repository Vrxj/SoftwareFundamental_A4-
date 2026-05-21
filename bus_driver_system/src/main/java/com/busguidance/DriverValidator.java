//contrains all bus validation rules
package com.busguidance;
import java.util.regex.Pattern;

public class DriverValidator {

    //Implementing D1 - Validating Driver ID 
    public static boolean isValidDriverID(String id) {
        
        //muat be exactly 10 characters
        if(id.length() != 10 ){
            return false;
        }

        //first 2 characters must be digits between 2 and 9
        if(!id.substring(0, 2).matches("[2-9]{2}")){
            return false;
        }

        //last 2 characters uppercase letters
        if(!id.substring(8).matches("[A-Z]{2}")) {
            return false; 
        }

        //There must be at least two special characters between characters 3-8 
        int specialCount = 0; 
        for(int i=2 ; i < 8; ++i) {

            char c = id.charAt(i);
            //anything but not letter of digit counts as special char 
            if(!Character.isLetterOrDigit(c)) {
                ++specialCount;
            }
        }
        return specialCount >=2; 

    }

    //D2 - Validating address format
    public static boolean isValidAddress(String address) {
        //must contain this format : street Number|Street Name|City|State|Country
        String[] parts = address.split("\\|");

        return parts.length == 5; 
    }

    //D3 - validating birthdate
    public static boolean isValidBirthdate(String birthdate) {

        //DD-MM-YYYY
        return Pattern.matches("\\d{2}-\\d{2}-\\d{4}", birthdate);
    }

    
}
