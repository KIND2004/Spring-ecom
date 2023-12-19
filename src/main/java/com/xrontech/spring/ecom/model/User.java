package com.xrontech.spring.ecom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name", length = 50)
    private String firstName;
    @Column(name = "last_name", length = 50)
    private String lastName;
    @Column(name = "email", unique = true, length = 100)
    private String email;
    @Column(name = "mobile", unique = true, length = 10)
    private String mobile;
    @Column(name = "password", length = 20)
    private String password;
    @Column(name = "verification_code", length = 10)
    private String verificationCode;
    @Column(name = "email_verified_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp emailVerifiedAt;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType = UserType.BUYER;
    @Column(name = "profile_image_path", columnDefinition = "TEXT")
    private String profileImagePath;
    @Column(name = "status")
    private boolean status;
}