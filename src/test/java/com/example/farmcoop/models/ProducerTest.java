package com.example.farmcoop.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProducerTest {

    private Producer producer;

    @BeforeEach
    void setUp() {
        producer = new Producer(3, 100); // 3 deliveries per week, max delivery quantity of 100 goods
    }

    @Test
    void testConstructor() {
        assertEquals(3, producer.getDeliveryFrequencyPerWeek());
        assertEquals(100, producer.getMaxDeliveryQuantity());
    }

    @Test
    void testDeliver() {
        int quantity = producer.deliver();
        assertTrue(quantity >= 0 && quantity <= producer.getMaxDeliveryQuantity());
    }

    @Test
    void testStartWeek() {
        producer.startWeek();
        assertNotNull(producer.getDeliveryDays());
        assertEquals(3, producer.getDeliveryDays().size());
    }
}
