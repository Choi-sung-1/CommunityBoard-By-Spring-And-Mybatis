package com.project.toyProject.validation.post;

import com.project.toyProject.domain.dto.post.PostEditDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Service
public class PostEditValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
    }
    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
