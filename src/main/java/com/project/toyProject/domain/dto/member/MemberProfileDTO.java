package com.project.toyProject.domain.dto.member;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberProfileDTO {

    private Long id;
    private String profileOneLineBio;

    private String fileType;
    private String fileOriginalName;
    private Long referenceId;

    private String memberName;
    private String memberLoginId;
    private String memberBirth;
    private String memberEmail;
    private String memberPhone;
}
