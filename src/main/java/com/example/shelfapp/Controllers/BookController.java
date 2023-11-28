package com.example.shelfapp.Controllers;

import com.example.shelfapp.Models.Book;
import com.example.shelfapp.Models.Status;
import com.example.shelfapp.Models.User;
import com.example.shelfapp.Services.BookService;
import com.example.shelfapp.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {
    private final BookService bookService;
    private final UserService userService;
    public BookController(BookService bookService, UserService userService){
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/userPage/{userId}/addBook")
    public String showAddBookForm(@CookieValue(value = "userCookie") String userIdCookie, @PathVariable("userId") Long userId, Model model){
        model.addAttribute("userId", userIdCookie);
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/userPage/{userId}/addBook")
    public String addBookToListing(@PathVariable("userId") Long userId, Book book, @CookieValue(value = "userCookie") String userIdCookie, User user, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("status", book.setStatus(Status.AVAILABLE));

        this.bookService.saveBook(book, userId);
        System.out.println(book);

        model.addAttribute("message", "new_listing");

        return "redirect:/userPage/{userId}?message=new_listing_OK";
    }
}
