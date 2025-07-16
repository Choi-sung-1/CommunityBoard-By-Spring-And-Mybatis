package com.project.toyProject.mapper;

import com.project.toyProject.domain.vo.PostLikeStatusVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostLikeStatusMapper {
    public void insert(PostLikeStatusVO postLikeStatusVO);
    public PostLikeStatusVO selectPostLikeStatus(@Param("postId") Long postId, @Param("memberId") Long memberId);
    public void update(PostLikeStatusVO postLikeStatusVO);
}
