package com.project.toyProject.validation.post;

import com.project.toyProject.domain.dto.post.PostDetailDTO;
import com.project.toyProject.domain.vo.PostVO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PostEditAndWriteValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof PostVO postVO) {
            if (postVO.getPostTitle() == null || postVO.getPostTitle().isBlank()) {
                errors.rejectValue("postTitle", null, "제목을 입력해주세요.");
            } else if (postVO.getPostTitle().length() > 20 || postVO.getPostTitle().length() < 2) {
                errors.rejectValue("postTitle", null, "제목은 2자 이상 20자 이하여야 합니다.");
            }

            if (postVO.getPostContent() == null || postVO.getPostContent().isBlank()) {
                errors.rejectValue("postContent", null, "내용을 입력해주세요.");
            } else if (postVO.getPostContent().length() < 2 || postVO.getPostContent().length() > 50) {
                errors.rejectValue("postContent", null, "내용은 2글자 이상 50자 이하여야 합니다.");
            }
        }else if (target instanceof PostDetailDTO postDetailDTO) {
            if (postDetailDTO.getPostTitle() == null || postDetailDTO.getPostTitle().isBlank()) {
                errors.rejectValue("postTitle", null, "제목을 입력해주세요.");
            } else if (postDetailDTO.getPostTitle().length() > 20 || postDetailDTO.getPostTitle().length() < 2)
                errors.rejectValue("postTitle", null, "제목은 2자 이상 20자 이하여야 합니다.");

            if (postDetailDTO.getPostContent() == null || postDetailDTO.getPostContent().isBlank()) {
                errors.rejectValue("postContent", null, "내용을 입력해주세요.");
            } else if (postDetailDTO.getPostContent().length() < 2 || postDetailDTO.getPostContent().length() > 50) {
                errors.rejectValue("postContent", null, "내용은 2글자 이상 50자 이하여야 합니다.");
            }
        }
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
