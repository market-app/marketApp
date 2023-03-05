package br.com.marketapp.product.controller;

import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.dto.ProductDto;
import br.com.marketapp.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveProduct(@RequestBody @Valid ProductDto productDto) {
        Product product = productService.saveProduct(productDto.toEntity());
        return ResponseEntity
                .created(linkTo(ProductController.class)
                        .slash(product.getId())
                        .withSelfRel()
                        .toUri())
                .build();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id) throws Exception {
        Product productById = productService.findProductById(id);
        return ResponseEntity.ok(productById.toDto());
    }

}
