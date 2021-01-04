import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

import database.classes.Parcel;
import database.classes.AlertBox;

import gui.boxes.ExitBox;
import gui.layouts.CourierLayout;
import gui.layouts.StoreKeeperLayout;
import gui.layouts.ManagerLayout;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Scene loginScene, storeKeeperScene, courierScene, managerScene;
    TableView<Parcel> parcelTable;
    String action;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Welcome Message - Label
        Label welcomeMsg = new Label("You have to enter your login and password");

        // Login - Label&TextField
        Label loginMsg = new Label("Login:");
        TextField loginField = new TextField();

        // Password - Label&TextField
        Label passwordMsg = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        // Login button
        Button loginBtn = new Button();
        loginBtn.setText("Log In");
        loginBtn.setOnAction(e -> {
            String courierLogin, courierPassword;
            String storeKeeperLogin, storeKeeperPassword;
            courierLogin = "courier";
            courierPassword = "courier";
            storeKeeperLogin = "storekeeper";
            storeKeeperPassword = "storekeeper";

            if (loginField.getText().equals(courierLogin) && passwordField.getText().equals(courierPassword)) {
                // System.out.println("logged in");
                primaryStage.setScene(courierScene);
            } else if (loginField.getText().equals(storeKeeperLogin)
                    && passwordField.getText().equals(storeKeeperPassword)) {
                primaryStage.setScene(storeKeeperScene);
            } else {
                AlertBox.display("ERROR", "WRONG PASSWORD OR LOGIN");
                System.out.println(loginField.getText());
            }
        });

        // Scene 1 - Logging layout
        // Grid
        GridPane loginLayout = new GridPane();
        loginLayout.setPadding(new Insets(10, 10, 10, 10));
        loginLayout.setVgap(8);
        loginLayout.setHgap(10);
        GridPane.setConstraints(welcomeMsg, 1, 1);
        GridPane.setConstraints(loginMsg, 0, 3);
        GridPane.setConstraints(loginField, 1, 3);
        GridPane.setConstraints(passwordMsg, 0, 4);
        GridPane.setConstraints(passwordField, 1, 4);
        GridPane.setConstraints(loginBtn, 1, 5);
        loginLayout.getChildren().addAll(welcomeMsg, loginMsg, loginField, passwordMsg, passwordField, loginBtn);
        loginLayout.setAlignment(Pos.BASELINE_CENTER);
        loginScene = new Scene(loginLayout, 400, 250);

        // Scene 2 - StoreKeeper Layout
        storeKeeperScene = StoreKeeperLayout.setStoreKeeperScene();

        // Scene 3 - Courier Layout
        courierScene = CourierLayout.setCourierScene();

        // Scene 4 - Menager Layout
        managerScene = ManagerLayout.setManagerScene();

        // Window Setting
        primaryStage.setTitle("Hermes - delivery company");
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(700);
        primaryStage.setScene(managerScene);
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(primaryStage);
        });

        // Last Command
        primaryStage.show();
    }

    // Function Closing Window
    private void closeProgram(Stage window) {
        Boolean answer = ExitBox.display();
        if (answer) {
            window.close();
        }
    }
}