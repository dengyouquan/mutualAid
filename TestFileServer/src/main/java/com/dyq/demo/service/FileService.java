package com.dyq.demo.service;

import com.dyq.demo.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public  interface FileService {
    File saveFile(File file);
    void removeFile(String id);
    File getFileById(String id);
    Page<File> listFilesByPage(Pageable pageable);
    long FileSize();
}
