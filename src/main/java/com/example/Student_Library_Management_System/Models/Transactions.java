package com.example.Student_Library_Management_System.Models;


import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="transactions")
public class Transactions {

    private String transactionId = UUID.randomUUID().toString();

    private TransactionStatus transactionStatus;

    private int fine;

    @CreationTimestamp
    private Date transactionDate;


    private boolean isIssueOperation;


    //Now I need to mappings

    //As transactions is a child entity for book
    @ManyToOne
    @JoinColumn
    private Book book;


    //MAPPING for Card entity
    @ManyToOne
    @JoinColumn
    private Card card;

    public Transactions() {
    }

    public Transactions(String transactionId, TransactionStatus transactionStatus, int fine, Date transactionDate, boolean isIssueOperation, Book book, Card card) {
        this.transactionId = transactionId;
        this.transactionStatus = transactionStatus;
        this.fine = fine;
        this.transactionDate = transactionDate;
        this.isIssueOperation = isIssueOperation;
        this.book = book;
        this.card = card;
    }



    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public boolean isIssueOperation() {
        return isIssueOperation;
    }

    public void setIssueOperation(boolean issueOperation) {
        isIssueOperation = issueOperation;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
