package com.conduit.application.feed;

import com.conduit.application.articles.requests.ArticleFacets;
import com.conduit.domain.content.ArticleRepository;
import com.conduit.domain.content.ArticleVO;
import com.conduit.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final ArticleRepository articleRepository;

    public List<ArticleVO> getFeedForUser(User user, ArticleFacets facets) {
        var page = PageRequest.of(facets.offset(), facets.limit());
        var feed = this.articleRepository.findByAuthorInOrderByCreatedAt(user.following(), page);

        return feed.stream()
                .map(a -> new ArticleVO(a, user))
                .toList();
    }
}
