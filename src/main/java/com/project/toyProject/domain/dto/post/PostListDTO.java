package com.project.toyProject.domain.dto.post;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
public class PostListDTO {
    private Long id;
    private String postTitle;
    private String memberLoginId;
    private LocalDateTime postRegisterDate;
    private LocalDateTime postUpdateDate;
    private Long postReadCount;


    public String getFormattedPostRegisterDate() {
        return postRegisterDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
    }

    public String getFormattedPostUpdateDate() {
        return postUpdateDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
    }
}