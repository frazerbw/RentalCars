package com.frazerbw;

import java.util.ArrayList;
import java.util.HashMap;

class JsonVehicleFile
{
    Search Search;
}

class Search
{
    ArrayList<Vehicle> VehicleList;
}

/**
 * Used to access car data read in from JSON file
 */
public class Vehicle {

    public String name;
    public double price;
    public String supplier;
    public String sipp;
    public double rating;
    private double score = -1;

    public HashMap<Character, String[]> sippMap;

    public Vehicle() {
        generateSippMap();
    }

    public Vehicle(String name, double price, String supplier, String sipp, double rating) {
        this.name = name;
        this.price = price;
        this.supplier = supplier;
        this.sipp = sipp;
        this.rating = rating;

        generateSippMap();
    }

    public void generateSippMap() {
        sippMap = new HashMap<>();
        sippMap.put('A', new String[] {"", "", "Automatic", ""});
        sippMap.put('B', new String[] {"", "2 doors", "", ""});
        sippMap.put('C', new String[] {"Compact", "4 doors", "", ""});
        sippMap.put('D', new String[] {"", "5 doors", "", ""});
        sippMap.put('E', new String[] {"Economy", "", "", ""});
        sippMap.put('F', new String[] {"Full size", "SUV", "", ""});
        sippMap.put('I', new String[] {"Intermediate", "", "", ""});
        sippMap.put('L', new String[] {"Luxury", "", "", ""});
        sippMap.put('M', new String[] {"Mini", "", "Manual", ""});
        sippMap.put('N', new String[] {"", "", "", "Petrol/no AC"});
        sippMap.put('P', new String[] {"Premium", "", "", ""});
        sippMap.put('R', new String[] {"", "", "", "Petrol/AC"});
        sippMap.put('S', new String[] {"Standard", "", "", ""});
        sippMap.put('V', new String[] {"", "Passenger Van", "", ""});
        sippMap.put('W', new String[] {"", "Estate", "", ""});
        sippMap.put('X', new String[] {"Special", "", "", ""});
    }

    public String getPrice() {
        return "" + price;
    }

    public String getCarType() {
        return sippMap.get(sipp.charAt(0))[0];
    }

    public double getScore() {
        if (score == -1) {
            calculateScore();
        }

        return score;
    }

    private void calculateScore() {
        /* Manual transmission – 1 point
           Automatic transmission – 5 points
           Air conditioned – 2 points
        */

        score = 0;

        if (isAirConditioned()) {
            score += 2;
        }

        if (isAutomatic()) {
            score += 5;
        } else {
            score += 1;
        }
    }

    public boolean isAutomatic() {
        return sippMap.get(sipp.charAt(2))[2].equals("Automatic");
    }

    public boolean isAirConditioned() {
        return sippMap.get(sipp.charAt(3))[3].equals("Petrol/AC");
    }


    public String getFullSippSpec() {
        return sippMap.get(sipp.charAt(0))[0] + " - " +
                sippMap.get(sipp.charAt(1))[1] + " - " +
                sippMap.get(sipp.charAt(2))[2] + " - " +
                sippMap.get(sipp.charAt(3))[3];
    }
}
