package com.rmit.sept.bk_bookcatalogservices;

import com.rmit.sept.bk_bookcatalogservices.Repositories.AdRepository;
import com.rmit.sept.bk_bookcatalogservices.Repositories.BookRepository;
import com.rmit.sept.bk_bookcatalogservices.model.Ad;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AdRepositoryUnitTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AdRepository adRepository;

    private Ad ad = new Ad();


    @Test
    public void save_WhenAllDetailsAreFilled_Test()
    {
        // given
        ad.setIsbn((long) 1 );
        ad.setCondition("ACTION");
        ad.setPrice(new BigDecimal(12.3));
        ad.setUserId((long) 123);

        // when persisted, then no exception is thrown
        Assertions.assertDoesNotThrow(() -> entityManager.persist(ad));
        entityManager.flush();
    }

    @Test
    public void findAllByConditionAndIsbn_returnFilledListOfAds_IfMatchingAdsExists()
    {
        // given
        ad.setIsbn((long) 1);
        ad.setCondition("NEW");
        ad.setPrice(new BigDecimal(12.3));
        ad.setUserId((long) 123);
        entityManager.persist(ad);
        entityManager.flush();

        assertEquals(1, adRepository.findAllByConditionAndIsbn("NEW", (long) 1).size());
        assertEquals((Long)((long)1), adRepository.findAllByConditionAndIsbn("NEW", (long) 1).get(0).getIsbn());
    }

    @Test
    public void findAllByConditionAndIsbn_returnEmptyListOfAds_IfMatchingIsbnDoesntExists()
    {
        // given
        ad.setIsbn((long) 1);
        ad.setCondition("NEW");
        ad.setPrice(new BigDecimal(12.3));
        ad.setUserId((long) 123);
        entityManager.persist(ad);
        entityManager.flush();

        assertEquals(0, adRepository.findAllByConditionAndIsbn("NEW", (long) 2).size());
    }

    @Test
    public void findAllByConditionAndIsbn_returnEmptyListOfAds_IfMatchingConditionDoesntExists()
    {
        // given
        ad.setIsbn((long) 1);
        ad.setCondition("NEW");
        ad.setPrice(new BigDecimal(12.3));
        ad.setUserId((long) 123);
        entityManager.persist(ad);
        entityManager.flush();

        assertEquals(0, adRepository.findAllByConditionAndIsbn("OLD", (long) 1).size());
    }


}
