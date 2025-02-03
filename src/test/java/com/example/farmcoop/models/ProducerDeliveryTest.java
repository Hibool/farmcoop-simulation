package com.example.farmcoop.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProducerDeliveryTest {

    @Test
    void testConstructor() {
        ProducerDelivery delivery = new ProducerDelivery(50, 1, WeekDay.MONDAY);
        assertEquals(50, delivery.getQuantity());
        assertEquals(1, delivery.getProducer());
        assertEquals(WeekDay.MONDAY, delivery.getWeekDay());
    }
}
