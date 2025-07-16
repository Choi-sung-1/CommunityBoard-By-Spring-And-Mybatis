package com.project.toyProject.service.post;

import com.project.toyProject.dao.CommentDAO;
import com.project.toyProject.dao.FileDAO;
import com.project.toyProject.dao.PostDAO;
import com.project.toyProject.domain.dto.post.PostDetailDTO;
import com.project.toyProject.domain.dto.post.PostEditDTO;
import com.project.toyProject.domain.dto.post.PostListDTO;
import com.project.toyProject.domain.vo.FileVO;
import com.project.toyProject.domain.vo.PostVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.project.toyProject.controller.PostController.LIMIT;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;
    private final FileDAO fileDAO;
    private final CommentDAO commentDAO;

    @Override
    public void writePost(PostVO postVO, MultipartFile[] files, Long memberId) {
        // 날짜 세팅
        postVO.setPostRegisterDate(LocalDateTime.now());
        postVO.setPostUpdateDate(LocalDateTime.now());

        //기본값 세팅
        postVO.setPostReadCount(0L);
        postVO.setPostLikeCount(0L);
        postVO.setMemberId(memberId);

        postDAO.insertPost(postVO);
        insertPostFile(files, postVO.getId());
    }

    @Override
    public List<PostListDTO> findAllPosts(int page) {
        int offset = (page - 1) * LIMIT;
        return postDAO.selectAllPost(LIMIT, offset);
    }

    @Override
    public PostDetailDTO findPostById(Long id, HttpSession session) {
        //게시글 수정상태가 false경우에만 조회수 증가
        Boolean readCountUpStatus = (Boolean) session.getAttribute("readCountUpStatus");
        if(readCountUpStatus ==null || !readCountUpStatus){
//            취소일 경우 조회수가 증가됨
            postDAO.updateReadCount(id);
        }
        session.removeAttribute("readCountUpStatus");

        PostDetailDTO postDetailDTO = postDAO.selectPostById(id);
        List<FileVO> fileList = fileDAO.selectAll(id);
        postDetailDTO.setFileList(fileList);
        return  postDetailDTO;
    }

    @Override
    public void updatePost(PostEditDTO postEditDTO,Long postId) {
        postEditDTO.setId(postId);
        postEditDTO.setPostUpdateDate(LocalDateTime.now());
        postDAO.updatePost(postEditDTO);
    }

    @Override
    public void updatePostLikeCount(Long postId, String likeButtonStatus) {
        postDAO.updateLikeCount(postId, likeButtonStatus);
    }

    @Override
    public Long findPostLikeCountById(Long postId) {
        return postDAO.selectPostLikeCount(postId);
    }

    @Override
    public void deletePost(Long postId) {
        commentDAO.deleteAllComment(postId);
        postDAO.deletePost(postId);
    }

    @Override
    public int selectAllPostCount() {
        return postDAO.selectAllPostCount();
    }


    //    게시글 작성 이미지 저장
    public void insertPostFile(MultipartFile[] files, Long postId) {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String originalName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String savedName = uuid + "_" + originalName;
                String filePath = "/Users/ChoiSungWon/Documents/Develope/spring/file/post";

                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File targetFile = new File(dir, originalName);
                try {
                    file.transferTo(targetFile);
                } catch (IOException e) {
                    throw new RuntimeException("파일 저장 실패", e);
                }

                FileVO fileVO = new FileVO();
                fileVO.setFileOriginalName(originalName);
                fileVO.setFileSavedName(savedName);
                fileVO.setFilePath(filePath + "/" + originalName);
                fileVO.setReferenceId(postId);
                fileVO.setFileType("POST");
                fileDAO.insertFile(fileVO); // DB 저장
            }
        }
    }

}
