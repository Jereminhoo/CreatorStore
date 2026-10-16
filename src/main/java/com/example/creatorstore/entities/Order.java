package com.example.creatorstore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String status;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;


    //Une commande peut contenir plusieurs produits, donc on a une relation OneToMany entre Order et OrderItems
    // ici, "order" est le nom de l'attribut dans la classe OrderItems qui fait référence à cette classe Order
    @OneToMany(mappedBy="order")
    private List<OrderItem> orderItems;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // cette annotation indique à Hibernate que la méthode sera appelée avant que l'entité ne soit persistée dans la base de données
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}
