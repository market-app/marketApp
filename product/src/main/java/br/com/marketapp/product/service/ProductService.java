package br.com.marketapp.product.service;

import br.com.marketapp.product.domain.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    Product findProductById(Long id) throws Exception;

    List<Product> searchAllProducts();

    void deleteProduct(Long id);

    Product updateProduct(Product product, Long id);
}
