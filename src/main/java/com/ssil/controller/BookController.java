package com.ssil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssil.service.BookService;
import com.ssil.exception.ErrorMessage;
import com.ssil.exception.ResourceNotFoundExcetion;
import com.ssil.model.Book;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<List<Book>>(service.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id) throws ResourceNotFoundExcetion {
        return new ResponseEntity<Book>(service.getBook(id), HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<Void> saveBook(@RequestBody Book book) {
        service.saveBook(book);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Integer id, @RequestBody Book book) throws ResourceNotFoundExcetion {
        Book updatedBook = service.getBook(id);
        updatedBook.setId(book.getId());
        updatedBook.setTitle(book.getTitle());
        service.updateBook(updatedBook);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) throws ResourceNotFoundExcetion {
        Book deleteBook = service.getBook(id);
        service.deleteBook(deleteBook);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ResourceNotFoundExcetion.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundExcetionHandler(ResourceNotFoundExcetion ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        errorMessage.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.PRECONDITION_FAILED);
    }
}
