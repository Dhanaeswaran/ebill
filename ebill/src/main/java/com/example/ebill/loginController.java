package com.example.ebill;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static javafx.stage.StageStyle.UNDECORATED;


public class loginController implements Initializable{

    @FXML
    private Label DISPLAYPAYBILL;

    @FXML
    public TextArea disamo;

    @FXML
    private Button FILLDATA;

    @FXML
    public TextArea disname;


    @FXML
    public TextArea dispow;

    public static String ser;

    public String getname(){
        String nam=ser;
        return nam;
    }

    @FXML
    private TextField serdisfield;



    @FXML
    private Button backbutton;

    @FXML
    private Button paybutton;

    @FXML
    void backaction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("electriclogin.fxml"));
        Stage window = (Stage) backbutton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 1540, 780));
    }

    @FXML
    void payaction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("paybill.fxml"));
        Stage window = new Stage();
        window.initStyle(UNDECORATED);
        window.setScene(new Scene(fxmlLoader.load(),600,400));
        window.show();

        DISPLAYPAYBILL.setText("Payed Successfully");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        electricloginController ak = new electricloginController();
        ser =ak.getname();
        serdisfield.setText(ser);
        Databaseconnection connectnow = new Databaseconnection();
        Connection connectDB = connectnow.getConnection();
        String verifylogin = "SELECT * FROM user WHERE service_no = '"+ser+"';";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            if(queryResult.next())
            {
                String name = queryResult.getString("name");
                System.out.println(name);
                disname.setText(name);
                String pass = queryResult.getString("unitused");
                dispow.setText(pass);
                disamo.setText(queryResult.getString("amount"));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
}
