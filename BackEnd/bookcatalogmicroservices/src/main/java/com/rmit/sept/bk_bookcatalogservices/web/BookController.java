package com.rmit.sept.bk_bookcatalogservices.web;

import com.rmit.sept.bk_bookcatalogservices.model.*;
import com.rmit.sept.bk_bookcatalogservices.services.AdService;
import com.rmit.sept.bk_bookcatalogservices.services.BookService;
import com.rmit.sept.bk_bookcatalogservices.services.MapValidationErrorService;
import com.rmit.sept.bk_bookcatalogservices.validator.AdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private AdService adService;

    @Autowired
    private AdValidator adValidator;

    @Autowired
    BookService bookservice;

    @RequestMapping("/bookCatalog")
    private List<Book> getAllBooks() {

        return bookservice.getAllBooks();
    }

    @GetMapping("/allCategories")
    private List<Category> getAllCategories(){
        return Arrays.asList(Category.values());
    }

    @GetMapping("{isbn}/allAds")
    private List<Ad> getAllAds(@RequestParam String condition, @PathVariable("isbn") Long isbn){
        return adService.getAllAds(condition, isbn);
    }

    @GetMapping("/{isbn}")
    private Book getBook(@PathVariable("isbn") Long isbn) {
        return bookservice.getBookByIsbn(isbn);
    }


    
    @RequestMapping(value = "/create")
    public Long saveBook(@RequestBody Book book) {
//        book.setCategory(Category.valueOf(book.getCategory()).toString());
        bookservice.saveBook(book);
        return book.getId();
    }


    @PostMapping("/search")
    private List<Book> searchFor(@Valid @RequestBody SearchForm searchForm){
//        System.out.println
        return bookservice.searchBooks(searchForm);
    }

    @PostMapping("/createAd")
    private ResponseEntity<?> createAd(@Valid @RequestBody Ad ad, BindingResult result){
        adValidator.validate(ad, result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        ad.setCondition(Condition.valueOf(ad.getCondition().toUpperCase()).toString());
        Ad newAd = adService.saveAd(ad);

        return  new ResponseEntity<Ad>(newAd, HttpStatus.CREATED);
    }
}
