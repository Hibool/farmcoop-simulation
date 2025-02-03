package com.example.farmcoop.models;

import com.example.farmcoop.utils.LoggerUtil;
import lombok.Data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

@Data
public class Warehouse {
    private int capacity;  // Maximum capacity of the warehouse
    private int currentStock;  // Current stock level in the warehouse
    private Queue<Truck> availableTrucks;  // List of trucks currently available for deliveries
    private Queue<Truck> unavailableTrucks;  // List of trucks currently delivering goods
    private List<Producer> producers;  // List of producers who supply the warehouse
    private Queue<ProducerDelivery> producerDeliveries;  // Queue of incoming producer deliveries
    private List<Superstore> clients;  // List of superstores that are clients of the warehouse

    public Warehouse(int capacity) {
        this.capacity = capacity;
        this.availableTrucks = new LinkedList<>();
        this.unavailableTrucks = new LinkedList<>();
        this.producerDeliveries = new LinkedList<>();
    }

    // Retrieve an available truck for delivery (or null if none available)
    public Truck getAvailableTruck() {
        return availableTrucks.peek();
    }

    // Update the status of trucks (reduce delivery time or return to the warehouse)
    public void updateTruckStatus() {
        Iterator<Truck> iterator = unavailableTrucks.iterator();
        while (iterator.hasNext()) {
            Truck truck = iterator.next();
            truck.updateDeliveryTime(); // Update delivery time
            if (truck.isInWarehouse()) {
                iterator.remove(); // If the truck is back, remove it from "unavailable"
                availableTrucks.add(truck); // Add it to "available" list
                LoggerUtil.info("Truck " + truck.getId() + " came back.");
            }
        }
    }

    // Add a producer's delivery to the warehouse's delivery queue
    public void addProducerDeliveryToQueue(ProducerDelivery producerDelivery) {
        producerDeliveries.add(producerDelivery);
    }

    // Retrieve the next producer delivery in the queue (or null if none available)
    public ProducerDelivery getNextProducerDelivery() {
        return producerDeliveries.peek();
    }

    // Process sending goods from the warehouse to a client (superstore)
    public void sendGoods(Truck truck) {
        if (currentStock >= truck.getMinCapacity()) {  // Check if there’s enough stock to fill the truck
            int quantityToDeliver = Math.min(this.currentStock, truck.getMaxCapacity());  // Determine the quantity to deliver
            currentStock -= quantityToDeliver;  // Reduce stock level
            Superstore client = clients.get(new Random().nextInt(clients.size()));  // Randomly choose a client
            truck.startDelivery(client);  // Start the delivery to the chosen client
            availableTrucks.remove(truck);  // Remove truck from available list
            unavailableTrucks.add(truck);  // Add it to unavailable list
            LoggerUtil.info("[SEND] Truck " + truck.getId() + " started delivery to client " + client.getName() + " in " + client.getCity() + ". (goods=" + quantityToDeliver + " / delivery time=" + client.getDeliveryTime() + " minutes)");
        }
    }

    // Store a delivery in the warehouse, updating stock levels
    public void storeGoods(ProducerDelivery producerDelivery) {
        if (currentStock + producerDelivery.getQuantity() <= capacity) {  // Check if there's enough space in the warehouse
            currentStock += producerDelivery.getQuantity();  // Add the goods to the stock
            producerDeliveries.remove(producerDelivery);  // Remove from the delivery queue
            LoggerUtil.info("[STORE] Delivery from producer " + producerDelivery.getProducer() + " has been added to the warehouse. (goods=" + producerDelivery.getQuantity() + ")");
        }
    }
}
