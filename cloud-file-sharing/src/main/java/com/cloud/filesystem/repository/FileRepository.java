package com.cloud.filesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloud.filesystem.entity.File;
import com.cloud.filesystem.entity.User;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    // Query to find files uploaded by specific user
    @Query("Select f from File f where f.uploader = :uploader")
    List<File> findByUploader(@Param("uploader") String uploader);

    // Query to find files shared with specific user
    @Query("SELECT f FROM File f WHERE :user IN f.sharedUsers")
    List<File> findBySharedUsers(@Param("user") User user);
}
