package ru.cinimex.deveducate.rest.filter;

import org.springframework.data.jpa.domain.Specification;
import ru.cinimex.deveducate.dal.entity.OrderEntity;

import javax.persistence.criteria.Predicate;

public final class OrderSpecification {

    public static Specification<OrderEntity> isOrderTotal(final Integer orderTotal) {

        return ((root, query, builder) -> {
            Predicate predicate = builder.equal(root.get("orderTotal"), orderTotal);
            return predicate;
        });
    }
}
