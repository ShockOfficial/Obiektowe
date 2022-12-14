package agh.ics.oop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RectangularMapTest {

    private static IWorldMap map;

    @BeforeAll
    public static void init() {
        map = new RectangularMap(10,5);
    }

    @Test
    public void runTest(){
        Animal animal1 = new Animal(map,new Vector2d(2,2));
        Animal animal2 = new Animal(map,new Vector2d(3,4));


        // place
        assertTrue(map.place(animal1));
        assertTrue(map.place(animal2));
        assertThrows(IllegalArgumentException.class, () -> map.place(animal1));
        assertThrows(IllegalArgumentException.class, () -> map.place(animal2));

        // isOccupied
        assertTrue(map.isOccupied(new Vector2d(2,2)));
        assertFalse(map.isOccupied(new Vector2d(3,0)));

        // objectAt
        assertEquals(map.objectAt(new Vector2d(2, 2)), animal1);
        assertNull(map.objectAt(new Vector2d(4, 4)));

        // canMoveTo
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
        assertTrue(map.canMoveTo(new Vector2d(0,0)));

    }
}
