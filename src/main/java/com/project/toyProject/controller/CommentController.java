package com.project.toyProject.controller;

import com.project.toyProject.domain.dto.comment.CommentRequestDTO;
import com.project.toyProject.domain.dto.comment.CommentResponseDTO;
import com.project.toyProject.domain.vo.CommentVO;
import com.project.toyProject.service.comment.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment/*")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<CommentResponseDTO> writeComment(@RequestBody CommentRequestDTO commentRequestDTO) {

        return ResponseEntity.ok(commentService.write(commentRequestDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteComment(@PathVariable Long id){
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
