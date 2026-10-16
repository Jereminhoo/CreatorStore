package com.example.creatorstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
//genere un constructeur avec tous les arguments et un constructeur sans argument
@AllArgsConstructor
// c'ets important de mettre le constructeur sans argument pour que JPA puisse instancier l'objet
@NoArgsConstructor
//genere un constructeur de type builder pour faciliter la creation d'objet et eviter de se tromper dans l'ordre des arguments
// ex : Product product = Product.builder().name("Product 1").price(new BigDecimal("10.99")).build();
@Builder

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Column(nullable = false)
    private String name;

    private String description;

    private String category;

    @NotNull(message="Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message="Stock quantity is required")
    @Min(value=0, message = "Stock quantity must be non-negative")
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;


    @JsonIgnore // pour éviter la sérialisation de la liste d'OrderItem lors de la conversion en JSON, ce qui pourrait causer des problèmes de récursion infinie
    @OneToMany(mappedBy = "product") // fait référence à l'attribut "product" dans la classe OrderItem
    private List<OrderItem> orderItems;
}