/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Kelechi
 */
@Entity
@Table(name="Ratings")
public class BookRatings implements java.io.Serializable{
    @EmbeddedId
    private BookRatingsId id;
    @Column(name="RATING")
    private int rating;

    /**
     * @return the id
     */
    public BookRatingsId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(BookRatingsId id) {
        this.id = id;
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
}
