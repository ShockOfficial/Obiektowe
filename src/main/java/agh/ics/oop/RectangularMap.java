package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    private int width;
    private int height;
    public RectangularMap(int width, int height) {
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.x < width  && position.y < height && super.canMoveTo(position);
    };

    @Override
    public Vector2d getUpperRight() {
        return new Vector2d(width - 1,height - 1);
    }

    @Override
    public Vector2d getLowerLeft() {
        return new Vector2d(0, 0);
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: this.animals) {
            if (animal.getPosition().equals(position)){
                return animal;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(getLowerLeft(), getUpperRight());
    }
}
