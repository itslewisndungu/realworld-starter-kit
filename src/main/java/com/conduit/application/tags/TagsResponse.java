package com.conduit.application.tags;

import com.conduit.domain.content.Tag;

import java.util.List;

public record TagsResponse(List<String> tags) {
}
