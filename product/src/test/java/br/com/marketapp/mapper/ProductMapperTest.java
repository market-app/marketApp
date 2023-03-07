package br.com.marketapp.mapper;

import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.dto.ProductDto;
import br.com.marketapp.product.mapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void toDto() {
        Product product =  new Product();
        product.setId(1L);

        ProductDto productDto = ProductMapper.INSTANCE.toDto(product);

        Assertions.assertEquals(1L, productDto.getId());
    }

    @Test
    void toEntity() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);

        Product product = ProductMapper.INSTANCE.toEntity(productDto);

        Assertions.assertEquals(1L, product.getId());

    }
}