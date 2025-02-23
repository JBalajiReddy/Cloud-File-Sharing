package com.cloud.filesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.filesystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
