package com.cloud.filesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.filesystem.entity.File;
import com.cloud.filesystem.repository.FileRepository;

@Service
public class FileService {
     @Autowired
     private FileRepository fileRepository;

     public File saveFile(File file) {
        return fileRepository.save(file);
     }

     
}
