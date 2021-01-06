package gui.boxes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;


import database.classes.CollectionPoint;
import database.classes.Converter;
import database.classes.Address;

public class AddBox_CPoint {

    static CollectionPoint collectionPoint;
    static Address address;

    public static CollectionPoint display(){
        Stage window = new Stage();
        window.setMinWidth(250);
        window.setMinHeight(150);
        Scene scene;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Box");

        //Collection Point Informations TextFields
        TextField name = new TextField();
        name.setPromptText("Name");
        TextField street = new TextField();
        street.setPromptText("Street");
        TextField houseNumber = new TextField();
        houseNumber.setPromptText("House Number");
        TextField apartmentNumber = new TextField();
        apartmentNumber.setPromptText("Apartment Number");
        TextField city = new TextField();
        city.setPromptText("City");
        TextField postalCode = new TextField();
        postalCode.setPromptText("Postal Code");
        TextField countryID = new TextField();
        countryID.setPromptText("Country ID");

        //OK Button
        Button btnOK = new Button();
        btnOK.setText("OK");
        btnOK.setMinWidth(25);
        btnOK.setOnAction(e -> {
            address = new Address(0, street.getText(), Converter.StringToInt(houseNumber.getText()), Converter.StringToInt(apartmentNumber.getText()), city.getText(), postalCode.getText(), Converter.StringToInt(countryID.getText()));
            collectionPoint = new CollectionPoint(0, name.getText(), address);
            window.close(); 
        });


        //Layouts
        HBox lay1 = new HBox();
        lay1.getChildren().addAll(name);
        lay1.setSpacing(5);
        
        HBox spacing1 = new HBox();
        spacing1.setMinHeight(10);

        HBox lay2 = new HBox();
        lay2.getChildren().addAll(street, houseNumber, apartmentNumber, postalCode, city, countryID);
        lay2.setSpacing(5);

        VBox finalLay = new VBox();
        finalLay.getChildren().addAll(lay1, spacing1, lay2, btnOK);
        finalLay.setSpacing(10);
        finalLay.setPadding(new Insets(10, 10, 10, 10));

        scene = new Scene(finalLay);
        scene.getStylesheets().add("./resources/styles/styles.css");

        window.setScene(scene);
        window.showAndWait();

        return collectionPoint;
    }
}