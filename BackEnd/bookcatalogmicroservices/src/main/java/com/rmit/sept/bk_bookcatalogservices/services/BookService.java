package com.rmit.sept.bk_bookcatalogservices.services;

import com.rmit.sept.bk_bookcatalogservices.Repositories.BookRepository;
import com.rmit.sept.bk_bookcatalogservices.model.Book;
import com.rmit.sept.bk_bookcatalogservices.model.SearchForm;
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


    public Book saveBook (Book newBook) {
        return bookrepository.save(newBook);
    }

    public Book getBookByIsbn(Long isbn) {
        return bookrepository.findByIsbn(isbn);
    }

    public List<Book> searchBooks(SearchForm searchForm){
        List<Book> searchResults = new ArrayList<Book>();
        bookrepository.findAll().forEach(book-> {
            if(book.getName().replaceAll(" ","").toLowerCase().contains(searchForm.searchFor.replaceAll(" ","").toLowerCase())){
                searchResults.add(book);
            }
        });
        return searchResults;
    }

    public boolean containsByIsbn(Long isbn){
        return bookrepository.existsByIsbn(isbn);
    }
}