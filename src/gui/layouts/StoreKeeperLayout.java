
package gui.layouts;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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

import database.classes.Parcel;
import gui.boxes.GivedOutBox;
import gui.boxes.AddBox;

import database.accessors.ParcelDataAccessor;
import database.Database;

import database.classes.Converter;

public class StoreKeeperLayout {

    static Scene storeKeeperScene;
    static TableView<Parcel> parcelTable;
    static String action;

    public static Scene setStoreKeeperScene() {
        // Search Field
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        // Setting ParcelTable

        // ID Column
        TableColumn<Parcel, String> idColumn = new TableColumn<>("Parcel ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID_S"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        idColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToInt(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setID(Converter.StringToInt(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });

        // sName Column
        TableColumn<Parcel, String> senderColumn = new TableColumn<>("Sender Name");
        senderColumn.setMinWidth(200);
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        senderColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        senderColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setSenderName(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // rName Column
        TableColumn<Parcel, String> receiverColumn = new TableColumn<>("Receiver Name");
        receiverColumn.setMinWidth(200);
        receiverColumn.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        receiverColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        receiverColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel,
        String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setReceiverName(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // carID Column
        TableColumn<Parcel, String> carIdColumn = new TableColumn<>("Car ID");
        carIdColumn.setMinWidth(100);
        carIdColumn.setCellValueFactory(new PropertyValueFactory<>("carID_S"));
        carIdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        carIdColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToInt(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setCarID(Converter.StringToInt(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });

        // Final Table
        parcelTable = new TableView<>();
        parcelTable.setItems(getParcel());
        parcelTable.getColumns().addAll(idColumn, senderColumn, receiverColumn, carIdColumn);
        parcelTable.setEditable(true);
        parcelTable.autosize();
        parcelTable.setMaxHeight(Double.MAX_VALUE);
        parcelTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Init scene and layout
        VBox layout2 = new VBox();
        storeKeeperScene = new Scene(layout2, 700, 500);

        // Buttons under table
        Double width = storeKeeperScene.getWidth() / 6;

        HBox buttonLayout = new HBox();
        buttonLayout.setPadding(new Insets(10, 0, 10, 0));
        buttonLayout.setSpacing(width / 5);

        // Gived Out
        Button giveOutButton = new Button("Gived Out");
        HBox.setHgrow(giveOutButton, Priority.ALWAYS);
        giveOutButton.setMinWidth(width);
        giveOutButton.setMaxWidth(Double.MAX_VALUE);
        giveOutButton.setOnAction(e -> {
            Integer carID = GivedOutBox.display();
            if(carID != 0) givedOutButtonClicked(carID);
        });

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

        // Give Car
        Button btn5 = new Button("Give Car");
        HBox.setHgrow(btn5, Priority.ALWAYS);
        btn5.setMinWidth(width);
        btn5.setMaxWidth(Double.MAX_VALUE);

        // Commit
        Button btn6 = new Button("Commit");
        HBox.setHgrow(btn6, Priority.ALWAYS);
        btn6.setMinWidth(width);
        btn6.setMaxWidth(Double.MAX_VALUE);

        buttonLayout.getChildren().addAll(giveOutButton, deletButton, addButton, btn5, btn6);

        // Scene/layout
        VBox.setVgrow(parcelTable, Priority.ALWAYS);
        layout2.setSpacing(5);
        layout2.setPadding(new Insets(10, 10, 10, 10));
        layout2.getChildren().addAll(searchField, parcelTable, buttonLayout);

        // Final Commands
        return storeKeeperScene;
    }

    // Get all of the
    public static ObservableList<Parcel> getParcel() {
        List<Parcel> parcelList = new ArrayList<>();
        try {
            Connection connection = Database.getConnection("BD1_Z15", "twheas");
            ParcelDataAccessor parcelAccessor = new ParcelDataAccessor(connection);
            parcelList = parcelAccessor.getParcelsList(
                    "select * from parcels where car_id = (select car_id from employees where employees_id = 5)");
            Database.closeConnection(connection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ObservableList<Parcel> parcels = FXCollections.observableArrayList(parcelList);

        return parcels;
    }

    // Delete button clicked
    public static void deleteButtonClicked() {
        ObservableList<Parcel> pracelsSelected;
        pracelsSelected = parcelTable.getSelectionModel().getSelectedItems();
        ArrayList<Parcel> rows = new ArrayList<>(pracelsSelected);
        rows.forEach(row -> parcelTable.getItems().remove(row));
    }

    //Add button clicked
    public static void addButtonClicked(){
        Parcel parcel = AddBox.display();
        parcelTable.getItems().add(parcel);
    }

    // Gived Out button clicked
    public static void givedOutButtonClicked(Integer carID) {
        ObservableList<Parcel> pracelsSelected, allParcels;
        allParcels = parcelTable.getItems();
        pracelsSelected = parcelTable.getSelectionModel().getSelectedItems();
        ArrayList<Parcel> rows = new ArrayList<>(pracelsSelected);
        for (Parcel r : rows) {
            allParcels.remove(r);
            r.setCarID(carID);
            allParcels.add(r);
            parcelTable.refresh();
        }
    }
}
