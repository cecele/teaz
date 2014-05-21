package hei.devweb.model;

import java.util.Date;


public class Offre{

	Integer cle_offre;
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
	Integer place_pourvue;
	
	
	
	/**
	 * Une offre est déposée par quelqu'un et comporte plusieurs paramètres
	 * 
	 * @param cle_offre : clé de l'offre, elle est unique 
	 * @param date_depot : date de dépot de l'offre
	 * @param date_miseenligne : date à laquelle l'offre a été validée
	 * @param date_tea : date de la tea à faire 
	 * @param heure_debut : heure du début
	 * @param heure_fin : heure de la fin
	 * @param statut : statut de l'offre (0 si en attente, 1 si validée, 2 si hors ligne)
	 * @param offre_description : description de l'offre
	 * @param eleve_mail : mail du responsable 
	 * @param offre_titre : titre de l'offre
	 * @param cle_structure : clé de la structure qui propose l'offre
	 * @param offre_place : nombre de places disponibles 
	 * @param structure_nom : nom de la structure
	 * @param structure_president_nom : nom du président
	 * @param structure_president_prenom : prénom du président
	 * @param place_pourvue : nombre de places pourvues
	 */
	public Offre(Integer cle_offre, Date date_depot, Date date_miseenligne,
			Date date_tea, String heure_debut, String heure_fin,
			Integer statut, String offre_description, String eleve_mail,
			String offre_titre, Integer cle_structure, Integer offre_place,
			String structure_nom, String structure_president_nom,
			String structure_president_prenom, Integer place_pourvue) {
		super();
		this.cle_offre = cle_offre;
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
		this.place_pourvue = place_pourvue;
	}
	public Integer getCle_offre() {
		return cle_offre;
	}
	public void setCle_offre(Integer cle_offre) {
		this.cle_offre = cle_offre;
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
	public Integer getPlace_pourvue() {
		return place_pourvue;
	}
	public void setPlace_pourvue(Integer place_pourvue) {
		this.place_pourvue = place_pourvue;
	}

}
	