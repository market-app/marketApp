package br.com.marketapp.repository;

import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void ProductRepository_Save_ReturnsSavedProduct() {

        //Arrange
        Product product = Product.builder()
                .name("macarrao")
                .price("30")
                .build();

        //Act
        Product savedProduct = productRepository.save(product);

        //Assert
        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }
}
