package com.codealpha.resoft_be.domain.user.repository;

import com.codealpha.resoft_be.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
