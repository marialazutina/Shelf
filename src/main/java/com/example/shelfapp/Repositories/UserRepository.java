package com.example.shelfapp.Repositories;

import com.example.shelfapp.Models.Book;
import com.example.shelfapp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);
    User findUserByEmail(String email);

}
