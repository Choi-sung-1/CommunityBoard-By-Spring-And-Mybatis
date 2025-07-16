package com.project.toyProject.service.comment;

import com.project.toyProject.dao.CommentDAO;
import com.project.toyProject.dao.MemberDAO;
import com.project.toyProject.domain.dto.comment.CommentRequestDTO;
import com.project.toyProject.domain.dto.comment.CommentResponseDTO;
import com.project.toyProject.domain.vo.CommentVO;
import com.project.toyProject.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDAO commentDAO;
    private final MemberDAO memberDAO;
//    댓글 작성
    @Override
    public CommentResponseDTO write(CommentRequestDTO commentRequestDTO) {
        Long postId = commentRequestDTO.getPostId();
        Long memberId = commentRequestDTO.getMemberId();
        Optional<MemberVO> memberVO = memberDAO.selectLoginMember(memberId);

        String commentContent = commentRequestDTO.getCommentContent();


        CommentVO commentVO = new CommentVO();
        commentVO.setCommentContent(commentContent);
        commentVO.setPostId(postId);
        commentVO.setMemberId(memberId);
        commentVO.setCommentRegisterDate(LocalDateTime.now());
        commentVO.setCommentWriterName(memberVO.get().getMemberLoginId());
        commentDAO.insertComment(commentVO);
        log.info(commentVO.toString());

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setId(commentVO.getId());
        commentResponseDTO.setCommentContent(commentVO.getCommentContent());
        commentResponseDTO.setPostId(postId);
        commentResponseDTO.setCommentWriterName(commentVO.getCommentWriterName());
        commentResponseDTO.setCommentRegisterDate(LocalDateTime.now());
        return commentResponseDTO;
    }

    @Override
    public List<CommentResponseDTO> findAll(Long postId) {
        return commentDAO.selectAllComment(postId);
    }

    @Override
    public void delete(Long id) {
        commentDAO.deleteComment(id);
    }
}
