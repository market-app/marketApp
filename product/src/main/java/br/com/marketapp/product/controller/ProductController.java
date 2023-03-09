package br.com.marketapp.product.controller;

import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.dto.ProductDto;
import br.com.marketapp.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductDto productDto) {
        Product product = productService.saveProduct(productDto.toEntity());
        return ResponseEntity
                .created(linkTo(ProductController.class)
                        .slash(product.getId())
                        .withSelfRel()
                        .toUri())
                .body(product);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> updateProductById(@PathVariable("id") Long id,
                                                  @RequestBody @Valid ProductDto productDto) {
        productService.updateProduct(productDto.toEntity(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id) throws Exception {
        Product productById = productService.findProductById(id);
        return ResponseEntity.ok(productById.toDto());
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> findAllProducts() {

        List<ProductDto> productDtoList = productService
                .findAllProducts()
                .stream()
                .map(Product::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDtoList);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
