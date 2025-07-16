package com.project.toyProject.mapper;

import com.project.toyProject.domain.dto.comment.CommentResponseDTO;
import com.project.toyProject.domain.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
//    댓글 작성
    public void insert(CommentVO commentVO);
//    댓글 목록
    public List<CommentResponseDTO> selectAll(Long postId);
//    댓글 삭제
    public void delete(Long id);
//    게시글 삭제시 해당 댓글 전체 삭제
    public void deleteAllComment(Long postId);
}
