package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal {
    private  MapDirection orientation;
    private  Vector2d position;
    private final IWorldMap map;
    private List<IPositionChangeObserver> observerList;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH; // Because without it we will have this unspecified (null)
        this.observerList = new ArrayList<>();
    }

    public Vector2d getPosition() {
        return position;
    }

    void addObserver(IPositionChangeObserver observer){
        this.observerList.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        this.observerList.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer: observerList) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
    @Override
    public String toString() {
        return switch (this.orientation){
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
        };
    }

    boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    void move(MoveDirection direction){
        if (direction == MoveDirection.LEFT  ){
            this.orientation = this.orientation.previous();
        } else if (direction == MoveDirection.RIGHT) {
            this.orientation = this.orientation.next();
        } else  {
            Vector2d unitVector = direction == MoveDirection.BACKWARD ? this.orientation.toUnitVector().opposite() :  this.orientation.toUnitVector();
            Vector2d tmpPosition = this.position.add(unitVector);
                if (this.map.canMoveTo(tmpPosition)){
                if (this.map instanceof GrassField && this.map.objectAt(tmpPosition) instanceof Grass){
                    ((GrassField) this.map).eatGrass(tmpPosition);
                }
                    positionChanged(this.position,tmpPosition);
                    this.position = tmpPosition;
                }

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return orientation == animal.orientation && position.equals(animal.position) && map.equals(animal.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, position, map);
    }
}