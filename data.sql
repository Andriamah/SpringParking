INSERT INTO admin(nom,mdp) VALUES('admin','admin');

INSERT INTO parking(titre) VALUES('parking1'),
('parking2'),
('parking3'),
('parking4'),
('parking5'),
('parking6'),
('parking7'),
('parking8'),
('parking9'),
('parking10'),
('parking11'),
('parking12'),
('parking13'),
('parking14'),
('parking15'),
('parking16'),
('parking17'),
('parking18'),
('parking19'),
('parking20')
;


INSERT INTO unite(nom) VALUES('Heure'),
('Minute');

INSERT INTO tarif(id_unite,duree,valeur) VALUES
(2,1,100),
(2,60,1500),
(2,15,500),
(2,2,100),
(2,30,1000);

CREATE OR REPLACE VIEW v_reservation as			   
select r.*,
t.valeur as valeur,
t.duree as duree,
 (DATE_PART('day', r.date_fin::timestamp - getNow()) * 24 * 60 + 
               DATE_PART('hour', r.date_fin::timestamp - getNow())) * 60 +
               DATE_PART('minute', r.date_fin::timestamp - getNow()) as restant
from reservation r 
JOIN tarif t 
ON t.id = r.id_tarif;

CREATE OR REPLACE VIEW compte_protefeuille as
select id_client, sum(valeur_entre)-sum(valeur_sortie) as restant 
from portefeuille where etat = 1
GROUP BY id_client;

CREATE OR REPLACE VIEW v_portefeuille as
select p.*, c.nom from portefeuille p 
JOIN client c 
ON c.id = p.id_client;

CREATE OR REPLACE VIEW reservation_depasse as
select r.* from v_reservation r 
left join amende a 
on a.id_reservation = r.id
where  a.id_reservation is null and restant<0;

CREATE OR REPLACE VIEW v_reservation_en_cours as
 select r.*,
 t.valeur as valeur,
 t.duree as duree,
  (DATE_PART('day', r.date_fin::timestamp - getNow()) * 24 * 60 + 
                DATE_PART('hour', r.date_fin::timestamp - getNow())) * 60 +
                DATE_PART('minute', r.date_fin::timestamp - getNow()) as restant
 from reservation r 
 JOIN tarif t 
 ON t.id = r.id_tarif
 where (date_quitter is null or (date_debut <=  getNow() )) and (etat is null or etat=0) ;

  CREATE OR REPLACE VIEW v_parking AS
SELECT r.*,
p.titre,
p.id as idparking,
disponibilite(date_fin,date_debut,date_quitter) as disponibilite
FROM parking p
FULL JOIN v_reservation_en_cours r 
ON p.id = r.id_parking;