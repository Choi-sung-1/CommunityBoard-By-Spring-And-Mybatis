package com.project.toyProject.domain.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberVO {
    private Long id;
    private String memberLoginId;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    @NotBlank(message = "생년월일은 필수 입력값입니다.")
    private String memberBirth;
    private String memberEmail;
}
