package com.poly.sneakerstore.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponse {
    private String id;
    private String name;
    private String imageUrl;
    private Integer displayOrder;
    private boolean active;
}
