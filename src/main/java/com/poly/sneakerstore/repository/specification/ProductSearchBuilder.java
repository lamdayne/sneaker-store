package com.poly.sneakerstore.repository.specification;

import com.poly.sneakerstore.model.Product;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static com.poly.sneakerstore.repository.specification.SearchOperation.ZERO_OR_MORE_REGEX;

public class ProductSearchBuilder {

    private List<SpecSearchCriteria> param;

    public ProductSearchBuilder() {
        this.param = new ArrayList<>();
    }

    public ProductSearchBuilder with(String key, String operation, String value, String prefix, String suffix) {
        return with(key, operation, value, prefix, suffix, null);
    }

    public ProductSearchBuilder with(
            String key, String operation, String value, String prefix, String suffix, String orPredicate
    ) {
        SearchOperation oper = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (oper == SearchOperation.EQUALITY) {
            boolean startWithAsterisk = prefix != null && prefix.contains(ZERO_OR_MORE_REGEX);
            boolean endWithAsterisk = suffix != null && suffix.contains(ZERO_OR_MORE_REGEX);

            if (startWithAsterisk && endWithAsterisk) {
                oper = SearchOperation.CONTAINS;
            } else if (startWithAsterisk) {
                oper = SearchOperation.ENDS_WITH;
            } else if (endWithAsterisk) {
                oper = SearchOperation.STARTS_WITH;
            }
        }
        param.add(new SpecSearchCriteria(key, oper, value, orPredicate));
        return this;
    }

    public Specification<Product> build() {
        if (param.isEmpty()) return null;

        Specification<Product> specification = new ProductSpecification(param.get(0));

        for (int i = 1; i < param.size(); i++) {
            specification = param.get(i).isOrPredicate()
                    ? Specification.where(specification).or(new ProductSpecification(param.get(i)))
                    : Specification.where(specification).and(new ProductSpecification(param.get(i)));
        }

        return specification;
    }
}
