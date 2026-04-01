/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user_interface;
import core.DBConnection;
import java.sql.Connection;
import database.BookDAO;
import entities.Book;
/**
 *
 * @author Даниил Василенко
 */
public class Main {
    public static void main(String[] args){
        Connection conn = DBConnection.connect();
        if(conn != null){
            System.out.println("Program started successfully.");
            Book book = new Book(0, "Java Programming", "John Smith", "Programming", true);
            BookDAO bookDAO = new BookDAO();
            bookDAO.addBook(book);
        } else {
            System.out.println("Program could not connect to the database.");
        }
        
    }
}
