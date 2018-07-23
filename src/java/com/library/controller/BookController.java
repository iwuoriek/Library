/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controller;

import com.library.model.Authors;
import com.library.model.Book;
import com.library.model.Genre;
import com.library.service.BookService;
import com.library.utils.FileUploader;
import com.library.utils.GenerateId;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kelechi
 */
@Named("bookBean")
@RequestScoped
public class BookController extends BooksAndAuthors implements java.io.Serializable{
    
    @Autowired
    private BookService service;
    private Part bookFile;
    
    public String addBook(){
        Book book = new Book();
        book.setBookId(new GenerateId().generateBookId(service.getBooks()));
        book.setBookTitle(getBookTitle());
        book.setDescription(getDescription());
        book.setGenre(getGenre());
        book.setRating(getRating());
        book.setFileName(new FileUploader().uploadBook(bookFile, book.getBookId()));
        book.setYear(getYear());
        service.addBook(book);
        return "newbook";
    }
    
    public String updateBook(){
        return "";
    }
    
    public String deleteBook(){
        return "";
    }
    
    public Integer[] getYears(){
        Integer[] years = new Integer[100];
        int year = new Date().getYear() + 1900;
        for (int i = 0; i < years.length; i++){
            years[i] = year;
            year--;
        }
        return years;
    }
    
    public List<Genre> getGenres(){
        return service.getGenre();
    }

    /**
     * @return the bookFile
     */
    public Part getBookFile() {
        return bookFile;
    }

    /**
     * @param bookFile the bookFile to set
     */
    public void setBookFile(Part bookFile) {
        this.bookFile = bookFile;
    }
    
    
}
