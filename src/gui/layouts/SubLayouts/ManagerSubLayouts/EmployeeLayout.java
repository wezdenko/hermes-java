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

import gui.boxes.AddBox_Emp;
import database.accessors.EmployeeDataAccessor;
import database.Database;

import database.classes.Converter;
import database.classes.Employee;

import gui.layouts.LoginLayout;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EmployeeLayout {

    static TableView<Employee> employeeTable;
    static JSONParser jsonParser;
    static JSONObject jsonObj;

    public static VBox setEmployeeLayout(int width, Stage primaryStage) {
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
        TableColumn<Employee, String> idColumn = new TableColumn<>("Employee ID");
        idColumn.setMinWidth(mediumTabWidth);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID_S"));

        // Name Column
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(mediumTabWidth);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                ((Employee)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                employeeTable.refresh();
            }
        });

        // Surname Column
        TableColumn<Employee, String> surNameColumn = new TableColumn<>("Surname");
        surNameColumn.setMinWidth(mediumTabWidth);
        surNameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        surNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                ((Employee)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setSurname(t.getNewValue());
                employeeTable.refresh();
            }
        });

        // Pesel Column
        TableColumn<Employee, String> peselColumn = new TableColumn<>("Pesel");
        peselColumn.setMinWidth(mediumTabWidth);
        peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel_S"));
        peselColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        peselColumn.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setPesel(Converter.StringToLong(t.getNewValue()));
                }
                employeeTable.refresh();
            }
        });

        // Salary Column
        TableColumn<Employee, String> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setMinWidth(mediumTabWidth);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary_S"));
        salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        salaryColumn.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setSalary(Converter.StringToInt(t.getNewValue()));
                }
                employeeTable.refresh();
            }
        });

        // EmploymentDate Column
        TableColumn<Employee, String> employmentDateColumn = new TableColumn<>("Employment Date");
        employmentDateColumn.setMinWidth(lowTabWidth);
        employmentDateColumn.setCellValueFactory(new PropertyValueFactory<>("employmentDate_S"));
        employmentDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        employmentDateColumn.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                Date d = new Date(0, 0, 0);
                if (!Converter.StringToDate(t.getNewValue()).equals(d)) {
                    ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setEmploymentDate(Converter.StringToDate(t.getNewValue()));
                }
                employeeTable.refresh();
            }
        });

        // Login Column
        TableColumn<Employee, String> loginColumn = new TableColumn<>("Login");
        loginColumn.setMinWidth(mediumTabWidth);
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        loginColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        loginColumn.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                ((Employee)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setLogin(t.getNewValue());
                employeeTable.refresh();
            }
        });

        // Password Column
        TableColumn<Employee, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setMinWidth(mediumTabWidth);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordColumn.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                ((Employee)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setPassword(t.getNewValue());
                employeeTable.refresh();
            }
        });

        // CarID Column
        TableColumn<Employee, String> carIDColumn = new TableColumn<>("Car ID");
        carIDColumn.setMinWidth(mediumTabWidth);
        carIDColumn.setCellValueFactory(new PropertyValueFactory<>("carID_S"));
        carIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        carIDColumn.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setCarID(Converter.StringToInt(t.getNewValue()));
                }
                employeeTable.refresh();
            }
        });

        // PositionID Column
        TableColumn<Employee, String> positionIDColumn = new TableColumn<>("Position ID");
        positionIDColumn.setMinWidth(mediumTabWidth);
        positionIDColumn.setCellValueFactory(new PropertyValueFactory<>("positionID_S"));
        positionIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        positionIDColumn.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setPositionID(Converter.StringToInt(t.getNewValue()));
                }
                employeeTable.refresh();
            }
        });

        // ManagerID Column
        TableColumn<Employee, String> managerIDColumn = new TableColumn<>("Manager ID");
        managerIDColumn.setMinWidth(mediumTabWidth);
        managerIDColumn.setCellValueFactory(new PropertyValueFactory<>("managerID_S"));
        managerIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        managerIDColumn.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setManagerID(Converter.StringToInt(t.getNewValue()));
                }
                employeeTable.refresh();
            }
        });

        // DepartmentID Column
        TableColumn<Employee, String> departmentIDColumn = new TableColumn<>("Department ID");
        departmentIDColumn.setMinWidth(mediumTabWidth);
        departmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("departmentID_S"));
        departmentIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        departmentIDColumn.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setDepartmentID(Converter.StringToInt(t.getNewValue()));
                }
                employeeTable.refresh();
            }
        });

        // Final Table
        employeeTable = new TableView<>();
        employeeTable.setItems(getEmployee());
        employeeTable.getColumns().addAll(idColumn, nameColumn, surNameColumn, peselColumn, salaryColumn, employmentDateColumn, loginColumn, passwordColumn, carIDColumn, positionIDColumn, managerIDColumn, departmentIDColumn);
        employeeTable.setEditable(true);
        employeeTable.autosize();
        employeeTable.setMaxHeight(Double.MAX_VALUE);
        employeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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
        VBox.setVgrow(employeeTable, Priority.ALWAYS);
        layout2.setSpacing(spacingSize);
        layout2.setPadding(new Insets(padding, padding, padding, padding));
        layout2.getChildren().addAll(searchField, employeeTable, buttonLayout);

        // Final Commands
        return layout2;
    }

    // Get all of the
    public static ObservableList<Employee> getEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            EmployeeDataAccessor employeeAccessor = new EmployeeDataAccessor(connection);
            employeeList = employeeAccessor.getEmployeeList(
                    "select * from EMPLOYEES");
            Database.closeConnection(connection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ObservableList<Employee> employees = FXCollections.observableArrayList(employeeList);

        return employees;
    }

    // Delete button clicked
    public static void deleteButtonClicked() {
        ObservableList<Employee> selected;
        selected = employeeTable.getSelectionModel().getSelectedItems();
        ArrayList<Employee> rows = new ArrayList<>(selected);
        rows.forEach(row -> employeeTable.getItems().remove(row));
    }

    //Add button clicked
    public static void addButtonClicked(){
        Employee employee = AddBox_Emp.display();
        employeeTable.getItems().add(employee);
    }
}
