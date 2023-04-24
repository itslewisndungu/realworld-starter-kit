package com.conduit.application.comments;

import com.conduit.domain.content.CommentVO;

import java.util.List;

public record MultipleCommentResponse(List<CommentVO> comments) {
}
