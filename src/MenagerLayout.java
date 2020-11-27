import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.beans.property.SimpleStringProperty;
 
public class MenagerLayout {

    static Scene scene2;
    static TableView<Parcel> parcelTable;
    static String action;

    
    public static Scene mg() {
        //Search Field
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        //Setting ParcelTable

                //ID Column
        TableColumn<Parcel, String> idColumn = new TableColumn<>("Parcel ID");
        idColumn.setMinWidth(100);  
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID_S"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        idColumn.setOnEditCommit(
        new EventHandler<CellEditEvent<Parcel, String>>() {
        @Override
        public void handle(CellEditEvent<Parcel, String> t) {
            if(Converter.StringToInt(t.getNewValue()) != 0){
                ((Parcel) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setID(Converter.StringToInt(t.getNewValue()));
                    }
            parcelTable.refresh();
                }
            }
        );

                //sName Column
        TableColumn<Parcel, String> sNameColumn = new TableColumn<>("Sender");
        sNameColumn.setMinWidth(200);  
        sNameColumn.setCellValueFactory(new PropertyValueFactory<>("sName"));
        sNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sNameColumn.setOnEditCommit(
        new EventHandler<CellEditEvent<Parcel, String>>() {
        @Override
        public void handle(CellEditEvent<Parcel, String> t) {
            ((Parcel) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
                ).setSName(t.getNewValue());
                parcelTable.refresh();
                }
            }
        );
        
                //rName Column
        TableColumn<Parcel, String> rNameColumn = new TableColumn<>("Receiver");
        rNameColumn.setMinWidth(200);  
        rNameColumn.setCellValueFactory(new PropertyValueFactory<>("rName"));
        rNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rNameColumn.setOnEditCommit(
        new EventHandler<CellEditEvent<Parcel, String>>() {
        @Override
        public void handle(CellEditEvent<Parcel, String> t) {
            ((Parcel) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
                ).setRName(t.getNewValue());
                parcelTable.refresh();
                }
            }
        );
                //carID Column
        TableColumn<Parcel, String> carIdColumn = new TableColumn<>("Car ID");
        carIdColumn.setMinWidth(100);  
        carIdColumn.setCellValueFactory(new PropertyValueFactory<>("carID_S"));
        carIdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        carIdColumn.setOnEditCommit(
        new EventHandler<CellEditEvent<Parcel, String>>() {
        @Override
        public void handle(CellEditEvent<Parcel, String> t) {
            if(Converter.StringToInt(t.getNewValue()) != 0){
                ((Parcel) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setCarID(Converter.StringToInt(t.getNewValue()));
                    }
            parcelTable.refresh();
            }
            }
        );
        
                //Final Table
        parcelTable = new TableView<>();
        parcelTable.setItems(getParcel());
        parcelTable.getColumns().addAll(idColumn, sNameColumn, rNameColumn, carIdColumn);
        parcelTable.setEditable(true);
        parcelTable.autosize();
        parcelTable.setMaxHeight(Double.MAX_VALUE);
        parcelTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        //Init scene and layout
        VBox layout2 = new VBox();
        scene2 = new Scene(layout2, 600, 500);

        //Buttons under table 
        Double width = scene2.getWidth()/6;

        HBox buttonLayout = new HBox();
        buttonLayout.setPadding(new Insets(10, 0, 10, 0));
        buttonLayout.setSpacing(width/5);


            //Gived Out
        Button giveOutButton = new Button("Gived Out");
        HBox.setHgrow(giveOutButton, Priority.ALWAYS);
        giveOutButton.setMinWidth(width);
        giveOutButton.setMaxWidth(Double.MAX_VALUE);
        giveOutButton.setOnAction(e -> {
            String carID = GivedOutBox.display();
            System.out.println(carID);
            givedOutButtonClicked(carID);
        });

            //Delete
        Button deletButton = new Button("Delete");
        HBox.setHgrow(deletButton, Priority.ALWAYS);
        deletButton.setMinWidth(width);
        deletButton.setMaxWidth(Double.MAX_VALUE);
        deletButton.setOnAction(e -> deleteButtonClicked());

            //Add
        Button btn4 = new Button("Add");
        HBox.setHgrow(btn4, Priority.ALWAYS);
        btn4.setMinWidth(width);
        btn4.setMaxWidth(Double.MAX_VALUE);

            //Give Car
        Button btn5 = new Button("Give Car");
        HBox.setHgrow(btn5, Priority.ALWAYS);
        btn5.setMinWidth(width);
        btn5.setMaxWidth(Double.MAX_VALUE);
        
            //Commit
        Button btn6 = new Button("Commit");
        HBox.setHgrow(btn6, Priority.ALWAYS); 
        btn6.setMinWidth(width);
        btn6.setMaxWidth(Double.MAX_VALUE);

        buttonLayout.getChildren().addAll(giveOutButton,deletButton,btn4,btn5, btn6);


        //Scene/layout
        VBox.setVgrow(parcelTable, Priority.ALWAYS);
        layout2.setSpacing(5);
        layout2.setPadding(new Insets(10, 10, 10, 10));
        layout2.getChildren().addAll(searchField, parcelTable, buttonLayout);


        //Final Commands
        return scene2;
    }

    //Get all of the 
    public static ObservableList<Parcel> getParcel() {
        ObservableList<Parcel> parcels = FXCollections.observableArrayList();
        parcels.add(new Parcel(123, "Car" , 0, 1, 2, 2, 2, 11, 2, 2, "adam", "tomek"));
        parcels.add(new Parcel(124, "Car" , 0, 1, 2, 2, 2, 11, 5, 2, "adam", "tomek"));
        parcels.add(new Parcel(125, "Car" , 0, 1, 2, 2, 2, 11, 11, 2, "rajmund", "jankos"));
        parcels.add(new Parcel(126, "Car" , 0, 1, 2, 2, 2, 11, 2, 2, "hitler", "stalin"));
        parcels.add(new Parcel(133, "Car" , 0, 1, 2, 2, 2, 11, 3, 2, "kot", "pies"));
        return parcels;
    }

    //Delete button clicked
    public static void deleteButtonClicked(){
        ObservableList<Parcel> pracelsSelected;
        pracelsSelected = parcelTable.getSelectionModel().getSelectedItems();
        ArrayList<Parcel> rows = new ArrayList<>(pracelsSelected);
        rows.forEach(row -> parcelTable.getItems().remove(row));
    }

    //Gived Out button clicked
    public static void givedOutButtonClicked(String carID){
        ObservableList<Parcel> pracelsSelected, allParcels;
        allParcels = parcelTable.getItems();
        pracelsSelected = parcelTable.getSelectionModel().getSelectedItems();
        ArrayList<Parcel> rows = new ArrayList<>(pracelsSelected);
        for (Parcel r : rows) {
            allParcels.remove(r);
            r.setCarID(Converter.StringToInt(carID));
            allParcels.add(r);
            parcelTable.refresh();
        }
    }
}