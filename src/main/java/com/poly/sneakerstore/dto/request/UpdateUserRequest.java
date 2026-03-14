package com.poly.sneakerstore.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private String avatarUrl;
    private boolean active;
    private LocalDateTime updatedAt;
}
