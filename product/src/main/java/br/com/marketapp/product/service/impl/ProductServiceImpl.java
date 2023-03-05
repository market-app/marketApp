package br.com.marketapp.product.service.impl;

import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.repository.ProductRepository;
import br.com.marketapp.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Product findProductById(Long id) throws Exception {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Produto ñ existe"));
    }
}
