package com.project.toyProject.domain.dto.comment;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
    private Long id;
    private String commentContent;
    private String commentWriterName;
    private LocalDateTime commentRegisterDate;
    private Long postId;

    public String getCommentRegisterDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();

        Duration duration = Duration.between(commentRegisterDate, currentDateTime);

        long second = duration.getSeconds();

        if (second < 60) {
            return second + "초 전";
        }else if (second < 3600) {
            return (second/60)+"분 전";
        }else if (second < 86400) {
            return (second/3600)+"시간 전";
        }else {
            return (second/86400)+"일 전";
        }
    }
}
