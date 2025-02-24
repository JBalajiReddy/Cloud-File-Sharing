package com.cloud.filesystem.entity;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileKey;
    
    @Column(nullable = false, unique = true)
    private String fileName;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private Date uploadDate;

    @ManyToOne
    @JoinColumn(name = "uploaded_by", nullable = false)
    private User uploadedBy;

    // many files can be uploaded by one user, but each file is uploaded by only one
    // user
    @ManyToMany
    @JoinTable(name = "file_share", joinColumns = @JoinColumn(name = "file_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> sharedWith;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "user_shared_files", joinColumns = @JoinColumn(name = "file_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Share> shares;

    // @ManyToMany
    // A file can be shared with multiple users.
    // A user can have multiple files shared with them.
    // @JoinTable
    // Defines a junction table (file_share) that links files and users.
    // joinColumns = @JoinColumn(name = "file_id")
    // The file_id column in file_share references the File entity.
    // inverseJoinColumns = @JoinColumn(name = "user_id")
    // The user_id column in file_share references the User entity.
}