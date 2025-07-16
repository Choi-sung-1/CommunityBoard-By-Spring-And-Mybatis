package com.project.toyProject.domain.dto.post;

import com.project.toyProject.domain.vo.FileVO;
import lombok.Data;

import java.util.List;

@Data
public class PostDetailDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    private Long postLikeCount;

    private String fileOriginalName;
    private String memberLoginId;
    private List<FileVO> fileList;
}
