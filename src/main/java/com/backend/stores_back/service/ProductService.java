package com.backend.stores_back.service;

import com.backend.stores_back.exception.ResourceNotFoundException;
import com.backend.stores_back.model.Product;
import com.backend.stores_back.model.Store;
import com.backend.stores_back.repository.ProductRepository;
import com.backend.stores_back.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final StoreRepository storeRepository;

    public ProductService(ProductRepository productRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public List<Product> getAllProducts(int store_id) {
        Optional<Store> store = storeRepository.findById(store_id);

        return store.get().getProducts();
    }

    public Product getProductById(int product_id) {

        return productRepository.findById(product_id).get();
    }

    public Product addProduct(Product newProduct, int store_id) {
        Product product = storeRepository.findById(store_id).map(store -> {
            newProduct.setStore(store);
            return productRepository.save(newProduct);
        }).orElseThrow(() -> new ResourceNotFoundException("Store with " + store_id + " not found"));

        return product;
    }

    public Product updateProduct(Product newProduct, int product_id) {
        Product product = productRepository.findById(product_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with " + product_id + " not found"));

        product.setName(newProduct.getName());
        product.setQuantity(newProduct.getQuantity());
        product.setPrice(newProduct.getPrice());

        return productRepository.save(product);
    }

    public void deleteProduct(int product_id, int store_id) {
        Optional<Product> product = productRepository.findById(product_id);

        Optional<Store> store = storeRepository.findById(store_id);

        store.get().getProducts().remove(product.get());

        productRepository.deleteById(product_id);
    }
}
