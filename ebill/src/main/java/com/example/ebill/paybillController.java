package com.example.ebill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class paybillController implements Initializable {

        @FXML
        private Button closebuttondis;

        @FXML
        private Label amodis;

        @FXML
        private Label distdis;

        @FXML
        private Label namedis;

        @FXML
        private Label serdis;

        @FXML
        private Label unitdis;

    @FXML
    void closeact(ActionEvent event) {
        Stage stage = (Stage) closebuttondis.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        electricloginController ak = new electricloginController();
        String ser =ak.getname();
        serdis.setText(ser);
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
                namedis.setText(name);
                distdis.setText(queryResult.getString("district"));
                String pass = queryResult.getString("unitused");
                unitdis.setText(pass);
                amodis.setText(queryResult.getString("amount"));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
        String insert = "update user set unitused='0' where service_no='" +ser+ "';";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insert);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        insert = "update user set amount='0' where service_no='" +ser+ "';";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insert);
//            DISPLAYPAYBILL.setText("Payed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
