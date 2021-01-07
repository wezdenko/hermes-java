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

import gui.boxes.AddBox_Pos;
import database.accessors.PositionDataAccessor;
import database.Database;
import database.classes.Position;
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

public class PositionLayout {

    static TableView<Position> positionTable;
    static JSONParser jsonParser;
    static JSONObject jsonObj;

    public static VBox setPositionLayout(int width, Stage primaryStage) {
      int spacingDivider=0, spacingButtonDivider=0, padding=0, buttonMinWidth=0;
      int mediumTabWidth=0, spacingSize=0;;
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
        TableColumn<Position, String> idColumn = new TableColumn<>("Position ID");
        idColumn.setMinWidth(mediumTabWidth);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id_S"));

        // Name Column
        TableColumn<Position, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(mediumTabWidth);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Position, String>>() {
            @Override
            public void handle(CellEditEvent<Position, String> t) {
                ((Position)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                positionTable.refresh();
            }
        });

        // MaxSalary Column
        TableColumn<Position, String> maxSalaryColumn = new TableColumn<>("Max Salary");
        maxSalaryColumn.setMinWidth(mediumTabWidth);
        maxSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("maxSalary_S"));
        maxSalaryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        maxSalaryColumn.setOnEditCommit(new EventHandler<CellEditEvent<Position, String>>() {
            @Override
            public void handle(CellEditEvent<Position, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Position) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setMaxSalary(Converter.StringToInt(t.getNewValue()));
                }
                positionTable.refresh();
            }
        });

        // MinSalary Column
        TableColumn<Position, String> minSalaryColumn = new TableColumn<>("Min Salary");
        minSalaryColumn.setMinWidth(mediumTabWidth);
        minSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("minSalary_S"));
        minSalaryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        minSalaryColumn.setOnEditCommit(new EventHandler<CellEditEvent<Position, String>>() {
            @Override
            public void handle(CellEditEvent<Position, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Position) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setMinSalary(Converter.StringToInt(t.getNewValue()));
                }
                positionTable.refresh();
            }
        });

        // Final Table
        positionTable = new TableView<>();
        positionTable.setItems(getPosition());
        positionTable.getColumns().addAll(idColumn, nameColumn, maxSalaryColumn, minSalaryColumn);
        positionTable.setEditable(true);
        positionTable.autosize();
        positionTable.setMaxHeight(Double.MAX_VALUE);
        positionTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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
        VBox.setVgrow(positionTable, Priority.ALWAYS);
        layout2.setSpacing(spacingSize);
        layout2.setPadding(new Insets(padding, padding, padding, padding));
        layout2.getChildren().addAll(searchField, positionTable, buttonLayout);

        // Final Commands
        return layout2;
    }

    // Get all of the
    public static ObservableList<Position> getPosition() {
        List<Position> positionList = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            PositionDataAccessor positionAccessor = new PositionDataAccessor(connection);
            positionList = positionAccessor.getPositionsList("select * from positions");
            Database.closeConnection(connection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ObservableList<Position> positions = FXCollections.observableArrayList(positionList);

        return positions;
    }

    // Delete button clicked
    public static void deleteButtonClicked() {
        ObservableList<Position> selected;
        selected = positionTable.getSelectionModel().getSelectedItems();
        ArrayList<Position> rows = new ArrayList<>(selected);
        rows.forEach(row -> positionTable.getItems().remove(row));
    }

    //Add button clicked
    public static void addButtonClicked(){
        Position position = AddBox_Pos.display();
        positionTable.getItems().add(position);
    }
}
