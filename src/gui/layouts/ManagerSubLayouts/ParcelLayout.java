package gui.layouts.ManagerSubLayouts;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.Connection;

import database.classes.Parcel;
import gui.boxes.AddBox;

import database.accessors.ParcelDataAccessor;
import database.Database;

import database.classes.Converter;

public class ParcelLayout {

    static TableView<Parcel> parcelTable;

    public static VBox setParcelLayout(Double sceneWidth) {
        // Search Field
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        // Setting ParcelTable

        // ID Column
        TableColumn<Parcel, String> idColumn = new TableColumn<>("Parcel ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID_S"));

        // sName Column
        TableColumn<Parcel, String> sNameColumn = new TableColumn<>("Sender Name");
        sNameColumn.setMinWidth(100);
        sNameColumn.setCellValueFactory(new PropertyValueFactory<>("sName"));
        sNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setSName(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // sSurname Column
        TableColumn<Parcel, String> sSurnameColumn = new TableColumn<>("Sender Surname");
        sSurnameColumn.setMinWidth(100);
        sSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("sSurname"));
        sSurnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sSurnameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setSSurname(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // sAddress_street Column
        TableColumn<Parcel, String> sStreetColumn = new TableColumn<>("Sender Street");
        sStreetColumn.setMinWidth(100);
        sStreetColumn.setCellValueFactory(new PropertyValueFactory<>("sAddress_street"));
        sStreetColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sStreetColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setSAddress_street(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // sAddress_houseNumber Column
        TableColumn<Parcel, String> sHouseNumberColumn = new TableColumn<>("Sender HN");
        sHouseNumberColumn.setMinWidth(100);
        sHouseNumberColumn.setCellValueFactory(new PropertyValueFactory<>("sAddress_houseNumber"));
        sHouseNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sHouseNumberColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setSAddress_houseNumber(Converter.StringToInt(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });
        
        // sAddress_apartmentNumber Column
        TableColumn<Parcel, String> sApartmentNumberColumn = new TableColumn<>("Sender AN");
        sApartmentNumberColumn.setMinWidth(100);
        sApartmentNumberColumn.setCellValueFactory(new PropertyValueFactory<>("sAddress_apartmentNumber"));
        sApartmentNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sApartmentNumberColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setSAddress_apartmentNumber(Converter.StringToInt(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });
        
        // sAddress_city Column
        TableColumn<Parcel, String> sCityColumn = new TableColumn<>("Sender City");
        sCityColumn.setMinWidth(100);
        sCityColumn.setCellValueFactory(new PropertyValueFactory<>("sAddress_city"));
        sCityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sCityColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setSAddress_city(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // sAddress_postalCode Column
        TableColumn<Parcel, String> sPostalCodeColumn = new TableColumn<>("Sender PC");
        sPostalCodeColumn.setMinWidth(100);
        sPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("sAddress_postalCode"));
        sPostalCodeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sPostalCodeColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setSAddress_postalCode(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // sAddress_countryID Column
        TableColumn<Parcel, String> sCountryIDColumn = new TableColumn<>("Sender Country ID");
        sCountryIDColumn.setMinWidth(100);
        sCountryIDColumn.setCellValueFactory(new PropertyValueFactory<>("sAddress_countryID"));
        sCountryIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sCountryIDColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setSAddress_countryID(Converter.StringToInt(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });        

        // sEmail Column
        TableColumn<Parcel, String> sEmailColumn = new TableColumn<>("Sender Email");
        sEmailColumn.setMinWidth(100);
        sEmailColumn.setCellValueFactory(new PropertyValueFactory<>("sEmail"));
        sEmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sEmailColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setSEmail(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // sPhoneNumber Column
        TableColumn<Parcel, String> sPhoneNumberColumn = new TableColumn<>("Sender PN");
        sPhoneNumberColumn.setMinWidth(100);
        sPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("sPhoneNumber"));
        sPhoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        sPhoneNumberColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setSPhoneNumber(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // rName Column
        TableColumn<Parcel, String> rNameColumn = new TableColumn<>("Receiver Name");
        rNameColumn.setMinWidth(100);
        rNameColumn.setCellValueFactory(new PropertyValueFactory<>("rName"));
        rNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setRName(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // rSurname Column
        TableColumn<Parcel, String> rSurnameColumn = new TableColumn<>("Receiver Surname");
        rSurnameColumn.setMinWidth(100);
        rSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("rSurname"));
        rSurnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rSurnameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setRSurname(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // rAddress_street Column
        TableColumn<Parcel, String> rStreetColumn = new TableColumn<>("Receiver Street");
        rStreetColumn.setMinWidth(100);
        rStreetColumn.setCellValueFactory(new PropertyValueFactory<>("rAddress_street"));
        rStreetColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rStreetColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setRAddress_street(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // rAddress_houseNumber Column
        TableColumn<Parcel, String> rHouseNumberColumn = new TableColumn<>("Receiver HN");
        rHouseNumberColumn.setMinWidth(100);
        rHouseNumberColumn.setCellValueFactory(new PropertyValueFactory<>("rAddress_houseNumber"));
        rHouseNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rHouseNumberColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setRAddress_houseNumber(Converter.StringToInt(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });
        
        // sAddress_apartmentNumber Column
        TableColumn<Parcel, String> rApartmentNumberColumn = new TableColumn<>("Receiver AN");
        rApartmentNumberColumn.setMinWidth(100);
        rApartmentNumberColumn.setCellValueFactory(new PropertyValueFactory<>("rAddress_apartmentNumber"));
        rApartmentNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rApartmentNumberColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setRAddress_apartmentNumber(Converter.StringToInt(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });
        
        // sAddress_city Column
        TableColumn<Parcel, String> rCityColumn = new TableColumn<>("Receiver City");
        rCityColumn.setMinWidth(100);
        rCityColumn.setCellValueFactory(new PropertyValueFactory<>("rAddress_city"));
        rCityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rCityColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setRAddress_city(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // rAddress_postalCode Column
        TableColumn<Parcel, String> rPostalCodeColumn = new TableColumn<>("Receiver PC");
        rPostalCodeColumn.setMinWidth(100);
        rPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("rAddress_postalCode"));
        rPostalCodeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rPostalCodeColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setRAddress_postalCode(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // rAddress_countryID Column
        TableColumn<Parcel, String> rCountryIDColumn = new TableColumn<>("Receiver Country ID");
        rCountryIDColumn.setMinWidth(100);
        rCountryIDColumn.setCellValueFactory(new PropertyValueFactory<>("rAddress_countryID"));
        rCountryIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rCountryIDColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setRAddress_countryID(Converter.StringToInt(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });

        // rEmail Column
        TableColumn<Parcel, String> rEmailColumn = new TableColumn<>("Receiver Email");
        rEmailColumn.setMinWidth(100);
        rEmailColumn.setCellValueFactory(new PropertyValueFactory<>("rEmail"));
        rEmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rEmailColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setREmail(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // rPhoneNumber Column
        TableColumn<Parcel, String> rPhoneNumberColumn = new TableColumn<>("Receiver PN");
        rPhoneNumberColumn.setMinWidth(100);
        rPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("rPhoneNumber"));
        rPhoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rPhoneNumberColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                ((Parcel)
                t.getTableView().getItems().get(t.getTablePosition().getRow())).setRPhoneNumber(t.getNewValue());
                parcelTable.refresh();
            }
        });

        // Status Column
        TableColumn<Parcel, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMinWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Cost Column
        TableColumn<Parcel, String> costColumn = new TableColumn<>("Cost");
        costColumn.setMinWidth(100);
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost_S"));
        costColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        costColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setCost(Converter.StringToDouble(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });

        // Weight Column
        TableColumn<Parcel, String> weightColumn = new TableColumn<>("Weight");
        weightColumn.setMinWidth(50);
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight_S"));
        weightColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        weightColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setWeight(Converter.StringToDouble(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });

        // Length Column
        TableColumn<Parcel, String> lengthColumn = new TableColumn<>("Length");
        lengthColumn.setMinWidth(50);
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length_S"));
        lengthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lengthColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setLength(Converter.StringToDouble(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });
        
        // Width Column
        TableColumn<Parcel, String> widthColumn = new TableColumn<>("Width");
        widthColumn.setMinWidth(50);
        widthColumn.setCellValueFactory(new PropertyValueFactory<>("width_S"));
        widthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        widthColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setWidth(Converter.StringToDouble(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });

        // Height Column
        TableColumn<Parcel, String> heightColumn = new TableColumn<>("Height");
        heightColumn.setMinWidth(50);
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height_S"));
        heightColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        heightColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToDouble(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setHeight(Converter.StringToDouble(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });

        // Code Column
        TableColumn<Parcel, String> codeColumn = new TableColumn<>("Code");
        codeColumn.setMinWidth(100);
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code_S"));
        codeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        codeColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToInt(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setCode(Converter.StringToInt(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });



        // carID Column
        TableColumn<Parcel, String> carIdColumn = new TableColumn<>("Car ID");
        carIdColumn.setMinWidth(50);
        carIdColumn.setCellValueFactory(new PropertyValueFactory<>("carID_S"));
        carIdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        carIdColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToInt(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setCarID(Converter.StringToInt(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });

        // CollectionPointID(CPI) Column
        TableColumn<Parcel, String> collectionPointIDColumn = new TableColumn<>("CPI");
        collectionPointIDColumn.setMinWidth(50);
        collectionPointIDColumn.setCellValueFactory(new PropertyValueFactory<>("collectionPointID_S"));
        collectionPointIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        collectionPointIDColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToInt(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setCollectionPointID(Converter.StringToInt(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });

        // DepartmentID Column
        TableColumn<Parcel, String> departmentIDColumn = new TableColumn<>("Dep ID");
        departmentIDColumn.setMinWidth(50);
        departmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("departmentID_S"));
        departmentIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        departmentIDColumn.setOnEditCommit(new EventHandler<CellEditEvent<Parcel, String>>() {
            @Override
            public void handle(CellEditEvent<Parcel, String> t) {
                if (Converter.StringToInt(t.getNewValue()) != 0) {
                    ((Parcel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setDepartmentID(Converter.StringToInt(t.getNewValue()));
                }
                parcelTable.refresh();
            }
        });

        // Final Table
        parcelTable = new TableView<>();
        parcelTable.setItems(getParcel());
        parcelTable.getColumns().addAll(idColumn, sNameColumn, sSurnameColumn, sStreetColumn, sHouseNumberColumn, sApartmentNumberColumn, sCityColumn, sPostalCodeColumn, sCountryIDColumn, sEmailColumn, sPhoneNumberColumn, rNameColumn, rSurnameColumn, rStreetColumn, rHouseNumberColumn, rApartmentNumberColumn, rCityColumn, rPostalCodeColumn, rCountryIDColumn, rEmailColumn, rPhoneNumberColumn, carIdColumn, statusColumn, costColumn, weightColumn, lengthColumn, widthColumn, heightColumn, codeColumn, collectionPointIDColumn, departmentIDColumn);
        parcelTable.setEditable(true);
        parcelTable.autosize();
        parcelTable.setMaxHeight(Double.MAX_VALUE);
        parcelTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Init scene and layout
        VBox layout2 = new VBox();

        // Buttons under table
        Double width = sceneWidth / 6;

        HBox buttonLayout = new HBox();
        buttonLayout.setPadding(new Insets(10, 0, 10, 0));
        buttonLayout.setSpacing(width / 3);

        // Delete
        Button deletButton = new Button("Delete");
        HBox.setHgrow(deletButton, Priority.ALWAYS);
        deletButton.setMinWidth(width);
        deletButton.setMaxWidth(Double.MAX_VALUE);
        deletButton.setOnAction(e -> deleteButtonClicked());

        // Add
        Button addButton = new Button("Add");
        HBox.setHgrow(addButton, Priority.ALWAYS);
        addButton.setMinWidth(width);
        addButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setOnAction(e -> addButtonClicked());

        // Commit
        Button btn6 = new Button("Commit");
        HBox.setHgrow(btn6, Priority.ALWAYS);
        btn6.setMinWidth(width);
        btn6.setMaxWidth(Double.MAX_VALUE);

        buttonLayout.getChildren().addAll(deletButton, addButton, btn6);

        // Scene/layout
        VBox.setVgrow(parcelTable, Priority.ALWAYS);
        layout2.setSpacing(5);
        layout2.setPadding(new Insets(10, 10, 10, 10));
        layout2.getChildren().addAll(searchField, parcelTable, buttonLayout);

        // Final Commands
        return layout2;
    }

    // Get all of the
    public static ObservableList<Parcel> getParcel() {
        List<Parcel> parcelList = new ArrayList<>();
        try {
            Connection connection = Database.getConnection("BD1_Z15", "twheas");
            ParcelDataAccessor parcelAccessor = new ParcelDataAccessor(connection);
            parcelList = parcelAccessor.getParcelsList(
                    "select * from parcels");
            Database.closeConnection(connection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ObservableList<Parcel> parcels = FXCollections.observableArrayList(parcelList);

        return parcels;
    }

    // Delete button clicked
    public static void deleteButtonClicked() {
        ObservableList<Parcel> pracelsSelected;
        pracelsSelected = parcelTable.getSelectionModel().getSelectedItems();
        ArrayList<Parcel> rows = new ArrayList<>(pracelsSelected);
        rows.forEach(row -> parcelTable.getItems().remove(row));
    }

    //Add button clicked
    public static void addButtonClicked(){
        Parcel parcel = AddBox.display();
        parcelTable.getItems().add(parcel);
    }

}