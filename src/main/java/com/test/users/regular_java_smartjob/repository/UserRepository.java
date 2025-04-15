package com.test.users.regular_java_smartjob.repository;

import com.test.users.regular_java_smartjob.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  Boolean existsByEmail(String email);
  User findByEmail(String email);
}
