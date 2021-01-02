package gui.boxes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;

import database.classes.Parcel;
import database.classes.Client;
import database.classes.ClientAddress;
import database.classes.Converter;

public class AddBox {

    static Parcel parcel;
    static ClientAddress sAddress;
    static ClientAddress rAddress;
    static Client sClient;
    static Client rClient;

    public static Parcel display(){
        Stage window = new Stage();
        window.setMinWidth(250);
        window.setMinHeight(150);
        Scene scene;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Gived Out Box");

        //Parcel Informations TextFields
        //Label msg = new Label("Enter new parcel");
        TextField carID = new TextField();
        carID.setPromptText("Car ID");
        TextField rName = new TextField();
        rName.setPromptText("Receiver Name");
        TextField rSurname = new TextField();
        rSurname.setPromptText("Receiver Surname");
        TextField rEmail = new TextField();
        rEmail.setPromptText("Receiver Email");
        TextField rPhone = new TextField();
        rPhone.setPromptText("Receiver Phone Number");
        TextField rStreet = new TextField();
        rStreet.setPromptText("Receiver Street");
        TextField rHouseNumber = new TextField();
        rHouseNumber.setPromptText("Receiver House Number");
        TextField rApartmentNumber = new TextField();
        rApartmentNumber.setPromptText("Receiver Apartment Number");
        TextField rCity = new TextField();
        rCity.setPromptText("Receiver City");
        TextField rPostalCode = new TextField();
        rPostalCode.setPromptText("Receiver Postal Code");
        TextField rCountryID = new TextField();
        rCountryID.setPromptText("Receiver Country ID");
        TextField sName = new TextField();
        sName.setPromptText("Sender Name");
        TextField sSurname = new TextField();
        sSurname.setPromptText("Sender Surname");
        TextField sEmail = new TextField();
        sEmail.setPromptText("Sender Email");
        TextField sPhone = new TextField();
        sPhone.setPromptText("Sender Phone Number");
        TextField sStreet = new TextField();
        sStreet.setPromptText("Sender Street");
        TextField sHouseNumber = new TextField();
        sHouseNumber.setPromptText("Sender House Number");
        TextField sApartmentNumber = new TextField();
        sApartmentNumber.setPromptText("Sender Apartment Number");
        TextField sCity = new TextField();
        sCity.setPromptText("Sender City");
        TextField sPostalCode = new TextField();
        sPostalCode.setPromptText("Sender Postal Code");
        TextField sCountryID = new TextField();
        sCountryID.setPromptText("Sender Country ID");
        TextField status = new TextField();
        status.setPromptText("Status");
        TextField length = new TextField();
        length.setPromptText("Length");
        TextField width = new TextField();
        width.setPromptText("Width");
        TextField height = new TextField();
        height.setPromptText("Height");
        TextField code = new TextField();
        code.setPromptText("Code");
        TextField collectionPointID = new TextField();
        collectionPointID.setPromptText("Collection Point ID");
        TextField departmentID = new TextField();
        departmentID.setPromptText("Department ID");
        TextField cost = new TextField();
        cost.setPromptText("Cost");
        TextField weight = new TextField();
        weight.setPromptText("Weight");

        //OK Button
        Button btnOK = new Button();
        btnOK.setText("OK");
        btnOK.setMinWidth(25);
        btnOK.setOnAction(e -> {
            sAddress = new ClientAddress(0, sStreet.getText(), Converter.StringToInt(sHouseNumber.getText()), Converter.StringToInt(sApartmentNumber.getText()), sCity.getText(), sPostalCode.getText(), Converter.StringToInt(sCountryID.getText()));
            rAddress = new ClientAddress(0, rStreet.getText(), Converter.StringToInt(rHouseNumber.getText()), Converter.StringToInt(rApartmentNumber.getText()), rCity.getText(), rPostalCode.getText(), Converter.StringToInt(rCountryID.getText()));
            sClient = new Client(0, sName.getText(), sSurname.getText(), sEmail.getText(), sPhone.getText(), sAddress);
            rClient = new Client(0, rName.getText(), rSurname.getText(), rEmail.getText(), rPhone.getText(), rAddress);
            parcel = new Parcel(1, status.getText(), Converter.StringToDouble(cost.getText()), sClient, rClient ,Converter.StringToDouble(weight.getText()), Converter.StringToDouble(length.getText()), Converter.StringToDouble(width.getText()), Converter.StringToDouble(height.getText()), Converter.StringToInt(code.getText()), Converter.StringToInt(carID.getText()),Converter.StringToInt(collectionPointID.getText()), Converter.StringToInt(departmentID.getText()));
            window.close(); 
        });


        //Layouts
        HBox senderLay1 = new HBox();
        senderLay1.getChildren().addAll(sName, sSurname, sCountryID, sPostalCode,sCity);
        senderLay1.setSpacing(5);
        
        HBox senderLay2 = new HBox();
        senderLay2.getChildren().addAll(sStreet, sHouseNumber, sApartmentNumber, sPhone, sEmail);
        senderLay2.setSpacing(5);
        
        HBox spacing1 = new HBox();
        spacing1.setMinHeight(10);

        HBox receiverLay1 = new HBox();
        receiverLay1.getChildren().addAll(rName, rSurname, rCountryID, rPostalCode,rCity);
        receiverLay1.setSpacing(5);

        
        HBox receiverLay2 = new HBox();
        receiverLay2.getChildren().addAll(rStreet, rHouseNumber, rApartmentNumber, rPhone, rEmail);
        receiverLay2.setSpacing(5);

        HBox spacing2 = new HBox();
        spacing2.setMinHeight(10);

        HBox othersLay1 = new HBox();
        othersLay1.getChildren().addAll(code, collectionPointID, departmentID, status, cost);
        othersLay1.setSpacing(5);

        HBox othersLay2 = new HBox();
        othersLay2.getChildren().addAll(carID, length, width, height, weight);
        othersLay2.setSpacing(5);

        VBox finalLay = new VBox();
        finalLay.getChildren().addAll(senderLay1, senderLay2, spacing1, receiverLay1, receiverLay2, spacing2, othersLay1, othersLay2 ,btnOK);
        finalLay.setSpacing(10);
        finalLay.setPadding(new Insets(10, 10, 10, 10));

        scene = new Scene(finalLay);

        window.setScene(scene);
        window.showAndWait();

        return parcel;
    }
}