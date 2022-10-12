package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    void equalsTest() {
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(1,1);
        Vector2d c = new Vector2d(2,2);
        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void toStringTest() {
        Vector2d a = new Vector2d(1,1);
        assertEquals("(1,1)", a.toString());

    }

    @Test
    void precedesTest() {
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(2,2);
        assertTrue(a.precedes(b));
        assertFalse(b.precedes(a));
    }

    @Test
    void followsTest() {
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(2,2);
        assertTrue(b.follows(a));
        assertFalse(a.follows(b));
    }

    @Test
    void upperRightTest() {
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(2,2);
        assertEquals(b, a.upperRight(b));
        assertEquals(b, b.upperRight(a));
    }

    @Test
    void lowerLeftTest() {
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(2,2);
        assertEquals(a, a.lowerLeft(b));
        assertEquals(a, b.lowerLeft(a));
    }

    @Test
    void addTest() {
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(2,2);
        assertEquals( new Vector2d(3, 3), a.add(b));
        assertEquals( new Vector2d(3, 3), b.add(a));
    }
    @Test
    void subtractTest() {
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(2,2);
        assertEquals( new Vector2d(-1, -1), a.subtract(b));
        assertEquals( new Vector2d(1, 1), b.subtract(a));
    }

    @Test
    void opposite() {
        Vector2d a = new Vector2d(1,1);
        assertEquals(new Vector2d(-1,-1), a.opposite());
    }
}