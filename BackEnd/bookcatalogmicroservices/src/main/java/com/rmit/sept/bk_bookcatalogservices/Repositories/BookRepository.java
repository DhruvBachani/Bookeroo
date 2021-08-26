package com.rmit.sept.bk_bookcatalogservices.Repositories;

import com.rmit.sept.bk_bookcatalogservices.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
