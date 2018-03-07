package com.dyq.demo.service;

import com.dyq.demo.entity.File;
import com.dyq.demo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    public FileRepository fileRepository;

    @Transactional
    @Override
    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    @Transactional
    @Override
    public void removeFile(String id) {
        fileRepository.delete(id);
    }

    @Override
    public File getFileById(String id) {
        return fileRepository.findOne(id);
    }

    @Override
    public Page<File> listFilesByPage(Pageable pageable) {
        return fileRepository.findAll(pageable);
    }

    @Override
    public long FileSize() {
        return fileRepository.count();
    }
}
