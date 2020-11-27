import java.util.Observable;

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


public class App extends Application{
    
    Button button;
    Stage courierWindow;
    TableView<Parcel> parcelsTable;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        courierWindow = stage;
        courierWindow.setTitle("Courier Panel");

        // Id column
        TableColumn<Parcel, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("parcelID"));

        // Status column
        TableColumn<Parcel, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMinWidth(200);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Cost column
        TableColumn<Parcel, Double> costColumn = new TableColumn<>("Cost");
        costColumn.setMinWidth(100);
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        // Weight column
        TableColumn<Parcel, Double> weightColumn = new TableColumn<>("Weight");
        weightColumn.setMinWidth(100);
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        // Length column
        TableColumn<Parcel, Double> lengthColumn = new TableColumn<>("Length");
        lengthColumn.setMinWidth(100);
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));

        // Width column
        TableColumn<Parcel, Double> widthColumn = new TableColumn<>("Width");
        widthColumn.setMinWidth(100);
        widthColumn.setCellValueFactory(new PropertyValueFactory<>("width"));

        // Height column
        TableColumn<Parcel, Double> heightColumn = new TableColumn<>("Height");
        heightColumn.setMinWidth(100);
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));

        // Code column
        TableColumn<Parcel, Integer> codeColumn = new TableColumn<>("Code");
        codeColumn.setMinWidth(100);
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        // Code column
        TableColumn<Parcel, Integer> carColumn = new TableColumn<>("carID");
        carColumn.setMinWidth(100);
        carColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));

        // Code column
        TableColumn<Parcel, Integer> departmentColumn = new TableColumn<>("departmentID");
        departmentColumn.setMinWidth(100);
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("departmentID"));

        // Code column
        TableColumn<Parcel, Integer> senderColumn = new TableColumn<>("senderID");
        senderColumn.setMinWidth(100);
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("senderID"));

        // Code column
        TableColumn<Parcel, Integer> receiverColumn = new TableColumn<>("receiverID");
        receiverColumn.setMinWidth(100);
        receiverColumn.setCellValueFactory(new PropertyValueFactory<>("receiverID"));


        parcelsTable = new TableView<>();
        ObservableList<Parcel> parcels = getTestParcels();
        parcelsTable.setItems(parcels);
        parcelsTable.getColumns().addAll(idColumn, statusColumn, costColumn, 
        weightColumn, lengthColumn, widthColumn, heightColumn, codeColumn,
        carColumn, departmentColumn, senderColumn, receiverColumn);

        Label searchLabel = new Label("Browser");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(searchLabel, parcelsTable);


        Scene scene = new Scene(vBox);
        courierWindow.setScene(scene);
        courierWindow.show();

    }

    public ObservableList<Parcel> getTestParcels(){
        ObservableList<Parcel> parcels = FXCollections.observableArrayList();
        parcels.add(new Parcel(101, "Magazyn", 150.0, 10.0, 2.0, 4.0, 5.0, 11, 1, 1, 1, 5));
        parcels.add(new Parcel(102, "Samochod", 120.0, 10.0, 6.0, 6.3, 2.5, 0, 1, 1, 31, 1));
        parcels.add(new Parcel(103, "Doreczona", 110.0, 2.2, 2.0, 8.8, 12.0, 0, 11, 1, 1, 1));
        parcels.add(new Parcel(104, "Samochod", 160.0, 8.5, 2.0, 3.0, 17.0, 123, 1, 1, 1, 1));
        

        return parcels;
    }
}
