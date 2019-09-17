package com.mulyadi.controller;

import com.mulyadi.entity.Category;
import com.mulyadi.entity.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<Category> comboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableColumn<Item, String> col01;
    @FXML
    private TableColumn<Item, String> col02;
    @FXML
    private TableColumn<Item, String> col03;
    @FXML
    private TableColumn<Item, String> col04;
    @FXML
    private TableView<Item> tableDepartment;
    private MainFormController controller;
    private ObservableList<Category> categories;
    private ObservableList<Item> items;

    @FXML
    private void saveBtn(ActionEvent actionEvent) {
    }

    @FXML
    private void resetBtn(ActionEvent actionEvent) {
    }

    @FXML
    private void updateBtn(ActionEvent actionEvent) {
    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        Item item = tableDepartment.getSelectionModel().getSelectedItem();
        txtID.setText(String.valueOf(item.getID()));
        txtName.setText(item.getNama());
//        comboBox.setItems();
//        datePicker.set
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col01.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(String.valueOf(i.getID()));

        });
        col02.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(i.getNama());
        });
        col03.setCellValueFactory((data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(i.getCategory().getNama());
        }));
        col04.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(i.getDate());
        });
    }

    public void setMaincontroller(MainFormController maincontroller) {
        this.controller = maincontroller;
        tableDepartment.setItems(maincontroller.getItems());
    }

    public ObservableList<Category> getCategories() {
        if(categories == null){
            categories = FXCollections.observableArrayList();
        }
        return categories;
    }

    public ObservableList<Item> getItems() {
        if(items == null){
            items = FXCollections.observableArrayList();
        }
        return items;
    }

}
