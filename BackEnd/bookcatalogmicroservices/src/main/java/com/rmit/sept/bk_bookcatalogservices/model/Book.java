package com.rmit.sept.bk_bookcatalogservices.model;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String author;
    private String category;
    private int isbn;

    public Book(String name, String author, String category, int isbn) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
    }

    public long getId() { return this.id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public int getIsbn() { return isbn; }

    public void setIsbn(int isbn) { this.isbn = isbn; }
}
