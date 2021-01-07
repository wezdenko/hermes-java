package database.classes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AlertBox {

  static JSONParser jsonParser;
  static JSONObject jsonObj;

    public static void display(String title, String message){
      int width=0, height=0; 
      String cssPath="";

      jsonParser = new JSONParser();
      try{
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/sharedConfiguration.json"));
        
        cssPath = (String) jsonObj.get("CSS_PATH");
        
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/boxes.json"));
        width = (int) (long) jsonObj.get("BOX_WIDTH");
        height = (int) (long) jsonObj.get("BOX_HEIGHT");

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
        window.setTitle(title);

        //OK Button
        Button btn = new Button();
        btn.setText("OK");
        btn.setOnAction(e -> window.close());

        //Label
        Label label = new Label(message);

        //Layout
        VBox layout = new VBox();
        layout.getChildren().addAll(label, btn);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);
        scene = new Scene(layout);
        scene.getStylesheets().add(cssPath);

        window.setScene(scene);
        window.showAndWait();
    }
    
}
