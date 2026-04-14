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
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Даниил Василенко
 */
public class BookDAO {
    public void addBook(Book book){
        String sql = "INSERT INTO Books (Title, Author, Category, Available) VALUES (?, ?, ?, ?)";

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
    }
    }
    public void getAllBooks(){
        String sql = "SELECT * FROM Books";
        
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                int id = rs.getInt("ID");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String category = rs.getString("Category");
                int available = rs.getInt("Available");
                
                System.out.println("ID: " + id + 
                                   ", Title: " + title +
                                   ", Author: " + author +
                                   ", Category: " + category + 
                                   ", Available: " + (available == 1 ? "Yes" : "No"));
            }
        } catch(SQLException e){
            System.out.println("Error retrieving books: " + e.getMessage());
        }
    }
    public void deleteBookById(int id){
        String sql = "DELETE FROM Books WHERE ID = ?";
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Book deleted successfully");
            } else {
                System.out.println("No book found with that ID");
            }
        } catch (SQLException e){
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }
    public void updateBook(Book book){
        String sql = "UPDATE Books SET Title = ?, Author = ?, Category = ?, Available = ? WHERE ID = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setInt(4, book.isAvailable() ? 1:0);
            pstmt.setInt(5, book.getId());
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Book updated successfully");
            } else {
                System.out.println("No book found with that ID");
            }
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }
    public void searchBookById(int id){
        String sql = "SELECT * FROM Books WHERE ID = ?";
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                int bookId = rs.getInt("ID");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String category = rs.getString("Category");
                int available = rs.getInt("Available");
                System.out.println("ID: " + bookId + 
                                   ", Title: " + title + 
                                   ", Author: " + author + 
                                   ", Category: " + category + 
                                   ", Available: " + (available == 1 ? "Yes" : "No"));
            } else {
                System.out.println("No book found with that ID");
            }
        } catch (SQLException e){
            System.out.println("Error searching for a book: " + e.getMessage());
        }
    }
    public void searchBookByTitleOrAuthor(String keyword){
        String sql = "SELECT * FROM Books WHERE Title LIKE ? OR Author LIKE ?";
        
        try(Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            boolean found = false;
            while(rs.next()){
                found = true;
                int bookId = rs.getInt("ID");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String category = rs.getString("Category");
                int available = rs.getInt("Available");
                System.out.println("ID: " + bookId +
                                   ", Title: " + title + 
                                   ", Author: " + author + 
                                   ", Category: " + category + 
                                   ", Available: " + (available == 1 ? "Yes" : "No"));
            }
            if(!found){
                System.out.println("No books found with that title or author");
            }
        } catch (SQLException e){
            System.out.println("Error searching for a book: " + e.getMessage());
        } 
    }
    
}

