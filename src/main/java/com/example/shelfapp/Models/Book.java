package com.example.shelfapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private double price;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @ManyToOne
    private User user;
    public Status setStatus(Status status){
        return this.status = status;
    }


}
