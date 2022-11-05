package agh.ics.oop;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    ArrayList<Grass> grasses;
    private final MapVisualizer map = new MapVisualizer(this);
    private int numberOfGrass;
    private final int initialNumberOfGrass;
    public GrassField(int numberOfGrass) {
        this.grasses = new ArrayList<>();
        this.initialNumberOfGrass = numberOfGrass;
        this.sowGrass();
    }

    public void sowGrass(){
        Random random = new Random();
        while (this.numberOfGrass < this.initialNumberOfGrass) {
            int max = (int) Math.sqrt(numberOfGrass * 10);
            Vector2d tmpPosition = new Vector2d(random.nextInt(max + 1), random.nextInt(max + 1));
            if (!isOccupied(tmpPosition)){
                this.grasses.add(new Grass(tmpPosition));
                this.numberOfGrass++;
            }
        }
    }

    public void eatGrass(Vector2d position) {
        for (int i = 0; i < this.grasses.size(); i ++) {
            if (this.grasses.get(i).getPosition().equals(position)) {
                this.grasses.remove(i);
                this.numberOfGrass--;
                this.sowGrass();
                break;
            }
        }
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
        for (Animal animal: this.animals) {
            if(animal.getPosition().equals(position)){
                return animal;
            }
        }
        for (Grass grass : this.grasses) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    public ArrayList<Vector2d> mergeLists() {
        ArrayList<Vector2d> newList = new ArrayList<>();
        for (Animal animal: this.animals) {
            newList.add(animal.getPosition());
        }
        for (Grass grass : this.grasses) {
            newList.add(grass.getPosition());
        }
        return newList;
    }

    public Vector2d getUpperRight() {
        Vector2d upperRight = new Vector2d(0,0);
        ArrayList<Vector2d> mergedList = mergeLists();
        for (Vector2d vector2d: mergedList){
            upperRight = upperRight.upperRight(vector2d);
        }
        return upperRight;
    }
    public Vector2d getLowerLeft() {
        Vector2d lowerLeft = new Vector2d(0,0);
        ArrayList<Vector2d> mergedList = mergeLists();
        for (Vector2d vector2d: mergedList){
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
