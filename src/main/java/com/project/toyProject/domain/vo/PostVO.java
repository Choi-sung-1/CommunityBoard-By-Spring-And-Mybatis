package com.project.toyProject.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostVO {
    private Long id;
    private String postTitle;
    private String postContent;
    private LocalDateTime postRegisterDate;
    private LocalDateTime postUpdateDate;
    private Long postLikeCount;
    private Long postReadCount;
    private Long memberId;

}
