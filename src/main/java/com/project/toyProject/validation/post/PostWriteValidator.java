package com.project.toyProject.validation.post;

import com.project.toyProject.domain.vo.PostVO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PostWriteValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostVO postVO = (PostVO) target;

        if (postVO.getPostTitle() ==null || postVO.getPostTitle().isBlank()) {
            errors.rejectValue("postTitle", null, "제목을 입력해주세요.");
        } else if (postVO.getPostTitle().length() > 20 || postVO.getPostTitle().length()<2) {
            errors.rejectValue("postTitle",null,"제목은 2자 이상 20자 이하여야 합니다.");
        }

        if (postVO.getPostContent() ==null || postVO.getPostContent().isBlank()) {
            errors.rejectValue("postContent", null, "내용을 입력해주세요.");
        } else if (postVO.getPostContent().length() <2 || postVO.getPostContent().length()>50) {
            errors.rejectValue("postContent",null,"내용은 2글자 이상 50자 이하여야 합니다.");
        }
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
