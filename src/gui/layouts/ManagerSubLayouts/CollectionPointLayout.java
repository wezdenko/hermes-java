package gui.layouts.ManagerSubLayouts;

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

import gui.boxes.AddBox_CPoint;

import database.accessors.CollectionPointDataAccessor;
import database.Database;

import gui.layouts.LoginLayout;
import javafx.stage.Stage;
import javafx.scene.Scene;

import database.classes.CollectionPoint;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CollectionPointLayout {

    static TableView<CollectionPoint> collectionPointTable;
    static JSONParser jsonParser;
    static JSONObject jsonObj;

    public static VBox setCollectionPointLayout(int width, Stage primaryStage) {
      int spacingDivider=0, spacingButtonDivider=0, padding=0, buttonMinWidth=0;
      int mediumTabWidth=0, highTabWidth=0, spacingSize=0;
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
        highTabWidth = (int) (long) jsonObj.get("TAB_MIN_WIDTH_HIGH");

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
        TableColumn<CollectionPoint, String> idColumn = new TableColumn<>("Collection Point ID");
        idColumn.setMinWidth(mediumTabWidth);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("collectionPointID_S"));

        // Name Column
        TableColumn<CollectionPoint, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(highTabWidth);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(new EventHandler<CellEditEvent<CollectionPoint, String>>() {
            @Override
            public void handle(CellEditEvent<CollectionPoint, String> t) {
                ((CollectionPoint)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                collectionPointTable.refresh();
            }
        });

        // Address Column
        TableColumn<CollectionPoint, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setMinWidth(highTabWidth);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address_S"));


        // Final Table
        collectionPointTable = new TableView<>();
        collectionPointTable.setItems(getCollectionPoint());
        collectionPointTable.getColumns().addAll(idColumn, nameColumn, addressColumn);
        collectionPointTable.setEditable(true);
        collectionPointTable.autosize();
        collectionPointTable.setMaxHeight(Double.MAX_VALUE);
        collectionPointTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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
        VBox.setVgrow(collectionPointTable, Priority.ALWAYS);
        layout2.setSpacing(spacingSize);
        layout2.setPadding(new Insets(padding, padding, padding, padding));
        layout2.getChildren().addAll(searchField, collectionPointTable, buttonLayout);

        // Final Commands
        return layout2;
    }

    // Get all of the
    public static ObservableList<CollectionPoint> getCollectionPoint() {
        List<CollectionPoint> collectionPointList = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            CollectionPointDataAccessor collectionPointAccessor = new CollectionPointDataAccessor(connection);
            collectionPointList = collectionPointAccessor.getCollectionPointList(
                    "select * from COLLECTION_POINTS");
            Database.closeConnection(connection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ObservableList<CollectionPoint> collectionPoints = FXCollections.observableArrayList(collectionPointList);

        return collectionPoints;
    }

    // Delete button clicked
    public static void deleteButtonClicked() {
        ObservableList<CollectionPoint> selected;
        selected = collectionPointTable.getSelectionModel().getSelectedItems();
        ArrayList<CollectionPoint> rows = new ArrayList<>(selected);
        rows.forEach(row -> collectionPointTable.getItems().remove(row));
    }

    //Add button clicked
    public static void addButtonClicked(){
        CollectionPoint collectionPoint = AddBox_CPoint.display();
        collectionPointTable.getItems().add(collectionPoint);
    }
}
