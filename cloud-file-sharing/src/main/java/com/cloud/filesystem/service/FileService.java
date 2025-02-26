package com.cloud.filesystem.service;

import java.io.FilePermission;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.cloud.filesystem.dto.FileDTO;
import com.cloud.filesystem.entity.File;
import com.cloud.filesystem.repository.FileRepository;

@Service
public class FileService extends BaseService<File> {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserService userService;

    @Override
    protected JpaRepository<File, Long> getRepository() {
        return fileRepository;
    }

    @Override
    protected String getEntityName() {
        return "File";
    }

    public File uploadFile(File file2) {
        File file = new File();
        file.setName(file2.getName());
        file.setContentType(file2.getContentType());
        file.setSize(file2.getSize());
        file.setOwner(userService.getCurrentUser());
        file.setPermissions(FilePermission.PRIVATE);
        return save(file);
    }

    @PreAuthorize("hasRole('USER') or hasPermission(#id, 'FILE')")
    public File downloadFile(Long id) {
        File file = findById(id);
        if (file.getOwner().getId().equals(userService.getCurrentUser().getId())) {
            return file;
        }
        if (file.getPermissions().equals(FilePermission.PUBLIC)) {
            return file;
        }
        if (file.getSharedWith().contains(userService.getCurrentUser())) {
            return file;
        }
        throw new AccessDeniedException("You don't have permission to access this file");
    }

    public void shareFile(Long fileId, List<User> users) {
        File file = findById(fileId);
        if (!file.getOwner().getId().equals(userService.getCurrentUser().getId())) {
            throw new AccessDeniedException("You don't own this file");
        }
        file.setSharedWith(users);
        save(file);
    }

    @PreAuthorize("hasPermission(#id, 'FILE')")
    public void deleteFile(Long id) {
        File file = findById(id);
        if (!file.getOwner().getId().equals(userService.getCurrentUser().getId())) {
            throw new AccessDeniedException("You don't own this file");
        }
        fileRepository.delete(file);
    }
}