package com.ssil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssil.exception.ResourceNotFoundExcetion;
import com.ssil.model.Book;
import com.ssil.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository repository;

    public Book getBook(Integer id) throws ResourceNotFoundExcetion {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcetion("Book not found"));
    }

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public void saveBook(Book book) {
        repository.save(book);
    }

    public void updateBook(Book book) {
        repository.save(book);
    }

    public void deleteBook(Book book) {
        repository.delete(book);
    }

}
