package com.project.toyProject.service.post;


import com.project.toyProject.dao.PostDAO;
import com.project.toyProject.dao.PostLikeStatusDAO;
import com.project.toyProject.domain.vo.PostLikeStatusVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeStatusServiceImpl implements PostLikeStatusService {
    private final PostDAO postDAO;
    private final PostLikeStatusDAO postLikeStatusDAO;
    @Override
    public void toggleLikeStatus(Long postId, Long memberId) {
        PostLikeStatusVO status = postLikeStatusDAO.selectLikeStatus(postId, memberId);

        if (status == null) {
            // 처음 누르는 경우
           postLikeStatusDAO.insertLikeStatus(new PostLikeStatusVO(null, "onClick", memberId, postId));
           postDAO.updateLikeCount(postId,"onClick");
        } else if (status.getPostLikeStatus().equals("noneClick")) {
            status.setPostLikeStatus("onClick");
            postLikeStatusDAO.updateLikeStatus(status);
            postDAO.updateLikeCount(postId,"onClick");
            log.info(postDAO.selectPostLikeCount(postId).toString());
        } else {
            status.setPostLikeStatus("noneClick");
            postLikeStatusDAO.updateLikeStatus(status);
            postDAO.updateLikeCount(postId,"noneClick");
            log.info(postDAO.selectPostLikeCount(postId).toString());
        }
    }
}
