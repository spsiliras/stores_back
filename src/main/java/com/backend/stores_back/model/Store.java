package com.backend.stores_back.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "stores")
public class Store {
	
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "store_generator")
    private int storeId;

    private String name;

    private String owner;

    private String location;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Product.class)
    private List<Product> products;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Employee.class)
    private List<Employee> employees;
}
