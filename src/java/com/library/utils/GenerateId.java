/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.utils;

import com.library.model.Book;
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
}
