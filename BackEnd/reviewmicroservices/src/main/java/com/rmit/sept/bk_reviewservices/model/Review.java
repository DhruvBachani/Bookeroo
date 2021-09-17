package com.rmit.sept.bk_reviewservices.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Long bookid;
    private String review;
    private String rating;

    public Review() {

    }

    public Long getId() { return id; }

    public String getUserName() { return username; }

    public void setUserName(String username) { this.username = username; }

    public Long getBookId() { return bookid; }

    public void setBookId(Long bookid) { this.bookid = bookid; }

    public String getReview() { return review; }

    public void setReview(String review) { this.review = review; }

    public String getRating() { return rating; }

    public void setRating(String rating) { this.rating = rating; }
}
