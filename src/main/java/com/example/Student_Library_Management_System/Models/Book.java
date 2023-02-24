package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.Genre;
import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private int pages;

    private String name;


    //BOOK is the child w.r.to Author .
    //SO we need do the mapping here
    //We can have many books for one author. so it is many to one mapping
    @ManyToOne
    @JoinColumn  // here add join column in child class becoz we are adding an extra attribute(PK of parent) to the child table
    //It will be foreign for child table
    private Author author;


    // Book is also a child w.r.to card
    @ManyToOne
    @JoinColumn
    private Card card;

    private boolean bookIssued;

    public Book() {
    }

    public boolean isBookIssued() {
        return bookIssued;
    }

    public void setBookIssued(boolean bookIssued) {
        this.bookIssued = bookIssued;
    }

    public String getName() {
        return name;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setName(String name) {
    }
}
