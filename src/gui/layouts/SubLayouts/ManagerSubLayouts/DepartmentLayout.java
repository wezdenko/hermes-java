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
import java.sql.Date;

import gui.boxes.AddBox_Dep;
import database.accessors.DepartmentsDataAccessor;
import database.Database;

import database.classes.Converter;
import database.classes.Department;

import gui.layouts.LoginLayout;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DepartmentLayout {

    static TableView<Department> departmentTable;
    static JSONParser jsonParser;
    static JSONObject jsonObj;

    public static VBox setDepartmentLayout(int width, Stage primaryStage) {
      int spacingDivider=0, spacingButtonDivider=0, padding=0, buttonMinWidth=0;
      int mediumTabWidth=0, lowTabWidth=0, spacingSize=0;
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
        lowTabWidth = (int) (long) jsonObj.get("TAB_MIN_WIDTH_LOW");

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
        TableColumn<Department, String> idColumn = new TableColumn<>("Department ID");
        idColumn.setMinWidth(mediumTabWidth);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("departmentID_S"));

        // Name Column
        TableColumn<Department, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(mediumTabWidth);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Department, String>>() {
            @Override
            public void handle(CellEditEvent<Department, String> t) {
                ((Department)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                departmentTable.refresh();
            }
        });

        // Address_street Column
        TableColumn<Department, String> streetColumn = new TableColumn<>("Street");
        streetColumn.setMinWidth(mediumTabWidth);
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("address_street"));
        streetColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        streetColumn.setOnEditCommit(new EventHandler<CellEditEvent<Department, String>>() {
            @Override
            public void handle(CellEditEvent<Department, String> t) {
                ((Department)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress_street(t.getNewValue());
                departmentTable.refresh();
            }
        });

        // Address_houseNumber Column
        TableColumn<Department, String> houseNumberColumn = new TableColumn<>("HN");
        houseNumberColumn.setMinWidth(mediumTabWidth);
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<>("address_houseNumber"));
        houseNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        houseNumberColumn.setOnEditCommit(new EventHandler<CellEditEvent<Department, String>>() {
            @Override
            public void handle(CellEditEvent<Department, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Department) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setAddress_houseNumber(Converter.StringToInt(t.getNewValue()));
                }
                departmentTable.refresh();
            }
        });
        
        // Address_apartmentNumber Column
        TableColumn<Department, String> apartmentNumberColumn = new TableColumn<>("AN");
        apartmentNumberColumn.setMinWidth(mediumTabWidth);
        apartmentNumberColumn.setCellValueFactory(new PropertyValueFactory<>("address_apartmentNumber"));
        apartmentNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        apartmentNumberColumn.setOnEditCommit(new EventHandler<CellEditEvent<Department, String>>() {
            @Override
            public void handle(CellEditEvent<Department, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Department) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setAddress_apartmentNumber(Converter.StringToInt(t.getNewValue()));
                }
                departmentTable.refresh();
            }
        });
        
        // Address_city Column
        TableColumn<Department, String> cityColumn = new TableColumn<>("City");
        cityColumn.setMinWidth(mediumTabWidth);
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("address_city"));
        cityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cityColumn.setOnEditCommit(new EventHandler<CellEditEvent<Department, String>>() {
            @Override
            public void handle(CellEditEvent<Department, String> t) {
                ((Department)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress_city(t.getNewValue());
                departmentTable.refresh();
            }
        });

        // Address_postalCode Column
        TableColumn<Department, String> postalCodeColumn = new TableColumn<>("PC");
        postalCodeColumn.setMinWidth(mediumTabWidth);
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("address_postalCode"));
        postalCodeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        postalCodeColumn.setOnEditCommit(new EventHandler<CellEditEvent<Department, String>>() {
            @Override
            public void handle(CellEditEvent<Department, String> t) {
                ((Department)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress_postalCode(t.getNewValue());
                departmentTable.refresh();
            }
        });

        // Address_countryID Column
        TableColumn<Department, String> countryIDColumn = new TableColumn<>("Country ID");
        countryIDColumn.setMinWidth(mediumTabWidth);
        countryIDColumn.setCellValueFactory(new PropertyValueFactory<>("address_countryID"));
        countryIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        countryIDColumn.setOnEditCommit(new EventHandler<CellEditEvent<Department, String>>() {
            @Override
            public void handle(CellEditEvent<Department, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Department) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setAddress_countryID(Converter.StringToInt(t.getNewValue()));
                }
                departmentTable.refresh();
            }
        });        

        // CreationDate Column
        TableColumn<Department, String> creationDateColumn = new TableColumn<>("Creation Date");
        creationDateColumn.setMinWidth(lowTabWidth);
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate_S"));
        creationDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        creationDateColumn.setOnEditCommit(new EventHandler<CellEditEvent<Department, String>>() {
            @Override
            public void handle(CellEditEvent<Department, String> t) {
                Date d = new Date(0, 0, 0);
                if (!Converter.StringToDate(t.getNewValue()).equals(d)) {
                    ((Department) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setCreationDate(Converter.StringToDate(t.getNewValue()));
                }
                departmentTable.refresh();
            }
        });

        // Final Table
        departmentTable = new TableView<>();
        departmentTable.setItems(getDepartment());
        departmentTable.getColumns().addAll(idColumn, nameColumn, streetColumn, houseNumberColumn, apartmentNumberColumn, postalCodeColumn, cityColumn, creationDateColumn);
        departmentTable.setEditable(true);
        departmentTable.autosize();
        departmentTable.setMaxHeight(Double.MAX_VALUE);
        departmentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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
        VBox.setVgrow(departmentTable, Priority.ALWAYS);
        layout2.setSpacing(spacingSize);
        layout2.setPadding(new Insets(padding, padding, padding, padding));
        layout2.getChildren().addAll(searchField, departmentTable, buttonLayout);

        // Final Commands
        return layout2;
    }

    // Get all of the
    public static ObservableList<Department> getDepartment() {
        List<Department> departmentList = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            DepartmentsDataAccessor departmentAccessor = new DepartmentsDataAccessor(connection);
            departmentList = departmentAccessor.getDepartmentList(
                    "select * from departments");
            Database.closeConnection(connection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ObservableList<Department> departments = FXCollections.observableArrayList(departmentList);

        return departments;
    }

    // Delete button clicked
    public static void deleteButtonClicked() {
        ObservableList<Department> selected;
        selected = departmentTable.getSelectionModel().getSelectedItems();
        ArrayList<Department> rows = new ArrayList<>(selected);
        rows.forEach(row -> departmentTable.getItems().remove(row));
    }

    //Add button clicked
    public static void addButtonClicked(){
        Department department = AddBox_Dep.display();
        departmentTable.getItems().add(department);
    }
}