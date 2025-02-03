package com.example.farmcoop.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TruckTest {

    private Truck truck;
    private Superstore superstore;

    @BeforeEach
    void setUp() {
        truck = new Truck(50, 100);
        superstore = new Superstore("Carrefour", "Paris", 1000);
    }

    @Test
    void testConstructor() {
        assertEquals(50, truck.getMinCapacity());
        assertEquals(100, truck.getMaxCapacity());
        assertTrue(truck.isInWarehouse()); // By default, the truck is in the warehouse
    }

    @Test
    void testStartDelivery() {
        truck.startDelivery(superstore);
        assertFalse(truck.isInWarehouse());
        assertEquals(superstore.getId(), truck.getDestination());
        assertEquals(superstore.getDeliveryTime(), truck.getDeliveryTimeRemaining());
    }

    @Test
    void testEndDelivery() {
        truck.startDelivery(superstore);
        truck.endDelivery();
        assertTrue(truck.isInWarehouse());
        assertEquals(0, truck.getDeliveryTimeRemaining());
    }

    @Test
    void testUpdateDeliveryTime() {
        truck.startDelivery(superstore);
        truck.updateDeliveryTime();
        assertEquals(superstore.getDeliveryTime() - 1, truck.getDeliveryTimeRemaining());
    }
}
