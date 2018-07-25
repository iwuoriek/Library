/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.service;

import com.library.model.Book;
import com.library.model.Genre;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kelechi
 */
public interface BookService {
    public String addBook(Book book);
    
    public String updateBook(Book book);
    
    public Book getBook(String id);
    
    public List<Book> getBooks();
    
    public Map<String, Book> getAll();
    
    public List<Genre> getGenre();
}
