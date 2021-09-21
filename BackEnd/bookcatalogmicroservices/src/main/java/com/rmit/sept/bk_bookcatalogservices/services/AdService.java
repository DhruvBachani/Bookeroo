package com.rmit.sept.bk_bookcatalogservices.services;

import com.rmit.sept.bk_bookcatalogservices.Repositories.AdRepository;
import com.rmit.sept.bk_bookcatalogservices.Repositories.BookRepository;
import com.rmit.sept.bk_bookcatalogservices.exceptions.InvalidConditionException;
import com.rmit.sept.bk_bookcatalogservices.exceptions.InvalidIsbnException;
import com.rmit.sept.bk_bookcatalogservices.model.Ad;

import com.rmit.sept.bk_bookcatalogservices.model.Book;
import com.rmit.sept.bk_bookcatalogservices.model.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;


@Service
public class AdService {
    @Autowired
    AdRepository adRepository;

    @Autowired
    BookRepository bookRepository;

    public Ad saveAd(Ad newAd) {
        if(!validIsbn(newAd.getIsbn())){
            throw new InvalidIsbnException("Book with given Isbn doesn't exists");
        }
        if (!validCondition(newAd.getCondition())){
            throw new InvalidConditionException("Invalid Condition provided");
        }
        return adRepository.save(newAd);
    }

    public List<Ad> getAllAds(String condition, Long isbn){

        return adRepository.findAllByConditionAndIsbn(condition.toUpperCase(), isbn);
    }

    //helper methods
    private boolean validCondition(String testingCondition){
        for(Condition condition: Condition.values()){
            if(condition.toString().equalsIgnoreCase(testingCondition)){
                return true;
            }
        }
        return false;
    }

    private boolean validIsbn(Long testingIsbn){
        List<Book> allBooks = (List<Book>) bookRepository.findAll();
        for(Book book: allBooks){
            if(testingIsbn == book.getIsbn()){
                return true;
            }
        }
        return false;
    }

}