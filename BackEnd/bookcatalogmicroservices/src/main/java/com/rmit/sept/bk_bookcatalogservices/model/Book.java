package com.rmit.sept.bk_bookcatalogservices.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String author;
    private String category;
    private String description;
    private Long isbn;

    public Book() {
    }

    // public Book(String name, String author, String category, String description, int isbn) {
    //     this.name = name;
    //     this.author = author;
    //     this.category = category;
    //     this.description = description;
    //     this.isbn = isbn;
    // }

    public Long getId() { return this.id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return this.category; }

    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    public Long getIsbn() { return isbn; }

    public void setIsbn(Long isbn) { this.isbn = isbn; }
}
