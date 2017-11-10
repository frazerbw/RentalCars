package com.frazerbw;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        int choice = -1;

        if (args.length > 0) {
            try {
                choice = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("program argument must be a number from 1 - 4 (the task to return results for)");
            }
        } else {
            System.out.println("must supply the program an argument as a number from 1 - 4 (the task to return results for)");
        }

        try {
            String filename = "vehicles.json";

            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(filename));
            JsonVehicleFile vehicleData = gson.fromJson(reader, JsonVehicleFile.class);

            if (choice == 1) {
                // Print a list of all the cars, in ascending price order
                Collections.sort(vehicleData.Search.VehicleList,
                        (o1, o2) -> Double.compare(o1.price, o2.price));

                for (Vehicle vehicle : vehicleData.Search.VehicleList) {
                    System.out.print(vehicle.name + " - " + vehicle.getPrice() + "\n");
                }
            }
            else if (choice == 2) {
                // Print a list of the specifications for each car
                for (Vehicle vehicle : vehicleData.Search.VehicleList) {
                    System.out.println(vehicle.name + " - " + vehicle.getFullSippSpec());
                }
            }
            else if (choice == 3) {
                // Print out the highest rated supplier per car type, descending order
                Collections.sort(vehicleData.Search.VehicleList,
                        (o1, o2) -> Double.compare(o1.rating, o2.rating));

                Collections.sort(vehicleData.Search.VehicleList,
                        (o1, o2) -> o1.getCarType().compareTo(o2.getCarType()));

                Vehicle highestRatedVehicle = null;

                for (Vehicle vehicle : vehicleData.Search.VehicleList) {
                    if (highestRatedVehicle == null) {
                        highestRatedVehicle = vehicle;
                    }

                    if (!highestRatedVehicle.getCarType().equals(vehicle.getCarType())) {
                        System.out.println(vehicle.name + " - " + vehicle.getCarType() + " - " +
                                vehicle.supplier + " - " + vehicle.rating);
                    }

                    highestRatedVehicle = vehicle;


                }
            }
            else if (choice == 4) {
                // Print out a list of vehicles, ordered by the sum of the scores in descending order
                /* Manual transmission – 1 point
                   Automatic transmission – 5 points
                   Air conditioned – 2 points

                   {Vehicle name} – {Vehicle score} – {Supplier rating} – {Sum of scores}
                */

                Collections.sort(vehicleData.Search.VehicleList,
                        (o1, o2) -> Double.compare(o1.getScore() + o1.rating, o2.getScore() + o2.rating));

                for (Vehicle vehicle : vehicleData.Search.VehicleList) {
                    System.out.println(vehicle.name + " - " + vehicle.getScore() + " - " +
                            vehicle.rating + " - " + (vehicle.getScore() + vehicle.rating));
                }
            }
            else {
                System.out.println("ERROR: Task ID does not exist");
            }

        }
        catch (IOException e) {
            System.out.println("File missing, please try a different path");
        }
    }
}
