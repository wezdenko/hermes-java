package gui.boxes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;

import database.classes.Converter;

public class GivedOutBox {
    static Integer carID;
    static boolean clicked;

    public static Integer display() {
        Stage window = new Stage();
        window.setMinWidth(250);
        window.setMinHeight(150);
        Scene scene;
        clicked = false;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Gived Out Box");

        // Car ID - Label&TextField
        Label msg = new Label("Enter car ID:");
        TextField carIdField = new TextField();
        carIdField.setPromptText("...");

        // OK Button
        Button btnOK = new Button();
        btnOK.setText("OK");
        btnOK.setMinWidth(25);
        btnOK.setOnAction(e -> {
            carID = Converter.StringToInt(carIdField.getText());
            clicked = true;
            window.close();
        });

        // Layout
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(6);
        layout.setHgap(2);
        layout.setConstraints(msg, 0, 1);
        layout.setConstraints(carIdField, 0, 4);
        layout.setConstraints(btnOK, 1, 4);
        layout.getChildren().addAll(msg, carIdField, btnOK);
        layout.setAlignment(Pos.BASELINE_CENTER);

        scene = new Scene(layout);

        window.setScene(scene);
        window.showAndWait();

        if(clicked){
            return carID;
        }

        return 0;
}}
