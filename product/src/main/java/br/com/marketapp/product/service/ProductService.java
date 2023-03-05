package br.com.marketapp.product.service;

import br.com.marketapp.product.domain.Product;

public interface ProductService {

    Product saveProduct(Product product);

    Product findProductById(Long id) throws Exception;
}
