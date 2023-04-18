package com.conduit.domain.user;

import com.conduit.domain.content.Article;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    private String bio;

    @Column(nullable = false)
    private String password;

    private String image;

    @Transient
    private String token;

    @OneToMany(mappedBy = "author")
    private List<Article> articles;

}
