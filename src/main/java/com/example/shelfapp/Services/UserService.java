package com.example.shelfapp.Services;

import com.example.shelfapp.Models.User;
import com.example.shelfapp.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User findUserByEmail(String email){
        return this.userRepository.findUserByEmail(email);
    }
    public User getById(Long id){
        return userRepository.getReferenceById(id);
    }

    public User verifyUser(User userLoginRequest) throws Exception{
        if(userLoginRequest == null){
            throw new Exception("Invalid request.");
        }
        User foundUser = this.userRepository.findByEmailAndPassword(userLoginRequest.getEmail(), userLoginRequest.getPassword());
        if(foundUser == null) {
            throw new Exception("Email or password incorrect");
        }

        return foundUser;
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
