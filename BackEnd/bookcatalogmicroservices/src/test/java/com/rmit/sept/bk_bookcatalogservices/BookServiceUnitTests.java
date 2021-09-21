package com.rmit.sept.bk_bookcatalogservices;


import com.rmit.sept.bk_bookcatalogservices.Repositories.BookRepository;
import com.rmit.sept.bk_bookcatalogservices.model.Book;
import com.rmit.sept.bk_bookcatalogservices.model.SearchForm;
import com.rmit.sept.bk_bookcatalogservices.services.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.any;


@RunWith(MockitoJUnitRunner.class)
public class BookServiceUnitTests {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private Book book1 = new Book();
    private Book book2 = new Book();
    private Book book3 = new Book();

    long isbnNotInDatabase = 5;
    List<Book> booksInDatabase;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        booksInDatabase = new ArrayList<>();
        // Filling our 'database' with mock data
        book1.setName("Harry Potter");
        book1.setAuthor("J.K Rowling");
        book1.setDescription("It's Magic");
        book1.setCategory("THRILLER");
        book1.setIsbn((long) 1);

        book2.setName("Book 2");
        book2.setAuthor("Jamie");
        book2.setDescription("It's Magic and more ");
        book2.setCategory("ACTION");
        book2.setIsbn((long) 2);

        book3.setName("Book 3");
        book3.setAuthor("Morris");
        book3.setDescription("Dragons ");
        book3.setCategory("FICTION");
        book3.setIsbn((long) 3);


        booksInDatabase.add(book1);
        booksInDatabase.add(book2);
        booksInDatabase.add(book3);

        // Creating mock dependencies to not rely on external databases
        Mockito.when(bookRepository.existsByIsbn(book1.getIsbn())).thenReturn(true);
        Mockito.when(bookRepository.existsByIsbn(isbnNotInDatabase)).thenReturn(false);
        Mockito.when(bookRepository.findByIsbn(book1.getIsbn())).thenReturn(book1);
        Mockito.when(bookRepository.findAll()).thenReturn(booksInDatabase);


    }

    @Test
    public void getAllBooks_returnsAllBooksInDatabase_IfBooksInDatabase(){
        List<Book> books = bookService.getAllBooks();
        assertEquals(booksInDatabase.size(), books.size());
        assertFalse(booksInDatabase.stream().anyMatch(e ->  e.getIsbn()==isbnNotInDatabase));
        assertTrue(booksInDatabase.stream().anyMatch(e ->  e.getIsbn()==booksInDatabase.get(0).getIsbn()));
        assertTrue(booksInDatabase.stream().anyMatch(e ->  e.getIsbn()==booksInDatabase.get(1).getIsbn()));

    }

    @Test
    public void getBookByIsbn_ReturnBook_IfValidIsbn(){
        Book foundBook = bookService.getBookByIsbn(book1.getIsbn());
        assertEquals(book1.getIsbn(), foundBook.getIsbn());

    }



    @Test
    public void getBookByIsbn_ReturnNull_IfInvalidIsbn()
    {
        Book foundBook = bookService.getBookByIsbn(isbnNotInDatabase);
        assertNull(foundBook);
    }


    @Test
    public void containsByIsbn_returnFalse_IfBookWithIsbnNotInDatabase(){
        assertFalse(bookService.containsByIsbn(isbnNotInDatabase));
    }

    @Test
    public void containsByIsbn_returnTrue_IfBookWithIsbnInDatabase(){
        assertTrue(bookService.containsByIsbn(booksInDatabase.get(0).getIsbn()));
    }


}
