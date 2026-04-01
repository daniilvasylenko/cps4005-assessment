/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Даниил Василенко
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String category;
    private boolean available;
    
    public Book(int id, String title, String author, String category, boolean available){
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = available;
    }
    
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getCategory(){
        return category;
    }
    public boolean isAvailable(){
        return available;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setAvailable(boolean available){
        this.available = available;
    }

    
}
