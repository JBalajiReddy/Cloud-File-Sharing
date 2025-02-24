package com.cloud.filesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cloud.filesystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Query to find user by username
    @Query("Select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

    //Query to find user by email
    @Query("Select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);

}
