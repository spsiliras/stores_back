package com.backend.stores_back.controller;

import com.backend.stores_back.model.Employee;
import com.backend.stores_back.model.Store;
import com.backend.stores_back.repository.EmployeeRepository;
import com.backend.stores_back.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class StoreController {
    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/stores")
    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }

    @PostMapping("/store")
    public Store addStore(@RequestBody Store newStore){
        return storeRepository.save(newStore);
    }

    @DeleteMapping("/store/{id}")
    public String deleteStore(@PathVariable int id){
        storeRepository.deleteById(id);

        return "Store with id " + id + " deleted";
    }

    @PostMapping("/store/{id}")
    public Store updateStore(@PathVariable int id, @RequestBody Store newStore){
        Store store = storeRepository.findById(id).get();

        store.setName(newStore.getName());
        store.setOwner(newStore.getOwner());
        store.setLocation(newStore.getLocation());

        return storeRepository.save(store);
    }

    @GetMapping("/store/{id}")
    public Store getStore(@PathVariable int id){
        return storeRepository.findById(id).get();
    }


}
