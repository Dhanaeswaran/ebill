package com.example.ebill;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class newuserController {

    @FXML
    private Button adduserbackbutton;


    @FXML
    private Label updatelable;

    @FXML
    private TextField disgetfield;

    @FXML
    private TextField namegetfield;

    @FXML
    private TextField passgetfield;

    @FXML
    private Button regisbutton;

    @FXML
    private TextField usidgetfield;

    @FXML
    void adduserbackaction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("electriclogin.fxml"));
        Stage window = (Stage) adduserbackbutton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 1540, 780));
    }

    @FXML
    void registeraction(ActionEvent event) throws SQLException {
        updatefile();
    }

    private void updatefile() {
        String data = usidgetfield.getText();
        String pass = passgetfield.getText();
        int passle = pass.length();
        int len = data.length();
        if (usidgetfield.getText().equals("") || namegetfield.getText().equals("") || disgetfield.getText().equals("") || passgetfield.getText().equals("")) {
                updatelable.setText("Don't give NULL value");
        }
        else{
            if (len == 12) {
                if (passle >= 6 && passle <= 10) {
                    Databaseconnection connectnow = new Databaseconnection();
                    Connection connectDB = connectnow.getConnection();
                    String insert = "INSERT INTO user (service_no,name,district,password) VALUES ('" + usidgetfield.getText() + "','" + namegetfield.getText() + "','" + disgetfield.getText() + "','" + passgetfield.getText() + "');";
                    try {
                        Statement statement = connectDB.createStatement();
                        statement.executeUpdate(insert);
                        updatelable.setText("Successfully Updated");
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                } else {
                    updatelable.setText("Invalid syntax, Password should be in 6 to 10 digits");
                }
            } else {
                updatelable.setText("Invalid syntax, Service no should be in 12 digits");
            }
        }
    }

}
