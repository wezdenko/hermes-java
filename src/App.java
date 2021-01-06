import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import gui.layouts.LoginLayout;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Scene loginScene;
    JSONParser jsonParser;
    JSONObject jsonObject;

    @Override
    public void start(Stage primaryStage) throws Exception {

        jsonParser = new JSONParser();
        try{
          jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/configurations/mainApp.json"));
          Long width = (Long) jsonObject.get("PRIMARY_WIDTH");
          System.out.println(width);
        
        }catch (FileNotFoundException fe) {
          fe.printStackTrace();
        } catch (IOException io) {
          io.printStackTrace();
        } catch (ParseException pe) {
          pe.printStackTrace();
        } catch(Exception e){
          System.out.println(e);
        }

        // Window Setting
        loginScene = LoginLayout.setLoginScene(primaryStage);
        primaryStage.setTitle("Hermes - delivery company");
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(700);
        primaryStage.setScene(loginScene);
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            LoginLayout.closeProgram(primaryStage);
        });

        // Last Command
        primaryStage.show();
    }
}