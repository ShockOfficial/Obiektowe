package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d, Animal> animals = new HashMap<>();

    private final MapVisualizer mapVisualizer = new MapVisualizer(this);
    @Override
    public boolean canMoveTo(Vector2d position) {
        if(this.isOccupied(position)){
            return objectAt(position) instanceof Grass;
        }
        return true;
    }
    @Override
    public boolean place(Animal animal) throws IllegalArgumentException  {
        if (this.canMoveTo(animal.getPosition())) {
            this.animals.put(animal.getPosition(), animal);
            animal.addObserver(this);
            return true;
        }
        throw new IllegalArgumentException(animal.getPosition() + "is already taken!");
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
       if (oldPosition != newPosition){
           Animal animal = this.animals.get(oldPosition);
           this.animals.remove(oldPosition);
           this.animals.put(newPosition,animal);
       }
    };
    public abstract Vector2d getUpperRight();

    public abstract Vector2d getLowerLeft();

    @Override
    public String toString() {
        return this.mapVisualizer.draw(getLowerLeft(), getUpperRight());
    }
}
