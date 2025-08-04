package com.project.toyProject.validation.member;

import com.project.toyProject.domain.dto.member.MemberLoginDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Service
public class MemberLoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberLoginDTO memberLoginDTO = (MemberLoginDTO) target;

        if (memberLoginDTO.getMemberLoginId() == null || memberLoginDTO.getMemberLoginId().isBlank()) {
            errors.rejectValue("memberLoginId", "required.memberLoginId", "아이디를 입력해주세요.");
        }

        if (memberLoginDTO.getMemberPassword() == null || memberLoginDTO.getMemberPassword().isBlank()) {
            errors.rejectValue("memberPassword", "required.memberPassword", "비밀번호를 입력해주세요.");
        }
    }


    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
