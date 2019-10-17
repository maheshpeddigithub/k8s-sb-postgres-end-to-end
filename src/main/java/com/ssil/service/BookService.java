package com.ssil.service;

import java.util.List;

import com.ssil.exception.ResourceNotFoundExcetion;
import com.ssil.model.Book;

public interface BookService {

    Book getBook(Integer id) throws ResourceNotFoundExcetion;
    List<Book> getBooks();
    void saveBook(Book book);
    void updateBook(Book book);
    void deleteBook(Book book);

}
