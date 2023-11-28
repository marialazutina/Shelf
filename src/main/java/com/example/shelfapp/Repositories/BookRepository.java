package com.example.shelfapp.Repositories;

import com.example.shelfapp.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByAuthor(String author);
    Book findBookByTitle(String title);
    List<Book> findByUserId(Long userId);

}
