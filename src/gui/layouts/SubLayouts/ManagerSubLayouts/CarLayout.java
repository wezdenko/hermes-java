package gui.layouts.SubLayouts.ManagerSubLayouts;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.Connection;
import java.sql.SQLException;

import gui.boxes.AddBox_Car;
import database.accessors.CarDataAccessor;
import database.Database;
import database.classes.AlertBox;
import database.classes.Car;
import database.classes.Converter;

import gui.layouts.LoginLayout;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class CarLayout {

    static TableView<Car> carTable;
    static JSONParser jsonParser;
    static JSONObject jsonObj;
    static List<Car> modifiedCars;
    static List<Car> deletedCars;

    public static VBox setCarLayout(int width, Stage primaryStage) {
      int spacingDivider=0, spacingButtonDivider=0, padding=0, buttonMinWidth=0;
      int mediumTabWidth=0, spacingSize=0;
      String search="", deleteLabel="",  addLabel ="", commitLabel ="", logOutLabel ="";


      jsonParser = new JSONParser();
      try{
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/sharedConfiguration.json"));
        
        spacingDivider = (int) (long) jsonObj.get("SPACING_WIDTH_DIVIDER");
        spacingButtonDivider = (int) (long) jsonObj.get("SPACING_BUTTON_DIVIDER");
        padding = (int) (long) jsonObj.get("PADDING");
        spacingSize = (int) (long) jsonObj.get("SPACING");

        buttonMinWidth = (int) (long) jsonObj.get("BUTTON_MIN_WIDTH");
        mediumTabWidth = (int) (long) jsonObj.get("TAB_MIN_WIDTH_MEDIUM");

        search = (String) jsonObj.get("SEARCH");
        deleteLabel = (String) jsonObj.get("DELETE_BUTTON");
        addLabel = (String) jsonObj.get("ADD_BUTTON");
        commitLabel = (String) jsonObj.get("COMMIT_BUTTON");
        logOutLabel = (String) jsonObj.get("LOGOUT_BUTTON");


      }catch (FileNotFoundException fe) {
        fe.printStackTrace();
      } catch (IOException io) {
        io.printStackTrace();
      } catch (ParseException pe) {
        pe.printStackTrace();
      }

        // Search Field
        TextField searchField = new TextField();
        searchField.setPromptText(search);

        // Setting ParcelTable

        // ID Column
        TableColumn<Car, String> idColumn = new TableColumn<>("Car ID");
        idColumn.setMinWidth(mediumTabWidth);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("carsID_S"));

        // Plates Column
        TableColumn<Car, String> platesColumn = new TableColumn<>("Plates");
        platesColumn.setMinWidth(mediumTabWidth);
        platesColumn.setCellValueFactory(new PropertyValueFactory<>("plates"));
        platesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        platesColumn.setOnEditCommit(new EventHandler<CellEditEvent<Car, String>>() {
            @Override
            public void handle(CellEditEvent<Car, String> t) {
                ((Car)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setPlates(t.getNewValue());
                carTable.refresh();
                modifiedCars.add(((Car)t.getTableView().getItems().get(t.getTablePosition().getRow())));
            }
        });

        // Model Column
        TableColumn<Car, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setMinWidth(mediumTabWidth);
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        modelColumn.setOnEditCommit(new EventHandler<CellEditEvent<Car, String>>() {
            @Override
            public void handle(CellEditEvent<Car, String> t) {
                ((Car)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setModel(t.getNewValue());
                carTable.refresh();
                modifiedCars.add(((Car)t.getTableView().getItems().get(t.getTablePosition().getRow())));
            }
        });

        // DepartmentID Column
        TableColumn<Car, String> departmentIDColumn = new TableColumn<>("Department ID");
        departmentIDColumn.setMinWidth(mediumTabWidth);
        departmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("departmentID_S"));
        departmentIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        departmentIDColumn.setOnEditCommit(new EventHandler<CellEditEvent<Car, String>>() {
            @Override
            public void handle(CellEditEvent<Car, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Car) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setDepartmentID(Converter.StringToInt(t.getNewValue()));
                }
                carTable.refresh();
                modifiedCars.add(((Car)t.getTableView().getItems().get(t.getTablePosition().getRow())));
            }
        });

        // EmployeeID Column
        TableColumn<Car, String> employeeIDColumn = new TableColumn<>("Employee ID");
        employeeIDColumn.setMinWidth(mediumTabWidth);
        employeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID_S"));
        employeeIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        employeeIDColumn.setOnEditCommit(new EventHandler<CellEditEvent<Car, String>>() {
            @Override
            public void handle(CellEditEvent<Car, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Car) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setEmployeeID(Converter.StringToInt(t.getNewValue()));
                }
                carTable.refresh();
            }
        });

        // List of modified cars
        modifiedCars = new ArrayList<>();
        deletedCars = new ArrayList<>();

        // Final Table
        carTable = new TableView<>();
        carTable.setItems(getCar());
        carTable.getColumns().addAll(idColumn, platesColumn, modelColumn, departmentIDColumn, employeeIDColumn);
        carTable.setEditable(true);
        carTable.autosize();
        carTable.setMaxHeight(Double.MAX_VALUE);
        carTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Init scene and layout
        VBox layout2 = new VBox();

        // Buttons under table
        int spacingWidth = width / spacingDivider;

        HBox buttonLayout = new HBox();
        buttonLayout.setPadding(new Insets(padding, 0, padding, 0));
        buttonLayout.setSpacing(spacingWidth / spacingButtonDivider);

        // Delete
        Button deletButton = new Button(deleteLabel);
        HBox.setHgrow(deletButton, Priority.ALWAYS);
        deletButton.setMinWidth(buttonMinWidth);
        deletButton.setMaxWidth(Double.MAX_VALUE);
        deletButton.setOnAction(e -> deleteButtonClicked());

        // Add
        Button addButton = new Button(addLabel);
        HBox.setHgrow(addButton, Priority.ALWAYS);
        addButton.setMinWidth(buttonMinWidth);
        addButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setOnAction(e -> addButtonClicked());

        // Commit
        Button btn6 = new Button(commitLabel);
        HBox.setHgrow(btn6, Priority.ALWAYS);
        btn6.setMinWidth(buttonMinWidth);
        btn6.setMaxWidth(Double.MAX_VALUE);
        btn6.setOnAction(e -> commit());

        // Log Out
        Button logOutBtn = new Button(logOutLabel);
        HBox.setHgrow(logOutBtn, Priority.ALWAYS);
        logOutBtn.setMinWidth(buttonMinWidth);
        logOutBtn.setMaxWidth(Double.MAX_VALUE);
        logOutBtn.setOnAction(e -> {
          Scene loginScene;
          loginScene = LoginLayout.setLoginScene(primaryStage);
          primaryStage.setScene(loginScene);
        });

        buttonLayout.getChildren().addAll(deletButton, addButton, btn6, logOutBtn);

        // Scene/layout
        VBox.setVgrow(carTable, Priority.ALWAYS);
        layout2.setSpacing(spacingSize);
        layout2.setPadding(new Insets(padding, padding, padding, padding));
        layout2.getChildren().addAll(searchField, carTable, buttonLayout);

        // Final Commands
        return layout2;
    }

    // Get all of the
    public static ObservableList<Car> getCar() {
        List<Car> carList = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            CarDataAccessor carAccessor = new CarDataAccessor(connection);
            carList = carAccessor.getCarList(
                    "select * from cars");
            Database.closeConnection(connection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ObservableList<Car> cars = FXCollections.observableArrayList(carList);

        return cars;
    }

    // Delete button clicked
    public static void deleteButtonClicked() {
        ObservableList<Car> selected;
        selected = carTable.getSelectionModel().getSelectedItems();
        ArrayList<Car> rows = new ArrayList<>(selected);
        rows.forEach(row -> carTable.getItems().remove(row));
        deletedCars.addAll(rows);
    }

    //Add button clicked
    public static void addButtonClicked(){
        Car car = AddBox_Car.display();
        carTable.getItems().add(car);
    }

    // Commit button clicked
    public static void commit() {
        try {
            Connection con = Database.getConnection();
            CarDataAccessor carAccessor = new CarDataAccessor(con);

            for (Car car : modifiedCars) {
                carAccessor.updateCar(car);
            }

            for (Car car : deletedCars) {
                carAccessor.deleteCar(car);
            }

            modifiedCars.clear();
            deletedCars.clear();

            Database.closeConnection(con);
        } catch (SQLException e) {
            AlertBox.display("Error", "Cannot connect to database!");
        }
    }
}
