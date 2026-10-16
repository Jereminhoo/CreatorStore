package com.example.creatorstore.controllers;

import com.example.creatorstore.entities.Product;
import com.example.creatorstore.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // cette annotation indique que cette classe est un contrôleur REST cad qu'elle va gérer les requêtes HTTP et renvoyer des réponses HTTP
@RequestMapping("/api/products") // cette annotation indique que toutes les méthodes de ce contrôleur vont gérer des requêtes HTTP dont le chemin commence par /api/products
@RequiredArgsConstructor // propose un constructeur avec un paramètre pour chaque champ final de la classe, ce qui permet d'injecter les dépendances de manière plus simple et plus lisible
public class ProductController {

    private final ProductService productService; // cette variable va permettre d'accéder aux méthodes du service ProductService qui contient la logique métier pour gérer les produits

    @PostMapping // cette annotation indique que cette méthode va gérer les requêtes HTTP de type POST cad les requêtes qui vont créer une ressource
    public Product createProduct(@Valid @RequestBody Product product) { // cette annotation indique que le corps de la requête HTTP va contenir un objet Product au format JSON et que cet objet va être validé selon les contraintes définies dans la classe Product
        return productService.createProduct(product);
    }

    @PutMapping("/{id}") // cette annotation indique que cette méthode va gérer les requêtes HTTP de type PUT cad les requêtes qui vont mettre à jour une ressource et que l'identifiant de la ressource à mettre à jour va être passé dans le chemin de la requête HTTP
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) { // PathVariable indique que la variable id va être extraite du chemin de la requête HTTP
        return productService.updateProduct(id, product);
    }

    @GetMapping // cette annotation indique que cette méthode va gérer les requêtes HTTP de type GET cad les requêtes qui vont récupérer plusieurs ressources
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}") // cette annotation indique que cette méthode va gérer les requêtes HTTP de type GET cad les requêtes qui vont récupérer une ressource spécifique
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}") // cette annotation indique que cette méthode va gérer les requêtes HTTP de type DELETE cad les requêtes qui vont supprimer une ressource
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
