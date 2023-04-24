package com.conduit.domain.user;

import com.conduit.domain.content.Article;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(fluent = true, chain = true)
public class User {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_user_id")
    )
    private final Set<User> following = new HashSet<>();
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
    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>();

    public boolean isFollowing(User user) {
        System.out.println(this.following());
        return this.following().contains(user);
    }

    public void follow(User user) {
        if (isFollowing(user)) return;

        this.following().add(user);
        user.followers.add(this);
    }


    public void unfollow(User user) {
        if (!isFollowing(user)) return;

        this.following.remove(user);
        user.followers.remove(this);
    }
}
