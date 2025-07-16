package com.project.toyProject.dao;

import com.project.toyProject.domain.vo.FileVO;
import com.project.toyProject.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

    public void insertFile(FileVO fileVO) {
        fileMapper.insertFile(fileVO);
    }
    public void updateFile(FileVO fileVO) {
        fileMapper.updateFile(fileVO);
    }
    public List<FileVO> selectAll(Long postId) {
       return fileMapper.selectFileList(postId);
    }
}
