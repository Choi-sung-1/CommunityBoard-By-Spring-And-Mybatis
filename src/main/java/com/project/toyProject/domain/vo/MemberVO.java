package com.project.toyProject.domain.vo;

import lombok.Data;

@Data
public class MemberVO {
    private Long id;
    private String memberLoginId;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberBirth;
    private String memberEmail;
}
