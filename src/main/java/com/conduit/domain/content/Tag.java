package com.conduit.domain.content;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Accessors(fluent = true, chain = true)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 20)
    private String name;

    @ManyToMany(mappedBy = "tags")
    Set<Article> articles = new HashSet<>();
}
