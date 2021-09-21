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
        boolean validIsbn = false;
        List<Book> allBooks = (List<Book>) bookRepository.findAll();
        for(Book book: allBooks){
            if(newAd.getIsbn() == book.getIsbn()){
                validIsbn = true;
                break;
            }
        }

        if(!validIsbn){
            throw new InvalidIsbnException("Book with given Isbn doesn't exists");
        }
        boolean validCondition = false;
        for(Condition condition: Condition.values()){
            if(condition.toString().equalsIgnoreCase(newAd.getCondition())){
                validCondition=true;
            }
        }
        if(!validCondition){
            throw new InvalidConditionException("Invalid Condition provided");
        }
        return adRepository.save(newAd);
    }

    public List<Ad> getAllAds(String condition, Long isbn){
        return adRepository.findAllByConditionAndIsbn(condition.toUpperCase(), isbn);
    }


}