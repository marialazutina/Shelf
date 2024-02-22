package com.example.shelfapp.Repositories;

import com.example.shelfapp.Models.Book;
import com.example.shelfapp.Models.CustomUserDetails;
import com.example.shelfapp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<CustomUserDetails> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);

    User findByEmailAndPassword(String email, String password);
    User findUserByEmail(String email);

}
