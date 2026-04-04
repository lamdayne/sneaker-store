package com.poly.sneakerstore.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poly.sneakerstore.model.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String fullName;
    private String email;
    private String phone;
//    private String password;
    private String avatarUrl;
    private Role role;
    @JsonProperty("isActive")
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
