package agh.ics.oop;

import java.sql.Array;
import java.util.stream.Stream;

public class World {

    public static Direction mapEnum(String item){
        return switch (item) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "r" -> Direction.RIGHT;
            default -> Direction.LEFT;
        };
    }


    public static void main(String[] args) {
        System.out.println("system wystartował");
//        Direction[] directions = new Direction[args.length];
//        int index = 0;
//        for (String arg : args) {
//            switch (arg) {
//                case "f" -> directions[index] = Direction.FORWARD;
//                case "b" -> directions[index] = Direction.BACKWARD;
//                case "r" -> directions[index] = Direction.RIGHT;
//                case "l" -> directions[index] = Direction.LEFT;
//            }
//            index++;
//        }
        Direction[] directions = Stream.of(args).map(World::mapEnum).toArray(Direction[]::new);
        run(directions);
        System.out.println("system zakończył działanie");
    }

    public static void run(Direction[] args) {
//        System.out.println("Zwierzak idzie do przodu");
//        for (int i = 0; i < args.length; i++) {
//            String end = i == args.length - 1 ? "" : ", ";
//
//            System.out.print(args[i] + end);
//        }
//        System.out.println();
        System.out.println("Start");
        for (Direction direction : args) {
            System.out.print("Zwierzak ");
            switch (direction) {
                case FORWARD -> System.out.println("idzie do przodu");
                case BACKWARD -> System.out.println("idzie do tyłu");
                case RIGHT -> System.out.println("skręca w prawo");
                case LEFT -> System.out.println("skręca w lewo");
            }
        }
        System.out.println("Stop");
    }
}
