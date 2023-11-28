package com.example.shelfapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String password;
    @Column(columnDefinition = "boolean default true")
    private boolean active;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Book> myBookList;

}
