package com.example.creatorstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

// Cette classe est un DTO (Data Transfer Object) qui représente un élément de commande (OrderItem)
// dans une requête HTTP. Elle contient les informations nécessaires pour créer ou mettre à jour un élément de
// commande, à savoir l'identifiant du produit et la quantité commandée.
//Le but est de transférer les données entre le client et le serveur de manière sécurisée
// et efficace, en s'assurant que les contraintes de validation sont respectées.

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderItemRequest {

    @NotNull(message = "ProductId is required")
    private Long ProductId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
