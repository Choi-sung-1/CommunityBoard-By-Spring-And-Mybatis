package com.project.toyProject.service.post;

import com.project.toyProject.domain.dto.post.PostDetailDTO;
import com.project.toyProject.domain.dto.post.PostEditDTO;
import com.project.toyProject.domain.dto.post.PostListDTO;
import com.project.toyProject.domain.vo.PostVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
//    게시글 작성
    public void writePost(PostVO postVO, MultipartFile[] files,Long memberId);
//    게시글 목록
    public List<PostListDTO> findAllPosts(int page);
//    게시글 조회
    public PostDetailDTO findPostById(Long id, HttpSession session);
//    게시글 수정
    public void updatePost(PostEditDTO postEditDTO,Long postId);
//    좋아요 수 증가
    public void updatePostLikeCount(Long postId,String likeButtonStatus);
//    좋아요 수 가져오기
    public Long findPostLikeCountById(Long postId);
//    게시글 삭제
    public void deletePost(Long postId);
//    게시글 총 개수
    public int selectAllPostCount();
}
