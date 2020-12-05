package com.summit.whms.work.dto;

import com.summit.whms.work.domain.posts.Works;
import lombok.Getter;

@Getter
public class WorksResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public WorksResponseDto(Works entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
