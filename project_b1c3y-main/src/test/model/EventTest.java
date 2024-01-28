package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for the Event class
// code from:
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
public class EventTest {
    private Event e;
    private Event e1;
    private Event e2;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Movie added to watched list");   // (1)
        e1 = new Event("Movie added to watched list");
        e2 = new Event("Movie added to to watch list");
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Movie added to watched list", e.getDescription());
        assertEquals(d, e.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Movie added to watched list", e.toString());
    }

    @Test
    public void testEqualsNull() {
        assertFalse(e.equals(null));
    }

    @Test
    public void testEqualsOtherClass() {
        assertFalse(e.equals(EventLog.getInstance()));
    }

    @Test
    public void testHashCode() {
        assertTrue(e.hashCode() == e1.hashCode());
        assertFalse(e.hashCode() == e2.hashCode());
    }
}

