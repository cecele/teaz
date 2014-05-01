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
	String structure_president;
	
	

	public Offre(Integer cle_offre, Date date_depot, Date date_miseenligne,
			Date date_tea, String heure_debut, String heure_fin,
			Integer statut, String offre_description, String eleve_mail,
			String offre_titre, Integer cle_structure, Integer offre_place,
			String structure_nom, String structure_president) {
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
		this.structure_president = structure_president;
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

	public String getOffre_titre() {
		return offre_titre;
	}

	public void setOffre_titre(String offre_titre) {
		this.offre_titre = offre_titre;
	}

	public String getEleve_mail() {
		return eleve_mail;
	}

	public void setEleve_mail(String eleve_mail) {
		this.eleve_mail = eleve_mail;
	}

	public String getOffre_description() {
		return offre_description;
	}

	public void setOffre_description(String offre_description) {
		this.offre_description = offre_description;
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

	public String getStructure_nom() {
		return structure_nom;
	}

	public void setStructure_nom(String structure_nom) {
		this.structure_nom = structure_nom;
	}

	public String getStructure_president() {
		return structure_president;
	}

	public void setStructure_president(String structure_president) {
		this.structure_president = structure_president;
	}
	
}
