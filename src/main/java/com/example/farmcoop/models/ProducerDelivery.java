package com.example.farmcoop.models;

import lombok.Data;

@Data
public class ProducerDelivery {
    private static int idCounter = 0;

    private final int id;  // Unique ID for each delivery
    private final int quantity;  // Quantity of goods delivered
    private final int producer;  // ID of the producer making the delivery
    private final WeekDay weekDay;  // Day of the week when the delivery occurs

    public ProducerDelivery(int quantity, int producer, WeekDay weekDay) {
        this.id = idCounter++; // Incremental ID for each new delivery
        this.quantity = quantity;
        this.producer = producer;
        this.weekDay = weekDay;
    }
}
