package com.example.creatorstore.services;

import com.example.creatorstore.dto.OrderItemRequest;
import com.example.creatorstore.dto.OrderRequest;
import com.example.creatorstore.entities.Order;
import com.example.creatorstore.entities.OrderItem;
import com.example.creatorstore.entities.Product;
import com.example.creatorstore.repositories.OrderRepository;
import com.example.creatorstore.repositories.ProductRepository;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private List<OrderItem> orderItems = new ArrayList<>();

    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Transactional // Cette annotation indique que la méthode createOrder doit être exécutée
    // dans une transaction, ce qui signifie que toutes les opérations de base de données effectuées dans cette méthode seront regroupées et exécutées comme une seule unité. Si une exception est levée pendant l'exécution de la méthode, toutes les opérations de base de données effectuées dans cette méthode seront annulées.
    public Order createOrder(OrderRequest orderRequest) {

        BigDecimal totalPrice = BigDecimal.ZERO; // Initialiser le prix total à zéro

        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setStatus("CONFIRMED");

        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException(
                            "Product not found with id " + itemRequest.getProductId()
                    ));

            // verifier le stock du produit
            if(product.getStockQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException("Not enough stock for product " + product.getName());
            }

            //calculer le prix total de la commande
            BigDecimal priceOfItem = product.getPrice().
                    multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            totalPrice = totalPrice.add(priceOfItem);

            //modifier la table produit avec la dernier quantité de stock
            product.setStockQuantity(
                    product.getStockQuantity() - itemRequest.getQuantity());

            productRepository.save(product);

            //Builder pattern pour créer un OrderItem
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();


            orderItems.add(orderItem);

        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);


        return order;
    }
}
