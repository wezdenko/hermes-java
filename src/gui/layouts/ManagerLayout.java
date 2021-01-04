package gui.layouts;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import database.classes.Parcel;
import database.classes.AlertBox;

import gui.boxes.ExitBox;
import gui.layouts.ManagerSubLayouts.*;

public class ManagerLayout {

    static Scene menagerScene;
    static VBox parcelLayout;
    static VBox departmentLayout;
    static VBox carLayout;
    static VBox collectionPointLayout;
    static VBox positionLayout;
    static VBox employeeLayout;
    
    public static Scene setManagerScene() {
        double width = 700;
        double height = 500;
        parcelLayout = ParcelLayout.setParcelLayout(width);
        departmentLayout = DepartmentLayout.setDepartmentLayout(width);
        carLayout = CarLayout.setCarLayout(width);
        collectionPointLayout = CollectionPointLayout.setCollectionPointLayout(width);
        positionLayout = PositionLayout.setPositionLayout(width);
        employeeLayout = EmployeeLayout.setEmployeeLayout(width);
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
        carTab.setText("Cars");
        parcelTab.setText("Parcels");
        departmentTab.setText("Departments");
        collectionPointTab.setText("Collection Points");
        positionTab.setText("Positions");
        employeeTab.setText("Employees");

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
        Scene menagerScene = new Scene(pane, 595, 300);
        return menagerScene;
    }
}