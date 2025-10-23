package edu.cinema.pricing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestPriceBreakdown {

    @Test
    void testConstructorAndGetters() {
        PriceBreakdown pb = new PriceBreakdown(10.0, -2.0, 3.0, -1.0, 10.00);
        assertEquals(10.0, pb.getSubtotal());
        assertEquals(-2.0, pb.getWednesdayDisc());
        assertEquals(3.0, pb.getThreeDSurcharge());
        assertEquals(-1.0, pb.getGroupDisc());
        assertEquals(10.00, pb.getTotal());
    }

    @Test
    void testToString() {
        PriceBreakdown pb = new PriceBreakdown(12.0, -2.4, 4.0, -1.36, 12.24);
        String s = pb.toString();
        assertTrue(s.contains("Sous-total"));
        assertTrue(s.contains("TOTAL"));
    }
}
