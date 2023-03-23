package com.example.ebill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class adminloginController {

    @FXML
    private Button adminbackbutton;

    @FXML
    private Label adminerrorlable;

    @FXML
    private TextField adminidfield;

    @FXML
    private Button adminloginbutton;

    @FXML
    private PasswordField adminpasswordfield;

    @FXML
    void adminbackaction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("electriclogin.fxml"));
        Stage window = (Stage) adminbackbutton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 1540, 780));
    }

    @FXML
    void adminlogin(ActionEvent event) throws IOException {
        if (adminidfield.getText().isBlank() == false && adminpasswordfield.getText().isBlank() == false) {
            validatelodin();
        } else
        {
            adminerrorlable.setText("Plese enter your username and password");
        }
    }
    public void validatelodin() throws IOException {
                if(adminidfield.getText().equals("admin001") && adminpasswordfield.getText().equals("adminpass")){
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("admin.fxml"));
                    Stage window = (Stage) adminloginbutton.getScene().getWindow();
                    window.setScene(new Scene(fxmlLoader.load(), 1540, 780));
                }else {
                    adminerrorlable.setText("Invalid Username or Password");
                }


    }

}
