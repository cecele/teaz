DROP TABLE IF EXISTS ELEVE ;# MySQL a retourné un résultat vide (aucune ligne).
 CREATE TABLE ELEVE (id_eleve CHAR(6) NOT NULL, eleve_nom VARCHAR(150), eleve_prenom VARCHAR(150), date_naissance DATE, numrue INT, nomrue VARCHAR(1000), codepostal CHAR(5), ville VARCHAR(200), date_entree YEAR, cotisant BOOL, eleve_profil INT, diplome INT, motdepasse VARCHAR(50), PRIMARY KEY (id_eleve) ) ENGINE=InnoDB;# MySQL a retourné un résultat vide (aucune ligne).
  DROP TABLE IF EXISTS TEA ;# MySQL a retourné un résultat vide (aucune ligne).
 CREATE TABLE TEA (cle_tea int AUTO_INCREMENT NOT NULL, date_tea_realisee DATE, nbheure_realisee INT, statut_valide INT, date_validation DATE, nbheure_validee INT, cle_offre INT NOT NULL, id_eleve CHAR(6) NOT NULL, PRIMARY KEY (cle_tea) ) ENGINE=InnoDB;# MySQL a retourné un résultat vide (aucune ligne).
  DROP TABLE IF EXISTS STRUCTURE ;# MySQL a retourné un résultat vide (aucune ligne).
 CREATE TABLE STRUCTURE (cle_structure int AUTO_INCREMENT NOT NULL, structure_nom VARCHAR(500), PRIMARY KEY (cle_structure) ) ENGINE=InnoDB;# MySQL a retourné un résultat vide (aucune ligne).
  DROP TABLE IF EXISTS OFFRE ;# MySQL a retourné un résultat vide (aucune ligne).
 CREATE TABLE OFFRE (cle_offre int AUTO_INCREMENT NOT NULL, date_depot DATE, date_miseenligne DATE, date_tea DATE, heure_debut CHAR(6), heure_fin CHAR(6), statut INT, offre_description VARCHAR(1000), eleve_mail VARCHAR(200), offre_titre VARCHAR(50), offre_place INT, cle_structure INT NOT NULL, PRIMARY KEY (cle_offre) ) ENGINE=InnoDB;# MySQL a retourné un résultat vide (aucune ligne).
  DROP TABLE IF EXISTS CLASSE ;# MySQL a retourné un résultat vide (aucune ligne).
 CREATE TABLE CLASSE (cle_classe int AUTO_INCREMENT NOT NULL, classe CHAR(3), annee CHAR(4), nb_tea INT, PRIMARY KEY (cle_classe) ) ENGINE=InnoDB;# MySQL a retourné un résultat vide (aucune ligne).
  DROP TABLE IF EXISTS ARTICLE ;# MySQL a retourné un résultat vide (aucune ligne).
 CREATE TABLE ARTICLE (cle_article int AUTO_INCREMENT NOT NULL, image BLOB, titre VARCHAR(200), article VARCHAR(5000), PRIMARY KEY (cle_article) ) ENGINE=InnoDB;# MySQL a retourné un résultat vide (aucune ligne).
  DROP TABLE IF EXISTS PRESIDER ;# MySQL a retourné un résultat vide (aucune ligne).
 CREATE TABLE PRESIDER (id_eleve CHAR(6) NOT NULL, cle_structure INT NOT NULL, date_debut DATE, date_fin DATE, PRIMARY KEY (id_eleve,  cle_structure) ) ENGINE=InnoDB;# MySQL a retourné un résultat vide (aucune ligne).
  DROP TABLE IF EXISTS APPARTENIR ;# MySQL a retourné un résultat vide (aucune ligne).
 CREATE TABLE APPARTENIR (id_eleve CHAR(6) NOT NULL, cle_classe INT NOT NULL, PRIMARY KEY (id_eleve,  cle_classe) ) ENGINE=InnoDB;# MySQL a retourné un résultat vide (aucune ligne).
  DROP TABLE IF EXISTS ECRIRE ;# MySQL a retourné un résultat vide (aucune ligne).
 CREATE TABLE ECRIRE (id_eleve CHAR(6) NOT NULL, cle_article INT NOT NULL, ecrire_date DATE, PRIMARY KEY (id_eleve,  cle_article) ) ENGINE=InnoDB;# MySQL a retourné un résultat vide (aucune ligne).
  ALTER TABLE TEA ADD CONSTRAINT FK_TEA_cle_offre FOREIGN KEY (cle_offre) REFERENCES OFFRE (cle_offre);# MySQL a retourné un résultat vide (aucune ligne).
 ALTER TABLE TEA ADD CONSTRAINT FK_TEA_id_eleve FOREIGN KEY (id_eleve) REFERENCES ELEVE (id_eleve);# MySQL a retourné un résultat vide (aucune ligne).
 ALTER TABLE OFFRE ADD CONSTRAINT FK_OFFRE_cle_structure FOREIGN KEY (cle_structure) REFERENCES STRUCTURE (cle_structure);# MySQL a retourné un résultat vide (aucune ligne).
 ALTER TABLE PRESIDER ADD CONSTRAINT FK_PRESIDER_id_eleve FOREIGN KEY (id_eleve) REFERENCES ELEVE (id_eleve);# MySQL a retourné un résultat vide (aucune ligne).
 ALTER TABLE PRESIDER ADD CONSTRAINT FK_PRESIDER_cle_structure FOREIGN KEY (cle_structure) REFERENCES STRUCTURE (cle_structure);# MySQL a retourné un résultat vide (aucune ligne).
 ALTER TABLE APPARTENIR ADD CONSTRAINT FK_APPARTENIR_id_eleve FOREIGN KEY (id_eleve) REFERENCES ELEVE (id_eleve);# MySQL a retourné un résultat vide (aucune ligne).
 ALTER TABLE APPARTENIR ADD CONSTRAINT FK_APPARTENIR_cle_classe FOREIGN KEY (cle_classe) REFERENCES CLASSE (cle_classe);# MySQL a retourné un résultat vide (aucune ligne).
 ALTER TABLE ECRIRE ADD CONSTRAINT FK_ECRIRE_id_eleve FOREIGN KEY (id_eleve) REFERENCES ELEVE (id_eleve);# MySQL a retourné un résultat vide (aucune ligne).
 ALTER TABLE ECRIRE ADD CONSTRAINT FK_ECRIRE_cle_article FOREIGN KEY (cle_article) REFERENCES ARTICLE (cle_article);# MySQL a retourné un résultat vide (aucune ligne).
