CREATE TABLE admin(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(30),
    mdp VARCHAR(30)
);

CREATE TABLE pointeur(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(30),
    mdp VARCHAR(10)
);

CREATE TABLE client(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(30),
    email VARCHAR(50),
    mdp VARCHAR(10)
);

CREATE TABLE portefeuille(
    id SERIAL PRIMARY KEY,
    id_client INT NOT NULL,
    valeur_entre NUMERIC (10,2)  DEFAULT 0,
    valeur_sortie NUMERIC (10,2)  DEFAULT 0,
    date_portefeille Date DEFAULT NOW(),
    etat INT DEFAULT 0
);
ALTER TABLE portefeuille  ADD FOREIGN KEY(id_client) REFERENCES client(id);


CREATE TABLE parking(
    id SERIAL PRIMARY KEY
);
ALTER TABLE parking ADD COLUMN titre VARCHAR(10);
-- ALTER TABLE parking ADD COLUMN disponibilite BOOLEAN DEFAULT false;


CREATE TABLE unite(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(15)
);

CREATE TABLE tarif(
    id SERIAL PRIMARY KEY,
    id_unite INT,
    duree INT
);
ALTER TABLE tarif ADD FOREIGN KEY(id_unite) REFERENCES unite(id);
ALTER TABLE tarif ADD COLUMN  valeur NUMERIC (10,2);

CREATE TABLE reservation(
    id SERIAL PRIMARY KEY,
    id_client INT,
    id_parking INT,
    matriculation VARCHAR(7),
    date_debut TIMESTAMP DEFAULT NOW(),
    date_fin TIMESTAMP NOT NULL,
    etat INT
);
ALTER TABLE reservation ADD FOREIGN KEY (id_client) REFERENCES client(id);
ALTER TABLE reservation ADD FOREIGN KEY (id_parking) REFERENCES parking(id);
ALTER TABLE reservation ADD COLUMN date_quitter TIMESTAMP DEFAULT NULL;
ALTER TABLE reservation ADD COLUMN id_tarif INT NOT NULL;



CREATE TABLE amende(
    id SERIAL PRIMARY KEY,
    id_reservation INT,
    depassement NUMERIC (10,2),
    valeur NUMERIC (10,2)
);
ALTER TABLE amende ADD FOREIGN KEY(id_reservation) REFERENCES reservation(id);

CREATE TABLE datenow(
    date_projet TIMESTAMP
);
ALTER TABLE datenow ADD COLUMN id SERIAL PRIMARY KEY;
-----
--true libre
--false occupe
-------------------------
-- libre 0
-- occupe 1
-- infraction -1


create or replace function getNow()
    returns TIMESTAMP
    language plpgsql
    as
    $$
        declare 
            thecheck TIMESTAMP;
            nowdate TIMESTAMP;
		 begin	
            SELECT date_projet into nowdate from datenow ;
            if nowdate is null then 
                insert into datenow(date_projet) values(now()::timestamp);
                thecheck = now()::timestamp;
            else
                thecheck = nowdate;
            end if;

        return thecheck;
        end;
    $$;
--1 libre
--0 occupe
-- -1 linfraction
-- 2 reserve dans le futur
create or replace function disponibilite(date_sortie TIMESTAMP,date_debut TIMESTAMP,date_quitter TIMESTAMP)
    returns INT
    language plpgsql
    as
    $$
        declare 
            verify INT;
		 begin	
            if date_sortie is null then
                verify = 1;
                    raise notice ' LIBRE :bola tsy nidefinir sortie izy %', verify;
                   
            else
                if date_debut > getNow() then 
                    verify = 2;
                    raise notice ' LIBRE :bola tsy nidefinir sortie izy fa any @ futur %', verify;
                    
                else
                    if date_sortie > getNow() then
                        if date_quitter < getNow() then
                            verify = 1;
                            raise notice ' LIBRE :Efa niala izy %', verify;
                        else
                            verify = 0;
                            raise notice ' OCCUPE :bola ao izy %', verify;
                        end if;
                    else
                        if  date_quitter < getNow() then
                            verify = 1;
                            raise notice ' LIBRE :Efa niala izy %', verify;
                        else
                            verify = -1;
                            raise notice ' INFRACTION : efa tokony miala %', verify;
                        end if;
                    end if;
                end if;
                
            end if;
            
        return verify;
        end;
    $$;


--0 tsy possible
--1 possible

    create or replace function possibilite(sortie TIMESTAMP,debut TIMESTAMP,parking INT)
    returns INT
    language plpgsql
    as
    $$
        declare 
            verify INT;
            isa INT;
		 begin	
            select count(*) into isa from reservation where (id_parking = parking) and ( (sortie between date_debut and date_fin) or (debut between date_debut and date_fin));
            if isa > 0 then
               verify = 1;
               raise notice ' OCCUPE :Tsy possible %', verify;
            else
               verify = 0;
               raise notice ' LIBRE :Bola possible %', verify;
            end if;
        return verify;
        end;
    $$;