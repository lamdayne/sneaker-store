package com.poly.sneakerstore.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> {
    private int pageNo;
    private int pageSize;
    private int totalPage;
    private int totalElements;
    private T items;
}
