package com.example.shelfapp.Services;

import com.example.shelfapp.Models.Book;
import com.example.shelfapp.Models.User;
import com.example.shelfapp.Repositories.BookRepository;
import com.example.shelfapp.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private UserService userService;
    public BookService(BookRepository bookRepository, UserRepository userRepository){
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public void saveBook(Book book, Long userId){
        userRepository.findById(userId);
        bookRepository.save(book);
    }

    public Book findBookByTitle(String title){
        return this.bookRepository.findBookByTitle(title);
    }
    public Book findBookByAuthor(String author){
        return this.bookRepository.findBookByAuthor(author);
    }
    public List<Book> showAllBooks(){
        return this.bookRepository.findAll();
    }
    public List<Book> showMyListing(Long userId){
        return this.bookRepository.findByUserId(userId);
    }


}
