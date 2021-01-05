package gui.boxes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;


import database.classes.Position;
import database.classes.Converter;

public class AddBox_Pos {

    static Position position;

    public static Position display(){
        Stage window = new Stage();
        window.setMinWidth(250);
        window.setMinHeight(150);
        Scene scene;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Box");

        //Car Informations TextFields
        TextField name = new TextField();
        name.setPromptText("Name");
        TextField maxSalary = new TextField();
        maxSalary.setPromptText("Max Salary");
        TextField minSalary = new TextField();
        minSalary.setPromptText("Min Salary");

        //OK Button
        Button btnOK = new Button();
        btnOK.setText("OK");
        btnOK.setMinWidth(25);
        btnOK.setOnAction(e -> {
            position = new Position(0, name.getText(), Converter.StringToInt(maxSalary.getText()), Converter.StringToInt(minSalary.getText()));
            window.close(); 
        });


        //Layouts
        HBox lay1 = new HBox();
        lay1.getChildren().addAll(name);
        lay1.setSpacing(5);
        
        HBox spacing1 = new HBox();
        spacing1.setMinHeight(10);

        HBox lay2 = new HBox();
        lay2.getChildren().addAll(minSalary, maxSalary);
        lay2.setSpacing(5);

        VBox finalLay = new VBox();
        finalLay.getChildren().addAll(lay1, spacing1, lay2, btnOK);
        finalLay.setSpacing(10);
        finalLay.setPadding(new Insets(10, 10, 10, 10));

        scene = new Scene(finalLay);

        window.setScene(scene);
        window.showAndWait();

        return position;
    }
}
