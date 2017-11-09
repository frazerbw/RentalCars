package com.frazerbw.tests;

import static org.junit.Assert.assertEquals;

import com.frazerbw.Vehicle;
import org.junit.Test;

public class VehicleTest {
    @Test
    public void testFullSippSpecOutput() {
        Vehicle vehicle = new Vehicle("Test Car", 100.0f, "Test Supplier", "ECMR", 10.0f);
        assertEquals("Economy - 4 doors - Manual - Petrol/AC", vehicle.getFullSippSpec());
    }

    @Test
    public void testManualAirConditionedScore() {
        Vehicle vehicle = new Vehicle("Test Car", 200.0f, "Test Supplier", "ECMR", 10.0f);
        assertEquals(vehicle.getScore(), 3.0f, 0.0f);
    }

    @Test
    public void testAutomaticAirConditionedScore() {
        Vehicle vehicle = new Vehicle("Test Car", 200.0f, "Test Supplier", "ECAR", 10.0f);
        assertEquals(vehicle.getScore(), 7.0f, 0.0f);
    }
}