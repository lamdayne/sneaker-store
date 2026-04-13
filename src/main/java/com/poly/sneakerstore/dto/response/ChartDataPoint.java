package com.poly.sneakerstore.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChartDataPoint {
    private String date;
    private Long value;
}
