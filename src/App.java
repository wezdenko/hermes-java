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
 
public class App extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    Scene loginScene, storeKeeperScene, courierScene, managerScene;
    TableView<Parcel> parcelTable;
    String action;

    @Override
    public void start(Stage primaryStage) throws Exception{

        
        //Welcom Message - Label
        Label welcomeMsg = new Label("You have to enter your login and password");

        //Login - Label&TextField
        Label loginMsg = new Label("Login:");
        TextField loginField = new TextField();
        loginField.setPromptText("...");

        //Password - Label&TextField
        Label passwordMsg = new Label("Password:");
        TextField passwordField = new TextField();
        passwordField.setPromptText("...");


        //Button 1
        Button loginBtn = new Button();
        loginBtn.setText("Log In");
        loginBtn.setOnAction(e->{
            if(loginField.getText().equals("login") && passwordField.getText().equals("password")){
                 System.out.println("logged in");
                 primaryStage.setScene(courierScene);
            }
            else{
                AlertBox.display("ERROR", "WRONG PASSWORD OR LOGIN");
                System.out.println(loginField.getText());
            }     
                });

        //Scene 1 - Logging layout
        //Grid
        GridPane loginLayout = new GridPane();
        loginLayout.setPadding(new Insets(10, 10, 10, 10));
        loginLayout.setVgap(8);
        loginLayout.setHgap(10);
        loginLayout.setConstraints(welcomeMsg, 1, 1);
        loginLayout.setConstraints(loginMsg, 0, 3);
        loginLayout.setConstraints(loginField, 1, 3);
        loginLayout.setConstraints(passwordMsg, 0, 4);
        loginLayout.setConstraints(passwordField, 1, 4);
        loginLayout.setConstraints(loginBtn, 1, 5);
        loginLayout.getChildren().addAll(welcomeMsg,loginMsg,passwordMsg, loginBtn, passwordField, loginField);
        loginLayout.setAlignment(Pos.BASELINE_CENTER);
        loginScene = new Scene(loginLayout, 400, 250);
        
        //Scene 2 - Menager Layout
        //storeKeeperScene = MenagerLayout.mg();

        //Scene 3 - Courier Layout
        courierScene = CourierLayout.setCourierScene();

        //Scene 4 - Manager Layout
        // to be built
        
        //Window Setting
        primaryStage.setTitle("PAP_Prototype");
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(800);;
        primaryStage.setScene(courierScene);
        primaryStage.setOnCloseRequest(e->{
            e.consume();
            closeProgram(primaryStage);
        });


        //Last Command
        primaryStage.show();
    }

    //Function Closing Window 
    private void closeProgram(Stage window) {
        Boolean answer = ExitBox.display();
        if(answer){
            window.close();
        }

    }

}