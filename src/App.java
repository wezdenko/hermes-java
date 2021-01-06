import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import gui.layouts.LoginLayout;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Scene loginScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
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