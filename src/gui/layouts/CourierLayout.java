package gui.layouts;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.SelectionMode;

import java.sql.Connection;
import java.sql.SQLException;

import database.classes.Parcel;
import gui.boxes.ChoiceWindow;
import gui.layouts.SubLayouts.MenuBar_own;
import database.accessors.ParcelDataAccessor;
import database.Database;
import database.classes.AlertBox;
import database.classes.Employee;

public class CourierLayout {

    static Scene courierScene;
    static TableView<Parcel> parcelsTable;
    static Employee employee;

    public static Scene setCourierScene(Employee emp) {

        MenuBar menuBar = MenuBar_own.setMenuBar();
        employee = emp;

        // Search Field
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        // Id column
        TableColumn<Parcel, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID_S"));

        // Status column
        TableColumn<Parcel, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMinWidth(80);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Cost column
        TableColumn<Parcel, Double> costColumn = new TableColumn<>("Cost");
        costColumn.setMinWidth(50);
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        // senderID column
        TableColumn<Parcel, String> senderColumn = new TableColumn<>("Sender");
        senderColumn.setMinWidth(80);
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("senderFullName"));

        // receiverID column
        TableColumn<Parcel, String> receiverColumn = new TableColumn<>("Receiver");
        receiverColumn.setMinWidth(80);
        receiverColumn.setCellValueFactory(new PropertyValueFactory<>("receiverFullName"));

        // receiver address column
        TableColumn<Parcel, String> receiverAddressColumn = new TableColumn<>("Receiver Address");
        receiverAddressColumn.setMinWidth(200);
        receiverAddressColumn.setCellValueFactory(new PropertyValueFactory<>("receiverAddress"));

        // sender address column
        TableColumn<Parcel, String> senderAddressColumn = new TableColumn<>("Sender Address");
        senderAddressColumn.setMinWidth(200);
        senderAddressColumn.setCellValueFactory(new PropertyValueFactory<>("senderAddress"));

        parcelsTable = new TableView<>();
        parcelsTable.setItems(getParcelList(employee.getCarID()));
        // All columns

        parcelsTable.getColumns().addAll(idColumn, statusColumn, senderColumn, receiverColumn, costColumn,
                receiverAddressColumn, senderAddressColumn);
        parcelsTable.autosize();
        parcelsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox courierV = new VBox();
        courierScene = new Scene(courierV, 900, 500);

        Button changeBtn = new Button("Change Status");
        changeBtn.setMinWidth(100);
        changeBtn.setMaxWidth(Double.MAX_VALUE);
        changeBtn.setOnAction(e -> changeStatus());

        VBox.setVgrow(parcelsTable, Priority.ALWAYS);
        courierV.setSpacing(5);
        courierV.setPadding(new Insets(10, 10, 10, 10));
        courierV.getChildren().addAll(menuBar, searchField, parcelsTable, changeBtn);

        return courierScene;
    }

    public static ObservableList<Parcel> getParcelList(int carID) {
        List<Parcel> parcelList = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            ParcelDataAccessor parcelAccessor = new ParcelDataAccessor(connection);
            parcelList = parcelAccessor.getParcelsList(
                String.format("select * from parcels where car_id = %d", carID));
            Database.closeConnection(connection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ObservableList<Parcel> parcels = FXCollections.observableArrayList(parcelList);

        return parcels;
    }

    public static void changeStatus() {
        String action;
        ObservableList<Parcel> parcelSelected, allParcels;
        allParcels = parcelsTable.getItems();
        parcelSelected = parcelsTable.getSelectionModel().getSelectedItems();
        ArrayList<Parcel> parcelsArray = new ArrayList<>(parcelSelected);

        action = ChoiceWindow.display();

        for (Parcel parcel : parcelsArray) {
            allParcels.remove(parcel);
            parcel.setStatus(action);
            allParcels.add(parcel);
            parcelsTable.refresh();

            switch (action) {
                case "DELIVERED":
                    parcel.setCarID(0);
                    parcel.setCollectionPointID(0);
                    parcel.setDepartmentID(0);
                    break;
                case "ON_ROAD":
                    parcel.setCarID(employee.getCarID());
                    parcel.setCollectionPointID(0);
                    parcel.setDepartmentID(0);
                    break;
                case "IN_C_POINT":
                    parcel.setCarID(0);
                    parcel.setCollectionPointID(1);
                    parcel.setDepartmentID(0);
                    break;
                case "IN_WAREHOUSE":
                    parcel.setCarID(0);
                    parcel.setCollectionPointID(0);
                    parcel.setDepartmentID(employee.getDepartmentID());
                    break;
                default:
                    break;
            }
        }

        commit(parcelsArray);
    }

    private static void commit(ArrayList<Parcel> modifiedParcelsList) {
        try {
            Connection con = Database.getConnection();
            ParcelDataAccessor parcelAccessor = new ParcelDataAccessor(con);

            for (Parcel parcel : modifiedParcelsList) {
                parcelAccessor.updateParcel(parcel);
            }

            modifiedParcelsList.clear();

            Database.closeConnection(con);
        } catch (SQLException e) {
            AlertBox.display("Error", "Cannot connect to database!");
        }
    }
}
