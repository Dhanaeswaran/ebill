package com.example.ebill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

public class electricloginController {

    private Parent root;

    @FXML
    public Button cancelbutton;


    public static String service;

    public String getname(){
        String lkj = service;
        return lkj;
    }

    @FXML
    public Button loginbutton;


    @FXML
    public Label invalidlable;

    @FXML
    public PasswordField passwordtextfield;

    @FXML
    public TextField usreidtextfield;

    @FXML
    public Button adduser;

    @FXML
    public Button adminlogin;

    public ResultSet queryResult;

    @FXML
    public void cancelbuttonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void adduseraction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newuser.fxml"));
        Stage window = (Stage) loginbutton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 1540, 780));
    }

    @FXML
    public void adminloginaction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("adminlogin.fxml"));
        Stage window = (Stage) loginbutton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 1540, 780));
    }

    @FXML
    public void loginaction(ActionEvent event) throws IOException {
        if (usreidtextfield.getText().isBlank() == false && passwordtextfield.getText().isBlank() == false) {
            validatelodin();
        } else
        {
            invalidlable.setText("Plese enter your username and password");
        }
    }

    public void validatelodin() {
        service = usreidtextfield.getText();
        Databaseconnection connectnow = new Databaseconnection();
        Connection connectDB = connectnow.getConnection();
        String verifylogin = "SELECT count(1) FROM user WHERE service_no = '"+usreidtextfield.getText()+"' and password = '"+passwordtextfield.getText()+"';";
        try {
            Statement statement = connectDB.createStatement();
            queryResult = statement.executeQuery(verifylogin);
            while (queryResult.next())
            {
                if(queryResult.getInt(1)==1){
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
                    root = fxmlLoader.load();
                    Stage window = (Stage) loginbutton.getScene().getWindow();
                    window.setScene(new Scene(root, 1540, 780));
//                    loginController log = new loginController();

                }else {
                    invalidlable.setText("Invalid Username or Password");
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
}
