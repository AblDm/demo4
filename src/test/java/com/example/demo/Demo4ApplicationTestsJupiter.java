package com.example.demo;



import com.example.demo.Entity.Book;
import com.example.demo.controller.BookController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Demo4ApplicationTestsJupiter {

    @LocalServerPort
    private int port;

    @Autowired
    private BookController bookController;
    @Autowired
    private TestRestTemplate restTemplate;

    Book book= new Book();

    final String name = "testBook";
    final String author = "testAuthor";
    final long id = 1;
    @Test
    void contextLoads()  throws Exception  {
        Assertions
                .assertThat(bookController).isNotNull();
    }
    @Test
    public void testDefaultMessage()  throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"+ port +"/",String.class))
                .isEqualTo("web app is working");
    }
    @Test
    public void testDefaultGetBooks()  throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"+ port +"/books", String.class))
                .isNotNull();
    }

    @Test
    public void testPostBooks() throws Exception {


        book.setName(name);
        book.setAuthor(author);
        book.setId(id);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:"+ port +"/books", book, String.class))
                .isNotNull();
    }


//    @Test
//    public void testSaveBookTest() throws Exception {
//
//        final String name = "testBook";
//        final String author = "testAuthor";
//        final long id = 1;
//
//
//        JSONObject bookObject = new JSONObject();
//
//        bookObject.put("name",name);
//        bookObject.put("author",author);
//
//        Book book = new Book();
//        book.setId(id);
//        book.setName(name);
//        book.setAuthor(author);
//
//        when(bookRepository.save(any(Book.class))).thenReturn(book);
//        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(book));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/books/" )
//                .content(bookObject.toString())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.author").value(author));
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/books/" + id)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.author").value(author));
//    }
//
//
//






}

