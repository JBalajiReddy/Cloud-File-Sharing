package com.cloud.filesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.filesystem.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
