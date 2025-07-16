package com.project.toyProject.dao;

import com.project.toyProject.domain.dto.comment.CommentResponseDTO;
import com.project.toyProject.domain.vo.CommentVO;
import com.project.toyProject.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentDAO {

    private final CommentMapper commentMapper;

    public void insertComment(CommentVO commentVO) {
        commentMapper.insert(commentVO);
    }
    public List<CommentResponseDTO> selectAllComment(Long postId) {
        return commentMapper.selectAll(postId);
    }
    public void deleteComment(Long id) {
        commentMapper.delete(id);
    }
    public void deleteAllComment(Long postId) {
        commentMapper.deleteAllComment(postId);
    }
}
