package com.project.toyProject.dao;

import com.project.toyProject.domain.vo.PostLikeStatusVO;
import com.project.toyProject.mapper.PostLikeStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikeStatusDAO {
    private final PostLikeStatusMapper postLikeStatusMapper;

    public void insertLikeStatus(PostLikeStatusVO postLikeStatusVO) {
        postLikeStatusMapper.insert(postLikeStatusVO);
    }
    public PostLikeStatusVO selectLikeStatus(Long postId,Long memberId) {
        return postLikeStatusMapper.selectPostLikeStatus(postId,memberId);
    }
    public void updateLikeStatus(PostLikeStatusVO postLikeStatusVO) {
        postLikeStatusMapper.update(postLikeStatusVO);
    }
}
