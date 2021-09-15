package com.rmit.sept.bk_bookcatalogservices.services;

import com.rmit.sept.bk_bookcatalogservices.Repositories.BookRepository;
import com.rmit.sept.bk_bookcatalogservices.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
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

    // public void saveBook(Book book) {
    //     bookrepository.save(book);
    // }

    public Book saveBook (Book newBook) {
        newBook.setName(newBook.getName());
        newBook.setAuthor(newBook.getAuthor());
        newBook.setCategory(newBook.getCategory());
        newBook.setDescription(newBook.getDescription());
        newBook.setIsbn(newBook.getIsbn());
        newBook.setPrice(newBook.getPrice());

        return bookrepository.save(newBook);
    }

    public Book getBookById(Long id) {
        return bookrepository.findById(id).get();
    }

    public void updateBook(Book book, Long id) {
        bookrepository.save(book);
    }

    public void deleteBook(Long id) {
        bookrepository.deleteById(id);
    }
}