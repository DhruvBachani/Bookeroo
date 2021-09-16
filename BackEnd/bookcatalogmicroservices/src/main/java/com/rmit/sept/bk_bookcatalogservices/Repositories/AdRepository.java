package com.rmit.sept.bk_bookcatalogservices.Repositories;

import com.rmit.sept.bk_bookcatalogservices.model.Ad;
import com.rmit.sept.bk_bookcatalogservices.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends CrudRepository<Ad, Long> {

}
