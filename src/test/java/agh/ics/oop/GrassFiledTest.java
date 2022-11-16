package agh.ics.oop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrassFiledTest {
    private static IWorldMap map;

    @BeforeAll
    public static void init() {
        map = new GrassField(10);
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
        // Może nie działać czasem ze wzlędu na losowość rozkladu trawy
        assertTrue(map.isOccupied(new Vector2d(2,2)));
        assertFalse(map.isOccupied(new Vector2d(3,0)));

        // objectAt
        // Może nie działać czasem ze względu na losowość trawy
        assertEquals(map.objectAt(new Vector2d(2, 2)), animal1);
        assertNull(map.objectAt(new Vector2d(4, 4)));

        // canMoveTo
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
        assertTrue(map.canMoveTo(new Vector2d(0,0)));

    }
}
