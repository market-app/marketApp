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
    public ResponseEntity<Void> saveProduct(@RequestBody @Valid ProductDto productDto) {
        Product product = productService.saveProduct(productDto.toEntity());
        return ResponseEntity
                .created(linkTo(ProductController.class)
                        .slash(product.getId())
                        .withSelfRel()
                        .toUri())
                .build();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> updateProductById (@PathVariable("id") Long id, @RequestBody @Valid ProductDto productDto) throws Exception {
        Product productById = productService.findProductById(id);

        if (productById != null) {
            productService.updateProduct(productDto.toEntity(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id) throws Exception {
        Product productById = productService.findProductById(id);
        return ResponseEntity.ok(productById.toDto());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> SearchAllProducts() {

        List<Product> productList = productService.searchAllProducts();

        List<ProductDto> productDtoList = productList.stream().map(Product::toDto).collect(Collectors.toList());

        return ResponseEntity.ok(productDtoList);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws Exception {

        Product productById = productService.findProductById(id);

        if (productById != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
