package com.project.toyProject.dao;

import com.project.toyProject.domain.dto.post.PostDetailDTO;
import com.project.toyProject.domain.dto.post.PostEditDTO;
import com.project.toyProject.domain.dto.post.PostListDTO;
import com.project.toyProject.domain.vo.PostVO;
import com.project.toyProject.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PostDAO {
    private final PostMapper postMapper;

//    게시글 작성
    public void insertPost(PostVO postVO){
        postMapper.insert(postVO);
    }
//    게시글 목록
    public List<PostListDTO> selectAllPost(int limit,int offset){
        return postMapper.selectAllPost(limit,offset);
    }
//    게시글 조회
    public PostDetailDTO selectPostById(Long id){
        return postMapper.selectPostById(id);
    }
//    게시글 수정
    public void updatePost(PostEditDTO postEditDTO){
        postMapper.updatePost(postEditDTO);
    }
//    조회수 증가
    public void updateReadCount(Long id){
        postMapper.updateReadCount(id);
    }
//    좋아요 수 증가
    public void updateLikeCount(Long id,String likeButtonStatus){
        postMapper.updateLikeCount(id,likeButtonStatus);
    }
//    좋아요 수 가져오기
    public Long selectPostLikeCount(Long id){
        return postMapper.selectPostLikeCount(id);
    }
//    게시글 삭제
    public void deletePost(Long id){
        postMapper.deletePostById(id);
    }
//    게시글 총 개수
    public int selectAllPostCount(){
        return postMapper.selectAllPostCount();
    }
}

