package com.conduit.application.comments;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("comment")
public record CreateCommentRequest(String body) {
}
