/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.utils;

import com.library.model.Authors;
import com.library.model.Book;
import com.library.model.UserAccount;
import java.util.List;

/**
 *
 * @author Kelechi
 */
public class GenerateId {

    public String generateBookId(List<Book> books) {
        String bookId = null;
        try {
            String[] id = books.get(books.size() - 1).getBookId().split("-");
            int number = Integer.parseInt(id[1]) + 1;
            bookId = id[0] + "-" + number;
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "LIBBK-1";
        }
        return bookId;
    }
    
    public String generateUserId(List<UserAccount> users) {
        String userId = null;
        try {
            String[] id = users.get(users.size() - 1).getId().split("-");
            int number = Integer.parseInt(id[1]) + 1;
            userId = id[0] + "-" + number;
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "LIBUSR-1";
        }
        return userId;
    }
    
    public String generateAuthorId(List<Authors> authors) {
        String userId = null;
        try {
            String[] id = authors.get(authors.size() - 1).getAuthorId().split("-");
            int number = Integer.parseInt(id[1]) + 1;
            userId = id[0] + "-" + number;
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "BKATHR-1";
        }
        return userId;
    }
}
