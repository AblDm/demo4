package com.example.demo.controller;

import com.example.api_swagger.model.Book;
import com.example.demo.services.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {


    private BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")  // GET http://localhost:8080/books/23
    public Book getBookInfo(@PathVariable long id) {

        return bookService.findBook(id);
    }

    @PostMapping("/") // POST http://localhost:8080/books/23
    public Book createBook (Book book) {
        return bookService.createBook(book);
    }

}
