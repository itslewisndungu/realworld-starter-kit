package com.conduit.application.feed;


import com.conduit.application.articles.requests.ArticleFacets;
import com.conduit.application.articles.response.MultipleArticlesResponse;
import com.conduit.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/articles/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService service;

    @GetMapping
    public MultipleArticlesResponse getFeed(
            User user,
            @RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset
    ) {
        ArticleFacets facets = new ArticleFacets(null, null, offset, limit);
        var articles = this.service.getFeedForUser(user, facets);
        return new MultipleArticlesResponse(articles);
    }
}
