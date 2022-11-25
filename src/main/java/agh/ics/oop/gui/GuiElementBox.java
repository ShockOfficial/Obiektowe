package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import agh.ics.oop.MapDirection;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private VBox vbox;
    public GuiElementBox(IMapElement element) throws FileNotFoundException {
        try{
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/src/main/resources/"+element.getImagePath());

        Image image = new Image(new FileInputStream(filePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        Label label = element instanceof Animal ? new Label(element.toString()) : new Label("Grass");

        this.vbox = new VBox(2);
        this.vbox.getChildren().addAll(imageView, label);
        this.vbox.setAlignment(Pos.CENTER);

        }catch(FileNotFoundException e) {
           throw new FileNotFoundException("File not found!");
        }
    }

    public VBox getVbox() {
        return vbox;
    }
}
