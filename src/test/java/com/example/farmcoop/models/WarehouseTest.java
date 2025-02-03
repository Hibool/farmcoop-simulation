package com.example.farmcoop.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private Warehouse warehouse;
    private Truck truck;
    private ProducerDelivery delivery;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse(500);
        truck = new Truck(50, 100);
        delivery = new ProducerDelivery(100, 1, WeekDay.MONDAY);
    }

    @Test
    void testStoreGoods() {
        warehouse.storeGoods(delivery);
        assertEquals(100, warehouse.getCurrentStock());
    }

    @Test
    void testSendGoods() {
        warehouse.setCurrentStock(200);
        warehouse.sendGoods(truck);
        assertEquals(100, warehouse.getCurrentStock());
        assertTrue(truck.getDestination() != 0);
    }
}
