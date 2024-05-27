# SpringParking
Ceci est une application gestion de parking

Cote Admin:
-Gestion de portfeuille (Validation de l'argent par l'admin)  
-Historique du portefeuille pour chaque client  
-Gestion de disponnibilite de parking  
-Gestion de paiment du parking (avec un ticket)  
-Gestion de paiement d'amende puisque le client recevra une amende si le temps passe sur le parking est depassée  


Pour le testé nous avons déjà une otion pour gerer l'heure afin de voir si c'est déjà depasser ou pas au lieu d'attendre  

##Technologie  
 - Spring Boot  
 - Postgres  
 - JSP (Jstl)  

## Configuration 

- Creer Une base de donnee dans Postgres : au nom de " parking "    
- Veuillez ajouter le script pour les bases de données : - base.sql  
                                                            -data.sql
                                                        

- pense à changer le mot de passe et user si besoin dans : parking\src\main\resources\application.properties
        spring.datasource.url = jdbc:postgresql://localhost:5432/parking
        spring.datasource.username=postgres
        spring.datasource.password=mpdprom13 (ici)
        spring.jpa.show-sql=true 

- Lancer la commande : si sur windows : mvnw spring-boot:run
                        Autre : mvn spring-boot:run

- Puis lancer le : locahttp://localhost:8080/



Ceci est un projet assez lucratif qui me tient aussi à coeur qui est aussi encore en cours de programmation.    
 Bon visionnage !! 

