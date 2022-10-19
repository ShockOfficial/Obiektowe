package agh.ics.oop;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalIntegrationTest {
    private static Animal animal1;
    private static Animal animal2;
    private static OptionsParser parser ;

    @BeforeAll
    public static void init() {
        animal1 = new Animal();
        animal2 = new Animal();
        parser = new OptionsParser();
    }

    @Test
    public void runTest() {
        // initial position
        assertEquals("orientation=" + MapDirection.NORTH +
                ", position=" + new Vector2d(2,2), animal1.toString());

        MoveDirection[] directions1 =  parser.parse(new String[]{"r", "f", "f", "f"});
        assertArrayEquals(directions1, new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD});

        for (MoveDirection direction: directions1
        ) {
            animal1.move(direction);
        }

        assertTrue(animal1.isAt(new Vector2d(4,2)));// Do isAt works?
        assertEquals("orientation=" + MapDirection.EAST +
                ", position=" + new Vector2d(4,2), animal1.toString());


        MoveDirection[] directions2 =  parser.parse(new String[]{"f", "f", "f", "f"});
        assertArrayEquals(directions2, new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD});

        for (MoveDirection direction: directions2
        ) {
            animal2.move(direction);
        }

        assertTrue(animal2.isAt(new Vector2d(2,4)));
        assertEquals("orientation=" + MapDirection.NORTH +
                ", position=" + new Vector2d(2,4), animal2.toString());


        MoveDirection[] directions3 =  parser.parse(new String[]{"l", "f", "f", "f"});
        assertArrayEquals(directions3, new MoveDirection[]{MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD});
        for (MoveDirection direction: directions3
        ) {
            animal2.move(direction);
        }
        assertTrue(animal2.isAt(new Vector2d(0,4)));
        assertEquals("orientation=" + MapDirection.WEST +
                ", position=" + new Vector2d(0,4), animal2.toString());

        MoveDirection[] directions4 =  parser.parse(new String[]{"b", "b", "b", "b", "b"});
        assertArrayEquals(directions4, new MoveDirection[]{MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD});

        for (MoveDirection direction: directions4
        ) {
            animal2.move(direction);
        }
        assertTrue(animal2.isAt(new Vector2d(4,4)));
        assertEquals("orientation=" + MapDirection.WEST +
                ", position=" + new Vector2d(4,4), animal2.toString());

        MoveDirection[] directions5 =  parser.parse(new String[]{"r", "b","error","b", "b", "b"});
        assertArrayEquals(directions5, new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD});
        for (MoveDirection direction: directions5
        ) {
            animal2.move(direction);
        }

        assertTrue(animal2.isAt(new Vector2d(4,0)));
        assertEquals("orientation=" + MapDirection.NORTH +
                ", position=" + new Vector2d(4,0), animal2.toString());

    }
}
