package gui.layouts;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import gui.layouts.SubLayouts.ManagerSubLayouts.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ManagerLayout {

    static Scene menagerScene;
    static VBox parcelLayout;
    static VBox departmentLayout;
    static VBox carLayout;
    static VBox collectionPointLayout;
    static VBox positionLayout;
    static VBox employeeLayout;
    static JSONParser jsonParser;
    static JSONObject jsonObj;
    
    public static Scene setManagerScene(Stage primaryStage) {
      int width=0, height=0;
      String carLabel="", parcelLabel="", departmentsLabel="", collectionLabel="";
      String positionLabel="", employeesLabel ="";


      jsonParser = new JSONParser();
      try{
        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/sharedConfiguration.json"));
        
        width = (int) (long) jsonObj.get("WIDTH");
        height = (int) (long) jsonObj.get("HEIGHT");

        jsonObj = (JSONObject) jsonParser.parse(new FileReader("src/configurations/managerLayout.json"));

        carLabel = (String) jsonObj.get("CAR_LABEL");
        parcelLabel = (String) jsonObj.get("PARCEL_LABEL");
        departmentsLabel = (String) jsonObj.get("DEPARTMENTS_LABEL");
        collectionLabel = (String) jsonObj.get("COLLECTION_POINTS_LABEL");
        positionLabel = (String) jsonObj.get("POSITIONS_LABEL");
        employeesLabel = (String) jsonObj.get("EMPLOYEES_LABEL");
        

      }catch (FileNotFoundException fe) {
        fe.printStackTrace();
      } catch (IOException io) {
        io.printStackTrace();
      } catch (ParseException pe) {
        pe.printStackTrace();
      }

        parcelLayout = ParcelLayout.setParcelLayout(width, primaryStage);
        departmentLayout = DepartmentLayout.setDepartmentLayout(width, primaryStage);
        carLayout = CarLayout.setCarLayout(width, primaryStage);
        collectionPointLayout = CollectionPointLayout.setCollectionPointLayout(width, primaryStage);
        positionLayout = PositionLayout.setPositionLayout(width, primaryStage);
        employeeLayout = EmployeeLayout.setEmployeeLayout(width, primaryStage);
        menagerScene = new Scene(employeeLayout, width, height);

        //Creating a TabPane
        TabPane tabPane = new TabPane();

        //Creating tabs and setting them unclosable
        Tab carTab = new Tab();
        carTab.setClosable(false);
        Tab parcelTab = new Tab();
        parcelTab.setClosable(false);
        Tab departmentTab = new Tab();
        departmentTab.setClosable(false);
        Tab collectionPointTab = new Tab();
        collectionPointTab.setClosable(false);
        Tab positionTab = new Tab();
        positionTab.setClosable(false);
        Tab employeeTab = new Tab();
        employeeTab.setClosable(false);

        //Setting the names of tabs
        carTab.setText(carLabel);
        parcelTab.setText(parcelLabel);
        departmentTab.setText(departmentsLabel);
        collectionPointTab.setText(collectionLabel);
        positionTab.setText(positionLabel);
        employeeTab.setText(employeesLabel);

        //Setting the content
        carTab.setContent(carLayout);
        parcelTab.setContent(parcelLayout);
        departmentTab.setContent(departmentLayout);
        collectionPointTab.setContent(collectionPointLayout);
        positionTab.setContent(positionLayout);
        employeeTab.setContent(employeeLayout);


        //Adding tabs to the tab pane
        tabPane.getTabs().addAll(parcelTab, employeeTab, positionTab, departmentTab, collectionPointTab, carTab);
        //Setting anchor pane as the layout
        AnchorPane pane = new AnchorPane();
        AnchorPane.setTopAnchor(tabPane, 15.0);
        AnchorPane.setRightAnchor(tabPane, 15.0);
        AnchorPane.setBottomAnchor(tabPane, 15.0);
        AnchorPane.setLeftAnchor(tabPane, 15.0);
        pane.getChildren().addAll(tabPane);
        //Setting the stage
        Scene menagerScene = new Scene(pane, width, height);
        return menagerScene;
    }
}