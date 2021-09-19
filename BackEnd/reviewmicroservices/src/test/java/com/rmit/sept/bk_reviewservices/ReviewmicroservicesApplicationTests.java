package com.rmit.sept.bk_reviewservices;

import com.rmit.sept.bk_reviewservices.Repositories.ReviewRepository;
import com.rmit.sept.bk_reviewservices.model.Review;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewmicroservicesApplicationTests {

    @Autowired
    ReviewRepository reviewrepository;

    @Test
    @Order(1)
    void testSaveReview() {
        Review testReview = new Review();

        testReview.setUserName("User1");
        testReview.setBookISBN(((long) 123456));
        testReview.setRating("3");
        testReview.setReview("I liked it!");

        reviewrepository.save(testReview);

        List<Review> temp = new ArrayList<>();
        reviewrepository.findAll().forEach(reviewTemp -> temp.add(reviewTemp));
        for (Review rev : temp) {
            if (rev.getBookISBN().equals(testReview.getBookISBN())) {
                assertNotNull(reviewrepository.findById(rev.getId()).get());
            }
        }
    }


    @Test
    @Order(2)
    void testGetAllReviews() {
        List<Review> allReviews = new ArrayList<>();
        reviewrepository.findAll().forEach(reviewTemp -> allReviews.add(reviewTemp));
        boolean cond = (allReviews.size() > 0);
        assertTrue(cond);
    }


    @Test
    @Order(3)
    void testGetReviewsByISBN() {
        Long isbn = (long) 123456;
        List<Review> allReviews = new ArrayList<>();
        reviewrepository.findAll().forEach(reviewTemp -> allReviews.add(reviewTemp));
        for (Review rev : allReviews) {
            assertEquals(isbn, rev.getBookISBN());
        }
    }


    @Test
    @Order(4)
    void testUpdateReview() {
        Long isbn = (long) 123456;
        List<Review> temp = new ArrayList<>();
        reviewrepository.findAll().forEach(reviewTemp -> temp.add(reviewTemp));
        for (Review rev : temp) {
            if (rev.getBookISBN().equals(isbn)) {
                Long id = rev.getId();
                Review getRev = reviewrepository.findById(id).get();
                getRev.setRating("7");
                reviewrepository.save(getRev);
                assertNotEquals("3", reviewrepository.findById(id).get().getRating());
            }
        }
    }


    @Test
    @Order(5)
    void testDeleteReview() {
        Long isbn = (long) 123456;
        List<Review> temp = new ArrayList<>();
        reviewrepository.findAll().forEach(reviewTemp -> temp.add(reviewTemp));
        for (Review rev : temp) {
            if (rev.getBookISBN().equals(isbn)) {
                Long id = rev.getId();
                reviewrepository.deleteById(id);
                assertFalse(reviewrepository.existsById(id));
            }
        }
    }

}
