package com.xrontech.spring.ecom.domain.user.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    Optional<UserAddress> findByUserId(Long id);
}