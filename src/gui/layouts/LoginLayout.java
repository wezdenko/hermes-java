package gui.layouts;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.control.PasswordField;

import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

import database.classes.Parcel;
import database.classes.AlertBox;

import gui.boxes.ExitBox;

import java.sql.Connection;
import java.sql.SQLException;

import database.Database;
import database.accessors.LoginAccessor;
import database.accessors.EmployeeDataAccessor;
import database.classes.Employee;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoginLayout {

    static Scene loginScene, storeKeeperScene, courierScene, managerScene;
    static TableView<Parcel> parcelTable;
    static String action;
    static Employee employee;
    static JSONParser jsonParser;
    static JSONObject jsonObject;

    public static Scene setLoginScene(Stage primaryStage) {
      // long width=0, height=0;
      // String welcomeLabel="", loginLabel="", passwordLabel="",;

      // jsonParser = new JSONParser();
      // try{
      //   jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/configurations/mainApp.json"));
      //   width = (Long) jsonObject.get("PRIMARY_WIDTH");
      //   height = (Long) jsonObject.get("PRIMARY_HEIGHT");
      //   title = (String) jsonObject.get("TITLE");
      // }catch (FileNotFoundException fe) {
      //   fe.printStackTrace();
      // } catch (IOException io) {
      //   io.printStackTrace();
      // } catch (ParseException pe) {
      //   pe.printStackTrace();
      // }
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
            login(primaryStage, loginField, passwordField);
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
        loginScene.getStylesheets().add("./resources/styles/styles.css");

        // Login on enter key pressed
        loginScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                login(primaryStage, loginField, passwordField);
            }
        });

        return loginScene;
}
    private static void login(Stage primaryStage, TextField loginField, TextField passwordField) {
        int employeeID = -1;
        
        try {
            Connection con = Database.getConnection();
            LoginAccessor loginAccessor = new LoginAccessor(con);

            try {
                employeeID = loginAccessor.login(loginField.getText(), passwordField.getText());
            } catch (SQLException exception) {
                if (exception.getErrorCode() == LoginAccessor.LoginError) {
                    AlertBox.display("ERROR", "Wrong login!");
                } 
                else if (exception.getErrorCode() == LoginAccessor.PasswordError) {
                    AlertBox.display("ERROR", "Wrong password!");
                }
                else {
                    AlertBox.display("ERROR", "Connection error!");
                }
            } catch (Exception exception) {
                AlertBox.display("ERROR", "Unknown error!");
            }

            try {
                if (employeeID >= 0) {
                    EmployeeDataAccessor employeeAccessor = new EmployeeDataAccessor(con);
                    employee = employeeAccessor.getEmployee(
                        String.format("select * from employees where employees_id = %d", employeeID));
                }
            } catch (Exception exception) {
                employeeID = -1;
                AlertBox.display("ERROR", "Cannot download data, it might be corrupted!");
            }

            Database.closeConnection(con);

        } catch (SQLException exception) {
            AlertBox.display("ERROR", "Cannot connect to database!");
        } finally {
            if (employeeID >= 0) {
                switch (employee.getPositionID()) {
                    case 0:
                        // Scene 4 - Menager Layout
                        managerScene = ManagerLayout.setManagerScene(primaryStage);
                        managerScene.getStylesheets().add("./resources/styles/styles.css");
                        primaryStage.setScene(managerScene);
                        break;
                    case 1:
                        // Scene 3 - Courier Layout
                        courierScene = CourierLayout.setCourierScene(primaryStage);
                        courierScene.getStylesheets().add("./resources/styles/styles.css");
                        primaryStage.setScene(courierScene);
                        break;
                    case 2:
                        // Scene 2 - StoreKeeper Layout
                        storeKeeperScene = StoreKeeperLayout.setStoreKeeperScene(primaryStage);
                        storeKeeperScene.getStylesheets().add("./resources/styles/styles.css");

                        primaryStage.setScene(storeKeeperScene);
                    break;
                    default:
                        AlertBox.display("ERROR", "This employee has no assigned position!");
                        break;
                }
            }
        }
    }



    // Function Closing Window
    public static void closeProgram(Stage window) {
        Boolean answer = ExitBox.display();
        if (answer) {
            window.close();
        }
    }

}
