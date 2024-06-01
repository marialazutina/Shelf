package com.example.shelfapp.Controllers;

import com.example.shelfapp.Models.User;
import com.example.shelfapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
//
//    @PostMapping("/registration")
//    public String createUser(@ModelAttribute("user") User user, BindingResult result, Model model){
//
//        try {
//            User existingUser = userService.findUserByEmail(user.getEmail());
//
//            if (existingUser != null && existingUser.getEmail() != null && existingUser.getEmail().isEmpty()) {
//                result.rejectValue("email", "email error code",
//                        "User with this email already exists!");
//                System.out.println("Failed. Email already exists.");
//                model.addAttribute("message_failed", "Sign up failed.");
//
//            }
//            userService.saveUser(user);
//            model.addAttribute("message_signup", "Registration successful!");
//            System.out.println("User has been created. Full name: " + user.getFirstName() + " " + user.getLastName());
//        }catch (Exception e){
//            model.addAttribute("message_failed", "Registration failed");
//            model.addAttribute("error", e.getMessage());
//            model.addAttribute("user", user);
//
//            return "index";
//        }
//        return "redirect:/?message_signup=signup_success";
//    }

//    @GetMapping("/login")
//    public String adminLoginForm(
//            @RequestParam(name = "message_signup", required = false) String message,
//            Model model
//    ){
//        model.addAttribute("message_signup", message);
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String handleUserLogin(User user, HttpServletResponse response, Model model){
//        try{
//            User loggedInUser = userService.verifyUser(user);
//            Cookie userCookie = new Cookie("userCookie", loggedInUser.getId().toString());
//            response.addCookie(userCookie);
//
//            return "redirect:userPage/" + loggedInUser.getId();
//        }catch (Exception e){
//            System.out.println(e);
//            model.addAttribute("message_signup", "login_failed");
//            return "redirect:login?message_login=login_failed&error=" + e.getMessage();
//        }
//    }
//    @GetMapping("userPage/{userId}")
//    public String showUserPage(@PathVariable("userId") Long userId, Model model, User user){
//        model.addAttribute("userId", userId);
//        model.addAttribute("user", user);
//
//        String firstName = userService.findUserById(userId).get().getFirstName();
//        model.addAttribute("firstName", firstName);
//
//        return "userPage";
//    }
}