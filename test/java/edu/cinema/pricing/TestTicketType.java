package edu.cinema.pricing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestTicketType {

    @Test
    void testEnumValues() {
        assertEquals(TicketType.ADULT, TicketType.valueOf("ADULT"));
        assertEquals(TicketType.CHILD, TicketType.valueOf("CHILD"));
        assertEquals(TicketType.SENIOR, TicketType.valueOf("SENIOR"));
        assertEquals(TicketType.STUDENT, TicketType.valueOf("STUDENT"));
        assertEquals(4, TicketType.values().length);
    }
}
