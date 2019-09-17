package com.mulyadi.controller;

import com.mulyadi.entity.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.Inet4Address;
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

    public void setController(MainFormController controller) {
        this.controller = controller;
        tableDepartment.setItems(controller.getCategories());
    }

    Alert alert = new Alert(Alert.AlertType.ERROR);
    private MainFormController controller;

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        Category c = tableDepartment.getSelectionModel().getSelectedItem();
        txtID.setText(String.valueOf(c.getID()));
        txtNama.setText(c.getNama());
    }

    @FXML
    private void saveBtn(ActionEvent actionEvent) {
        Category c = new Category();
        boolean found = false;
        alert.setHeaderText("Error");
        if (txtID.getText().isEmpty() && txtNama.getText().isEmpty()) {
            alert.setContentText("Please fill category id and name");
            alert.showAndWait();
        }
        else if(txtID.getText().isEmpty()) {
            alert.setContentText("Please fill category id");
            alert.showAndWait();
        }
        else if(txtNama.getText().isEmpty()){
            alert.setContentText("Please fill category name");
            alert.showAndWait();
        }
        else {
            c.setID(Integer.parseInt(txtID.getText()));
            c.setNama(txtNama.getText());
            for (Category cat : controller.getCategories()) {
                if (c.getNama().equals(cat.getNama()) && c.getID() == cat.getID()) {
                    found = true;
                    alert.setContentText("Duplicate category name and id");
                    alert.showAndWait();
                    break;
                }
                else if (c.getID() == cat.getID()) {
                    found = true;
                    alert.setContentText("Duplicate category id");
                    alert.showAndWait();
                    break;
                }
                else if (c.getNama().equals(cat.getNama())) {
                    found = true;
                    alert.setContentText("Duplicate category id");
                    alert.showAndWait();
                    break;
                }
            }
            if (!found) {
                c.setID(Integer.parseInt(txtID.getText()));
                c.setNama(txtNama.getText());
                controller.getCategories().add(c);
            }
        }
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
