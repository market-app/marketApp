package br.com.marketapp.repository;

import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void ProductRepository_Save_ReturnsSavedProduct() {

        Product product = Product.builder()
                .name("macarrao")
                .price("30")
                .build();

        Product savedProduct = productRepository.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isPositive();
    }

    @Test
    void ProductRepository_FindAll_ReturnsMoreThanOneProduct() {
        Product productOne = Product.builder()
                .name("macarrao")
                .price("30")
                .build();

        Product productTwo = Product.builder()
                .name("carne")
                .price("30")
                .build();

        productRepository.save(productOne);
        productRepository.save(productTwo);

        List<Product> productList = productRepository.findAll();

        assertThat(productList, is(notNullValue()));
        assertThat(productList, Matchers.hasSize(2));
    }

    @Test
    void ProductRepository_FindByID_ReturnsMoreThanOneProduct() {
        Product productOne = Product.builder()
                .name("macarrao")
                .price("30")
                .build();

        productRepository.save(productOne);

        Product byId = productRepository.findById(productOne.getId()).get();

        Assertions.assertThat(byId).isNotNull();
    }

    @Test
    void ProductRepository_UpdateProduct_ReturnsAnotherProduct() {
        Product productOne = Product.builder()
                .name("macarrao")
                .price("30")
                .build();

        productRepository.save(productOne);
        Product beforeUpdate = productRepository.findById(productOne.getId()).get();

        beforeUpdate.setName("carne");
        beforeUpdate.setPrice("10");

        Product afterUpdate = productRepository.save(beforeUpdate);

        Assertions.assertThat(afterUpdate.getName()).isNotNull();
        Assertions.assertThat(afterUpdate.getPrice()).isNotNull();

    }

    @Test
    void ProductRepository_DeleteProduct_ReturnsProductIsEmpty() {
        Product productOne = Product.builder()
                .name("macarrao")
                .price("30")
                .build();

        productRepository.save(productOne);

        productRepository.deleteById(productOne.getId());

        Optional<Product> productOptional = productRepository.findById(productOne.getId());

        assertThat(productOptional.isEmpty(), equalTo(true));
    }

}
