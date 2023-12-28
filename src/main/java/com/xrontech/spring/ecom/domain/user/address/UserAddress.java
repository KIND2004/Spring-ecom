package com.xrontech.spring.ecom.domain.user.address;

import com.xrontech.spring.ecom.domain.user.User;
import com.xrontech.spring.ecom.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class UserAddress extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "address_name", length = 20)
    private String addressNumber;
    @Column(name = "street_name", columnDefinition = "TEXT")
    private String streetName;
    @Column(name = "city", length = 50)
    private String city;
    @Column(name = "zip_code", length = 5)
    private String zipCode;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
}
