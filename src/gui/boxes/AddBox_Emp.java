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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.Date;

public class AddBox_Emp {

    static Employee employee;
    static Date date;
    static JSONParser jsonParser;
    static JSONObject jsonObj;

    public static Employee display(){
      int width=0, height=0, buttonSize=0, padding=0, spacing=0; 
      String cssPath="";

      jsonParser = new JSONParser();
      try{
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/sharedConfiguration.json"));
        
        padding = (int) (long) jsonObj.get("PADDING");
        spacing = (int) (long) jsonObj.get("SPACING");
        cssPath = (String) jsonObj.get("CSS_PATH");
        
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/boxes.json"));
        width = (int) (long) jsonObj.get("BOX_WIDTH");
        height = (int) (long) jsonObj.get("BOX_HEIGHT");
        buttonSize = (int) (long) jsonObj.get("BUTTON_SIZE");

      }catch (FileNotFoundException fe) {
        fe.printStackTrace();
      } catch (IOException io) {
        io.printStackTrace();
      } catch (ParseException pe) {
        pe.printStackTrace();
      }
        Stage window = new Stage();
        window.setMinWidth(width);
        window.setMinHeight(height);
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
        btnOK.setMinWidth(buttonSize);
        btnOK.setOnAction(e -> {
            date = Converter.StringToDate(date_S.getText());
            employee = new Employee(0, name.getText(), surname.getText(), Converter.StringToInt(pesel.getText()), Converter.StringToInt(salary.getText()), date, "", "", 0, Converter.StringToInt(position.getText()), Converter.StringToInt(manager.getText()), Converter.StringToInt(department.getText()));
            window.close(); 
        });


        //Layouts
        HBox lay1 = new HBox();
        lay1.getChildren().addAll(name, surname, pesel, salary, date_S);
        lay1.setSpacing(spacing);
        
        HBox spacing1 = new HBox();
        spacing1.setMinHeight(padding);

        HBox lay2 = new HBox();
        lay2.getChildren().addAll(position, manager, department);
        lay2.setSpacing(spacing);

        VBox finalLay = new VBox();
        finalLay.getChildren().addAll(lay1, spacing1, lay2, btnOK);
        finalLay.setSpacing(padding);
        finalLay.setPadding(new Insets(padding, padding, padding, padding));

        scene = new Scene(finalLay);
        scene.getStylesheets().add(cssPath);

        window.setScene(scene);
        window.showAndWait();

        return employee;
    }
}
