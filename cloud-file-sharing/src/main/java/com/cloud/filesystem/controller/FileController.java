package com.cloud.filesystem.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.filesystem.entity.File;
import com.cloud.filesystem.service.FileService;

// FileController.java
@RestController
@RequestMapping("/api/files")
public class FileController {

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/download/{fileId}")
    public ResponseEntity<File> downloadFile(@PathVariable Long fileId, Principal principal) {
        // Only authenticated users with ROLE_USER or ROLE_ADMIN can access this
        // endpoint
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file, Principal principal) {
        // Only users with ROLE_ADMIN can access this endpoint
    }
}