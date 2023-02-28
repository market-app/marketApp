package br.com.marketapp.controller;

import br.com.marketapp.domain.Product;
import br.com.marketapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductRestController extends ProductService {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product> getProductList() {
       return productService.getProductList();
    }
}
