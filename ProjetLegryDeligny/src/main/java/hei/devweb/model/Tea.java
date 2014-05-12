package hei.devweb.model;

import java.sql.Date;

public class Tea {
	
Integer cle_tea;
Date date_tea_realisee;
Integer nbheure_realisee;
Integer nbheure_validee;
Integer statut_valide;
Date date_validation;
Integer cle_offre;
String id_eleve;
Date date_depot;
Date date_miseenligne;
Date date_tea;
String heure_debut;
String heure_fin;
Integer statut;
String offre_description;
String eleve_mail;
String offre_titre;
Integer cle_structure;
Integer offre_place;
String structure_nom;
String structure_president_nom;
String structure_president_prenom;
String eleve_nom;
String eleve_prenom;


/**
 * Informations sur une TEA. Une heure de TEA est dérivée d'une offre et doit être validée une première fois par le reponsable de la structure qui a déposé l'annonce, puis par le responsable TEA pour qu'elle soit valide
 * 
 * @param cle_tea : la clé de la tea, unique
 * @param date_tea_realisee : la date à laquelle a été faite la tea
 * @param nbheure_realisee : le nombre d'heures proposées
 * @param nbheure_validee : le nombre d'heures validées pour cette TEA
 * @param statut_valide : le statut de la TEA (O si en attente, 1 si validée par le resp de structure, 2 si validée définitivement
 * @param date_validation
 * @param cle_offre
 * @param id_eleve
 * @param date_depot
 * @param date_miseenligne
 * @param date_tea
 * @param heure_debut
 * @param heure_fin
 * @param statut
 * @param offre_description
 * @param eleve_mail
 * @param offre_titre
 * @param cle_structure
 * @param offre_place
 * @param structure_nom
 * @param structure_president_nom
 * @param structure_president_prenom
 * @param eleve_nom
 * @param eleve_prenom
 */
public Tea(Integer cle_tea, Date date_tea_realisee, Integer nbheure_realisee,
		Integer nbheure_validee, Integer statut_valide, Date date_validation,
		Integer cle_offre, String id_eleve, Date date_depot,
		Date date_miseenligne, Date date_tea, String heure_debut,
		String heure_fin, Integer statut, String offre_description,
		String eleve_mail, String offre_titre, Integer cle_structure,
		Integer offre_place, String structure_nom,
		String structure_president_nom, String structure_president_prenom,
		String eleve_nom, String eleve_prenom) {
	super();
	this.cle_tea = cle_tea;
	this.date_tea_realisee = date_tea_realisee;
	this.nbheure_realisee = nbheure_realisee;
	this.nbheure_validee = nbheure_validee;
	this.statut_valide = statut_valide;
	this.date_validation = date_validation;
	this.cle_offre = cle_offre;
	this.id_eleve = id_eleve;
	this.date_depot = date_depot;
	this.date_miseenligne = date_miseenligne;
	this.date_tea = date_tea;
	this.heure_debut = heure_debut;
	this.heure_fin = heure_fin;
	this.statut = statut;
	this.offre_description = offre_description;
	this.eleve_mail = eleve_mail;
	this.offre_titre = offre_titre;
	this.cle_structure = cle_structure;
	this.offre_place = offre_place;
	this.structure_nom = structure_nom;
	this.structure_president_nom = structure_president_nom;
	this.structure_president_prenom = structure_president_prenom;
	this.eleve_nom = eleve_nom;
	this.eleve_prenom = eleve_prenom;
}
public Integer getCle_tea() {
	return cle_tea;
}
public void setCle_tea(Integer cle_tea) {
	this.cle_tea = cle_tea;
}
public Date getDate_tea_realisee() {
	return date_tea_realisee;
}
public void setDate_tea_realisee(Date date_tea_realisee) {
	this.date_tea_realisee = date_tea_realisee;
}
public Integer getNbheure_realisee() {
	return nbheure_realisee;
}
public void setNbheure_realisee(Integer nbheure_realisee) {
	this.nbheure_realisee = nbheure_realisee;
}
public Integer getNbheure_validee() {
	return nbheure_validee;
}
public void setNbheure_validee(Integer nbheure_validee) {
	this.nbheure_validee = nbheure_validee;
}
public Integer getStatut_valide() {
	return statut_valide;
}
public void setStatut_valide(Integer statut_valide) {
	this.statut_valide = statut_valide;
}
public Date getDate_validation() {
	return date_validation;
}
public void setDate_validation(Date date_validation) {
	this.date_validation = date_validation;
}
public Integer getCle_offre() {
	return cle_offre;
}
public void setCle_offre(Integer cle_offre) {
	this.cle_offre = cle_offre;
}
public String getId_eleve() {
	return id_eleve;
}
public void setId_eleve(String id_eleve) {
	this.id_eleve = id_eleve;
}
public Date getDate_depot() {
	return date_depot;
}
public void setDate_depot(Date date_depot) {
	this.date_depot = date_depot;
}
public Date getDate_miseenligne() {
	return date_miseenligne;
}
public void setDate_miseenligne(Date date_miseenligne) {
	this.date_miseenligne = date_miseenligne;
}
public Date getDate_tea() {
	return date_tea;
}
public void setDate_tea(Date date_tea) {
	this.date_tea = date_tea;
}
public String getHeure_debut() {
	return heure_debut;
}
public void setHeure_debut(String heure_debut) {
	this.heure_debut = heure_debut;
}
public String getHeure_fin() {
	return heure_fin;
}
public void setHeure_fin(String heure_fin) {
	this.heure_fin = heure_fin;
}
public Integer getStatut() {
	return statut;
}
public void setStatut(Integer statut) {
	this.statut = statut;
}
public String getOffre_description() {
	return offre_description;
}
public void setOffre_description(String offre_description) {
	this.offre_description = offre_description;
}
public String getEleve_mail() {
	return eleve_mail;
}
public void setEleve_mail(String eleve_mail) {
	this.eleve_mail = eleve_mail;
}
public String getOffre_titre() {
	return offre_titre;
}
public void setOffre_titre(String offre_titre) {
	this.offre_titre = offre_titre;
}
public Integer getCle_structure() {
	return cle_structure;
}
public void setCle_structure(Integer cle_structure) {
	this.cle_structure = cle_structure;
}
public Integer getOffre_place() {
	return offre_place;
}
public void setOffre_place(Integer offre_place) {
	this.offre_place = offre_place;
}
public String getStructure_nom() {
	return structure_nom;
}
public void setStructure_nom(String structure_nom) {
	this.structure_nom = structure_nom;
}
public String getStructure_president_nom() {
	return structure_president_nom;
}
public void setStructure_president_nom(String structure_president_nom) {
	this.structure_president_nom = structure_president_nom;
}
public String getStructure_president_prenom() {
	return structure_president_prenom;
}
public void setStructure_president_prenom(String structure_president_prenom) {
	this.structure_president_prenom = structure_president_prenom;
}
public String getEleve_nom() {
	return eleve_nom;
}
public void setEleve_nom(String eleve_nom) {
	this.eleve_nom = eleve_nom;
}
public String getEleve_prenom() {
	return eleve_prenom;
}
public void setEleve_prenom(String eleve_prenom) {
	this.eleve_prenom = eleve_prenom;
}


}