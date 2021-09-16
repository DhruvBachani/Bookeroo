package com.rmit.sept.bk_reviewservices.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userid;
    private Long bookid;
    private String review;
    private String rating;

    public Review() {

    }

    public Long getId() { return id; }

    public Long getUserId() { return userid; }

    public void setUserId(Long userid) { this.userid = userid; }

    public Long getBookId() { return bookid; }

    public void setBookId(Long bookid) { this.bookid = bookid; }

    public String getReview() { return review; }

    public void setReview(String review) { this.review = review; }

    public String getRating() { return rating; }

    public void setRating(String rating) { this.rating = rating; }
}
