package gui.boxes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;


import database.classes.Car;
import database.classes.Converter;

public class AddBox_Car {

    static Car car;

    public static Car display(){
        Stage window = new Stage();
        window.setMinWidth(250);
        window.setMinHeight(150);
        Scene scene;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Box");

        //Car Informations TextFields
        TextField model = new TextField();
        model.setPromptText("Model");
        TextField plates = new TextField();
        plates.setPromptText("Plates");
        TextField departmentID = new TextField();
        departmentID.setPromptText("Department ID");

        //OK Button
        Button btnOK = new Button();
        btnOK.setText("OK");
        btnOK.setMinWidth(25);
        btnOK.setOnAction(e -> {
            car = new Car(0, plates.getText(), model.getText(), Converter.StringToInt(departmentID.getText()), 0);
            window.close(); 
        });


        //Layouts
        HBox lay1 = new HBox();
        lay1.getChildren().addAll(model, plates);
        lay1.setSpacing(5);
        
        HBox spacing1 = new HBox();
        spacing1.setMinHeight(10);

        HBox lay2 = new HBox();
        lay2.getChildren().add(departmentID);
        lay2.setSpacing(5);

        VBox finalLay = new VBox();
        finalLay.getChildren().addAll(lay1, spacing1, lay2, btnOK);
        finalLay.setSpacing(10);
        finalLay.setPadding(new Insets(10, 10, 10, 10));

        scene = new Scene(finalLay);

        window.setScene(scene);
        window.showAndWait();

        return car;
    }
}
