package com.example.creatorstore;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreatorStoreApplication {

    public static void main(String[] args) {


        /*
        *    On charge les variables du fichier .env en ignorant l'erreur si le fichier n'existe pas.
             Ensuite, on boucle sur chaque variable pour la stocker dans le système Java (System.setProperty).
             Comme ça, Spring Boot peut lire ${NOM_VARIABLE} dans application.properties sans afficher les mots de passe sur GitHub.*/
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        dotenv.entries().forEach(entry -> System.setProperty(
                entry.getKey(),entry.getValue()
        ));


        /*De base, quand on execute du code, java execute le main puis s'eteint
        * mais dans une API, on a besoin qu'elle reste allumée
        * c'est ce que la ligne suivante fait :
        * Démarre l'application Spring Boot : lance le serveur web Tomcat (port 8080),
          scanne les composants/annotations et maintient l'API active.
          CreatorStoreApplication.class sert de point de départ pour ce scan. */
        SpringApplication.run(CreatorStoreApplication.class, args);
    }

}
