package com.mulyadi.controller;

import com.mulyadi.entity.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryManagementController implements Initializable {
    @FXML
    private TableView<Category> tableDepartment;
    @FXML
    private TableColumn<Category, String> col01;
    @FXML
    private TableColumn<Category, String> col02;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNama;
    private MainFormController controller;

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        Category c = tableDepartment.getSelectionModel().getSelectedItem();
        txtID.setText(String.valueOf(c.getID()));
        txtNama.setText(c.getNama());
    }

    @FXML
    private void saveBtn(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col01.setCellValueFactory(data -> {
            Category c = data.getValue();
            return new SimpleStringProperty(String.valueOf(c.getID()));

        });
        col02.setCellValueFactory(data -> {
            Category c = data.getValue();
            return new SimpleStringProperty(c.getNama());
        });
    }
}
