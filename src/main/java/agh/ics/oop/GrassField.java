package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    Map<Vector2d,Grass> grasses = new HashMap<>();
    private final MapVisualizer map = new MapVisualizer(this);
    private int numberOfGrass;
    private final int initialNumberOfGrass;
    public GrassField(int numberOfGrass) {
        this.initialNumberOfGrass = numberOfGrass;
        this.sowGrass();
    }

    public void sowGrass(){
        Random random = new Random();
        while (this.numberOfGrass < this.initialNumberOfGrass) {
            int max = (int) Math.sqrt(numberOfGrass * 10);
            Vector2d tmpPosition = new Vector2d(random.nextInt(max + 1), random.nextInt(max + 1));
            if (!isOccupied(tmpPosition)){
                this.grasses.put(tmpPosition,new Grass(tmpPosition));
                this.numberOfGrass++;
            }
        }
    }

    public void eatGrass(Vector2d position) {
        this.grasses.remove(position);
        this.numberOfGrass--;
        this.sowGrass();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrassField that = (GrassField) o;
        return grasses.equals(that.grasses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grasses);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.animals.get(position) == null ? this.grasses.get(position) : this.animals.get(position);
    }

    public Vector2d getUpperRight() {
        Vector2d upperRight = new Vector2d(0,0);
        for (Vector2d vector2d: this.animals.keySet()){
            upperRight = upperRight.upperRight(vector2d);
        }
        for (Vector2d vector2d: this.grasses.keySet()){
            upperRight = upperRight.upperRight(vector2d);
        }
        return upperRight;
    }
    public Vector2d getLowerLeft() {
        Vector2d lowerLeft = new Vector2d(0,0);
        for (Vector2d vector2d: this.animals.keySet()){
            lowerLeft = lowerLeft.lowerLeft(vector2d);
        }
        for (Vector2d vector2d: this.grasses.keySet()){
            lowerLeft = lowerLeft.lowerLeft(vector2d);
        }
        return lowerLeft;
    }
    // Can be moved to AbstractWorldMap
    @Override
    public String toString() {
        return map.draw(getLowerLeft(), getUpperRight());
    }

}
