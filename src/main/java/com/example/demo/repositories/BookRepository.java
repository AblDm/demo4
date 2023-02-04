package com.example.demo.repositories;

import com.example.demo.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByNameIgnoreCase(String name);

    Collection<Book> findBooksByAuthorIgnoreCase(String author);

    Collection<Book> findAllByNameContainsIgnoreCase(String part);

    Collection<Book> findBooksByNameOrAuthor(String name, String author);
}
