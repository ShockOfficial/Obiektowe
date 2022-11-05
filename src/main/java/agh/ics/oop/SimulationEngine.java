package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    private final ArrayList<Animal> animals;
    private final MoveDirection[] moveArr;
    private final IWorldMap map;
    public SimulationEngine(MoveDirection[] moveArr, IWorldMap map, Vector2d[] initialPositions){
        this.animals = new ArrayList<>();
        this.moveArr = moveArr;
        this.map = map;

        for (Vector2d position: initialPositions){
            Animal animal = new Animal(map, position);
            if(this.map.place(animal)){
                this.animals.add(animal);
            }
        }
    }
    @Override
    public void run() {
        for (int i = 0; i < this.moveArr.length; i++) {
            Animal animal =this.animals.get(i % this.animals.size());
            Vector2d prevPosition = animal.getPosition();
            animal.move(this.moveArr[i]);
//            if(this.map.place(animal)) {
//            ((AbstractWorldMap) this.map).clearPreviousLocation(prevPosition);
//            }
            System.out.println(this.map.toString());
        }
    }
}
