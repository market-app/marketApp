package br.com.marketapp.controller;

import br.com.marketapp.product.controller.ProductController;
import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.dto.ProductDto;
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

import static br.com.marketapp.util.ConverterJsonToString.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        productDto.setPrice("80");

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
    void findProductById() throws Exception {

        Product product = Product
                .builder()
                .id(1L)
                .name("macarrao")
                .price("30")
                .build();

        when(productService
                .findProductById(1L))
                .thenReturn(product);

        mockMvc.perform(
                        get(PRODUCT_PATH + "/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateProductById() throws Exception {

        Product product = Product
                .builder()
                .id(1L)
                .name("macarrao")
                .price("30")
                .build();

        mockMvc.perform(
                        put(PRODUCT_PATH+"/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product.toDto())))
                .andExpect(status().isOk());
    }

    @Test
    void findAllProducts() throws Exception {
        mockMvc.perform(
                        get(PRODUCT_PATH)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProduct() throws Exception {
        mockMvc.perform(
                        delete(PRODUCT_PATH+"/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

}