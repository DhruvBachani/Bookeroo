package com.rmit.sept.bk_bookcatalogservices.web;

import com.rmit.sept.bk_bookcatalogservices.model.Book;
import com.rmit.sept.bk_bookcatalogservices.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookservice;

    @RequestMapping("/bookCatalog")
    private List<Book> getAllBooks() {
        // return bookservice.getAllBooks();
        return Collections.singletonList(
            new Book("Persuader", "Lee Child", "Thriller", "Description", 123)
        );
    }

    @GetMapping("/books/{id}")
    private Book getBook(@PathVariable("id") long id) {
        return bookservice.getBookById(id);
    }

    @DeleteMapping("/books/{id}")
    private void deleteBook(@PathVariable("id") long id) {
        bookservice.deleteBook(id);
    }

    @PostMapping("/books")
    private long saveBook(@RequestBody Book book) {
        bookservice.saveBook(book);
        return book.getId();
    }

    @PutMapping("/books")
    private Book updateBook(@RequestBody Book book) {
        bookservice.updateBook(book, book.getId());
        return book;
    }
}
