package com.rmit.sept.bk_bookcatalogservices.services;

import com.rmit.sept.bk_bookcatalogservices.Repositories.AdRepository;
import com.rmit.sept.bk_bookcatalogservices.model.Ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;


@Service
public class AdService {
    @Autowired
    AdRepository adRepository;

    public Ad saveAd(Ad newAd) {
        return adRepository.save(newAd);
    }

    public List<Ad> getAllAds(String condition, Long isbn){
        return adRepository.findAllByConditionAndIsbn(condition.toUpperCase(), isbn);
    }


}