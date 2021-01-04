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

public class PositionLayout {

    static TableView<Position> positionTable;

    public static VBox setPositionLayout(Double sceneWidth) {
        // Search Field
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        // Setting ParcelTable

        // ID Column
        TableColumn<Position, String> idColumn = new TableColumn<>("Position ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id_S"));

        // Name Column
        TableColumn<Position, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
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
        maxSalaryColumn.setMinWidth(100);
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
        minSalaryColumn.setMinWidth(100);
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

        buttonLayout.getChildren().addAll(deletButton, addButton, btn6);

        // Scene/layout
        VBox.setVgrow(positionTable, Priority.ALWAYS);
        layout2.setSpacing(5);
        layout2.setPadding(new Insets(10, 10, 10, 10));
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
            positionList = positionAccessor.getPositionsList();
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
