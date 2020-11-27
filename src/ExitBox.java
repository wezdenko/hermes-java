import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;




public class ExitBox {

    static boolean answer;

    public static boolean display(){
        Stage window = new Stage();
        window.setMinWidth(250);
        window.setMinHeight(150);
        Scene scene;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Exit");

        //Yes Button
        Button btnY = new Button();
        btnY.setMinWidth(60);
        btnY.setText("Yes");
        btnY.setOnAction(e -> {
            window.close(); 
            answer = true;
        });

        //No Button
        Button btnN = new Button();
        btnN.setMinWidth(60);
        btnN.setText("No");
        btnN.setOnAction(e -> {
            window.close(); 
            answer = false;
        });

        //Label
        Label label = new Label("Are you sure you want to exit?");

        //Layout
        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(btnY, btnN);
        buttons.setAlignment(Pos.CENTER);


        VBox layout = new VBox();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(label, buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        scene = new Scene(layout);

        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
    
}
