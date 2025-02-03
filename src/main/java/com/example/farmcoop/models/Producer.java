package com.example.farmcoop.models;

import lombok.Data;

import java.util.*;

@Data
public class Producer {
    private static int idCounter = 0;

    private final int id;  // Unique ID for each producer
    private final int maxDeliveryQuantity;  // Maximum quantity a producer can deliver
    private final int deliveryFrequencyPerWeek;  // How often the producer delivers per week
    private List<WeekDay> deliveryDays;  // Days of the week when deliveries occur, it changes every week

    public Producer(int deliveryFrequencyPerWeek, int maxDeliveryQuantity) {
        this.id = idCounter++; // Incremental ID generation for each new producer
        this.maxDeliveryQuantity = maxDeliveryQuantity;
        this.deliveryFrequencyPerWeek = deliveryFrequencyPerWeek;
    }

    // Randomly generate the quantity to deliver within the producer's max delivery quantity
    public int deliver() {
        return new Random().nextInt(this.maxDeliveryQuantity);
    }

    // Randomly select delivery days for the producer, based on their delivery frequency per week
    public void startWeek() {
        List<WeekDay> weekDaysList = new ArrayList<>(Arrays.asList(WeekDay.values()));
        Collections.shuffle(weekDaysList);
        List<WeekDay> randomWeekDays = weekDaysList.subList(0, this.deliveryFrequencyPerWeek);
        Collections.sort(randomWeekDays);
        this.deliveryDays = randomWeekDays;
    }
}
