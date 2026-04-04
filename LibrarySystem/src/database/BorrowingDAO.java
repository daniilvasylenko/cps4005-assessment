/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import core.DBConnection;
import entities.Borrowing;
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
        String checkAvailabilitySql = "SELECT Available FROM Books WHERE ID = ?";
        String sql = "INSERT INTO Borrowings(\"Book ID\", \"Member ID\", \"Borrow Date\", \"Due Date\", \"Status\") VALUES (?, ?, ?, ?, ?)";
        String updateBookSql = "UPDATE Books SET Available = ? WHERE ID = ?";
        
        try(Connection conn = DBConnection.connect()){
            int available = -1;
            
            try(PreparedStatement checkStmt = conn.prepareStatement(checkAvailabilitySql)){
                checkStmt.setInt(1, borrowing.getBookId());
                ResultSet rs = checkStmt.executeQuery();
                
                if(rs.next()){
                    available = rs.getInt("Available");
                } else{
                    System.out.println("Book not found");
                    return;
                }
                if(available == 0){
                    System.out.println("The book is currently unavailable");
                    return;
                }
            
            } 
            } catch(SQLException e){
                System.out.println("Error checking availability: " + e.getMessage());  
        }   
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, borrowing.getBookId());
            pstmt.setInt(2, borrowing.getMemberId());
            pstmt.setString(3, borrowing.getBorrowDate());
            pstmt.setString(4, borrowing.getDueDate());
            pstmt.setString(5, borrowing.getStatus());
            
            pstmt.executeUpdate();
            if (borrowing.getStatus().equalsIgnoreCase("Borrowed")){
                try (PreparedStatement updateStmt = conn.prepareStatement(updateBookSql)){
                    updateStmt.setInt(1, 0);
                    updateStmt.setInt(2, borrowing.getBookId());
                    updateStmt.executeUpdate();
                }
            }
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
            System.out.println("Error retrieving borrowings: " + e.getMessage());
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
    public void searchBorrowingByMemberId(int id){
        String sql = "SELECT * FROM Borrowings WHERE \"Member ID\" = ?";
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            boolean found = false;
            
            while(rs.next()){
                found = true;
                int borrowingId = rs.getInt("ID");
                int memberID = rs.getInt("Member ID");
                int bookId = rs.getInt("Book ID");
                String borrowDate = rs.getString("Borrow Date");
                String dueDate = rs.getString("Due Date");
                String status = rs.getString("Status");
                System.out.println("ID: " + borrowingId + 
                                   ", Member ID: " + memberID +
                                   ", Book ID: " + bookId + 
                                   ", Borrow Date: " + borrowDate +
                                   ", Due Date: " + dueDate +
                                   ", Status: " + status);
            } 
            if(!found){
                System.out.println("No borrowing found for this member");
            }
            
        } catch (SQLException e){
            System.out.println("Error searching for borrowing: " + e.getMessage());
        } 
                
                
    }
}
