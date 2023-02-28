package br.com.marketapp.service;

import br.com.marketapp.domain.Product;
import br.com.marketapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.getReferenceById(id);
    }
}
