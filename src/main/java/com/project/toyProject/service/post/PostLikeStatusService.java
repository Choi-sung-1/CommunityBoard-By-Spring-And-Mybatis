package com.project.toyProject.service.post;

public interface PostLikeStatusService {
//    좋아요 상태변화
    public void toggleLikeStatus(Long postId,Long memberId);
}
