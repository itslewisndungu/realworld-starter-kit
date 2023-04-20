package com.conduit.application.article.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ArticleDto {
    @NotNull
    private String slug;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String body;
}
