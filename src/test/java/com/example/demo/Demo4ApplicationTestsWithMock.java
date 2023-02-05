package com.example.demo;


import com.example.demo.Entity.Book;
import com.example.demo.controller.BookController;
import com.example.demo.controller.ExpenseController;
import com.example.demo.repositories.BookCoverRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.ExpenseRepository;
import com.example.demo.services.BookCoverService;
import com.example.demo.services.BookService;
import com.example.demo.services.ExpenseService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class Demo4ApplicationTestsWithMock {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private BookCoverRepository bookCoverRepository;
    @MockBean
    ExpenseRepository expenseRepository;

    @SpyBean
    private BookService bookService;
    @SpyBean
    private BookCoverService bookCoverService;
    @SpyBean
    private ExpenseService expenseService;


    @InjectMocks
    private BookController bookController;

    @Test
    public void saveBookTest() throws Exception {

        final String name = "testBook";
        final String author = "testAuthor";
        final long id = 1;


        JSONObject bookObject = new JSONObject();

        bookObject.put("name",name);
        bookObject.put("author",author);
        bookObject.put("id",id);

        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);

        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/books/" )
                .content(bookObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.author").value(author));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/books/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.author").value(author));
    }









}

