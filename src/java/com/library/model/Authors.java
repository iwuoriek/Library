/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Kelechi
 */
@Entity
@Table(name="Authors")
public class Authors implements java.io.Serializable{
    @Id
    @Column(name="AUTHOR_ID")
    private String authorId;
    @Column(name="AUTHOR_NAME")
    private String name;
    @Column(name="AUTHOR_RATING")
    private int rating;
    @OneToMany(mappedBy="author")
    private Set<Book> books;

    /**
     * @return the authorId
     */
    public String getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId the authorId to set
     */
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return the books
     */
    public Set<Book> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
