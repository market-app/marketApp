package br.com.marketapp.service.impl;

import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.exceptions.ProductNotFoundException;
import br.com.marketapp.product.repository.ProductRepository;
import br.com.marketapp.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void init() {

        Product product = Product
                .builder()
                .name("macarrao")
                .price("30")
                .build();

        productRepository.save(product);
    }

    @Test
    void deleteProduct() {

        productService.deleteProduct(1L);

        assertThrows(ProductNotFoundException.class, ()-> productService.findProductById(1L));
    }

    @Test
    void updateProduct() {

        Product product = Product
                .builder()
                .name("macarrao artualizado")
                .price("300")
                .build();

        Product updateProduct = productService.updateProduct(product, 1L);

        assertEquals(product.getName(), updateProduct.getName());
        assertEquals(product.getPrice(), updateProduct.getPrice());
    }
}