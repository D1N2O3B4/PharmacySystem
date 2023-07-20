package com.example.pharmacysystem;
import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    public static Connection connectDatabase(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/pharmacy","root","admin");
            return  connect;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
