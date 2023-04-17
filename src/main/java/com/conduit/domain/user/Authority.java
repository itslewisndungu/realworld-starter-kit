package com.conduit.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Authority {
    @Id
    private Integer id;

    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users;
}
