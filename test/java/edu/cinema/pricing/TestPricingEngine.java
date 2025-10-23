package edu.cinema.pricing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.DayOfWeek;
import java.util.Arrays;

class TestPricingEngine {

    @Test
    void testBasePrice() {
        PricingEngine engine = new PricingEngine();
        assertEquals(10.00, engine.basePrice(TicketType.ADULT));
        assertEquals(6.00, engine.basePrice(TicketType.CHILD));
        assertEquals(7.50, engine.basePrice(TicketType.SENIOR));
        assertEquals(8.00, engine.basePrice(TicketType.STUDENT));
    }

    @Test
    void testWednesdayDiscount() {
        PricingEngine engine = new PricingEngine();
        PriceBreakdown pb = engine.computeTotal(
                Arrays.asList(TicketType.ADULT, TicketType.ADULT),
                DayOfWeek.WEDNESDAY,
                false
        );
        assertTrue(pb.getWednesdayDisc() < 0); // remise négative
        assertEquals(16.00, pb.getSubtotal());
        assertEquals(-3.20, pb.getWednesdayDisc());
    }

    @Test
    void testThreeD() {
        PricingEngine engine = new PricingEngine();
        PriceBreakdown pb = engine.computeTotal(
                Arrays.asList(TicketType.ADULT, TicketType.CHILD),
                DayOfWeek.MONDAY,
                true
        );
        assertEquals(16.00, pb.getSubtotal());
        assertEquals(4.00, pb.getThreeDSurcharge());
        assertEquals(20.00, pb.getTotal());
    }

    @Test
    void testGroupDiscount() {
        PricingEngine engine = new PricingEngine();
        PriceBreakdown pb = engine.computeTotal(
                Arrays.asList(TicketType.ADULT, TicketType.CHILD, TicketType.SENIOR, TicketType.STUDENT),
                DayOfWeek.MONDAY,
                false
        );
        assertTrue(pb.getGroupDisc() < 0);
    }

    @Test
    void testAllReductionsTogether() {
        PricingEngine engine = new PricingEngine();
        PriceBreakdown pb = engine.computeTotal(
                Arrays.asList(TicketType.ADULT, TicketType.CHILD, TicketType.SENIOR, TicketType.STUDENT),
                DayOfWeek.WEDNESDAY,
                true
        );
        assertEquals(pb.getTotal(),
            PricingEngine.roundToCents(
                pb.getSubtotal() + pb.getWednesdayDisc() + pb.getThreeDSurcharge() + pb.getGroupDisc()
            )
        );
    }

    @Test
    void testErrorCases() {
        PricingEngine engine = new PricingEngine();
        assertThrows(IllegalArgumentException.class, () -> engine.basePrice(null));
        assertThrows(IllegalArgumentException.class, () -> engine.computeTotal(null, DayOfWeek.MONDAY, false));
        assertThrows(IllegalArgumentException.class, () -> engine.computeTotal(Arrays.asList(TicketType.ADULT), null, false));
    }

}
