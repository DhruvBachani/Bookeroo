package com.rmit.sept.bk_reviewservices.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long bookId;
    private String review;
    private String rating;

    public Review() {

    }

    public Long getId() { return id; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public Long getBookId() { return bookId; }

    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getReview() { return review; }

    public void setReview(String review) { this.review = review; }

    public String getRating() { return rating; }

    public void setRating(String rating) { this.rating = rating; }
}
