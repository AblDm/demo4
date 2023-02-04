package com.example.demo.services;

import com.example.demo.Entity.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService {

    private BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook (Book book) {
        return bookRepository.save(book);
    }

    public  Book findBook (long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book editBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook (long id) {
        bookRepository.deleteById(id);
    }

    public Collection<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book findByName (String name){
        return bookRepository.findBookByNameIgnoreCase(name);
    }

    public  Collection <Book> findByAuthor (String author) {
        return bookRepository.findBooksByAuthorIgnoreCase(author);
    }

    public  Collection <Book> findByNamePart (String part) {
        return  bookRepository.findAllByNameContainsIgnoreCase(part);
    }

}
