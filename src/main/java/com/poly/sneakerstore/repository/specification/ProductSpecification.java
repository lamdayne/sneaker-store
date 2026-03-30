package com.poly.sneakerstore.repository.specification;

import com.poly.sneakerstore.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification implements Specification<Product> {

    private SpecSearchCriteria criteria;

    public ProductSpecification(SpecSearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public @Nullable Predicate toPredicate(
            @NonNull final Root<Product> root,
            @NonNull final CriteriaQuery<?> query,
            @NonNull final CriteriaBuilder builder
    ) {
        return switch (criteria.getOperation()) {
            case EQUALITY -> builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION -> builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN -> builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN -> builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LIKE -> builder.like(root.get(criteria.getKey()), String.format("%%%s%%", criteria.getValue()));
            case STARTS_WITH -> builder.like(root.get(criteria.getKey()), String.format("%s%%", criteria.getValue()));
            case ENDS_WITH -> builder.like(root.get(criteria.getKey()), String.format("%%%s", criteria.getValue()));
            case CONTAINS -> builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        };
    }
}
