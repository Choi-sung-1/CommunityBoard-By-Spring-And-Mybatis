package com.project.toyProject.domain.vo;

import lombok.Data;

@Data
public class PostLikeStatusVO {
    private Long id;
    private String postLikeStatus; // onClick, noneClick
    private Long memberId;
    private Long postId;

    public PostLikeStatusVO(Object o, String onClick, Long memberId, Long postId) {
        this.postLikeStatus = onClick;
        this.memberId = memberId;
        this.postId = postId;
    }
}
