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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GivedOutBox {
    static Integer carID;
    static boolean clicked;
    static JSONParser jsonParser;
    static JSONObject jsonObj;

    public static Integer display() {
      int width=0, height=0, buttonSize=0, padding=0; 
      String cssPath="";

      jsonParser = new JSONParser();
      try{
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/sharedConfiguration.json"));
        
        padding = (int) (long) jsonObj.get("PADDING");
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
        btnOK.setMinWidth(buttonSize);
        btnOK.setOnAction(e -> {
            carID = Converter.StringToInt(carIdField.getText());
            clicked = true;
            window.close();
        });

        // Layout
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(padding, padding, padding, padding));
        layout.setVgap(6);
        layout.setHgap(2);
        GridPane.setConstraints(msg, 0, 1);
        GridPane.setConstraints(carIdField, 0, 4);
        GridPane.setConstraints(btnOK, 1, 4);
        layout.getChildren().addAll(msg, carIdField, btnOK);
        layout.setAlignment(Pos.BASELINE_CENTER);

        scene = new Scene(layout);
        scene.getStylesheets().add(cssPath);

        window.setScene(scene);
        window.showAndWait();

        if(clicked){
            return carID;
        }

        return 0;
}}
