import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class App extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Welcom Message - Label
        Label welcom_msg = new Label("You have to enter your login and password");

        //Login - Label&TextField
        Label login_msg = new Label("Login:");
        TextField login_field = new TextField();
        login_field.setPromptText("...");

        //Password - Label&TextField
        Label password_msg = new Label("Password:");
        TextField password_field = new TextField();
        password_field.setPromptText("...");


        //Button 1
        Button log_btn = new Button();
        log_btn.setText("Log In");
        log_btn.setOnAction(e->{
            if(login_field.getText().equals("login") && password_field.getText().equals("password")){
                 System.out.println("logged in");
                 primaryStage.setScene(scene2);
            }
            else{
                AlertBox.display("ERROR", "WRONG PASSWORD OR LOGIN");
                System.out.println(login_field.getText());
            }     
                });
        

        //Scene 1 - Logging layout
        GridPane log_layout = new GridPane();
        log_layout.setPadding(new Insets(10, 10, 10, 10));
        log_layout.setVgap(8);
        log_layout.setHgap(10);
        log_layout.setConstraints(welcom_msg, 1, 1);
        log_layout.setConstraints(login_msg, 0, 3);
        log_layout.setConstraints(login_field, 1, 3);
        log_layout.setConstraints(password_msg, 0, 4);
        log_layout.setConstraints(password_field, 1, 4);
        log_layout.setConstraints(log_btn, 1, 5);
        log_layout.getChildren().addAll(welcom_msg,login_msg,password_msg,log_btn, password_field, login_field);
        log_layout.setAlignment(Pos.BASELINE_CENTER);
        scene1 = new Scene(log_layout, 400, 250);


        //Scene2 - Left Layout
        HBox layout2 = new HBox(20);
        Button btn2 = new Button("Search");
        Button btn3 = new Button("Your Profile");
        Button btn4 = new Button("Tasks");
        Button btn5 = new Button("Help");
        layout2.getChildren().addAll(btn2, btn3, btn4, btn5);
        layout2.setAlignment(Pos.BASELINE_LEFT);
        scene2 = new Scene(layout2, 600, 500);
        

        //Window Setting
        primaryStage.setTitle("PAP_Prototype");
        primaryStage.setScene(scene1);
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