package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    private final TreeSet<Vector2d> alongX = new TreeSet<>(Comparator.comparingInt(vec -> vec.x));
    private final TreeSet<Vector2d> alongY = new TreeSet<>(Comparator.comparingInt(vec -> vec.y));
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removeFromSet(oldPosition);
        addToSet(newPosition);
    }

    public void addToSet(Vector2d vec){
        alongX.add(vec);
        alongY.add(vec);
    }

    public void removeFromSet(Vector2d vec) {
        alongX.remove(vec);
        alongY.remove(vec);
    }

    public Vector2d getUpperRight() {
        return alongY.last().upperRight(alongX.last());
    }

    public Vector2d getLowerLeft() {
        return alongY.first().lowerLeft(alongX.first());
    }
}
