package com.rmit.sept.bk_bookcatalogservices;


import com.rmit.sept.bk_bookcatalogservices.Repositories.AdRepository;
import com.rmit.sept.bk_bookcatalogservices.Repositories.BookRepository;
import com.rmit.sept.bk_bookcatalogservices.exceptions.IsbnAlreadyExistsException;
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

    List<Ad> ads;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);


    }




}
