package gui.boxes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;

import database.classes.Converter;
import database.classes.Employee;

import java.sql.Date;

public class AddBox_Emp {

    static Employee employee;
    static Date date;

    public static Employee display(){
        Stage window = new Stage();
        window.setMinWidth(250);
        window.setMinHeight(150);
        Scene scene;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Box");

        //Department Informations TextFields
        TextField name = new TextField();
        name.setPromptText("Name");
        TextField surname = new TextField();
        surname.setPromptText("Surname");
        TextField pesel = new TextField();
        pesel.setPromptText("Pesel");
        TextField salary = new TextField();
        salary.setPromptText("Salary");
        TextField date_S = new TextField();
        date_S.setPromptText("Employment Date \"yyyy-[m]m-[d]d\"");
        TextField position = new TextField();
        position.setPromptText("Position_ID");
        TextField department = new TextField();
        department.setPromptText("Department ID");
        TextField manager = new TextField();
        manager.setPromptText("Manager ID");

        //OK Button
        Button btnOK = new Button();
        btnOK.setText("OK");
        btnOK.setMinWidth(25);
        btnOK.setOnAction(e -> {
            date = Converter.StringToDate(date_S.getText());
            employee = new Employee(0, name.getText(), surname.getText(), Converter.StringToInt(pesel.getText()), Converter.StringToInt(salary.getText()), date, "", "", 0, Converter.StringToInt(position.getText()), Converter.StringToInt(manager.getText()), Converter.StringToInt(department.getText()));
            window.close(); 
        });


        //Layouts
        HBox lay1 = new HBox();
        lay1.getChildren().addAll(name, surname, pesel, salary, date_S);
        lay1.setSpacing(5);
        
        HBox spacing1 = new HBox();
        spacing1.setMinHeight(10);

        HBox lay2 = new HBox();
        lay2.getChildren().addAll(position, manager, department);
        lay2.setSpacing(5);

        VBox finalLay = new VBox();
        finalLay.getChildren().addAll(lay1, spacing1, lay2, btnOK);
        finalLay.setSpacing(10);
        finalLay.setPadding(new Insets(10, 10, 10, 10));

        scene = new Scene(finalLay);

        window.setScene(scene);
        window.showAndWait();

        return employee;
    }
}
