package com.poly.sneakerstore.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class CartResponse {
    private String id;
    private String userId;
    private String userFullName;
    private LocalDateTime expiresAt;
}