package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable{
    private final ArrayList<Animal> animals;
    private  MoveDirection[] moveArr;
    private final IWorldMap map;
    private App app;
    private int moveDelay;
    public SimulationEngine(MoveDirection[] moveArr, IWorldMap map, App app, Vector2d[] initialPositions, int moveDelay) {
        this.animals = new ArrayList<>();
        this.moveArr = moveArr;
        this.map = map;
        this.app = app;
        this.moveDelay = moveDelay;

        for (Vector2d position: initialPositions){
            Animal animal = new Animal(map, position);
            if(this.map.place(animal)){
                this.animals.add(animal);
            }
        }
    }

    public SimulationEngine(MoveDirection[] moveArr, IWorldMap map, Vector2d[] initialPositions) {
        this.animals = new ArrayList<>();
        this.map = map;
        this.moveArr = moveArr;

        for (Vector2d position : initialPositions) {
            Animal animal = new Animal(map, position);
            if (this.map.place(animal)) {
                this.animals.add(animal);
            }
        }
    }
    public GrassField calculateMapBoundaries(){
        ArrayList<Animal> animalsCopy = new ArrayList<>();
        GrassField tmpMap = new GrassField(10);

        this.animals.forEach((Animal animal) -> {
            Animal animal1 = new Animal(tmpMap, animal.getPosition());
            animalsCopy.add(animal1);
            tmpMap.place(animal1);
        });
        for (int i = 0; i < this.moveArr.length; i++) {
            Animal animal =animalsCopy.get(i % animalsCopy.size());
            animal.move(this.moveArr[i]);
        }
        return tmpMap;
    }
    @Override
    public void run() {
        Platform.runLater(() -> {
            this.app.render((GrassField) this.map);
        });
        try {
            Thread.sleep(this.moveDelay);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < this.moveArr.length; i++) {
            Animal animal =this.animals.get(i % this.animals.size());
            animal.move(this.moveArr[i]);
            System.out.println(this.map.toString());
            Platform.runLater(() -> {
                this.app.render((GrassField) this.map);
            });
            try {
                Thread.sleep(this.moveDelay);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
