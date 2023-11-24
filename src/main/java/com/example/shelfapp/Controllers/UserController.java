package com.example.shelfapp.Controllers;

import com.example.shelfapp.Models.User;
import com.example.shelfapp.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/")
    public String createUserForm(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") User user, BindingResult result, Model model){

        try {
            User existingUser = userService.findUserByEmail(user.getEmail());

            if (existingUser != null && existingUser.getEmail() != null && existingUser.getEmail().isEmpty()) {
                result.rejectValue("email", "email error code",
                        "User with this email already exists!");
                System.out.println("Failed. Email already exists.");
                model.addAttribute("message_signup", "signup_failed");

            }
            userService.saveUser(user);
            model.addAttribute("message_signup", "signup_success");

            System.out.println("User has been created. Full name: " + user.getFirstName() + " " + user.getLastName());
        }catch (Exception e){
            model.addAttribute("message_signup", "signup_failed");
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", user);

            return "index";
        }


        return "redirect:/?message_signup=signup_success";
    }

    @GetMapping("/login")
    public String adminLoginForm(
            @RequestParam(name = "message_signup", required = false) String message,
            Model model
    ){
        model.addAttribute("message_signup", message);
        return "login";
    }

    @PostMapping("/login")
    public String handleUserLogin(User user, HttpServletResponse response, Model model){
        try{
            User loggedInUser = userService.verifyUser(user);
            Cookie cookie = new Cookie("userCookie", loggedInUser.getId().toString());
            response.addCookie(cookie);

            return "redirect:userPageAfterLogin/" + loggedInUser.getId();
        }catch (Exception e){
            System.out.println(e);
            model.addAttribute("message_login", "login_failed");
            return "redirect:login?message_login=login_failed&error=" + e.getMessage();
        }
    }
    @GetMapping("userPage/{userId}")
    public String showUserPage(@PathVariable Long userId, Model model, User user){
        model.addAttribute("userId", userId);
        model.addAttribute("user", user);
        return "userPageAfterLogin";
    }
}
