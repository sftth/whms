package com.summit.whms.work.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorksUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public WorksUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
