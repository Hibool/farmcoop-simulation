package com.example.farmcoop.models;

import lombok.Data;

@Data
public class Truck {
    private static int idCounter = 0;

    private final int id;  // Unique ID for each truck
    private final int minCapacity;  // Minimum capacity the truck can carry
    private final int maxCapacity;  // Maximum capacity the truck can carry
    private int destination;  // Destination superstore ID (0 means the truck is in the warehouse)
    private int deliveryTimeRemaining;  // Time remaining for the truck to complete its delivery (in minutes)

    public Truck(int minCapacity, int maxCapacity) {
        this.id = ++idCounter;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
    }

    // Start the delivery to a given superstore
    public void startDelivery(Superstore superstore) {
        this.destination = superstore.getId();
        this.deliveryTimeRemaining = superstore.getDeliveryTime();
    }

    // End the delivery (reset truck to warehouse)
    public void endDelivery() {
        this.destination = 0;
        this.deliveryTimeRemaining = 0;
    }

    // Update the remaining delivery time for the truck (decreases by 1 minute every cycle)
    public void updateDeliveryTime() {
        if (this.deliveryTimeRemaining > 0) {
            this.deliveryTimeRemaining--;
        } else {
            endDelivery();
        }
    }

    // Check if the truck is in the warehouse (destination is 0)
    public boolean isInWarehouse() {
        return this.destination == 0;
    }
}
