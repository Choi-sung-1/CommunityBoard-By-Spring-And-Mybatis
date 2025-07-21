package com.project.toyProject.service.post;

import com.project.toyProject.domain.vo.PostLikeStatusVO;

public interface PostLikeStatusService {
//    좋아요 상태변화
    public void toggleLikeStatus(Long postId,Long memberId);
//    좋아요 상태가져오기
    public PostLikeStatusVO getPostLikeStatus(Long postId, Long memberId);
}
