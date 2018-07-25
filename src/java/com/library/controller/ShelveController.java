/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controller;

import com.library.model.Book;
import com.library.model.BookShelf;
import com.library.model.ShelfId;
import com.library.model.UserAccount;
import com.library.service.ShelveService;
import com.library.utils.FacesUtil;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kelechi
 */
@Named("shelveBean")
@RequestScoped
public class ShelveController extends BooksAndAuthors {

    @Autowired
    private ShelveService shelveService;

    public String addToShelve(Book book) throws IOException {
        try {
            UserAccount user = (UserAccount) FacesUtil.getSession().getAttribute("user");
            BookShelf shelve = new BookShelf();
            ShelfId id = new ShelfId(user, book);
            if (user.getUserRole().equals("NON-ADMIN")) {
                if (shelveService.getSpecificFromShelve(id) == null) {
                    shelve.setId(id);
                    shelveService.addToShelve(shelve);
                }
            }
        } catch (NullPointerException e) {
            FacesUtil.getResponse().sendRedirect(FacesUtil.getRequest().getContextPath() + "/faces/login.xhtml");
        }
        return "userhome";
    }
}
