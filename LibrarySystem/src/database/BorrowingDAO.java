/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import core.DBConnection;
import entities.Borrowing;
import entities.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Даниил Василенко
 */
public class BorrowingDAO {
    public void addBorrowing(Borrowing borrowing){
        String sql = "INSERT INTO Borrowings(\"Book ID\", \"Member ID\", \"Borrow Date\", \"Due Date\", \"Status\") VALUES (?, ?, ?, ?, ?)";
        
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, borrowing.getBookId());
            pstmt.setInt(2, borrowing.getMemberId());
            pstmt.setString(3, borrowing.getBorrowDate());
            pstmt.setString(4, borrowing.getDueDate());
            pstmt.setString(5, borrowing.getStatus());
            
            pstmt.executeUpdate();
            System.out.println("Borrowing added successfully");
        } catch (SQLException e){
            System.out.println("Error adding borrowing: " + e.getMessage());
        }
    }
    public void getAllBorrowings(){
        String sql = "SELECT * FROM Borrowings";
        
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                int id = rs.getInt("ID");
                int bookId = rs.getInt("Book ID");
                int memberId = rs.getInt("Member ID");
                String borrowDate = rs.getString("Borrow Date");
                String dueDate = rs.getString("Due Date");
                String status = rs.getString("Status");
                
                System.out.println("ID: " + id +
                                   ", Book ID: " + bookId +
                                   ", Member ID: " + memberId +
                                   ", Borrow Date: " + borrowDate + 
                                   ", Due Date: " + dueDate +
                                   ", Status: " + status);
            }
        } catch (SQLException e){
            System.out.println("Error retrieving borrowings" + e.getMessage());
        }
    }
    public void updateBorrowingStatus(int borrowingId, String newStatus){
        String getBookSql = "SELECT \"Book ID\" FROM Borrowings WHERE ID = ?";
        String updateBorrowingSql = "UPDATE Borrowings SET \"Status\" = ? WHERE ID = ?";
        String updateBookSql = "UPDATE Books SET Available = ? WHERE ID = ?";
        
        try (Connection conn = DBConnection.connect()){
            int bookId = -1;
            try (PreparedStatement pstmt = conn.prepareStatement(getBookSql)){
                pstmt.setInt(1, borrowingId);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()){
                    bookId = rs.getInt("Book ID");
                } else {
                    System.out.println("No borrowing found with that ID");
                    return;
                }
            }
            try (PreparedStatement pstmt = conn.prepareStatement(updateBorrowingSql)) {
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, borrowingId);
            pstmt.executeUpdate();
            
            }   
            
            int availableValue;
            if (newStatus.equalsIgnoreCase("Returned")){
                availableValue = 1;
            } else if (newStatus.equalsIgnoreCase("Borrowed")) {
                availableValue = 0;
            } else {
                System.out.println("Status updated, but book availability was not changed");
                return;
            }
        
        try(PreparedStatement pstmt = conn.prepareStatement(updateBookSql)){
            pstmt.setInt(1, availableValue);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
            System.out.println("Borrowing status updated successfully");
        }

    } catch (SQLException e){
            System.out.println("Error updating borrowing status: " + e.getMessage());
        }
        
    }
}
