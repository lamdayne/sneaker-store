package com.poly.sneakerstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private String avatarUrl;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "is_active")
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @OneToMany(mappedBy = "user")
    private List<CartItem> cartItems;
}
