package com.rmit.sept.bk_bookcatalogservices.services;

import com.rmit.sept.bk_bookcatalogservices.Repositories.BookRepository;
import com.rmit.sept.bk_bookcatalogservices.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private BookRepository bookRepository;

    @Autowired
    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void run(ApplicationArguments args) {
        Book book1 = new Book();
        book1.setName("Murder on the Orient Express");
        book1.setAuthor("Agatha Christie");
        book1.setCategory("THRILLER");
        book1.setDescription("Just after midnight, a snowdrift stops the Orient Express in its tracks. " +
        "An American tycoon lies dead in his compartment, stabbed a dozen times, his door locked from the inside.\n");
        book1.setIsbn((long) 99798);

        bookRepository.save(book1);
    }
}

