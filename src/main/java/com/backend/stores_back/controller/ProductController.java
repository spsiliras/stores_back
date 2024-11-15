package com.backend.stores_back.controller;

import com.backend.stores_back.exception.ResourceNotFoundException;
import com.backend.stores_back.model.Employee;
import com.backend.stores_back.model.Product;
import com.backend.stores_back.model.Store;
import com.backend.stores_back.repository.ProductRepository;
import com.backend.stores_back.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/{store_id}")
    public List<Product> getProducts(@PathVariable int store_id) {
        Optional<Store> store = storeRepository.findById(store_id);

        return store.get().getProducts();
    }

    @PostMapping("/add/{store_id}")
    public Product addProduct(@RequestBody Product newProduct, @PathVariable int store_id) {
        Product product = storeRepository.findById(store_id).map(store -> {
            newProduct.setStore(store);
            return productRepository.save(newProduct);
        }).orElseThrow(() -> new ResourceNotFoundException("Store with " + store_id + " not found"));

        return product;
    }

    @PostMapping("/edit/{product_id}")
    public Product editProduct(@RequestBody Product newProduct, @PathVariable int product_id) {
        Product product = productRepository.findById(product_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with " + product_id + " not found"));

        product.setName(newProduct.getName());
        product.setQuantity(newProduct.getQuantity());
        product.setPrice(newProduct.getPrice());

        return productRepository.save(product);
    }

    @DeleteMapping("/delete/{store_id}/{product_id}")
    public void deleteProduct(@PathVariable int product_id, @PathVariable int store_id) {
        Optional<Product> product = productRepository.findById(product_id);

        Optional<Store> store = storeRepository.findById(store_id);

        store.get().getProducts().remove(product.get());

        productRepository.deleteById(product_id);
    }

    @GetMapping("/product/{product_id}")
    public Product getProduct(@PathVariable int product_id){
        return productRepository.findById(product_id).get();
    }
}
