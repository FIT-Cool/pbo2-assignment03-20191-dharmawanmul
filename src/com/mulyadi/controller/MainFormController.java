package com.mulyadi.controller;

import com.mulyadi.Main;
import com.mulyadi.entity.Category;
import com.mulyadi.entity.Item;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public Button SaveButton;
    public Button ResetButton;
    public Button UpdateButton;
    public MenuItem MenuClose;
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

    public void setController(MainFormController controller) {
        this.controller = controller;
        tableDepartment.setItems(controller.getItems());
        comboBox.setItems(controller.getCategories());
    }

    Alert alert = new Alert(Alert.AlertType.ERROR);
    private MainFormController controller;
    private ObservableList<Category> categories;
    private ObservableList<Item> items;

    @FXML
    private void saveBtn(ActionEvent actionEvent) {
        Item i = new Item();
        boolean found = false;
        alert.setHeaderText("Error");
        if (txtID.getText().isEmpty() || txtName.getText().isEmpty() || comboBox.getValue() == null || datePicker.getValue() == null) {
            alert.setContentText("Please fill item id/ name/ category/ date");
            alert.showAndWait();
        }
        else {
            i.setNama(txtName.getText());
            for (Item j : items) {
                if (i.getNama().equals(j.getNama())) {
                    found = true;
                    alert.setContentText("Duplicate item name");
                    alert.showAndWait();
                    break;
                }
                else if (i.getID() == j.getID()) {
                    found = true;
                    alert.setContentText("Duplicate item id");
                    alert.showAndWait();
                    break;
                }
            }
            if (!found) {
                i.setID(Integer.parseInt(txtID.getText()));
                i.setNama(txtName.getText());
                i.setCategory(comboBox.getValue());
                i.setDate(datePicker.getValue());
                controller.getItems().add(i);
            }
        }
    }

    @FXML
    private void resetBtn(ActionEvent actionEvent) {
        txtName.setText("");
        txtID.setText("");
        comboBox.setValue(null);
        datePicker.setValue(null);
        SaveButton.setDisable(false);
        UpdateButton.setDisable(true);
    }

    @FXML
    private void updateBtn(ActionEvent actionEvent) {
        SaveButton.setDisable(true);
        UpdateButton.setDisable(false);
        Item i = tableDepartment.getSelectionModel().getSelectedItem();
        i.setID(Integer.parseInt(txtID.getText()));
        i.setNama(txtName.getText());
        i.setCategory(comboBox.getValue());
        i.setDate(datePicker.getValue());
        tableDepartment.refresh();
    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        Item item = tableDepartment.getSelectionModel().getSelectedItem();
        txtID.setText(String.valueOf(item.getID()));
        txtName.setText(item.getNama());
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
            return new SimpleStringProperty(i.getDate().toString());
        });
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

    public void showCategoryManagement(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/CategoryManagement.fxml"));
            BorderPane root = loader.load();
            CategoryManagementController categoryManagementController = loader.getController();
            categoryManagementController.setController(this);
            Stage mainStage = new Stage();
            mainStage.initModality(Modality.APPLICATION_MODAL);
            mainStage.setTitle("Category Management");
            mainStage.setScene(new Scene(root));
            mainStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void MenuClose(ActionEvent actionEvent) {
        Platform.exit();
    }
}
