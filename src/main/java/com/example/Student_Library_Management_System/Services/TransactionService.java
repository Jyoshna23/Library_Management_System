package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception{

        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();


        //Make a transacction entity. set its attributes and save it;

        Transactions transactions = new Transactions();

        //setting the attributes
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setIssueOperation(true);
        transactions.setTransactionStatus(TransactionStatus.PENDING);


        // validations
        //If the validations are true, then the transaction status will be success
        //Otherwise failed\

        if(book == null || book.isBookIssued() == true) {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Book not Available");
        }


        if(card == null || (card.getCardStatus() != CardStatus.ACTIVATED)){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Card is not available");
        }


        //success case
        transactions.setTransactionStatus(TransactionStatus.SUCCESS);


        transactionRepository.save(transactions);

        return "Book issued successfully";
    }

}
