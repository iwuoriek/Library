/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.service.handler;

import com.library.model.Book;
import com.library.model.Genre;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kelechi
 */
@Service
@Transactional
public class BookServiceHandler implements com.library.service.BookService{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public String addBook(Book book) {
        sessionFactory.getCurrentSession().save(book);
        return "success";
    }

    @Override
    public String updateBook(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Book getBook(String bookId) {
        Book book = (Book) sessionFactory.getCurrentSession()
                .createCriteria(Book.class)
                .add(Restrictions.like("bookId", bookId))
                .uniqueResult();
        return book;
    }

    @Override
    public List<Book> getBooks() {
        return sessionFactory.getCurrentSession().createQuery("From Book").list();
    }
    
    @Override
    public Map<String, Book> getAll(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Genre> getGenre(){
        return sessionFactory.getCurrentSession().createQuery("From Genre").list();
    }
    
}
