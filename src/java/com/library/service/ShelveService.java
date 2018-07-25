/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.service;

import com.library.model.BookShelf;
import com.library.model.ShelfId;
import java.util.List;

/**
 *
 * @author Kelechi
 */
public interface ShelveService {

    public String addToShelve(BookShelf shelve);

    public String removeFromShelve(BookShelf shelve);

    public List<BookShelf> getAllFromShelve(String userId);
    
    public BookShelf getSpecificFromShelve(ShelfId id);
    
    public String addRating(BookShelf shelve);
}
