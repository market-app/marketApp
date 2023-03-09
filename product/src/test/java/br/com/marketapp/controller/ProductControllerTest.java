package br.com.marketapp.controller;

import br.com.marketapp.product.controller.ProductController;
import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.dto.ProductDto;
import br.com.marketapp.product.mapper.ProductMapper;
import br.com.marketapp.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static br.com.marketapp.product.util.ConverterJsonToString.asJsonString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
class ProductControllerTest {

    private final String PRODUCT_PATH = "/products";

    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveProduct() throws Exception {

        ProductDto productDto = new ProductDto();
        productDto.setName("Carne");
        productDto.setPrice("79");

        when(productService
                .saveProduct(productDto.toEntity()))
                .thenReturn(productDto.toEntity());

        mockMvc.perform(
                post(PRODUCT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION));
    }

    @Test
    void findProductById() {
    }

    @Test
    void updateProductById() {
    }

    @Test
    void findAllProducts() {
    }

    @Test
    void deleteProduct() {
    }

}