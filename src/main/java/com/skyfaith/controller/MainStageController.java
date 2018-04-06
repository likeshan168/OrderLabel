package com.skyfaith.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class MainStageController implements Initializable {
//public class MainStageController {
    @FXML
    private TextField orderNoField;


    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleOrderNoChange() {
//        if (event.getKeyCode() == 13) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");

            alert.setContentText(this.orderNoField.getText());
            alert.showAndWait();
//        }

    }

//    @FXML
//    private void chooseEnglish(){
//        loadView
//    }
}
