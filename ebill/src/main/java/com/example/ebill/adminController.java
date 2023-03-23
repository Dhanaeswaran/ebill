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
import java.sql.Statement;

public class adminController {

    @FXML
    private Button babutton;



    @FXML
    private TextField servicefield;

    @FXML
    private Button upbutton;


    @FXML
    private TextField finalunitusedfield;

    @FXML
    private TextField initialunitusedfield;

    @FXML
    private Label successlable;


    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("adminlogin.fxml"));
        Stage window = (Stage) babutton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 1540, 780));
    }

    @FXML
    void update(ActionEvent event) {
        if(servicefield.getText().isBlank()==false&&initialunitusedfield.getText().isBlank()==false&&finalunitusedfield.getText().isBlank()==false) {
            Databaseconnection connectnow = new Databaseconnection();
            Connection connectDB = connectnow.getConnection();
            String insert = "update user set initialize_reading='" + initialunitusedfield.getText() + "' where service_no='" + servicefield.getText() + "';";
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insert);
//                successlable.setText("Successfully Updated");
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            insert = "update user set final_reading='" + finalunitusedfield.getText() + "' where service_no='" + servicefield.getText() + "';";
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insert);
//                successlable.setText("Successfully Updated");
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

            double units = (Double.parseDouble(finalunitusedfield.getText()))-(Double.parseDouble(initialunitusedfield.getText()));
            System.out.println(units);
            insert = "update user set unitused='" +units+ "' where service_no='" + servicefield.getText() + "';";
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insert);
//                successlable.setText("Successfully Updated");
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            double billToPay = 0;
            // check whether units are less than 100
            if(units < 100)
            {
                billToPay = 0;
            }
            // check whether the units are less than 300
            else if(units < 300){
                billToPay = 100 * 1.20 + (units - 100) * 2;
            }
            // check whether the units are greater than 300
            else if(units > 300) {
                billToPay = 100 * 1.20 + 200 * 2 + (units - 300) * 3;
            }
            insert = "update user set amount='" + billToPay + "' where service_no='" + servicefield.getText() + "';";
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insert);
                successlable.setText("Successfully Updated");
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        } else if (servicefield.getText().isBlank()==true&&initialunitusedfield.getText().isBlank()==true&&finalunitusedfield.getText().isBlank()==true) {
            successlable.setText("Please enter service no or unit used");
        } else {
            successlable.setText("Invalid service no or Enter unit used");
        }
    }


}
