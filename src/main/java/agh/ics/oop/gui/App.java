package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    private GrassField map;
    private final int width = 20;
    private final int height = 20;
    @Override
    public void init() throws Exception {
        super.init();
        OptionsParser parser = new OptionsParser();
        MoveDirection[] directions = parser.parse(getParameters().getRaw().toArray(new String[0]));
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        this.map = new GrassField(10);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }

    public void start(Stage primaryStage){
        try{

            GridPane gridPane = new GridPane();
            gridPane.getColumnConstraints().add(new ColumnConstraints(this.width));
            gridPane.getRowConstraints().add(new RowConstraints(this.height));
            gridPane.setGridLinesVisible(true);
            gridPane.setAlignment(Pos.CENTER);
            int minX = Math.min(map.getLowerLeft().x, map.getUpperRight().x);
            int maxX = Math.max(map.getLowerLeft().x, map.getUpperRight().x);
            int minY = Math.min(map.getLowerLeft().y, map.getUpperRight().y);
            int maxY = Math.max(map.getLowerLeft().y, map.getUpperRight().y);

            Label directionsLabel = new Label("y/x");
            gridPane.add(directionsLabel, 0, 0, 1, 1);
            GridPane.setHalignment(directionsLabel, HPos.CENTER);


            int currentPos = 1;
            for (int i = minX; i <= maxX; i++){
                Label tmp = new Label("" + i);
                gridPane.add(tmp, currentPos, 0, 1, 1);
                gridPane.getColumnConstraints().add(new ColumnConstraints(this.width));
                GridPane.setHalignment(tmp, HPos.CENTER);
                currentPos++;
            }

            currentPos = 1;
            for (int i = maxY; i >= minY; i--){
                Label tmp = new Label("" + i);
                gridPane.add(tmp, 0, currentPos, 1, 1);
                GridPane.setHalignment(tmp, HPos.CENTER);
                gridPane.getRowConstraints().add(new RowConstraints(this.height));
                currentPos++;
            }

            // We should have one list with grasses and animals so the updating the UI works faster
            // Probably will change it in the future
            for (int i = minY; i <= maxY; i++) {
                for (int j = minX; j <= maxX; j++) {
                    Object obj = this.map.objectAt(new Vector2d(j,i));
                    if (obj != null) {
                        Label objLabel = new Label(obj.toString());
                        gridPane.add(objLabel, 1 + j - minX, 1 + maxY - i, 1, 1);
                        GridPane.setHalignment(objLabel, HPos.CENTER);
                    }
                }
            }

            Scene scene = new Scene(gridPane, 400, 400);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


    }


}
