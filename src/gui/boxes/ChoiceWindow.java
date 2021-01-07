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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ChoiceWindow {

    static String action;
    static JSONParser jsonParser;
    static JSONObject jsonObj;

    public static String display() {
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
        btnOK.setMinWidth(buttonSize);
        btnOK.setOnAction(e -> {
            action = getChoice(choiceBox);
            window.close();
        });

        // Label
        Label label = new Label("What you want to do?");

        HBox hbox = new HBox(padding);
        hbox.getChildren().addAll(choiceBox, btnOK);
        hbox.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.setPadding(new Insets(padding, padding, padding, padding));
        layout.getChildren().addAll(label, hbox);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(padding);

        scene = new Scene(layout);
        scene.getStylesheets().add(cssPath);

        window.setScene(scene);
        window.showAndWait();

        return action;
    }

    private static String getChoice(ChoiceBox<String> choiceBox) {
        String action = choiceBox.getValue();
        return action;
    }
}
