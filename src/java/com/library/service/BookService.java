/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.service;

import com.library.model.Books;
import java.util.List;

/**
 *
 * @author Kelechi
 */
public interface BookService {
    public String addBook(Books book);
    
    public String updateBook(Books book);
    
    public Books getBook(String id);
    
    public List<Books> getBooks();
}
