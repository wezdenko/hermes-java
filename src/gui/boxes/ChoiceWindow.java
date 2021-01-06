package gui.boxes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChoiceWindow {

    static String action;

    public static String display() {
        Stage window = new Stage();
        window.setMinWidth(250);
        window.setMinHeight(150);
        Scene scene;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Change Status");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        // getItems
        choiceBox.getItems().addAll("Delivered", "Road", "Car", "Collection Point", "Warehouse");

        // Set a default value
        choiceBox.setValue("Delivered");

        // No Button
        Button btnOK = new Button();
        btnOK.setText("OK");
        btnOK.setMinWidth(40);
        btnOK.setOnAction(e -> {
            action = getChoice(choiceBox);
            window.close();
        });

        // Label
        Label label = new Label("What you want to do?");

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(choiceBox, btnOK);
        hbox.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(label, hbox);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        scene = new Scene(layout);
        scene.getStylesheets().add("./resources/styles/styles.css");

        window.setScene(scene);
        window.showAndWait();

        return action;
    }

    private static String getChoice(ChoiceBox<String> choiceBox) {
        String action = choiceBox.getValue();
        return action;
    }
}
