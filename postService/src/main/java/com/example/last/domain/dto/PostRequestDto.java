package com.example.last.domain.dto;

import com.example.last.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class PostRequestDto {
    private String title;
    private String contents;
    private String author;

    @Builder
    public PostRequestDto(Post post) {
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.author = post.getAuthor();
    }
}
