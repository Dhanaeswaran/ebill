package com.example.ebill;
import java.sql.Connection;
import java.sql.DriverManager;
public class Databaseconnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "electricitybilling";
        String databaseUser = "root";
        String databasePassword = "dhanaeswaran2004";
        String url = "jdbc:mysql://localhost/"+databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
