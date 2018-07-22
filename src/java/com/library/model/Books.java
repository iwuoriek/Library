/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Kelechi
 */
@Entity
@Table(name="Books")
public class Books implements java.io.Serializable{
    @Id
    @Column(name="BOOK_ID")
    private String bookId;
    @Column(name="BOOK_TITLE", nullable=false)
    private String bookTitle;
    @Column(name="GENRE", nullable=false)
    private String genre;
    @ManyToOne
    @JoinColumn(name="AUTHOR_ID", nullable=false)
    private Authors author;
    @Column(name="AVE_RATING")
    private double rating;
    @Column(name="DESCRIPTION")
    private String description;
    
    /**
     * @return the bookId
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * @return the bookTitle
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     * @param bookTitle the bookTitle to set
     */
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the author
     */
    public Authors getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Authors author) {
        this.author = author;
    }

    /**
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
