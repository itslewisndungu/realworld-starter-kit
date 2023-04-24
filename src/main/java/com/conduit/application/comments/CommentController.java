package com.conduit.application.comments;

import com.conduit.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles/{slug}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    @GetMapping
    public MultipleCommentResponse getCommentsFromAnArticle(@PathVariable String slug, User me) {
        var comments = this.service.getCommentsFromAnArticle(slug, me);
        return new MultipleCommentResponse(comments);
    }

    @PostMapping
    public CommentResponse addCommentToAnArticle(
            @PathVariable String slug,
            @RequestBody CreateCommentRequest request,
            User me
    ) {
        var comment = this.service.createComment(slug, request, me);
        return new CommentResponse(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id, @PathVariable String slug, User me) {
        this.service.deleteComment(id, slug, me);
    }
}
