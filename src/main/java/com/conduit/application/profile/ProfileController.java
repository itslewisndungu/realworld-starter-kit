package com.conduit.application.profile;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @GetMapping("/{username}")
    public String getProfile(@PathVariable String username) {
        return username;
    }

    @PostMapping("/{username}/follow")
    public String followUser(@PathVariable String username){
        return "followed the user with username " + username;
    }

    @DeleteMapping ("/{username}/follow")
    public String unfollowUser(@PathVariable String username){
        return "unfollowed the user with username " + username;
    }
}
