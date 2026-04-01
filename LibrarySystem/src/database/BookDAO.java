/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import core.DBConnection;
import entities.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Даниил Василенко
 */
public class BookDAO {
    public void addBook(Book book){
        String sql = "INSERT INTO Books (Title, Author, Category, Availability) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setInt(4, book.isAvailable() ? 1 : 0);

            pstmt.executeUpdate();
            System.out.println("Book added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
    }  }
    
}

