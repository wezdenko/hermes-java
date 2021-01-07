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

public class CarLayout {

    static TableView<Car> carTable;
    static List<Car> modifiedCars;
    static List<Car> deletedCars;

    public static VBox setCarLayout(Double sceneWidth) {
        // Search Field
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        // Setting ParcelTable

        // ID Column
        TableColumn<Car, String> idColumn = new TableColumn<>("Car ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("carsID_S"));

        // Plates Column
        TableColumn<Car, String> platesColumn = new TableColumn<>("Plates");
        platesColumn.setMinWidth(100);
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
        modelColumn.setMinWidth(100);
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
        departmentIDColumn.setMinWidth(100);
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
        employeeIDColumn.setMinWidth(100);
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
        Double width = sceneWidth / 6;

        HBox buttonLayout = new HBox();
        buttonLayout.setPadding(new Insets(10, 0, 10, 0));
        buttonLayout.setSpacing(width / 3);

        // Delete
        Button deletButton = new Button("Delete");
        HBox.setHgrow(deletButton, Priority.ALWAYS);
        deletButton.setMinWidth(width);
        deletButton.setMaxWidth(Double.MAX_VALUE);
        deletButton.setOnAction(e -> deleteButtonClicked());

        // Add
        Button addButton = new Button("Add");
        HBox.setHgrow(addButton, Priority.ALWAYS);
        addButton.setMinWidth(width);
        addButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setOnAction(e -> addButtonClicked());

        // Commit
        Button btn6 = new Button("Commit");
        HBox.setHgrow(btn6, Priority.ALWAYS);
        btn6.setMinWidth(width);
        btn6.setMaxWidth(Double.MAX_VALUE);
        btn6.setOnAction(e -> commit());

        buttonLayout.getChildren().addAll(deletButton, addButton, btn6);

        // Scene/layout
        VBox.setVgrow(carTable, Priority.ALWAYS);
        layout2.setSpacing(5);
        layout2.setPadding(new Insets(10, 10, 10, 10));
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
