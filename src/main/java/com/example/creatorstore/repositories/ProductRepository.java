package com.example.creatorstore.repositories;

import com.example.creatorstore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//Cette interface est un repository pour l'entité Product, elle hérite de JpaRepository qui fournit des méthodes CRUD pour gérer les produits dans la base de données.
//elle prend en paramètre l'entité Product et le type de sa clé primaire pour permettre à Spring Data JPA de générer automatiquement les implémentations des méthodes CRUD pour l'entité Product.
public interface ProductRepository extends JpaRepository<Product, Long> {
}
