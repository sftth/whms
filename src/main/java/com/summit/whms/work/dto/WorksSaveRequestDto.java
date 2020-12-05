package com.summit.whms.work.dto;

import com.summit.whms.work.domain.posts.Works;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorksSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public WorksSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Works toEntity() {
        return Works.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
