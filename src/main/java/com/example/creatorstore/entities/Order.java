package com.example.creatorstore.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @JsonManagedReference // cette annotation est utilisée pour gérer la sérialisation JSON des relations bidirectionnelles entre les entités.
    // Elle indique à Jackson (la bibliothèque de sérialisation JSON utilisée par Spring Boot) que cette partie de la relation doit être sérialisée normalement,
    // tandis que l'autre partie (dans OrderItem) sera marquée avec @JsonBackReference pour éviter une boucle infinie lors de la sérialisation.

    //Une commande peut contenir plusieurs produits, donc on a une relation OneToMany entre Order et OrderItems
    // ici, "order" est le nom de l'attribut dans la classe OrderItems qui fait référence à cette classe Order
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL) //Propage automatiquement la création, modification et suppression de la commande à tous ses articles
    private List<OrderItem> orderItems;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // cette annotation indique à Hibernate que la méthode sera appelée avant que l'entité ne soit persistée dans la base de données
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}
