package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal);
//        System.out.println("------ TEST -------");
//        animal.move(MoveDirection.RIGHT);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        System.out.println(animal);
        OptionsParser parser = new OptionsParser();
        MoveDirection[] directions =  parser.parse(new String[]{"r","r", "f", "f", "f"});
        for (MoveDirection direction: directions
             ) {
            animal.move(direction);
        }
        System.out.println(animal);
    }
}
