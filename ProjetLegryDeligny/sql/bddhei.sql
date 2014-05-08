 DROP TABLE IF EXISTS ELEVE ; CREATE TABLE ELEVE (id_eleve CHAR(6) NOT NULL, eleve_nom VARCHAR(150), eleve_prenom VARCHAR(150), date_naissance DATE, numrue INT, nomrue VARCHAR(1000), codepostal CHAR(5), ville VARCHAR(200), date_entree YEAR, cotisant BOOL, eleve_profil INT, diplome INT, motdepasse VARCHAR(50), PRIMARY KEY (id_eleve) ) ENGINE=InnoDB;  DROP TABLE IF EXISTS TEA ; CREATE TABLE TEA (cle_tea int AUTO_INCREMENT NOT NULL, date_tea_realisee DATE, nbheure_realisee INT, statut_valide INT, date_validation DATE, cle_offre INT NOT NULL, id_eleve CHAR(6) NOT NULL, PRIMARY KEY (cle_tea) ) ENGINE=InnoDB;  DROP TABLE IF EXISTS STRUCTURE ; CREATE TABLE STRUCTURE (cle_structure int AUTO_INCREMENT NOT NULL, structure_nom VARCHAR(500), PRIMARY KEY (cle_structure) ) ENGINE=InnoDB;  DROP TABLE IF EXISTS OFFRE ; CREATE TABLE OFFRE (cle_offre int AUTO_INCREMENT NOT NULL, date_depot DATE, date_miseenligne DATE, date_tea DATE, heure_debut CHAR(6), heure_fin CHAR(6), statut INT, offre_description VARCHAR(1000), eleve_mail VARCHAR(200), offre_titre VARCHAR(50), offre_place INT, cle_structure INT NOT NULL, PRIMARY KEY (cle_offre) ) ENGINE=InnoDB;  DROP TABLE IF EXISTS CLASSE ; CREATE TABLE CLASSE (cle_classe int AUTO_INCREMENT NOT NULL, classe CHAR(3), annee CHAR(4), nb_tea INT, PRIMARY KEY (cle_classe) ) ENGINE=InnoDB;  DROP TABLE IF EXISTS PRESIDER ; CREATE TABLE PRESIDER (id_eleve CHAR(6) NOT NULL, cle_structure INT NOT NULL, date_debut DATE, date_fin DATE, PRIMARY KEY (id_eleve,  cle_structure) ) ENGINE=InnoDB;  DROP TABLE IF EXISTS APPARTENIR ; CREATE TABLE APPARTENIR (id_eleve CHAR(6) NOT NULL, cle_classe INT NOT NULL, PRIMARY KEY (id_eleve,  cle_classe) ) ENGINE=InnoDB;  ALTER TABLE TEA ADD CONSTRAINT FK_TEA_cle_offre FOREIGN KEY (cle_offre) REFERENCES OFFRE (cle_offre); ALTER TABLE TEA ADD CONSTRAINT FK_TEA_id_eleve FOREIGN KEY (id_eleve) REFERENCES ELEVE (id_eleve); ALTER TABLE OFFRE ADD CONSTRAINT FK_OFFRE_cle_structure FOREIGN KEY (cle_structure) REFERENCES STRUCTURE (cle_structure); ALTER TABLE PRESIDER ADD CONSTRAINT FK_PRESIDER_id_eleve FOREIGN KEY (id_eleve) REFERENCES ELEVE (id_eleve); ALTER TABLE PRESIDER ADD CONSTRAINT FK_PRESIDER_cle_structure FOREIGN KEY (cle_structure) REFERENCES STRUCTURE (cle_structure); ALTER TABLE APPARTENIR ADD CONSTRAINT FK_APPARTENIR_id_eleve FOREIGN KEY (id_eleve) REFERENCES ELEVE (id_eleve); ALTER TABLE APPARTENIR ADD CONSTRAINT FK_APPARTENIR_cle_classe FOREIGN KEY (cle_classe) REFERENCES CLASSE (cle_classe); 