package com.backend.stores_back.service;

import com.backend.stores_back.model.Store;
import com.backend.stores_back.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Optional<Store> getStore(int id) {
        return storeRepository.findById(id);
    }

    public Store addStore(Store store) {
        return storeRepository.save(store);
    }

    public void deleteStore(int id) {
        storeRepository.deleteById(id);
    }
}
