package com.rmit.sept.bk_bookcatalogservices.web;

import com.rmit.sept.bk_bookcatalogservices.model.Ad;
import com.rmit.sept.bk_bookcatalogservices.model.Book;
import com.rmit.sept.bk_bookcatalogservices.model.Category;
import com.rmit.sept.bk_bookcatalogservices.model.SearchForm;
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

    @GetMapping("/book_by_id/{id}")
    private Book getBook(@PathVariable("id") long id) {
        return bookservice.getBookById(id);
    }

    @DeleteMapping("/books/{id}")
    private void deleteBook(@PathVariable("id") long id) {
        bookservice.deleteBook(id);
    }
    
    @RequestMapping(value = "/create")
    public Long saveBook(@RequestBody Book book) {
        book.setCategory(Category.valueOf(book.getCategory()).toString());
        bookservice.saveBook(book);
        return book.getId();
    }

    @PutMapping("/books")
    private Book updateBook(@RequestBody Book book) {
        bookservice.updateBook(book, book.getId());
        return book;
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

        Ad newAd = adService.saveAd(ad);

        return  new ResponseEntity<Ad>(newAd, HttpStatus.CREATED);
    }
}
