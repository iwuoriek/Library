/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controller;

import com.library.model.Book;
import com.library.model.Genre;
import com.library.service.BookService;
import com.library.utils.FacesUtil;
import com.library.utils.FileUploader;
import com.library.utils.GenerateId;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kelechi
 */
@Named("bookBean")
@SessionScoped
public class BookController extends BooksAndAuthors implements java.io.Serializable{
    
    @Autowired
    private BookService bookService;
    private Part bookFile;
    private final String root = FileUploader.ROOT + "Books\\";
    public String addBook(){
        Book book = new Book();
        book.setBookId(new GenerateId().generateBookId(bookService.getBooks()));
        book.setBookTitle(getBookTitle());
        book.setDescription(getDescription());
        book.setGenre(getGenre());
        book.setAuthor(getAuthor());
        book.setRating(getRating());
        book.setFileName(new FileUploader().uploadBook(bookFile, book.getBookId()));
        book.setYear(getYear());
        bookService.addBook(book);
        return "newbook";
    }
    
    public String updateBook(){
        return "";
    }
    
    public String deleteBook(){
        return "";
    }
    
    public String readBook(Book book){
        HttpSession session = FacesUtil.getSession();
        session.setAttribute("bookToRead", root + book.getFileName());
        setBook(book);
        return "bookpage?faces-redirect=true";
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
        return bookService.getGenre();
    }
    
    public List<Book> getBookList(){
        return bookService.getBooks();
    }
    
    private void setBook(Book book){
        setBookTitle(book.getBookTitle());
        setFileName(book.getFileName());
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
