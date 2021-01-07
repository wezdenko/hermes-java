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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;

import database.classes.Parcel;
import gui.boxes.ChoiceWindow;
import gui.layouts.SubLayouts.MenuBar_own;
import database.accessors.ParcelDataAccessor;
import database.Database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CourierLayout {

    static Scene courierScene;
    static TableView<Parcel> parcelsTable;
    static JSONParser jsonParser;
    static JSONObject jsonObj;
    

    public static Scene setCourierScene(Stage primaryStage) {
      MenuBar menuBar = MenuBar_own.setMenuBar();

      int width=0, height=0, spacingDivider=0, spacingButtonDivider=0, padding=0, buttonMinWidth=0;
      int highTabWidth=0, mediumTabWidth=0, lowTabWidth=0, spacingSize=0;
      String search="", logOutLabel="", changeStatusLabel="", commitLabel="";


      jsonParser = new JSONParser();
      try{
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/sharedConfiguration.json"));
        
        width = (int) (long) jsonObj.get("WIDTH");
        height = (int) (long) jsonObj.get("HEIGHT");
        spacingDivider = (int) (long) jsonObj.get("SPACING_WIDTH_DIVIDER");
        spacingButtonDivider = (int) (long) jsonObj.get("SPACING_BUTTON_DIVIDER");
        padding = (int) (long) jsonObj.get("PADDING");
        spacingSize = (int) (long) jsonObj.get("SPACING");

        buttonMinWidth = (int) (long) jsonObj.get("BUTTON_MIN_WIDTH");
        highTabWidth = (int) (long) jsonObj.get("TAB_MIN_WIDTH_HIGH");
        mediumTabWidth = (int) (long) jsonObj.get("TAB_MIN_WIDTH_MEDIUM");
        lowTabWidth = (int) (long) jsonObj.get("TAB_MIN_WIDTH_LOW");

        search = (String) jsonObj.get("SEARCH");
        logOutLabel = (String) jsonObj.get("LOGOUT_BUTTON");
        changeStatusLabel = (String) jsonObj.get("CHANGESTATUS_BUTTON");
        commitLabel = (String) jsonObj.get("COMMIT_BUTTON");


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

        // Id column
        TableColumn<Parcel, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(lowTabWidth);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID_S"));

        // Status column
        TableColumn<Parcel, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMinWidth(mediumTabWidth);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Cost column
        TableColumn<Parcel, Double> costColumn = new TableColumn<>("Cost");
        costColumn.setMinWidth(lowTabWidth);
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        // senderID column
        TableColumn<Parcel, String> senderColumn = new TableColumn<>("Sender");
        senderColumn.setMinWidth(mediumTabWidth);
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("senderFullName"));

        // receiverID column
        TableColumn<Parcel, String> receiverColumn = new TableColumn<>("Receiver");
        receiverColumn.setMinWidth(mediumTabWidth);
        receiverColumn.setCellValueFactory(new PropertyValueFactory<>("receiverFullName"));

        // receiver address column
        TableColumn<Parcel, String> receiverAddressColumn = new TableColumn<>("Receiver Address");
        receiverAddressColumn.setMinWidth(highTabWidth);
        receiverAddressColumn.setCellValueFactory(new PropertyValueFactory<>("receiverAddress"));

        // sender address column
        TableColumn<Parcel, String> senderAddressColumn = new TableColumn<>("Sender Address");
        senderAddressColumn.setMinWidth(highTabWidth);
        senderAddressColumn.setCellValueFactory(new PropertyValueFactory<>("senderAddress"));

        parcelsTable = new TableView<>();
        parcelsTable.setItems(getParcelList());
        // All columns

        parcelsTable.getColumns().addAll(idColumn, statusColumn, senderColumn, receiverColumn, costColumn,
                receiverAddressColumn, senderAddressColumn);
        parcelsTable.autosize();
        parcelsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox courierV = new VBox();
        courierScene = new Scene(courierV, width, height);

        int spacingWidth = width / spacingDivider;

        HBox buttonLayout = new HBox();
        buttonLayout.setPadding(new Insets(padding, 0, padding, 0));
        buttonLayout.setSpacing(spacingWidth / spacingButtonDivider);

        Button changeBtn = new Button(changeStatusLabel);
        HBox.setHgrow(changeBtn, Priority.ALWAYS);
        changeBtn.setMinWidth(buttonMinWidth);
        changeBtn.setMaxWidth(Double.MAX_VALUE);
        changeBtn.setOnAction(e -> changeStatus());

        Button btn6 = new Button(commitLabel);
        HBox.setHgrow(btn6, Priority.ALWAYS);
        btn6.setMinWidth(buttonMinWidth);
        btn6.setMaxWidth(Double.MAX_VALUE);

        Button logOutBtn = new Button(logOutLabel);
        HBox.setHgrow(logOutBtn, Priority.ALWAYS);
        logOutBtn.setMinWidth(buttonMinWidth);
        logOutBtn.setMaxWidth(Double.MAX_VALUE);
        logOutBtn.setOnAction(e -> {
          Scene loginScene;
          loginScene = LoginLayout.setLoginScene(primaryStage);
          primaryStage.setScene(loginScene);
        });

        buttonLayout.getChildren().addAll(changeBtn, btn6, logOutBtn);


        VBox.setVgrow(parcelsTable, Priority.ALWAYS);
        courierV.setSpacing(spacingSize);
        courierV.setPadding(new Insets(padding, padding, padding, padding));
        courierV.getChildren().addAll(searchField, parcelsTable, buttonLayout);

        return courierScene;
    }

    public static ObservableList<Parcel> getParcelList() {
        List<Parcel> parcelList = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
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

    public static void changeStatus() {
        String action;
        ObservableList<Parcel> parcelSelected, allParcels;
        allParcels = parcelsTable.getItems();
        parcelSelected = parcelsTable.getSelectionModel().getSelectedItems();
        ArrayList<Parcel> parcelsArray = new ArrayList<>(parcelSelected);

        action = ChoiceWindow.display();
        
        if (action != null && !action.isEmpty()) {
            for (Parcel parcel : parcelsArray) {
                allParcels.remove(parcel);
                parcel.setStatus(action);
                allParcels.add(parcel);
                parcelsTable.refresh();
            }
        }
    }
}
