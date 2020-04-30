package com.example.seproject.Users;


import com.example.seproject.Books.Book;

import java.util.Vector;

public  class User {
    private int userID;
    private String username;
    private String password;
    private String telephone;
    private String address;
    private int image;

    private Vector<Book> personalBooks = new Vector<>();
    public void uploadBook(Book book){
        personalBooks.add(book);
    }




    public User( String username, String password, String telephone, String address) {
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.address = address;
    }
    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
