package com.poly.sneakerstore.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "addresses")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String recipientName;
    private String phone;
    private String province;
    private String district;
    private String ward;
    private String streetAddress;
    @Column(name = "is_default")
    private boolean defaultAddress;
    @Column(name = "is_active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
