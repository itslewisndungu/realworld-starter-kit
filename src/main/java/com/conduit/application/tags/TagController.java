package com.conduit.application.tags;

import com.conduit.domain.content.Tag;
import com.conduit.domain.content.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagRepository tagRepository;

    @GetMapping
    public TagsResponse getAllTags() {
        var tags = tagRepository.findAll().stream().map(Tag::name).toList();
        return new TagsResponse(tags);
    }
}
