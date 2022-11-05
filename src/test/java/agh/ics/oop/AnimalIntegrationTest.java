package agh.ics.oop;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalIntegrationTest {
    private static OptionsParser parser ;
    private static IWorldMap map;
    private static IWorldMap map2;

    @BeforeAll
    public static void init() {
        parser = new OptionsParser();
        map = new RectangularMap(10,5);
        map2 = new RectangularMap(10,5);
    }

    @Test
    public void runTest() {

        MoveDirection[] directions =  parser.parse(new String[]{"f" ,"b" ,"r" ,"l" ,"f" ,"f" ,"r","r" ,"f" ,"f" ,"f","f", "f","f","f", "f"});
        assertArrayEquals(directions, new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD});

        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        Animal animal1 = (Animal) map.objectAt(new Vector2d(2,2));
        Animal animal2 = (Animal) map.objectAt(new Vector2d(3,4));

        assertTrue(animal1.isAt(new Vector2d(2,2)));
        assertTrue(animal2.isAt(new Vector2d(3,4)));

        engine.run();

        assertEquals("S", animal1.toString());
        assertEquals("N", animal2.toString());

        // 3 Obiekty
        Vector2d[] positions2 = { new Vector2d(2,2), new Vector2d(3,4), new Vector2d(0,0) };
        IEngine engine2 = new SimulationEngine(directions, map2, positions2);
        Animal animal3 = (Animal) map2.objectAt(new Vector2d(2,2));
        Animal animal4 = (Animal) map2.objectAt(new Vector2d(3,4));
        Animal animal5 = (Animal) map2.objectAt(new Vector2d(0,0));

        assertTrue(animal3.isAt(new Vector2d(2,2)));
        assertTrue(animal4.isAt(new Vector2d(3,4)));
        assertTrue(animal5.isAt(new Vector2d(0,0)));

        engine2.run();
        assertTrue(animal3.isAt(new Vector2d(2,4)));
        assertTrue(animal4.isAt(new Vector2d(5,4)));
        assertTrue(animal5.isAt(new Vector2d(4,0)));

        assertEquals("N", animal3.toString());
        assertEquals("E", animal4.toString());
        assertEquals("E", animal5.toString());

    }
}
