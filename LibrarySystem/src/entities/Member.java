/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Даниил Василенко
 */
public class Member {
    private int id;
    private String name;
    private String email;
    private String type;
    
    public Member(int id, String name, String email, String type){
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getType(){
        return type;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setType(String type){
        this.type = type;
    }
}
