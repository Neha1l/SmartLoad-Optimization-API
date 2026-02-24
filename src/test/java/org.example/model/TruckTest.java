package org.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TruckTest {
    @Test
    void validTruckFields_shouldReturnCorrectValues() {
        var truck = new Truck("truck-1", 10000, 500);
        assertEquals("truck-1", truck.id());
        assertEquals(10000, truck.maxWeightLbs());
        assertEquals(500, truck.maxVolumeCuft());
    }

    @Test
    void emptyIdZeroWeightZeroVolume_shouldReturnDefaults() {
        var truck = new Truck("", 0, 0);
        assertEquals("", truck.id());
        assertEquals(0, truck.maxWeightLbs());
        assertEquals(0, truck.maxVolumeCuft());
    }
}
