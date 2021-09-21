package com.rmit.sept.bk_bookcatalogservices;


import com.rmit.sept.bk_bookcatalogservices.Repositories.AdRepository;
import com.rmit.sept.bk_bookcatalogservices.Repositories.BookRepository;
import com.rmit.sept.bk_bookcatalogservices.exceptions.InvalidConditionException;
import com.rmit.sept.bk_bookcatalogservices.exceptions.InvalidIsbnException;
import com.rmit.sept.bk_bookcatalogservices.model.Ad;
import com.rmit.sept.bk_bookcatalogservices.model.Book;
import com.rmit.sept.bk_bookcatalogservices.model.SearchForm;
import com.rmit.sept.bk_bookcatalogservices.services.AdService;
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
public class AdServiceUnitTest {

    @InjectMocks
    private AdService adService;

    @Mock
    private AdRepository adRepository;
    @Mock
    private BookRepository bookRepository;

    private Ad ad = new Ad();
    private Book book1 = new Book();
    private Book book2 = new Book();


    long isbnNotInDatabase = 5;
    String invalidCondition = "ernt";
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


        booksInDatabase.add(book1);
        booksInDatabase.add(book2);

        // Creating mock dependencies to not rely on external databases
        Mockito.when(adRepository.save(any(Ad.class))).thenReturn(new Ad());
        Mockito.when(bookRepository.findAll()).thenReturn(booksInDatabase);



    }

    @Test(expected = InvalidConditionException.class)
    public void saveAd_throwsInvalidConditionException_IfInvalidConditionProvided(){
        ad.setIsbn(book1.getIsbn());
        ad.setCondition(invalidCondition);
        adService.saveAd(ad);
    }

    @Test(expected = InvalidIsbnException.class)
    public void saveAd_throwsInvalidIsbnException_IfInvalidIsbnProvided(){
        ad.setIsbn(isbnNotInDatabase);
        ad.setCondition("new");
        adService.saveAd(ad);
    }

    @Test
    public void saveAd_returnsAd_IfValidAdProvided(){
        ad.setIsbn(book1.getIsbn());
        ad.setCondition("New");
        assertNotNull(adService.saveAd(ad));
    }


}
