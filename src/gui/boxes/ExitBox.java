package gui.boxes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ExitBox {

    static boolean answer;
    static JSONParser jsonParser;
    static JSONObject jsonObj;

    public static boolean display(){
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
        window.setTitle("Exit");

        //Yes Button
        Button btnY = new Button();
        btnY.setMinWidth(buttonSize);
        btnY.setText("Yes");
        btnY.setOnAction(e -> {
            window.close(); 
            answer = true;
        });

        //No Button
        Button btnN = new Button();
        btnN.setMinWidth(buttonSize);
        btnN.setText("No");
        btnN.setOnAction(e -> {
            window.close(); 
            answer = false;
        });

        //Label
        Label label = new Label("Are you sure you want to exit?");

        //Layout
        HBox buttons = new HBox(padding);
        buttons.getChildren().addAll(btnY, btnN);
        buttons.setAlignment(Pos.CENTER);


        VBox layout = new VBox();
        layout.setPadding(new Insets(padding, padding, padding, padding));
        layout.getChildren().addAll(label, buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(padding);

        scene = new Scene(layout);
        scene.getStylesheets().add(cssPath);

        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
    
}
