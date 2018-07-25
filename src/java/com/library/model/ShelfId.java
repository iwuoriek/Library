/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Kelechi
 */
@Embeddable
public class ShelfId implements java.io.Serializable{
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private UserAccount user;
    
    @ManyToOne
    @JoinColumn(name="BOOK_ID")
    Book book;
    
    protected ShelfId(){}
    
    public ShelfId(UserAccount user, Book book){
        this.user = user;
        this.book = book;
    }
    
    public UserAccount getUser(){
        return user;
    }
    
    public Book getBook(){
        return book;
    }
}
