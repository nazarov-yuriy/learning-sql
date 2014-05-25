package ru.usb7.db.pg_test;

import java.util.Random;

/**
 * Created by firefish on 5/25/14.
 */
public class Vehicle {
    public String registrationNumber;

    public static Vehicle randomVehicle(){
        Vehicle vehicle = new Vehicle();
        Random rnd = new Random();
        char c1 = (char) ('A' + rnd.nextInt(26));
        char c2 = (char) ('A' + rnd.nextInt(26));
        char c3 = (char) ('A' + rnd.nextInt(26));
        vehicle.registrationNumber = ""+c1+c2+c3+"-"+(1000+rnd.nextInt(9000));
        return vehicle;
    }
}
