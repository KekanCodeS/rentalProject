package com.example.rentalproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productsAmount")
public class ProductAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "storeId", referencedColumnName = "id")
    private Store store;
    @OneToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;
    private Long amount;
}
