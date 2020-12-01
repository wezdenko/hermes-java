package gui.layouts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List ;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Priority;
import javafx.scene.control.SelectionMode;

import java.sql.Connection;

import database.classes.Parcel;
import gui.boxes.ChoiceWindow;
import database.accessors.ParcelDataAccessor;
import database.Database;


public class CourierLayout {

    static Scene courierScene;
    static TableView<Parcel> parcelsTable;
    //static TableView<Parcel> parcelTable;
    //static String action;
    
    public static Scene setCourierScene() {
        //Search Field
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

        // // Weight column
        // TableColumn<Parcel, Double> weightColumn = new TableColumn<>("Weight");
        // weightColumn.setMinWidth(50);
        // weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        // // Length column
        // TableColumn<Parcel, Double> lengthColumn = new TableColumn<>("Length");
        // lengthColumn.setMinWidth(50);
        // lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));

        // // Width column
        // TableColumn<Parcel, Double> widthColumn = new TableColumn<>("Width");
        // widthColumn.setMinWidth(50);
        // widthColumn.setCellValueFactory(new PropertyValueFactory<>("width"));

        // // Height column
        // TableColumn<Parcel, Double> heightColumn = new TableColumn<>("Height");
        // heightColumn.setMinWidth(50);
        // heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));

        // // Code column
        // TableColumn<Parcel, Integer> codeColumn = new TableColumn<>("Code");
        // codeColumn.setMinWidth(50);
        // codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        // // carID column
        // TableColumn<Parcel, Integer> carColumn = new TableColumn<>("Car ID");
        // carColumn.setMinWidth(50);
        // carColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));

        // // departmentID column
        // TableColumn<Parcel, Integer> departmentColumn = new TableColumn<>("Department ID");
        // departmentColumn.setMinWidth(50);
        // departmentColumn.setCellValueFactory(new PropertyValueFactory<>("departmentID"));

        // senderID column
        TableColumn<Parcel, String> senderColumn = new TableColumn<>("Sender");
        senderColumn.setMinWidth(80);
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("senderName"));

        // receiverID column
        TableColumn<Parcel, String> receiverColumn = new TableColumn<>("Receiver");
        receiverColumn.setMinWidth(80);
        receiverColumn.setCellValueFactory(new PropertyValueFactory<>("receiverName"));

        // receiver address column
        TableColumn<Parcel, String> receiverAddressColumn = new TableColumn<>("Receiver Address");
        receiverAddressColumn.setMinWidth(200);
        receiverAddressColumn.setCellValueFactory(new PropertyValueFactory<>("receiverAddress"));

        // sender address column
        TableColumn<Parcel, String> senderAddressColumn = new TableColumn<>("Sender Address");
        senderAddressColumn.setMinWidth(200);
        senderAddressColumn.setCellValueFactory(new PropertyValueFactory<>("senderAddress"));
        

        parcelsTable = new TableView<>();
        parcelsTable.setItems(getParcelList());
        // All columns
        // parcelsTable.getColumns().addAll(idColumn, statusColumn, sNameColumn, 
        // rNameColumn, costColumn, weightColumn, lengthColumn, widthColumn, 
        // heightColumn, codeColumn, carColumn, departmentColumn);

        parcelsTable.getColumns().addAll(idColumn, statusColumn, senderColumn, 
        receiverColumn, costColumn, receiverAddressColumn, senderAddressColumn);
        parcelsTable.autosize();
        parcelsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox courierV = new VBox();
        courierScene = new Scene(courierV, 600, 500);

        Button changeBtn = new Button("Change Status");
        changeBtn.setMinWidth(100);
        changeBtn.setMaxWidth(Double.MAX_VALUE);
        changeBtn.setOnAction(e->changeStatus());


        VBox.setVgrow(parcelsTable, Priority.ALWAYS);
        courierV.setSpacing(5);
        courierV.setPadding(new Insets(10, 10, 10, 10));
        courierV.getChildren().addAll(searchField, parcelsTable, changeBtn);
        
        return courierScene;
    }

    public static ObservableList<Parcel> getParcelList() {
        List<Parcel> parcelList = new ArrayList<>();
        try {
            Connection connection = Database.getConnection("BD1_Z15", "twheas");
            ParcelDataAccessor parcelAccessor = new ParcelDataAccessor(connection);
            parcelList = parcelAccessor.getParcelsList("select * from parcels where car_id = (select car_id from employees where employees_id = 5)");
            Database.closeConnection(connection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ObservableList<Parcel> parcels = FXCollections.observableArrayList(parcelList);

        // parcels.add(new Parcel(124, "Collection Point" , 11.5, 1, 2, 2, 2, 11, 5, 2, "adam", "imion i nazwisks asadsaasd"));
        // parcels.add(new Parcel(125, "Warehouse" , 55.99, 1, 2, 2, 2, 11, 11, 2, "rajmund", "jankos"));
        // parcels.add(new Parcel(126, "Car" , 122.55, 1, 2, 2, 2, 11, 2, 2, "hitler", "stalin"));
        // parcels.add(new Parcel(133, "Car" , 113.4, 1, 2, 2, 2, 11, 3, 2, "kot", "pies"));
        
        return parcels;
    }

    public static void changeStatus(){
        String action;
        ObservableList<Parcel> parcelSelected, allParcels;
        allParcels = parcelsTable.getItems();
        parcelSelected = parcelsTable.getSelectionModel().getSelectedItems();
        ArrayList<Parcel> parcelsArray = new ArrayList<>(parcelSelected);

        action = ChoiceWindow.display();

        for (Parcel parcel: parcelsArray){
            allParcels.remove(parcel);
            parcel.setStatus(action);
            allParcels.add(parcel);
            parcelsTable.refresh();
        }

        
    
    }

}
