package com.project.toyProject.domain.dto.post;

import lombok.Data;

@Data
public class PostSearchDTO {
    private String type;       // 검색 타입 (제목, 작성자, 내용 등)
    private String keyword;    // 검색어
    private int page;          // 현재 페이지
    private int startRow;      // DB 조회 시작 행 번호 (OFFSET)
    private int pageSize;      // 한 페이지당 게시글 수
}
