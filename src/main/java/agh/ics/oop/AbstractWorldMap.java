package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap{
    protected ArrayList<Animal> animals = new ArrayList<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);
    @Override
    public boolean canMoveTo(Vector2d position) {
        if(this.isOccupied(position)){
            return objectAt(position) instanceof Grass && position.x >= 0 && position.y >= 0;
        }
        return position.x >= 0 && position.y >= 0;
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            this.animals.add(animal);
            return true;
        }
        return false;
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public abstract Vector2d getUpperRight();

    public abstract Vector2d getLowerLeft();

    @Override
    public String toString() {
        return this.mapVisualizer.draw(getLowerLeft(), getUpperRight());
    }
}
