package com.poly.sneakerstore.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenueResponse {
//    private Double totalPrice;
    private Long totalOrder;
//    private String bestProduct;
    private Long totalUser;
    private List<Object[]> bestSellingProduct;
    private List<ChartDataPoint> totalUserRegister;
    private List<ChartDataPoint> totalNewOrders;
}
