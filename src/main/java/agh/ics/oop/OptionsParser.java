package agh.ics.oop;

import java.util.ArrayList;
public class OptionsParser {
    public MoveDirection[] parse(String[] directives){
        ArrayList<MoveDirection> outputDirectives = new ArrayList<>();
        for (String directive:
             directives) {
            switch (directive) {
                case "f", "forward" -> outputDirectives.add(MoveDirection.FORWARD);
                case "b", "backward" -> outputDirectives.add(MoveDirection.BACKWARD);
                case "l", "left" -> outputDirectives.add(MoveDirection.LEFT);
                case "r", "right" -> outputDirectives.add(MoveDirection.RIGHT);
            }
        }
        return outputDirectives.toArray(new MoveDirection[0]);
    }
}
