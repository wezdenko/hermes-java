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

import java.sql.Connection;
import java.sql.SQLException;

import database.Database;
import database.accessors.LoginAccessor;
import database.accessors.EmployeeDataAccessor;
import database.classes.Employee;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Scene loginScene, storeKeeperScene, courierScene, managerScene;
    TableView<Parcel> parcelTable;
    String action;
    Employee employee;

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
                            primaryStage.setScene(managerScene);
                            break;
                        case 1:
                            primaryStage.setScene(courierScene);
                            break;
                        case 2:
                            primaryStage.setScene(storeKeeperScene);
                            break;
                        default:
                            AlertBox.display("ERROR", "This employee has no assigned position!");
                            break;
                    }
                }
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

        // Scene 4 - Manager Layout
        // to be built

        // Window Setting
        primaryStage.setTitle("Hermes - delivery company");
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(500);
        primaryStage.setScene(loginScene);
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