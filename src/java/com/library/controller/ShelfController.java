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
import com.library.service.BookService;
import com.library.utils.FacesUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import com.library.service.ShelfService;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Kelechi
 */
@Named("shelfBean")
@RequestScoped
public class ShelfController extends BooksAndAuthors {

    @Autowired
    private ShelfService shelfService;

    public String addToShelf(Book book) throws IOException {
        try {
            UserAccount user = (UserAccount) FacesUtil.getSession().getAttribute("user");
            BookShelf shelf = new BookShelf();
            ShelfId id = new ShelfId(user, book);
            if (user.getUserRole().equals("NON-ADMIN")) {
                if (shelfService.getSpecificFromShelve(id) == null) {
                    shelf.setId(id);
                    shelfService.addToShelve(shelf);
                }
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "You do not have permission to use this feature!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "viewbooks";
            }
        } catch (NullPointerException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "User must be logged in to access this feature.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "viewbooks";
        }
        return "userhome?faces-redirect=true";
    }

    public String removeFromShelf(Book book) {
        UserAccount user = (UserAccount) FacesUtil.getSession().getAttribute("user");
        BookShelf shelf = new BookShelf();
        shelf.setId(new ShelfId(user, book));
        shelfService.removeFromShelve(shelf);
        return "userHome";
    }

    public List<Book> getMyBooks() {
        UserAccount user = (UserAccount) FacesUtil.getSession().getAttribute("user");
        List<BookShelf> myShelf = shelfService.getAllFromShelve(user.getId());
        List<Book> myBooks = new ArrayList();
        myShelf.stream().forEach((book) -> {
            myBooks.add(book.getId().getBook());
        });
        return myBooks;
    }
}
