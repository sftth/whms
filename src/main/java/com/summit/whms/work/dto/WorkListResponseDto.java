package com.summit.whms.work.dto;

import com.summit.whms.work.domain.posts.Works;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WorkListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public WorkListResponseDto(Works entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
