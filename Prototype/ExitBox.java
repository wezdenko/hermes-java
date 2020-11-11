import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        btnY.setText("Yes");
        btnY.setOnAction(e -> {
            window.close(); 
            answer = true;
        });

        //No Button
        Button btnN = new Button();
        btnN.setText("No");
        btnN.setOnAction(e -> {
            window.close(); 
            answer = false;
        });

        //Label
        Label label = new Label("Are you sure you want to exit?");

        //Layout
        VBox layout = new VBox();
        layout.getChildren().addAll(label, btnY, btnN);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout);

        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
    
}
