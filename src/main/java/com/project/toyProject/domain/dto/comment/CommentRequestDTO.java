package com.project.toyProject.domain.dto.comment;

import lombok.Data;

@Data
public class CommentRequestDTO {
    private Long postId;
    private Long memberId;
    private String commentContent;
}
