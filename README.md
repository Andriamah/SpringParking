# SpringParking

Ceci est une application de gestion de parking :

- Gestion de portefeuille (Validation de l'argent par l'admin, c'est-à-dire que l'argent utilisé par le client ne sera pas valable sans l'autorisation de l'admin)  
- Historique du portefeuille pour chaque client  
- Gestion de la disponibilité de parking  
- Gestion du paiement du parking (avec un ticket)  
- Gestion du paiement des amendes puisque le client recevra une amende si le temps passé sur le parking est dépassé  

* Pour le tester, nous avons déjà une option pour gérer l'heure afin de voir si c'est déjà dépassé ou pas au lieu d'attendre. 

## Technologies  
- Spring Boot  
- Postgres  
- JSP (Jstl)  

## Configuration 

- Créer une base de données dans Postgres nommée "parking".  
- Veuillez ajouter le script pour les bases de données : 
  - `base.sql`  
  - `data.sql`

- Pensez à changer le mot de passe et l'utilisateur si besoin dans : `parking\src\main\resources\application.properties`
    ```properties
    spring.datasource.url = jdbc:postgresql://localhost:5432/parking
    spring.datasource.username=postgres
    spring.datasource.password=mpdprom13 (ici)
    spring.jpa.show-sql=true 
    ```

- Lancer la commande : 
  - Sur Windows : `mvnw spring-boot:run`
  - Autre : `mvn spring-boot:run`

- Puis accéder à : [http://localhost:8080/](http://localhost:8080/)
  - Côté client : username : client / password : client
  - Côté admin : username : admin / password : admin

Ceci est un projet assez lucratif qui me tient aussi à cœur et qui est encore en cours de programmation.  
Bon visionnage !!
