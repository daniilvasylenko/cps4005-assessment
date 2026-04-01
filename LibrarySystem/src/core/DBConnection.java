/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Даниил Василенко
 */
public class DBConnection {
    private static final String URL = "jdbc:sqlite:C:/sqlite/library.db";
    public static Connection connect(){
        try{
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Connected to database!");
            return conn;
        } catch(SQLException e){
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }
}
