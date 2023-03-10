package br.com.marketapp.service;

import br.com.marketapp.exceptions.ProductNotFoundException;
import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.repository.ProductRepository;
import br.com.marketapp.product.service.impl.ProductServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void saveProduct() {
        Product product = Product.builder()
                .name("macarrao")
                .price("30")
                .build();

        when(productRepository
                .save(Mockito.any(Product.class)))
                .thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertThat(savedProduct,is(notNullValue()));
        assertThat(savedProduct, Matchers.is(equalTo(product)));
    }

    @Test
    void findProductById() {
        Product product = Product.builder()
                .id(1L)
                .name("macarrao")
                .price("30")
                .build();

        when(productRepository
                .findById(1L))
                .thenReturn(Optional.of(product));

        Product productById = productService.findProductById(1L);

        assertThat(productById,is(notNullValue()));
        assertThat(productById, Matchers.is(equalTo(product)));
    }

    @Test
    void findAllProducts() {

        Product product = Product.builder()
                .id(1L)
                .name("macarrao")
                .price("30")
                .build();

        when(productRepository
                .findAll())
                .thenReturn(Collections.singletonList(product));

        List<Product> allProducts = productService.findAllProducts();

        assertThat(allProducts, is(not(empty())));
        assertThat(allProducts, hasSize(1));
    }

    @Test
    void deleteProductNotFound() {

        assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(()-> productService.deleteProduct(1L));
    }

    @Test
    void updateProductNotFound() {
        Product product = Product.builder()
                .id(1L)
                .name("macarrao")
                .price("30")
                .build();

        assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(()->  productService.updateProduct(product,1L));
    }
}
