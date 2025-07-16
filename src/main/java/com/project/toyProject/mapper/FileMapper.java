package com.project.toyProject.mapper;

import com.project.toyProject.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
//    파일 저장
    public void insertFile(FileVO file);
//    파일 업데이트
    public void updateFile(FileVO file);
//    해당 포스트의 파일 모두 불러오기
    public List<FileVO> selectFileList(Long postId);
}
