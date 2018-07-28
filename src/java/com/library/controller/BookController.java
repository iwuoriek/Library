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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
public class BookController extends BooksAndAuthors implements java.io.Serializable {

    @Autowired
    private BookService bookService;
    private Part bookFile;
    private Part cover;
    private final String root = FileUploader.ROOT + "Books\\";

    public String addBook() {
        try {
            Book book = new Book();
            book.setBookId(new GenerateId().generateBookId(bookService.getBooks()));
            book.setBookTitle(getBookTitle());
            book.setDescription(getDescription());
            book.setGenre(getGenre());
            book.setAuthor(getAuthor());
            book.setRating(getRating());
            book.setFileName(new FileUploader().uploadBook(bookFile, book.getBookId()));
            book.setCover(new FileUploader().uplaodBookCover(cover, book.getBookId()));
            book.setYear(getYear());
            bookService.addBook(book);
            setBook(new Book());
        } catch (NullPointerException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Upload the appropriate files");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "newbook";
    }

    public String updateBook() {
        return "";
    }

    public String deleteBook() {
        return "";
    }

    public String readBook(Book book) {
        HttpSession session = FacesUtil.getSession();
        session.setAttribute("bookToRead", root + book.getFileName());
        setBook(book);
        return "bookpage?faces-redirect=true";
    }

    public Integer[] getYears() {
        Integer[] years = new Integer[100];
        int year = new Date().getYear() + 1900;
        for (int i = 0; i < years.length; i++) {
            years[i] = year;
            year--;
        }
        return years;
    }

    public List<Genre> getGenres() {
        return bookService.getGenre();
    }

    public List<Book> getBookList() {
        return bookService.getBooks();
    }

    public List<Book> getRecentBooks() throws FacesException {
        List<Book> list = getBookList();
        List<Book> newList = new ArrayList();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (newList.size() < 9) {
                newList.add(list.get(i));
            } else {
                break;
            }
        }
        return newList;
    }

    private void setBook(Book book) {
        setBookTitle(book.getBookTitle());
        setFileName(book.getFileName());
        setAuthor(book.getAuthor());
        setDescription(book.getDescription());
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

    /**
     * @return the cover
     */
    public Part getCover() {
        return cover;
    }

    /**
     * @param cover the cover to set
     */
    public void setCover(Part cover) {
        this.cover = cover;
    }
}
