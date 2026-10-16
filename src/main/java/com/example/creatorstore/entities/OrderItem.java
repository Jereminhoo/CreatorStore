package com.example.creatorstore.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "price_at_purchase", nullable = false)
    private BigDecimal priceAtPurchase;


    @JsonBackReference // Empêche Jackson de réafficher la commande ici pour éviter de boucler à l'infini
    // Une commande peut contenir lignes d'articles, donc on a une relation ManyToOne entre OrderItem et Order
    // JoinColumn indique à Hibernate que la colonne "order_id" dans la table OrderItem est une clé étrangère qui fait référence à la table Order
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
