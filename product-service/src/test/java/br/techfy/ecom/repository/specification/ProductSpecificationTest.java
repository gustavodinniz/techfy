package br.techfy.ecom.repository.specification;

import br.techfy.ecom.model.Product;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductSpecificationTest {

    @Mock
    private Root<Product> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private Path<String> stringPath;

    @Mock
    private Path<BigDecimal> pricePath;

    @Mock
    private Join<Object, Object> categoryJoin;

    private Predicate predicate;

    private static final String NAME = "test";

    @Test
    void shouldNotInstantiateProductSpecification() {
        assertThrows(IllegalStateException.class, ProductSpecification::new);
    }

    @Test
    void shouldReturnSpecificationForName() {
        givenRootGetReturns();
        givenCriteriaBuilderLowerReturns();
        givenCriteriaBuilderLikeReturns();
        whenHasName();
        thenExpectReturnsNotNull();
        thenExpectCriteriaBuilderLikeCalledOnce();
    }

    @Test
    void shouldReturnNullSpecificationForNullName() {
        whenHasNameWithNameNull();
        thenExpectReturnsNull();
    }


    // GIVEN

    private void givenCriteriaBuilderLikeReturns() {
        doReturn(mock(Predicate.class)).when(criteriaBuilder).like(stringPath, "%test%");
    }

    private void givenCriteriaBuilderLowerReturns() {
        doReturn(stringPath).when(criteriaBuilder).lower(stringPath);
    }

    private void givenRootGetReturns() {
        doReturn(stringPath).when(root).get(anyString());
    }

    // WHEN

    private void whenHasName() {
        predicate = ProductSpecification.hasName(NAME).toPredicate(root, query, criteriaBuilder);
    }

    private void whenHasNameWithNameNull() {
        predicate = ProductSpecification.hasName(null).toPredicate(root, query, criteriaBuilder);
    }

    // THEN

    private void thenExpectCriteriaBuilderLikeCalledOnce() {
        verify(criteriaBuilder).like(stringPath, "%test%");
    }

    private void thenExpectReturnsNotNull() {
        assertNotNull(predicate);
    }


    private void thenExpectReturnsNull() {
        assertNull(predicate);
    }

}