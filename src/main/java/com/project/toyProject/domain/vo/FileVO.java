package com.project.toyProject.domain.vo;

import lombok.Data;

@Data

public class FileVO {
    private Long id;
    private String fileOriginalName;
    private String fileSavedName;
    private String filePath;
    private String fileType;
    private Long referenceId;
}
