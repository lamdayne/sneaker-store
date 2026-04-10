package com.poly.sneakerstore.repository.specification;

import lombok.Getter;

import static com.poly.sneakerstore.repository.specification.SearchOperation.*;

@Getter
public class SpecSearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
    private boolean orPredicate;

    public SpecSearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SpecSearchCriteria(String key, SearchOperation operation, Object value, String orPredicate) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.orPredicate = orPredicate != null && orPredicate.equals(OR_PREDICATE_FLAG);
    }

    public SpecSearchCriteria(String key, String operation, String value, String prefix, String suffix) {
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
        this.key = key;
        this.operation = oper;
        this.value = value;
    }
}
