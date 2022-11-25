package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class App extends Application {
    private GrassField map;
    private HBox hBox;
    private GridPane gridPane;
    private IEngine engine;
    private final int width = 35;
    private final int height = 35;

    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    private Stage stage;
    @Override
    public void init() throws Exception {
        super.init();
        this.map = new GrassField(10);
        gridPane = new GridPane();
    }

    public void start(Stage primaryStage){
        try{
            TextField textField = new TextField();
            Button startButton = createButton(textField);
            hBox = new HBox(this.gridPane, textField, startButton);
            hBox.setAlignment(Pos.CENTER);

            this.stage= primaryStage;
            Scene scene = new Scene(hBox, 800, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    private void generateGrid(GrassField map) throws FileNotFoundException {
        setLabels();
        fillWithObjects(map);
    }

    public void fillWithObjects(GrassField map) throws FileNotFoundException {
        // We should have one list with grasses and animals so the updating the UI works faster
        // Probably will change it in the future
        for (int i = minY; i <= maxY; i++) {
            for (int j = minX; j <= maxX; j++) {
                IMapElement el = (IMapElement) map.objectAt(new Vector2d(j,i));
                if (el != null){
                    GuiElementBox vBox = new GuiElementBox(el);
                    Label label = new Label();
                    gridPane.add(vBox.getVbox(), 1 + j - minX, 1 + maxY - i, 1, 1);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }
    }

    public Button createButton(TextField textField) {
        Button startButton = new Button("Start");
        startButton.setOnAction((action) -> {
            String inputText = textField.getText();
            MoveDirection[] directions = OptionsParser.parse(inputText.split(" "));

            Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
            GrassField map = new GrassField(10);
            engine = new SimulationEngine(directions,map,this, positions,500);

            GrassField tmpMap = ((SimulationEngine) engine).calculateMapBoundaries();
             minX = Math.min(tmpMap.getLowerLeft().x, tmpMap.getUpperRight().x);
             maxX = Math.max(tmpMap.getLowerLeft().x, tmpMap.getUpperRight().x);
             minY = Math.min(tmpMap.getLowerLeft().y, tmpMap.getUpperRight().y);
             maxY = Math.max(tmpMap.getLowerLeft().y, tmpMap.getUpperRight().y);
            this.stage.setFullScreen(true);
            try {
                generateGrid(map);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            hBox.getChildren().remove(startButton);
            hBox.getChildren().remove(textField);
            Thread engineThread = new Thread(engine::run);
            engineThread.start();
        });
        return startButton;
    }

    public void setLabels(){
        Label directionsLabel = new Label("y/x");
        gridPane.getColumnConstraints().add(new ColumnConstraints(this.width));
        gridPane.getRowConstraints().add(new RowConstraints(this.height));
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(directionsLabel, 0, 0, 1, 1);
        GridPane.setHalignment(directionsLabel, HPos.CENTER);

        int currentPos = 1;
        for (int i = minX; i <= maxX; i++){
            Label tmp = new Label("" + i);
            this.gridPane.add(tmp, currentPos, 0, 1, 1);
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(this.width));
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
    }

    public void render(GrassField map) {
        try{
            this.gridPane.setGridLinesVisible(false);
            this.gridPane.getChildren().clear();
            this.gridPane.getColumnConstraints().clear();
            this.gridPane.getRowConstraints().clear();
            setLabels();
            this.gridPane.setGridLinesVisible(true);
            this.fillWithObjects(map);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        }
    }

