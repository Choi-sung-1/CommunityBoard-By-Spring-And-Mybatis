package com.project.toyProject.service.comment;

import com.project.toyProject.domain.dto.comment.CommentRequestDTO;
import com.project.toyProject.domain.dto.comment.CommentResponseDTO;
import com.project.toyProject.domain.vo.CommentVO;

import java.util.List;

public interface CommentService {
//    댓글 작성
    public CommentResponseDTO write(CommentRequestDTO commentRequestDTO);
//    댓글 목록
    public List<CommentResponseDTO> findAll(Long postId);
//    댓글 삭제
    public void delete(Long id);
}
