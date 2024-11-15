package com.backend.stores_back.controller;

import com.backend.stores_back.model.Store;
import com.backend.stores_back.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/stores")
    public List<Store> getAllStores(){
        return storeService.getAllStores();
    }

    @PostMapping("/store")
    public Store addStore(@RequestBody Store newStore){
        return storeService.addStore(newStore);
    }

    @DeleteMapping("/store/{id}")
    public String deleteStore(@PathVariable int id){
            storeService.deleteStore(id);

        return "Store with id " + id + " deleted";
    }

    @PostMapping("/store/{id}")
    public Store updateStore(@PathVariable int id, @RequestBody Store newStore){
        Store store = storeService.getStore(id).get();

        store.setName(newStore.getName());
        store.setOwner(newStore.getOwner());
        store.setLocation(newStore.getLocation());

        return storeService.addStore(store);
    }

    @GetMapping("/store/{id}")
    public Store getStore(@PathVariable int id){
        return storeService.getStore(id).get();
    }


}
