package org.example.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    void validOrderFields_shouldReturnCorrectValues() {
        var pickup = LocalDate.of(2025, 12, 5);
        var delivery = LocalDate.of(2025, 12, 9);
        var order = new Order("id1", 1000, 200, 50, "A", "B", pickup, delivery, true);
        assertEquals("id1", order.id());
        assertEquals(1000, order.payoutCents());
        assertEquals(200, order.weightLbs());
        assertEquals(50, order.volumeCuft());
        assertEquals("A", order.origin());
        assertEquals("B", order.destination());
        assertEquals(pickup, order.pickupDate());
        assertEquals(delivery, order.deliveryDate());
        assertTrue(order.isHazmat());
    }

    @Test
    void emptyStringsZeroValuesNullDates_shouldReturnDefaults() {
        var order = new Order("", 0, 0, 0, "", "", null, null, false);
        assertEquals("", order.id());
        assertEquals(0, order.payoutCents());
        assertEquals(0, order.weightLbs());
        assertEquals(0, order.volumeCuft());
        assertEquals("", order.origin());
        assertEquals("", order.destination());
        assertNull(order.pickupDate());
        assertNull(order.deliveryDate());
        assertFalse(order.isHazmat());
    }
}
