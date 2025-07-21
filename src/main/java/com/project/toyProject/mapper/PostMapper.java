package com.project.toyProject.mapper;

import com.project.toyProject.domain.dto.post.PostDetailDTO;
import com.project.toyProject.domain.dto.post.PostEditDTO;
import com.project.toyProject.domain.dto.post.PostListDTO;
import com.project.toyProject.domain.dto.post.PostSearchDTO;
import com.project.toyProject.domain.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {
//    게시글 작성
    public void insert(PostVO postVO);
//    게시글 목록
    public List<PostListDTO> selectAllPost(PostSearchDTO postSearchDTO);
//    게시글 조회
    public PostDetailDTO selectPostById(Long id);
//    조회수 증가
    public void updateReadCount(Long id);
//    게시글 수정
    public void updatePost(PostEditDTO postEditDTO);
//    게시글 좋아요 증가
    public void updateLikeCount(Long id,@Param("likeButtonStatus")String likeButtonStatus);
//    게시글 좋아요 수 가져오기
    public Long selectPostLikeCount(Long id);
//    게시글 삭제
    public void deletePostById(Long id);
//    게시글 전체 개수
    public int selectAllPostCount(PostSearchDTO postSearchDTO);
}
