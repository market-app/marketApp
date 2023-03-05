package br.com.marketapp.service;

import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.repository.ProductRepository;
import br.com.marketapp.product.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void productService_CreateProduct_ReturnsProduct() {
        Product product = Product.builder()
                .name("macarrao")
                .price("30")
                .build();

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        Assertions.assertThat(savedProduct).isNotNull();
    }
}
