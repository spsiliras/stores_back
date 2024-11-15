package com.backend.stores_back.controller;

import com.backend.stores_back.model.Store;
import com.backend.stores_back.repository.StoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoreController.class)
class StoreControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StoreRepository storeRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldReturnAllStores() throws Exception {
        Store store = new Store();
        store.setStoreId(1);
        store.setName("test");
        store.setOwner("test");
        store.setLocation("test");

        given(storeRepository.findAll()).willReturn(List.of(store));
            mockMvc.perform(get("/stores"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].name").value(store.getName()));

    }

    @Test
    void shouldAddNewStore() throws Exception {
        Store store = new Store();
        store.setStoreId(1);
        store.setName("test");
        store.setOwner("test");
        store.setLocation("test");

        given(storeRepository.save(store)).willReturn(store);
        mockMvc.perform(post("/store")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(store)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnStoreById() throws Exception {
        Store store = new Store();
        store.setStoreId(1);
        store.setName("test");
        store.setOwner("test");
        store.setLocation("test");

        given(storeRepository.findById(1)).willReturn(Optional.of(store));
        mockMvc.perform(get("/store/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(store.getName()));
    }

    @Test
    void shouldUpdateStore() throws Exception {
        Store store = new Store();
        store.setStoreId(1);
        store.setName("test");
        store.setOwner("test");
        store.setLocation("test");

        Store updatedStore = new Store();
        updatedStore.setName("updated");
        updatedStore.setOwner("updated");
        updatedStore.setLocation("updated");

        given(storeRepository.findById(store.getStoreId())).willReturn(Optional.of(store));
        given(storeRepository.save(store)).willReturn(updatedStore);

        mockMvc.perform(post("/store/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedStore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedStore.getName()));

    }
}