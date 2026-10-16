package com.example.creatorstore.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrderRequest {

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Customer email is required")
    @Email(message = "Customer email should be valid")
    private String customerEmail;

    @Valid // Cette annotation indique que chaque élément de la liste doit être validé selon les contraintes définies dans la classe OrderItemRequest
    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderItemRequest> items;
}

