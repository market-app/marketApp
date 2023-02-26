package br.com.marketapp.controller;

import br.com.marketapp.service.ProductService;
import br.com.marketapp.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductRestController extends ProductService {

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity getProductList() {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
}
