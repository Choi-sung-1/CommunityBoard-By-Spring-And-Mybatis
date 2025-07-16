package com.project.toyProject.domain.dto.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostEditDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    private LocalDateTime postUpdateDate;
}
