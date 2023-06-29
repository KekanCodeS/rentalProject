package com.example.rentalproject.entity;

import com.example.rentalproject.enums.PledgeStatus;
import com.example.rentalproject.enums.PurchaseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    private LocalDate rentStart;
    private LocalDate rentEnd;
    private PurchaseStatus status;
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "storeId", referencedColumnName = "id")
    private Store store;
    private Float startPrice;
    private Float totalPrice;
    private Float startPledge;
    private PledgeStatus pledgeStatus;
}
