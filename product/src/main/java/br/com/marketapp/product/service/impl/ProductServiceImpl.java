package br.com.marketapp.product.service.impl;

import br.com.marketapp.exceptions.ProductNotFoundException;
import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.repository.ProductRepository;
import br.com.marketapp.product.service.ProductService;
import br.com.marketapp.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(Translator.toLocale("product.not.found.exception")));
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        Long productById = findProductById(id).getId();
        productRepository.deleteById(productById);
    }

    @Override
    @Transactional
    public Product updateProduct(Product product, Long id) {
        Product productById = findProductById(id);

        productById.setName(product.getName());
        productById.setPrice(product.getPrice());

        return productRepository.save(productById);
    }
}