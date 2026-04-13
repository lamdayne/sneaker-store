package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/revenue")
public class RevenueController {

    private final StatisticService statisticService;

    @GetMapping("/statistic")
    public ResponseSuccess getStatistic(@RequestParam(required = false,  defaultValue = "7", name = "day") int day) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get statistic revenue",
                statisticService.getRevenueLastDays(day)
        );
    }

}
