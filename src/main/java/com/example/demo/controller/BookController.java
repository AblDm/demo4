package com.example.demo.controller;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.BookCover;
import com.example.demo.services.BookCoverService;
import com.example.demo.services.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("books")
public class BookController {


    private final BookService bookService;
    private final BookCoverService bookCoverService;
    public BookController(BookService bookService, BookCoverService bookCoverService) {
        this.bookService = bookService;
        this.bookCoverService = bookCoverService;
    }

    @GetMapping("{id}")  // GET http://localhost:8080/books/23
    public ResponseEntity<Book> getBookInfo(@PathVariable long id) {

        Book book = bookService.findBook(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping // GET http://localhost:8080/books
    public  ResponseEntity findBooks(@RequestParam(required = false) String name,@RequestParam(required = false) String author, @RequestParam(required = false) String part){
        if (name!=null && !name.isBlank()){
            return ResponseEntity.ok(bookService.findByName(name));
        }
        if (author!=null && !author.isBlank()){
            return ResponseEntity.ok(bookService.findByAuthor(author));
        }
        if (part!=null && !part.isBlank()){
            return ResponseEntity.ok(bookService.findByNamePart(part));
        }
        return ResponseEntity.ok(bookService.getAllBooks());
    }


    @PostMapping // POST http://localhost:8080/books
    public Book createBook (@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping// PUT http://localhost:8080/books
    public ResponseEntity<Book> editBook(@RequestBody Book book) {
        Book foundBook = bookService.editBook(book);
        if (foundBook == null) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundBook);
    }

    @DeleteMapping ("{id}") //DELETE http://localhost:8080/books/23
    public ResponseEntity deleteBook (@PathVariable long id){
         bookService.deleteBook(id);
         return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/covers",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCover (@PathVariable Long id, @RequestParam MultipartFile cover) throws IOException {
        if (cover.getSize()> 1024 * 300){
            return ResponseEntity.badRequest().body("File is too big");
        }
        bookCoverService.uploadCover(id, cover);
        return ResponseEntity.ok().build();
    }

    @GetMapping (value = "/{id}/cover/preview")
    public ResponseEntity<byte[]> downloadCover (@PathVariable Long id){
        BookCover bookCover = bookCoverService.findBookCover(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(bookCover.getMediaType()));
        headers.setContentLength(bookCover.getPreview().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bookCover.getPreview());

    }

    @GetMapping (value = "/{id}/cover")
    public void downloadCover(@PathVariable Long id, HttpServletResponse response) throws IOException {
        BookCover bookCover = bookCoverService.findBookCover(id);

        Path path = Path.of(bookCover.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {

            response.setStatus(200);
            response.setContentType(bookCover.getMediaType());
            response.setContentLength((int) bookCover.getFileSize());

            is.transferTo(os);
        }
    }



}
