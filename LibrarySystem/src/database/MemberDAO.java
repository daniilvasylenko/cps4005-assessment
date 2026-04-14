/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import core.DBConnection;
import entities.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Даниил Василенко
 */
public class MemberDAO {
    public void addMember(Member member){
        String sql = "INSERT INTO Members(Name, Email, Type) VALUES(?, ?, ?)";
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getType());
            
            pstmt.executeUpdate();
            System.out.println("Member added successfully");
        } catch (SQLException e){
            System.out.println("Error adding a member: " + e.getMessage());
        }
    }
    public void getAllMembers(){
        String sql = "SELECT * FROM Members";
        
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String type = rs.getString("Type");
                
                System.out.println("ID: " + id +
                                   ", Name: " + name +
                                   ", Email: " + email +
                                   ", Type: " + type);
            }
        } catch (SQLException e){
            System.out.println("Error retrieving members: " + e.getMessage());
        }
    }
    public void deleteMemberById(int id){
        String sql = "DELETE FROM Members WHERE ID = ?";
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Member deleted successfully");
            } else {
                System.out.println("No member found with that ID");
            }
        } catch (SQLException e){
            System.out.println("Error deleting a member: " + e.getMessage());
        }
    }
    public void updateMember(Member member){
        String sql = "UPDATE Members SET Name = ?, Email = ?, Type = ? WHERE ID = ?";
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getType());
            pstmt.setInt(4, member.getId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Member updated successfully");
            } else {
                System.out.println("No member found with that ID");
            }
        } catch (SQLException e){
            System.out.println("Error updating a member: " + e.getMessage());
        }
    }
    public void searchMemberById(int id){
        String sql = "SELECT * FROM Members WHERE ID = ?";
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
                 pstmt.setInt(1, id);
                 ResultSet rs = pstmt.executeQuery();
                 if(rs.next()){
                     int memberId = rs.getInt("ID");
                     String name = rs.getString("Name");
                     String email = rs.getString("Email");
                     String type = rs.getString("Type");
                     System.out.println("ID: " + memberId + 
                                        ", Name: " + name +
                                        ", Email: " + email + 
                                        ", Type: " + type);
                 } else {
                     System.out.println("No member found with that ID");
               }
            } catch (SQLException e){
                System.out.println("Error searching for a member: " + e.getMessage());
            }        
    }
    public void searchMemberByName(String name){
        String sql = "SELECT * FROM Members WHERE Name LIKE ?";
        
        try(Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            boolean found = false;
            while(rs.next()){
                found = true;
                int id = rs.getInt("ID");
                String memberName = rs.getString("Name");
                String email = rs.getString("Email");
                String type = rs.getString("Type");
                System.out.println("ID: " + id + 
                                   ", Name: " + memberName +
                                   ", Email: " + email +
                                   ", Type: " + type);
            }
            if(!found){
                System.out.println("No member found with that name");
            }
        } catch (SQLException e){
            System.out.println("Error searching for a member: " + e.getMessage());
        }
    }
}   
