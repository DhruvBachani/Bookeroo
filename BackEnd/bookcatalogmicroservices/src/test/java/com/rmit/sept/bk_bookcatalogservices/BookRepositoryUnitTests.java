package com.rmit.sept.bk_bookcatalogservices;

import com.rmit.sept.bk_bookcatalogservices.Repositories.BookRepository;
import com.rmit.sept.bk_bookcatalogservices.model.Book;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryUnitTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    private Book book = new Book();


    @Test
    public void save_WhenAllDetailsAreFilled_Test()
    {
        // given
        book.setAuthor("Patrick");
        book.setName("World");
        book.setIsbn((long)1);

        // when persisted, then no exception is thrown
        Assertions.assertDoesNotThrow(() -> entityManager.persist(book));
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void save_WhenIsbnIsNull_Test()
    {
        // given
        book.setAuthor("Patrick");
        book.setName("World");
        book.setIsbn(null);
        entityManager.persist(book);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void save_WhenNameIsBlank_Test()
    {
        // given
        book.setAuthor("Patrick");
        book.setIsbn((long)1);
        entityManager.persist(book);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void save_WhenAuthorIsBlank_Test()
    {
        // given
        book.setName("World");
        book.setIsbn((long)1);
        entityManager.persist(book);
        entityManager.flush();
    }

    @Test
    public void existsByIsbn_returnTrue_IfBookWithIsbnExists(){

        book.setAuthor("Patrick");
        book.setName("World");
        book.setIsbn((long)1);
        entityManager.persist(book);
        entityManager.flush();

        assertTrue(bookRepository.existsByIsbn((long)1));

    }

    @Test
    public void existsByIsbn_returnFalse_IfBookWithIsbnDoesntExist(){

        book.setAuthor("Patrick");
        book.setName("World");
        book.setIsbn((long)1);
        entityManager.persist(book);
        entityManager.flush();

        assertFalse(bookRepository.existsByIsbn((long)2));

    }

    @Test
    public void findByIsbn_returnNull_IfBookWithIsbnDoesntExist(){

        book.setAuthor("Patrick");
        book.setName("World");
        book.setIsbn((long)1);
        entityManager.persist(book);
        entityManager.flush();

        assertNull(bookRepository.findByIsbn((long)2));

    }

    @Test
    public void findByIsbn_returnBook_IfBookWithIsbnExists(){

        book.setAuthor("Patrick");
        book.setName("World");
        book.setIsbn((long)1);
        entityManager.persist(book);
        entityManager.flush();

        assertEquals((Long)((long)1),bookRepository.findByIsbn((long)1).getIsbn());
        assertTrue(bookRepository.findByIsbn((long)1) instanceof Book);

    }

    @Test
    public void findByNameIgnoreCaseContaining_returnListOfBook_IfBookNameContainingStringExists(){

        book.setAuthor("Patrick");
        book.setName("World");
        book.setIsbn((long)1);
        entityManager.persist(book);
        entityManager.flush();

        assertEquals(1, bookRepository.findByNameIgnoreCaseContaining("ld").size());
        assertEquals((Long)((long)1), bookRepository.findByNameIgnoreCaseContaining("ld").get(0).getIsbn());

    }

    @Test
    public void findByNameIgnoreCaseContaining_returnEmptyListOfBook_IfBookNameContainingStringDoesntExist(){

        book.setAuthor("Patrick");
        book.setName("World");
        book.setIsbn((long)1);
        entityManager.persist(book);
        entityManager.flush();

        assertTrue(bookRepository.findByNameIgnoreCaseContaining("docker").isEmpty());


    }




}
