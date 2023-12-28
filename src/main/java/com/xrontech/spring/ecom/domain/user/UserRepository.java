package com.xrontech.spring.ecom.domain.user;

import com.xrontech.spring.ecom.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByMobile(String mobile);

    Optional<User> findByEmailAndPassword(String email, String password);
}