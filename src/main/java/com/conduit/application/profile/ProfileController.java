package com.conduit.application.profile;

import com.conduit.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    @GetMapping("/{username}")
    public ProfileResponse getProfile(@PathVariable String username, User user) {
        var profile = this.profileService.getProfile(username, user);
        return new ProfileResponse(profile);
    }

    @PostMapping("/{username}/follow")
    public String followUser(@PathVariable String username, User me){
        this.profileService.followUser(me, username);
        return "followed the user with username " + username;
    }

    @DeleteMapping ("/{username}/follow")
    public String unfollowUser(@PathVariable String username){
        return "unfollowed the user with username " + username;
    }
}
