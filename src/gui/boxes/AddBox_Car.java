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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AddBox_Car {

    static Car car;
    static JSONParser jsonParser;
    static JSONObject jsonObj;

    public static Car display(){
      int width=0, height=0, buttonSize=0, padding=0, spacing=0; 
      String cssPath="";

      jsonParser = new JSONParser();
      try{
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/sharedConfiguration.json"));
        
        padding = (int) (long) jsonObj.get("PADDING");
        spacing = (int) (long) jsonObj.get("SPACING");
        cssPath = (String) jsonObj.get("CSS_PATH");
        
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/boxes.json"));
        width = (int) (long) jsonObj.get("BOX_WIDTH");
        height = (int) (long) jsonObj.get("BOX_HEIGHT");
        buttonSize = (int) (long) jsonObj.get("BUTTON_SIZE");

      }catch (FileNotFoundException fe) {
        fe.printStackTrace();
      } catch (IOException io) {
        io.printStackTrace();
      } catch (ParseException pe) {
        pe.printStackTrace();
      }

        Stage window = new Stage();
        window.setMinWidth(width);
        window.setMinHeight(height);
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
        btnOK.setMinWidth(buttonSize);
        btnOK.setOnAction(e -> {
            car = new Car(0, plates.getText(), model.getText(), Converter.StringToInt(departmentID.getText()), 0);
            window.close(); 
        });


        //Layouts
        HBox lay1 = new HBox();
        lay1.getChildren().addAll(model, plates);
        lay1.setSpacing(spacing);
        
        HBox spacing1 = new HBox();
        spacing1.setMinHeight(padding);

        HBox lay2 = new HBox();
        lay2.getChildren().add(departmentID);
        lay2.setSpacing(spacing);

        VBox finalLay = new VBox();
        finalLay.getChildren().addAll(lay1, spacing1, lay2, btnOK);
        finalLay.setSpacing(10);
        finalLay.setPadding(new Insets(padding, padding, padding, padding));

        scene = new Scene(finalLay);
        scene.getStylesheets().add(cssPath);

        window.setScene(scene);
        window.showAndWait();

        return car;
    }
}
