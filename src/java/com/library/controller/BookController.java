/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controller;

import com.library.model.Authors;
import com.library.model.Books;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Kelechi
 */
@Named("book")
@RequestScoped
public class BookController extends BooksAndAuthors implements java.io.Serializable{
    
    public String addBook(){
        return "";
    }
    
    public String updateBook(){
        return "";
    }
    
    public String deleteBook(){
        return "";
    }
    
//    public List<Books> getBooks(){
//        
//    }
    
    
}
