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
    static JSONObject jsonObj;

    public static Scene setLoginScene(Stage primaryStage) {
      long width=0, height=0, padding=0;
      String welcomeLabel="", loginLabel="", passwordLabel="", loginText="", cssPath="";

      jsonParser = new JSONParser();
      try{
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/loginLayout.json"));
        
        width = (Long) jsonObj.get("LOGIN_WIDTH");
        height = (Long) jsonObj.get("LOGIN_HEIGHT");

        welcomeLabel = (String) jsonObj.get("WELCOME_LABEL");
        loginLabel = (String) jsonObj.get("LOGIN_LABEL");
        passwordLabel = (String) jsonObj.get("PASSWORD_LABEL");
        loginText = (String) jsonObj.get("LOGIN_TEXT");

        
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/sharedConfiguration.json"));
        
        padding = (Long) jsonObj.get("PADDING");
        cssPath = (String) jsonObj.get("CSS_PATH");

      }catch (FileNotFoundException fe) {
        fe.printStackTrace();
      } catch (IOException io) {
        io.printStackTrace();
      } catch (ParseException pe) {
        pe.printStackTrace();
      }
        // Welcome Message - Label
        Label welcomeMsg = new Label(welcomeLabel);

        // Login - Label&TextField
        Label loginMsg = new Label(loginLabel);
        TextField loginField = new TextField();

        // Password - Label&TextField
        Label passwordMsg = new Label(passwordLabel);
        PasswordField passwordField = new PasswordField();

        // Login button
        Button loginBtn = new Button();
        loginBtn.setText(loginText);
        loginBtn.setOnAction(e -> {
            login(primaryStage, loginField, passwordField);
        });   

        // Scene 1 - Logging layout
        // Grid
        GridPane loginLayout = new GridPane();
        loginLayout.setPadding(new Insets(padding, padding, padding, padding));
        loginLayout.setVgap(padding);
        loginLayout.setHgap(padding);
        GridPane.setConstraints(welcomeMsg, 1, 1);
        GridPane.setConstraints(loginMsg, 0, 3);
        GridPane.setConstraints(loginField, 1, 3);
        GridPane.setConstraints(passwordMsg, 0, 4);
        GridPane.setConstraints(passwordField, 1, 4);
        GridPane.setConstraints(loginBtn, 1, 5);
        loginLayout.getChildren().addAll(welcomeMsg, loginMsg, loginField, passwordMsg, passwordField, loginBtn);
        loginLayout.setAlignment(Pos.BASELINE_CENTER);
        loginScene = new Scene(loginLayout, width, height);
        loginScene.getStylesheets().add(cssPath);

        // Login on enter key pressed
        loginScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                login(primaryStage, loginField, passwordField);
            }
        });

        return loginScene;
}
    private static void login(Stage primaryStage, TextField loginField, TextField passwordField) {
      String errorMsg="", loginError="", passwordError="", connectionError="";
      String unknownError="",corruptionError="", dbConnectionError="", noPositionError="", cssPath="";

      jsonParser = new JSONParser();
      try{
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/errors.json"));
        
        errorMsg = (String) jsonObj.get("ERROR_MSG");
        loginError = (String) jsonObj.get("LOGIN_ERROR");
        passwordError = (String) jsonObj.get("PASSWORD_ERROR");
        connectionError = (String) jsonObj.get("CONNECTION_ERROR");
        unknownError = (String) jsonObj.get("UNKNOWN_ERROR");
        corruptionError = (String) jsonObj.get("CORRUPTION_ERROR");
        dbConnectionError = (String) jsonObj.get("DB_CONNECTION_ERROR");
        noPositionError = (String) jsonObj.get("NO_POSITION_ERROR");

        
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/sharedConfiguration.json"));
        
        cssPath = (String) jsonObj.get("CSS_PATH");

      }catch (FileNotFoundException fe) {
        fe.printStackTrace();
      } catch (IOException io) {
        io.printStackTrace();
      } catch (ParseException pe) {
        pe.printStackTrace();
      }

        int employeeID = -1;
        
        try {
            Connection con = Database.getConnection();
            LoginAccessor loginAccessor = new LoginAccessor(con);

            try {
                employeeID = loginAccessor.login(loginField.getText(), passwordField.getText());
            } catch (SQLException exception) {
                if (exception.getErrorCode() == LoginAccessor.LoginError) {
                    AlertBox.display(errorMsg, loginError);
                } 
                else if (exception.getErrorCode() == LoginAccessor.PasswordError) {
                    AlertBox.display(errorMsg, passwordError);
                }
                else {
                    AlertBox.display(errorMsg, connectionError);
                }
            } catch (Exception exception) {
                AlertBox.display(errorMsg, unknownError);
            }

            try {
                if (employeeID >= 0) {
                    EmployeeDataAccessor employeeAccessor = new EmployeeDataAccessor(con);
                    employee = employeeAccessor.getEmployee(
                        String.format("select * from employees where employees_id = %d", employeeID));
                }
            } catch (Exception exception) {
                employeeID = -1;
                AlertBox.display(errorMsg, corruptionError);
            }

            Database.closeConnection(con);

        } catch (SQLException exception) {
            AlertBox.display(errorMsg, dbConnectionError);
        } finally {
            if (employeeID >= 0) {
                switch (employee.getPositionID()) {
                    case 0:
                        // Scene 4 - Menager Layout
                        managerScene = ManagerLayout.setManagerScene(primaryStage);
                        managerScene.getStylesheets().add(cssPath);
                        primaryStage.setScene(managerScene);
                        break;
                    case 1:
                        // Scene 3 - Courier Layout
                        courierScene = CourierLayout.setCourierScene(primaryStage);
                        courierScene.getStylesheets().add(cssPath);
                        primaryStage.setScene(courierScene);
                        break;
                    case 2:
                        // Scene 2 - StoreKeeper Layout
                        storeKeeperScene = StoreKeeperLayout.setStoreKeeperScene(primaryStage);
                        storeKeeperScene.getStylesheets().add(cssPath);

                        primaryStage.setScene(storeKeeperScene);
                    break;
                    default:
                        AlertBox.display(errorMsg, noPositionError);
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
