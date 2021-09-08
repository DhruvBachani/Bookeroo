package com.rmit.sept.bk_bookcatalogservices.web;

import com.rmit.sept.bk_bookcatalogservices.model.Book;
import com.rmit.sept.bk_bookcatalogservices.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

        return Arrays.asList(
            new Book("Persuader", "Lee Child", "Thriller", "No desc", 123),
            new Book("If Tomorrow Comes", "Sydney Sheldon", "Thriller", "No desc", 456),
            new Book("Murder on the Orient Express", "Agatha Christie", "Mystery", "No desc", 2244),
            new Book("The Martian", "Andy Weir", "Adventure", "No desc", 765),
            new Book("NYPD Red", "James Patterson", "Thriller", "No desc", 014),
            new Book("The Recruit", "Robert Muchamore", "Action", "No desc", 775)
        );

        // return Collections.singletonList(
        //     new Book("Persuader", "Lee Child", "Thriller", 123)
        // );
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
