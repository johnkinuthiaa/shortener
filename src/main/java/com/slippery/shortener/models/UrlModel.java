package com.slippery.shortener.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UrlModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private String name;
    private String description;
    private LocalDateTime createdOn =LocalDateTime.now();
    @OneToOne(cascade = CascadeType.ALL)
    private Clicks clicks;
    @ManyToOne
    @JsonBackReference
    private Users users;
}
