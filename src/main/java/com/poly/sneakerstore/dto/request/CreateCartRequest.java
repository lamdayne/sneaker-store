package com.poly.sneakerstore.dto.request;

import lombok.*;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class CreateCartRequest {
    private String userId;
    private Integer daysToLive;
}