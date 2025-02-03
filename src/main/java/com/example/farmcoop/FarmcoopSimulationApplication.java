package com.example.farmcoop;

import com.example.farmcoop.services.SimulationService;
import com.example.farmcoop.utils.ConfigUtil;
import com.example.farmcoop.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FarmcoopSimulationApplication implements CommandLineRunner {

    @Autowired
    private SimulationService simulationService;

    public static void main(String[] args) {
        SpringApplication.run(FarmcoopSimulationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Simulation parameters
        int simulationTime = ConfigUtil.getInt("simulationTime", 6);
        int warehouseCapacity = ConfigUtil.getInt("warehouseCapacity", 500);
        int nbProducers = ConfigUtil.getInt("nbProducers", 10);
        int nbTrucks = ConfigUtil.getInt("nbTrucks", 10);
        int minTruckCapacity = ConfigUtil.getInt("minTruckCapacity", 50);
        int maxTruckCapacity = ConfigUtil.getInt("maxTruckCapacity", 100);

        LoggerUtil.info("---------- SIMULATION PARAMETERS ----------");
        LoggerUtil.info("Simulation time: " + simulationTime);
        LoggerUtil.info("Warehouse capacity: " + warehouseCapacity);
        LoggerUtil.info("Number of producers: " + nbProducers);
        LoggerUtil.info("Number of trucks: " + nbTrucks);
        LoggerUtil.info("Minimum truck capacity: " + minTruckCapacity);
        LoggerUtil.info("Maximum truck capacity: " + maxTruckCapacity);
        LoggerUtil.info("-------------------------------------------");

        // Lancer la simulation
        simulationService.runSimulation(simulationTime, warehouseCapacity, nbProducers, nbTrucks, minTruckCapacity, maxTruckCapacity);
    }
}
