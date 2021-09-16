package com.rmit.sept.bk_bookcatalogservices.Repositories;

import com.rmit.sept.bk_bookcatalogservices.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    boolean existsByIsbn(Long isbn);
    Book findByIsbn(Long isbn);

}
