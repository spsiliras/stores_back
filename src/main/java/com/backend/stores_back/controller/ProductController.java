package com.backend.stores_back.controller;

import com.backend.stores_back.exception.ResourceNotFoundException;
import com.backend.stores_back.model.Employee;
import com.backend.stores_back.model.Product;
import com.backend.stores_back.model.Store;
import com.backend.stores_back.repository.ProductRepository;
import com.backend.stores_back.repository.StoreRepository;
import com.backend.stores_back.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{store_id}")
    public List<Product> getProducts(@PathVariable int store_id) {

        return productService.getAllProducts(store_id);
    }

    @PostMapping("/add/{store_id}")
    public Product addProduct(@RequestBody Product newProduct, @PathVariable int store_id) {

        return productService.addProduct(newProduct, store_id);
    }

    @PostMapping("/edit/{product_id}")
    public Product editProduct(@RequestBody Product newProduct, @PathVariable int product_id) {

        return productService.updateProduct(newProduct, product_id);
    }

    @DeleteMapping("/delete/{store_id}/{product_id}")
    public void deleteProduct(@PathVariable int product_id, @PathVariable int store_id) {
        productService.deleteProduct(product_id, store_id);
    }

    @GetMapping("/product/{product_id}")
    public Product getProduct(@PathVariable int product_id){
        return productService.getProductById(product_id);
    }
}
