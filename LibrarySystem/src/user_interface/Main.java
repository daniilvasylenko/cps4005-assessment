/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user_interface;
import core.DBConnection;
import java.sql.Connection;
import database.BookDAO;
import entities.Book;
import database.MemberDAO;
import entities.Member;
import java.util.Scanner;
/**
 *
 * @author Даниил Василенко
 */
public class Main {
    public static void main(String[] args){
        Connection conn = DBConnection.connect();
        if (conn != null){
            System.out.println("Program started successfully");
        } else {
            System.out.println("Program could not connect to the database");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();
        
        int choice;
        
        do{
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Show all books");
            System.out.println("2. Add a book");
            System.out.println("3. Update a book");
            System.out.println("4. Delete a book");
            System.out.println("=======================");
            System.out.println("5. Show all members");
            System.out.println("6. Add member");
            System.out.println("7. Update a member");
            System.out.println("8. Delete a member");
            System.out.println("========================");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch(choice){
                case 1:
                    bookDAO.getAllBooks();
                    break;
                case 2:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Is this book available? (true/false): ");
                    boolean available = scanner.nextBoolean();
                    scanner.nextLine();
                    
                    Book newBook = new Book(0, title, author, category, available);
                    bookDAO.addBook(newBook);
                    break;
                case 3:
                    System.out.print("Enter book ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new author: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter new category: ");
                    String newCategory = scanner.nextLine();
                    System.out.print("Is this book available?(true/false): ");
                    boolean newAvailable = scanner.nextBoolean();
                    scanner.nextLine();
                    
                    Book updatedBook = new Book(updateId, newTitle, newAuthor, newCategory, newAvailable);
                    bookDAO.updateBook(updatedBook);
                    break;
                case 4:
                    System.out.print("Enter book ID to delete: ");
                    int deleteID = scanner.nextInt();
                    scanner.nextLine();
                    
                    bookDAO.deleteBookById(deleteID);
                    break;
                case 5:
                    memberDAO.getAllMembers();
                    break;
                case 6:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter type: ");
                    String type = scanner.nextLine();
                    
                    Member newMember = new Member(0, name, email, type);
                    memberDAO.addMember(newMember);
                    break;
                case 7:
                    System.out.print("Enter member ID to update: ");
                    int updateID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Enter new type: ");
                    String newType = scanner.nextLine();
                    
                    Member updatedMember = new Member(updateID, newName, newEmail, newType);
                    memberDAO.updateMember(updatedMember);
                    break;
                case 8: 
                    System.out.print("Enter member ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    
                    memberDAO.deleteMemberById(deleteId);
                    break;
                case 9:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 9);
        scanner.close();
        
         
    }
}
