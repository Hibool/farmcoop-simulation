package com.example.farmcoop.services;

import com.example.farmcoop.models.Producer;
import com.example.farmcoop.models.Superstore;
import com.example.farmcoop.models.Truck;
import com.example.farmcoop.models.Warehouse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DataInitializer {
    private static final int MIN_PRODUCER_DELIVERY_FREQUENCY_PER_WEEK = 1;
    private static final int MAX_PRODUCER_DELIVERY_FREQUENCY_PER_WEEK = 5;
    private static final Random RANDOM = new Random();

    public static Warehouse initializeWarehouse(int warehouseCapacity, int nbProducers, int nbTrucks, int minTruckCapacity, int maxTruckCapacity) {
        Warehouse warehouse = new Warehouse(warehouseCapacity);
        warehouse.setProducers(createProducers(nbProducers, warehouseCapacity, minTruckCapacity));
        warehouse.setAvailableTrucks(createTrucks(nbTrucks, minTruckCapacity, maxTruckCapacity));
        warehouse.setClients(createSuperstores());
        return warehouse;
    }

    private static List<Producer> createProducers(int nbProducers, int warehouseCapacity, int minTruckCapacity) {
        // Check that the number of producers is positive
        if (nbProducers <= 0) {
            throw new IllegalArgumentException("The number of producers must be greater than 0.");
        }

        List<Producer> producers = new ArrayList<>(nbProducers);

        for (int i = 0; i < nbProducers; i++) {
            int deliveryFrequencyPerWeek = RANDOM.nextInt(MAX_PRODUCER_DELIVERY_FREQUENCY_PER_WEEK - MIN_PRODUCER_DELIVERY_FREQUENCY_PER_WEEK + 1) + MIN_PRODUCER_DELIVERY_FREQUENCY_PER_WEEK;
            int maxDeliveryQuantity = warehouseCapacity - minTruckCapacity; // Prevents traffic to and from the warehouse from being blocked.
            producers.add(new Producer(deliveryFrequencyPerWeek, maxDeliveryQuantity));
        }

        return producers;
    }

    private static List<Superstore> createSuperstores() {
        List<Superstore> superstores = new ArrayList<>(50);

        superstores.add(new Superstore("Carrefour", "Paris", 1100));
        superstores.add(new Superstore("Auchan", "Lille", 1500));
        superstores.add(new Superstore("Lidl", "Berlin", 1700));
        superstores.add(new Superstore("Tesco", "London", 1600));
        superstores.add(new Superstore("Sainsbury's", "London", 1600));
        superstores.add(new Superstore("Metro", "Düsseldorf", 1360));
        superstores.add(new Superstore("Intermarché", "Paris", 1100));
        superstores.add(new Superstore("Leclerc", "Paris", 1100));
        superstores.add(new Superstore("Waitrose", "London", 1600));
        superstores.add(new Superstore("E.Leclerc", "Lyon", 300));
        superstores.add(new Superstore("Carrefour Market", "Madrid", 2000));
        superstores.add(new Superstore("Cora", "Brussels", 1300));
        superstores.add(new Superstore("Makro", "Amsterdam", 1500));
        superstores.add(new Superstore("Penny Market", "Vienna", 1800));
        superstores.add(new Superstore("Coop", "Zurich", 60));
        superstores.add(new Superstore("Lidl", "Vienna", 1800));
        superstores.add(new Superstore("Real", "Berlin", 1700));
        superstores.add(new Superstore("Billa", "Vienna", 1800));
        superstores.add(new Superstore("Spar", "Vienna", 1800));
        superstores.add(new Superstore("Aldi", "Munich", 1300));
        superstores.add(new Superstore("Costco", "London", 1600));
        superstores.add(new Superstore("Metro", "Zurich", 60));
        superstores.add(new Superstore("Sainsbury's", "Birmingham", 1700));
        superstores.add(new Superstore("Carrefour", "Madrid", 2000));
        superstores.add(new Superstore("Tengelmann", "Munich", 1300));
        superstores.add(new Superstore("Cora", "Paris", 1100));
        superstores.add(new Superstore("Auchan", "Toulouse", 1200));
        superstores.add(new Superstore("Real", "Hamburg", 1600));
        superstores.add(new Superstore("Ipercoop", "Rome", 2400));
        superstores.add(new Superstore("Spar", "Amsterdam", 1500));
        superstores.add(new Superstore("Aligro", "Geneva", 40));
        superstores.add(new Superstore("Carrefour", "Lyon", 300));
        superstores.add(new Superstore("Lidl", "Munich", 1300));
        superstores.add(new Superstore("Biedronka", "Warsaw", 2400));
        superstores.add(new Superstore("Coop", "Geneva", 40));
        superstores.add(new Superstore("Penny", "Berlin", 1700));
        superstores.add(new Superstore("Intermarché", "Paris", 1100));
        superstores.add(new Superstore("E.Leclerc", "Strasbourg", 400));
        superstores.add(new Superstore("Aldi", "Hamburg", 1600));
        superstores.add(new Superstore("Carrefour", "Brussels", 1300));
        superstores.add(new Superstore("Super U", "Paris", 1100));
        superstores.add(new Superstore("Mercadona", "Valencia", 2000));
        superstores.add(new Superstore("Billa", "Bratislava", 2200));
        superstores.add(new Superstore("Auchan", "Luxembourg", 900));
        superstores.add(new Superstore("Sainsbury's", "Manchester", 1800));
        superstores.add(new Superstore("Metro", "Paris", 1100));
        superstores.add(new Superstore("Coop", "Zurich", 60));
        superstores.add(new Superstore("Carrefour", "Marseille", 600));
        superstores.add(new Superstore("E.Leclerc", "Paris", 1100));
        superstores.add(new Superstore("Lidl", "Copenhagen", 2400));
        superstores.add(new Superstore("Tesco", "Manchester", 1800));
        superstores.add(new Superstore("Spar", "Geneva", 60));
        superstores.add(new Superstore("Aldi", "Vienna", 1800));
        superstores.add(new Superstore("Carrefour", "Nice", 560));
        superstores.add(new Superstore("Makro", "Berlin", 1700));
        superstores.add(new Superstore("Lidl", "Barcelona", 2000));

        return superstores;
    }

    private static LinkedList<Truck> createTrucks(int nbTrucks, int minCapacity, int maxCapacity) {
        // Check that the number of trucks is positive
        if (nbTrucks <= 0) {
            throw new IllegalArgumentException("The number of trucks must be greater than 0.");
        }

        // Check the validity of truck capacities
        if (minCapacity <= 0 || maxCapacity <= 0) {
            throw new IllegalArgumentException("Truck capacities must be greater than 0.");
        }
        if (minCapacity > maxCapacity) {
            throw new IllegalArgumentException("Min capacity cannot be greater than max capacity.");
        }

        LinkedList<Truck> trucks = new LinkedList<>();

        for (int i = 0; i < nbTrucks; i++) {
            trucks.add(new Truck(minCapacity, maxCapacity));
        }

        return trucks;
    }
}
