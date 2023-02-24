package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.BookReqDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public String createBook(BookReqDto bookReqDto){

        int authorId = bookReqDto.getAuthorId();

        //Here I am fetching author Entity

        Author author = authorRepository.findById(authorId).get();

        //Set the attributes before saving.

        Book book = new Book();

        book.setPages(bookReqDto.getPages());
        book.setGenre(bookReqDto.getGenre());
        book.setBookIssued(false);
        book.setName(bookReqDto.getName());


        //setting the foreign key attribute, as author is the parent
        book.setAuthor(author);

        //here we need to update the list of books written
        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);

        authorRepository.save(author);

        return "Book added successfully";
    }



}
