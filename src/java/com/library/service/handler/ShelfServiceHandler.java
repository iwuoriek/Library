/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.service.handler;

import com.library.model.BookShelf;
import com.library.model.ShelfId;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.service.ShelfService;

/**
 *
 * @author Kelechi
 */
@Service
@Transactional
public class ShelfServiceHandler implements ShelfService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public String addToShelve(BookShelf shelve) {
        sessionFactory.getCurrentSession().save(shelve);
        return "success";
    }

    @Override
    public String removeFromShelve(BookShelf shelf) {
        sessionFactory.getCurrentSession().delete(shelf);
        return "success";
    }

    @Override
    public List<BookShelf> getAllFromShelve(String userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM BookShelf WHERE USER_ID = :userId")
                .setParameter("userId", userId)
                .list();
    }

    @Override
    public BookShelf getSpecificFromShelve(ShelfId id) {
        return (BookShelf) sessionFactory.getCurrentSession()
                .createQuery("FROM BookShelf WHERE USER_ID = :userId AND BOOK_ID = :bookId")
                .setParameter("userId", id.getUser())
                .setParameter("bookId", id.getBook())
                .uniqueResult();
    }

    @Override
    public String addRating(BookShelf shelve) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
