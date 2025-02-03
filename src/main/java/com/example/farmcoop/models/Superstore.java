package com.example.farmcoop.models;

import lombok.Data;

@Data
public class Superstore {
    private static int idCounter = 0;

    private final int id;  // Unique ID for each superstore
    private String name;  // Name of the superstore
    private String city;  // City where the superstore is located
    private int deliveryTime;  // Round-trip delivery time (in minutes) from the warehouse

    public Superstore(String name, String city, int deliveryTime) {
        this.id = idCounter++; // Incremental ID for each new superstore
        this.name = name;
        this.city = city;
        this.deliveryTime = deliveryTime;
    }
}
