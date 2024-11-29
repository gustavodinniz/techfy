package br.techfy.ecom.repository.specification;

import br.techfy.ecom.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    private static final String NAME = "name";
    private static final String CATEGORY = "category";
    private static final String PRICE = "price";

    private ProductSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get(NAME)), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
                category == null ? null : criteriaBuilder.equal(root.join(CATEGORY).get(NAME), category);
    }

    public static Specification<Product> hasPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.get(PRICE), minPrice, maxPrice);
            } else if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(PRICE), minPrice);
            } else if (maxPrice != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get(PRICE), maxPrice);
            }
            return null;
        };
    }
}
