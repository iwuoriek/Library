/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controller;

import com.library.model.Authors;
import com.library.model.Books;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author Kelechi
 */
@Named("book")
public class BookController extends BooksAndAuthors implements java.io.Serializable{
    
    private String bookTitle;
    private String genre;
    private String description;
    private String rating;
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
