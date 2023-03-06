package br.com.marketapp.product.service.impl;

import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.exceptions.ProductNotFoundException;
import br.com.marketapp.product.repository.ProductRepository;
import br.com.marketapp.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Product> searchAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Product updateProduct(Product product, Long id) {
        Optional<Product> byId = productRepository.findById(id);

        if (byId.isPresent()) {
            byId.get().setName(product.getName());
            byId.get().setPrice(product.getPrice());
            return productRepository.save(byId.get());
        }
        throw new ProductNotFoundException("Produto nao existe");
    }
}