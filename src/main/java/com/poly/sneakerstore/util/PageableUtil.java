package com.poly.sneakerstore.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PageableUtil {

    public Pageable createPageable(int pageNo, int pageSize, String sortBy) {
        int page = Math.max(pageNo - 1, 0);

        Sort sort = Sort.unsorted();
        if (StringUtils.hasLength(sortBy)) {
            sort = parseSort(sortBy);
        }

        return PageRequest.of(page, pageSize, sort);
    }

    private Sort parseSort(String sortBy) {
        List<Sort.Order> orders = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\w+?)(:)(asc|desc)");
        Matcher matcher = pattern.matcher(sortBy);
        if (matcher.find()) {
            if (matcher.group(3).equalsIgnoreCase("asc")) {
                orders.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
            } else {
                orders.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
            }
        }

        return Sort.by(orders);
    }

}
