package gui.layouts.SubLayouts;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import database.classes.AlertBox;

public class MenuBar_own {

    public static MenuBar setMenuBar() {
        MenuItem help = new MenuItem("Help");


        Menu menu = new Menu("Menu");
        menu.getItems().add(help);

        try{
        // Creating an image
        Image image;
        image = new Image("file:resources/tool.png", 15, 15, false, false);

        //Setting the image view 
        ImageView imageView = new ImageView(image);
        help.setGraphic(imageView);
        }
        catch(Exception e){
            AlertBox.display("Exception", "Problem with image loading");
        }

        help.setOnAction(e -> AlertBox.display("Help", "If you need help, contact us on:\n  programing@email.com"));

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);

        return menuBar;
    }}
