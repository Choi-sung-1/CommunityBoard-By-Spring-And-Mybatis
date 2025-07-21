package com.project.toyProject.service.post;

import com.project.toyProject.domain.vo.PostLikeStatusVO;

public interface PostLikeStatusService {
//    좋아요 상태변화
    void toggleLikeStatus(Long postId,Long memberId);
//    좋아요 상태가져오기
     PostLikeStatusVO getPostLikeStatus(Long postId, Long memberId);
}
