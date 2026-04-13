package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.response.RevenueResponse;
import com.poly.sneakerstore.repository.OrderItemRepository;
import com.poly.sneakerstore.repository.OrderRepository;
import com.poly.sneakerstore.repository.ProductRepository;
import com.poly.sneakerstore.repository.UserRepository;
import com.poly.sneakerstore.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public RevenueResponse getRevenueLastDays(int day) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.minusDays(day - 1);
        Long totalUser = userRepository.count();
        Long totalOrder = orderRepository.count();
        List<Object[]> bestSellingProduct = productRepository.findBestSellingProducts(PageRequest.of(0, 10));
        return RevenueResponse.builder()
                .totalUser(totalUser)
                .totalOrder(totalOrder)
                .bestSellingProduct(bestSellingProduct)
                .build();
    }
}
