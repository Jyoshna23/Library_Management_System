package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="card")
public class Card {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;   // It will be autogenerated, so no need to set

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;   // This attribute should be set before saving it in to the DB.

    @CreationTimestamp
    Date createdOn;    // It will be autogenerated, so no need to set

    @UpdateTimestamp
    Date updatedOn;     // It will be autogenerated, so no need to set

    @OneToOne
    @JoinColumn
    private Student student;


    // Card is the parent w.r.to Book
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Book> booksIssued = new ArrayList<>();    // we are taking list because we can have many books issued on one card.

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public Card() {
    }

    public Card(int id, CardStatus cardStatus, Date createdOn, Date updatedOn, Student student) {
        this.id = id;
        this.cardStatus = cardStatus;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }
}
