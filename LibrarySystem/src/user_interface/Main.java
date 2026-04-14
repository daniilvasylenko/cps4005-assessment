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
import database.BorrowingDAO;
import entities.Borrowing;
import java.util.Scanner;
import java.time.LocalDate;
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
        BorrowingDAO borrowingDAO = new BorrowingDAO();
        
        int choice;
        
        do{
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Show all books");
            System.out.println("2. Add a book");
            System.out.println("3. Update a book");
            System.out.println("4. Delete a book");
            System.out.println("5. Search by book ID");
            System.out.println("6. Search by title or author");
            System.out.println("========================");
            System.out.println("7. Show all members");
            System.out.println("8. Add member");
            System.out.println("9. Update a member");
            System.out.println("10. Delete a member");
            System.out.println("11. Search by member ID");
            System.out.println("12. Search by member name");
            System.out.println("========================");
            System.out.println("13. Show all borrowings");
            System.out.println("14. Add borrowing");
            System.out.println("15. Update borrowing status");
            System.out.println("16. Search borrowings by member ID");
            System.out.println("17. Search borrowings by book ID");
            System.out.println("18. Delete a borrowing");
            System.out.println("========================");
            System.out.println("19. Exit");
            System.out.println("========================");
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
                    
                    if (isEmpty(title) || isEmpty(author) || isEmpty(category)){
                        System.out.println("Book fields cannot be empty");
                        break;
                    }
                    
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
                    System.out.print("Enter book ID to search: ");
                    int searchBookId = scanner.nextInt();
                    scanner.nextLine();
                    
                    bookDAO.searchBookById(searchBookId);
                    break;
                case 6:
                    System.out.print("Enter keyword: ");
                    String keyword = scanner.nextLine();
                    bookDAO.searchBookByTitleOrAuthor(keyword);
                    break;
                case 7:
                    memberDAO.getAllMembers();
                    break;
                case 8:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter type: ");
                    String type = scanner.nextLine();
                    
                    if(isEmpty(name) || isEmpty(email) || isEmpty(type)){
                        System.out.println("Member fields cannot be empty");
                        break;
                    }
                    if(!isValidEmail(email)){
                        System.out.println("Invalid email format");
                        break;
                    }
                    if(!isValidMemberType(type)){
                        System.out.println("Member type must be Student or Staff");
                        break;
                    }
                    
                    Member newMember = new Member(0, name, email, type);
                    memberDAO.addMember(newMember);
                    break;
                case 9:
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
                case 10: 
                    System.out.print("Enter member ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    
                    memberDAO.deleteMemberById(deleteId);
                    break;
                case 11:
                    System.out.print("Enter member ID to search: ");
                    int searchMemberId = scanner.nextInt();
                    scanner.nextLine();
                    
                    memberDAO.searchMemberById(searchMemberId);
                    break;
                case 12:
                    System.out.print("Enter member name: ");
                    String searchName = scanner.nextLine();
                    memberDAO.searchMemberByName(searchName);
                    break;
                case 13:
                    borrowingDAO.getAllBorrowings();
                    break;
                case 14:
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter borrow date (YYYY-MM-DD): ");
                    String borrowDate = scanner.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    String dueDate = scanner.nextLine();
                    System.out.print("Enter status: ");
                    String status = scanner.nextLine();
                    
                    if(bookId <= 0 || memberId <= 0){
                        System.out.println("Book ID and Member ID must be positive numbers");
                        break;
                    }
                    if(isEmpty(borrowDate) || isEmpty(dueDate) || isEmpty(status)){
                        System.out.println("Borrowing fields cannot be empty");
                        break;
                    }
                    if(!isValidBorrowingStatus(status)){
                        System.out.println("Status must be Borrowed or Returned");
                        break;
                    }
                    try{
                        LocalDate borrow = LocalDate.parse(borrowDate);
                        LocalDate due = LocalDate.parse(dueDate);
                        
                        if(!due.isAfter(borrow)){
                            System.out.println("Due date must be after borrow date");
                            break;
                        }
                    } catch (Exception e){
                        System.out.println("Invalid date format. Please use YYYY-MM-DD");
                        break;
                    }
                    
                    Borrowing borrowing = new Borrowing(0, bookId, memberId, borrowDate, dueDate, status);
                    borrowingDAO.addBorrowing(borrowing);
                    break;
                case 15:
                    System.out.print("Enter borrowing ID: ");
                    int borrowingId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new status: ");
                    String newStatus = scanner.nextLine();
                    
                    if(!isValidBorrowingStatus(newStatus)){
                        System.out.println("Status must be Borrowed or Returned");
                        break;
                    }
                    
                    borrowingDAO.updateBorrowingStatus(borrowingId, newStatus);
                    break;
                case 16:
                    System.out.print("Enter member ID to search: ");
                    int searchMemberBorrowingId = scanner.nextInt();
                    scanner.nextLine();
                    
                    borrowingDAO.searchBorrowingByMemberId(searchMemberBorrowingId);
                    break;
                case 17:
                    System.out.print("Enter book ID: ");
                    int searchBookBorrowingId = scanner.nextInt();
                    scanner.nextLine();
                    
                    borrowingDAO.searchBorrowingByBookId(searchBookBorrowingId);
                    break;
                case 18:
                    System.out.print("Enter borrowing ID to delete: ");
                    int deleteBorrowingId = scanner.nextInt();
                    scanner.nextLine();
                    
                    borrowingDAO.deleteBorrowingById(deleteBorrowingId);
                    break;
                case 19:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 19);
        scanner.close();
        
    }
    private static boolean isEmpty(String value){
            return value == null || value.trim().isEmpty();
        }
    private static boolean isValidEmail(String email){
        return email !=null && email.contains("@") && email.contains(".");
    }
    private static boolean isValidMemberType(String type){
        return type.equalsIgnoreCase("Student") || type.equalsIgnoreCase("Staff");
    }
    private static boolean isValidBorrowingStatus(String status){
        return status.equalsIgnoreCase("Borrowed") || status.equalsIgnoreCase("Returned");
    }
}

