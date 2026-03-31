package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.dto.response.PageResponse;
import com.poly.sneakerstore.mapper.ProductMapper;
import com.poly.sneakerstore.model.Product;
import com.poly.sneakerstore.model.ProductVariant;
import com.poly.sneakerstore.repository.specification.SpecSearchCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class SearchRepository {

    private final ProductMapper productMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public PageResponse<?> getProductJoinedVariant(Pageable pageable, String[] product, String[] variant) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        Join<ProductVariant, Product> join = root.join("productVariants");

        List<Predicate> productPredicate = new ArrayList<>();
        List<Predicate> variantPredicate = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\w+?)([:<>~!])(.*)(\\p{Punct}?)(.*)(\\p{Punct}?)");
        for (String p : product) {
            Matcher matcher = pattern.matcher(p);
            if (matcher.find()) {
                SpecSearchCriteria criteria = new SpecSearchCriteria(
                        matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5)
                );
                Predicate predicate = toProductPredicate(root, builder, criteria);
                productPredicate.add(predicate);
            }
        }

        for (String v : variant) {
            Matcher matcher = pattern.matcher(v);
            if (matcher.find()) {
                SpecSearchCriteria criteria = new SpecSearchCriteria(
                        matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5)
                );
                Predicate predicate = toVariantPredicate(join, builder, criteria);
                variantPredicate.add(predicate);
            }
        }

        Predicate productPredicateArr = builder.and(productPredicate.toArray(new Predicate[0]));
        Predicate variantPredicateArr = builder.and(variantPredicate.toArray(new Predicate[0]));
        Predicate finalPredicate = builder.and(productPredicateArr, variantPredicateArr);

        query.where(finalPredicate);

        List<Product> resultList = entityManager
                .createQuery(query)
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return PageResponse.builder()
                .pageNo(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .items(productMapper.toProductResponse(resultList))
                .build();
    }

    public @Nullable Predicate toProductPredicate(
            @NonNull final Root<Product> root,
            @NonNull final CriteriaBuilder builder,
            @NonNull final SpecSearchCriteria criteria
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

    public @Nullable Predicate toVariantPredicate(
            @NonNull final Join<ProductVariant, Product> root,
            @NonNull final CriteriaBuilder builder,
            @NonNull final SpecSearchCriteria criteria
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
