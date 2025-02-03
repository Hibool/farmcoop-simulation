package com.example.farmcoop.services;

import com.example.farmcoop.models.*;
import com.example.farmcoop.utils.LoggerUtil;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    // Method to run the simulation with given parameters
    public void runSimulation(int simulationTime, int warehouseCapacity, int nbProducers, int nbTrucks, int minTruckCapacity, int maxTruckCapacity) {
        // Initialize the warehouse with given parameters (capacity, number of producers, trucks, etc.)
        Warehouse warehouse = DataInitializer.initializeWarehouse(warehouseCapacity, nbProducers, nbTrucks, minTruckCapacity, maxTruckCapacity);

        LoggerUtil.info("---------- START SIMULATION ----------");

        // Simulate for the given number of weeks
        for (int week = 0; week < simulationTime; week++) {
            // For each producer, start the week (initialize delivery days)
            for (Producer producer : warehouse.getProducers()) {
                producer.startWeek();  // Assign the delivery days for this week
            }

            // Simulate for each day of the week
            for (int weekDay = 0; weekDay < 7; weekDay++) {
                int day = 7 * week + (weekDay + 1);  // Calculate the current day in the simulation
                LoggerUtil.info("---------- Day " + day + " ----------");

                // Simulate the producers' deliveries for the day
                simulateProducersDelivery(warehouse, weekDay);

                // Simulate for each minute of the day (24 hours = 1440 minutes)
                for (int minutes = 0; minutes < 1440; minutes++) {
                    // Simulate truck deliveries (update truck status and process deliveries)
                    simulateTruckDeliveries(warehouse);
                    // Process the queue of incoming producer deliveries to store them in the warehouse
                    processProducerDeliveriesQueue(warehouse);
                }
            }
        }

        LoggerUtil.info("---------- END SIMULATION ----------");
    }

    // Method to simulate deliveries from producers on a specific day
    void simulateProducersDelivery(Warehouse warehouse, int weekDayIndex) {
        // Iterate through all producers and check if they are scheduled to deliver on this day
        for (Producer producer : warehouse.getProducers()) {
            WeekDay weekDay = WeekDay.values()[weekDayIndex];
            // If the producer has deliveries scheduled for this day
            if (producer.getDeliveryDays().contains(weekDay)) {
                // Create a new ProducerDelivery with random quantity and producer info
                ProducerDelivery producerDelivery = new ProducerDelivery(producer.deliver(), producer.getId(), weekDay);
                // Add the new delivery to the warehouse's queue of incoming deliveries
                warehouse.addProducerDeliveryToQueue(producerDelivery);
            }
        }
    }

    // Method to process the next delivery in the producer's delivery queue and store it in the warehouse
    void processProducerDeliveriesQueue(Warehouse warehouse) {
        // Get the next producer delivery in the queue
        ProducerDelivery nextProducerDelivery = warehouse.getNextProducerDelivery();
        if (nextProducerDelivery != null) {
            // Store the goods from the producer delivery in the warehouse
            warehouse.storeGoods(nextProducerDelivery);
        }
    }

    // Method to simulate truck deliveries (update truck statuses and process deliveries)
    void simulateTruckDeliveries(Warehouse warehouse) {
        // Update the status of trucks that are unavailable (i.e., check if they have completed their deliveries)
        warehouse.updateTruckStatus();

        // Process available trucks (i.e., those that are in the warehouse and ready to deliver)
        Truck truck = warehouse.getAvailableTruck();
        if (truck != null) {
            // Send goods from the warehouse to a client (superstore) using the available truck
            warehouse.sendGoods(truck);
        }
    }

}
