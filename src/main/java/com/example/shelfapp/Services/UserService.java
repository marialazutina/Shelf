package com.example.shelfapp.Services;

import com.example.shelfapp.Models.User;
import com.example.shelfapp.Registration.Token.ConfirmationToken;
import com.example.shelfapp.Registration.Token.ConfirmationTokenService;
import com.example.shelfapp.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
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

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with email " + email + " not found."));
    }

    public String signUpUser(User user){
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if(userExists){
            throw new IllegalStateException("Email already taken.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public int enableUser(String email){
        return userRepository.enableUser(email);
    }
}
