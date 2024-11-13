package com.backend.stores_back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy="native")
    private int employeeId;
    private String name;
    private String address;
    private double salary;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "store_id", referencedColumnName = "storeId", nullable = true)
    private Store store;
}
