package com.rmit.sept.bk_bookcatalogservices.services;

import com.rmit.sept.bk_bookcatalogservices.Repositories.BookRepository;
import com.rmit.sept.bk_bookcatalogservices.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookrepository;

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        bookrepository.findAll().forEach(booksTemp -> books.add(booksTemp));
        return books;
    }

    public void saveBook(Book book) {
        bookrepository.save(book);
    }

    public Book getBookById(long id) {
        return bookrepository.findById(id).get();
    }

    public void updateBook(Book book, long id) {
        bookrepository.save(book);
    }

    public void deleteBook(long id) {
        bookrepository.deleteById(id);
    }
}