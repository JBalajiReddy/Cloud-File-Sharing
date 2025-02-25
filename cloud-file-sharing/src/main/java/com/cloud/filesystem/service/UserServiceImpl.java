package com.cloud.filesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloud.filesystem.entity.User;
import com.cloud.filesystem.repository.RoleRepository;
import com.cloud.filesystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User registerUser(User user) {
        // Check if user already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        // Set default role to USER
        user.setRole(roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> new RuntimeException("User Role not found")));

        // Hash the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User createAdminUser(User user) {
        // Set admin role
        user.setRole(roleRepository.findByName(ERole.ADMIN)
                .orElseThrow(() -> new RuntimeException("Admin Role not found")));

        // Hash the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}