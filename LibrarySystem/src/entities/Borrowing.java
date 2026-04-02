/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Даниил Василенко
 */
public class Borrowing {
    private int id;
    private int bookId;
    private int memberId;
    private String borrowDate;
    private String dueDate;
    private String status;
    
    public Borrowing(int id, int bookId, int memberId, String borrowDate, String dueDate, String status){
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = status;
    }
    
    public int getId(){
        return id;
    }
    public int getBookId(){
        return bookId;
    }
    public int getMemberId(){
        return memberId;
    }
    public String getBorrowDate(){
        return borrowDate;
    }
    public String getDueDate(){
        return dueDate;
    }
    public String getStatus(){
        return status;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setBookId(int bookId){
        this.bookId = bookId;
    }
    public void setMemberId(int memberId){
        this.memberId = memberId;
    }
    public void setBorrowDate(String borrowDate){
        this.borrowDate = borrowDate;
    }
    public void setDueDate(String dueDate){
        this.dueDate = dueDate;
    }
    public void setStatus(String status){
        this.status = status;
    }
    
}
