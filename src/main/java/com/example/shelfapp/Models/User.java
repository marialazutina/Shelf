package com.example.shelfapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private Role role;
    private Boolean enabled = false;
    private Boolean locked = false;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Book> myBookList;

    public User(String firstName, String lastName, String email, String password, Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
