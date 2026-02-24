package org.example.model;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class OptimizeRequestTest {
    @Test
    void validRequest_shouldReturnCorrectFields() {
        var truck = new Truck("truck-1", 10000, 500);
        var order = new Order("id1", 1000, 200, 50, "A", "B", LocalDate.now(), LocalDate.now().plusDays(1), false);
        var req = new OptimizeRequest(truck, List.of(order));
        assertEquals(truck, req.truck());
        assertEquals(1, req.orders().size());
        assertEquals(order, req.orders().get(0));
    }
    @Test
    void nullTruckAndNullOrders_shouldReturnNulls() {
        var req = new OptimizeRequest(null, null);
        assertNull(req.truck());
        assertNull(req.orders());
    }
}
