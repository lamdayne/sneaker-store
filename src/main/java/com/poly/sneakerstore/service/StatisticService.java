package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.response.RevenueResponse;

public interface StatisticService {
    RevenueResponse getRevenueLastDays(int day);
}
