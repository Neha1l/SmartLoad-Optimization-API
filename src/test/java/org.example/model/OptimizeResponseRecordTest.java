package org.example.model;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OptimizeResponseRecordTest {
    @Test
    void validFields_shouldReturnCorrectValues() {
        var resp = new OptimizeResponseRecord(
            "truck-1",
            List.of("o1", "o2"),
            10000L,
            2000L,
            300L,
            50.0,
            60.0
        );
        assertEquals("truck-1", resp.truckId());
        assertEquals(List.of("o1", "o2"), resp.selectedOrderIds());
        assertEquals(10000L, resp.totalPayoutCents());
        assertEquals(2000L, resp.totalWeightLbs());
        assertEquals(300L, resp.totalVolumeCuft());
        assertEquals(50.0, resp.utilizationWeightPercent());
        assertEquals(60.0, resp.utilizationVolumePercent());
    }

    @Test
    void nullTruckIdAndEmptyOrderList_shouldReturnZerosAndNulls() {
        var resp = new OptimizeResponseRecord(null, List.of(), 0, 0, 0, 0, 0);
        assertNull(resp.truckId());
        assertEquals(0, resp.selectedOrderIds().size());
        assertEquals(0, resp.totalPayoutCents());
        assertEquals(0, resp.totalWeightLbs());
        assertEquals(0, resp.totalVolumeCuft());
        assertEquals(0, resp.utilizationWeightPercent());
        assertEquals(0, resp.utilizationVolumePercent());
    }
}
