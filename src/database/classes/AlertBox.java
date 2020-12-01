package database.classes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String message){
        Stage window = new Stage();
        window.setMinWidth(250);
        window.setMinHeight(150);
        Scene scene;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        //OK Button
        Button btn = new Button();
        btn.setText("OK");
        btn.setOnAction(e -> window.close());

        //Label
        Label label = new Label(message);

        //Layout
        VBox layout = new VBox();
        layout.getChildren().addAll(label, btn);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);
        scene = new Scene(layout);

        window.setScene(scene);
        window.showAndWait();
    }
    
}
