package agh.ics.oop;

import java.util.Arrays;

public class RectangularMap implements IWorldMap {
    Animal[][] map;
    public RectangularMap(int width, int height) {
        this.map = new Animal[height][width];
    }

    public void clearPreviousPosition(Vector2d prevPosition){
        this.map[prevPosition.y][prevPosition.x] = null;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.x >= 0 && position.x < this.map[0].length && position.y >= 0 && position.y < this.map.length;
    }

    @Override
    public boolean place(Animal animal) {
       Vector2d animalPosition = animal.getPosition();
       if (!this.isOccupied(animalPosition)){
           this.map[animalPosition.y][animalPosition.x] = animal;
           return true;
       }
       return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.map[position.y][position.x] != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.map[position.y][position.x];
    }

    @Override
    public String toString() {
      MapVisualizer mapVisualizer = new MapVisualizer(this);
      return mapVisualizer.draw(new Vector2d(0,0), new Vector2d(this.map[0].length - 1, this.map.length - 1));
    }
}
