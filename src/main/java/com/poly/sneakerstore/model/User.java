package com.poly.sneakerstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
}
