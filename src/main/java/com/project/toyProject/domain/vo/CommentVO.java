package com.project.toyProject.domain.vo;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class CommentVO {
    private Long id;
    private String commentContent;
    private String commentWriterName;
    private LocalDateTime commentRegisterDate;
    private Long memberId;
    private Long postId;

}
