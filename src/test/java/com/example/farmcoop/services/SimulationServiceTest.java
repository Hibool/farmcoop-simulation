package com.example.farmcoop.services;

import com.example.farmcoop.models.ProducerDelivery;
import com.example.farmcoop.models.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class SimulationServiceTest {

    private SimulationService simulationService;

    @Mock
    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        simulationService = new SimulationService();
    }

    /*@Test
    void testSimulateProducersDelivery() {
        simulationService.simulateProducersDelivery(warehouse, 1);
        verify(warehouse, times(1)).addProducerDeliveryToQueue(any(ProducerDelivery.class));
    }*/

    @Test
    void testSimulateTruckDeliveries() {
        simulationService.simulateTruckDeliveries(warehouse);
        verify(warehouse, times(1)).updateTruckStatus();
    }

    /*@Test
    void testProcessProducerDeliveriesQueue() {
        simulationService.processProducerDeliveriesQueue(warehouse);
        verify(warehouse, times(1)).storeGoods(any(ProducerDelivery.class));
    }*/
}
