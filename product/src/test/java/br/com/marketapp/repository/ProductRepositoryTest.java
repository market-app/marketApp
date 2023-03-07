package br.com.marketapp.repository;

import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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

    @Test
    public void ProductRepository_FindAll_ReturnsMoreThanOneProduct() {
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

        Assertions.assertThat(productList).isNotNull();
        Assertions.assertThat(productList.size()).isEqualTo(2);


    }

    @Test
    public void ProductRepository_FindByID_ReturnsMoreThanOneProduct() {
        Product productOne = Product.builder()
                .name("macarrao")
                .price("30")
                .build();

        productRepository.save(productOne);

        Product byId = productRepository.findById(productOne.getId()).get();

        Assertions.assertThat(byId).isNotNull();


    }

    @Test
    public void ProductRepository_UpdateProduct_ReturnsAnotherProduct() {
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
    public void ProductRepository_DeleteProduct_ReturnsProductIsEmpty() {
        Product productOne = Product.builder()
                .name("macarrao")
                .price("30")
                .build();

        productRepository.save(productOne);

        productRepository.deleteById(productOne.getId());

        Optional<Product> byId = productRepository.findById(productOne.getId());

        Assertions.assertThat(byId).isEmpty();
    }

}
