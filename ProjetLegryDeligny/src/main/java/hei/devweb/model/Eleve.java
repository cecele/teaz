package hei.devweb.model;

import java.sql.Date;

public class Eleve{
	

	String id_eleve;
	String eleve_nom;
	String eleve_prenom;
	Date date_naissance;
	Integer numrue;
	String nomrue;
	String codepostal;
	String ville;
	Date date_entree;
	Integer cotisant;
	Integer eleve_profil;
	Integer diplome;
	String motdepasse;
	



public Eleve(String id_eleve, String eleve_nom, String eleve_prenom,
			Date date_naissance, Integer numrue, String nomrue,
			String codepostal, String ville, Date date_entree,
			Integer cotisant, Integer eleve_profil, Integer diplome,
			String motdepasse) {
		super();
		this.id_eleve = id_eleve;
		this.eleve_nom = eleve_nom;
		this.eleve_prenom = eleve_prenom;
		this.date_naissance = date_naissance;
		this.numrue = numrue;
		this.nomrue = nomrue;
		this.codepostal = codepostal;
		this.ville = ville;
		this.date_entree = date_entree;
		this.cotisant = cotisant;
		this.eleve_profil = eleve_profil;
		this.diplome = diplome;
		this.motdepasse = motdepasse;
	}

public String getMotdepasse() {
	return motdepasse;
}

public void setMotdepasse(String motdepasse) {
	this.motdepasse = motdepasse;
}

public Integer getDiplome() {
	return diplome;
}

public void setDiplome(Integer diplome) {
	this.diplome = diplome;
}

public String getId_eleve() {
	return id_eleve;
}

public void setId_eleve(String id_eleve) {
	this.id_eleve = id_eleve;
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

public Date getDate_naissance() {
	return date_naissance;
}

public void setDate_naissance(Date date_naissance) {
	this.date_naissance = date_naissance;
}

public Integer getNumrue() {
	return numrue;
}

public void setNumrue(Integer numrue) {
	this.numrue = numrue;
}

public String getNomrue() {
	return nomrue;
}

public void setNomrue(String nomrue) {
	this.nomrue = nomrue;
}

public String getCodepostal() {
	return codepostal;
}

public void setCodepostal(String codepostal) {
	this.codepostal = codepostal;
}

public String getVille() {
	return ville;
}

public void setVille(String ville) {
	this.ville = ville;
}

public Date getDate_entree() {
	return date_entree;
}

public void setDate_entree(Date date_entree) {
	this.date_entree = date_entree;
}

public Integer getCotisant() {
	return cotisant;
}

public void setCotisant(Integer cotisant) {
	this.cotisant = cotisant;
}

public Integer getEleve_profil() {
	return eleve_profil;
}

public void setEleve_profil(Integer eleve_profil) {
	this.eleve_profil = eleve_profil;
}



}