package com.project.toyProject.validation.member;

import com.project.toyProject.domain.vo.MemberVO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service

public class MemberJoinValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberVO member = (MemberVO) target;

        //memberId: 길이 5~15자, 영문/숫자만 가능
        if (member.getMemberLoginId() == null || member.getMemberLoginId().isBlank()) {
            errors.rejectValue("memberLoginId", "memberVO.memberLoginId.notBlank");
        } else if (member.getMemberLoginId().length() < 5 || member.getMemberLoginId().length() > 15) {
            errors.rejectValue("memberLoginId", "memberVO.memberLoginId.size");
        } else if (!member.getMemberLoginId().matches("^[a-zA-Z0-9]+$")) {
            errors.rejectValue("memberLoginId", "memberVO.memberLoginId.pattern");
        }

        //memberPassword: 길이 8~20자, 영문/숫자/특수문자 포함
        if (member.getMemberPassword() == null || member.getMemberPassword().isBlank()) {
            errors.rejectValue("memberPassword", "memberVO.memberPassword.notBlank");
        }else if (!member.getMemberPassword().matches("^(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+{};:,<.>])(?=.*[A-Za-z])[A-Za-z0-9!@#$%^&*()\\-_=+{};:,<.>]{8,20}$")) {
            errors.rejectValue("memberPassword", "memberVO.memberPassword.pattern");
        }

        //memberName: 길이 2~20자
        if (member.getMemberName() == null || member.getMemberName().isBlank()) {
            errors.rejectValue("memberName", "memberVO.memberName.notBlank");
        }else if (member.getMemberName().length() < 2 || member.getMemberName().length() > 20) {
            errors.rejectValue("memberName", "memberVO.memberName.size");
        }
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
