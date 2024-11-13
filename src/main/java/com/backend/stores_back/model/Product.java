package com.backend.stores_back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy="native")
    private int productId;

    private String name;
    private int quantity;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "store_id", referencedColumnName = "storeId", nullable = true)
    private Store store;
}
