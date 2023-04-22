package com.conduit.application.tags;

import com.conduit.domain.content.Tag;
import com.conduit.domain.content.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag findOrCreateTagByName(String name) {
        var tag = tagRepository.findByName(name.toLowerCase());

        return tag.orElseGet(() -> tagRepository.save(
                new Tag().name(name)
        ));
    }
}
