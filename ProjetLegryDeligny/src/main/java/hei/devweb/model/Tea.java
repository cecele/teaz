package hei.devweb.model;

import java.sql.Date;

public class Tea {
	
Integer cle_tea;
Date date_tea_realisee;
Integer nbheure_realisee;
Integer statut_valide;
Date date_validation;
Integer cle_offre;
String id_eleve;
public Tea(Integer cle_tea, Date date_tea_realisee, Integer nbheure_realisee,
		Integer statut_valide, Date date_validation, Integer cle_offre,
		String id_eleve) {
	
	this.cle_tea = cle_tea;
	this.date_tea_realisee = date_tea_realisee;
	this.nbheure_realisee = nbheure_realisee;
	this.statut_valide = statut_valide;
	this.date_validation = date_validation;
	this.cle_offre = cle_offre;
	this.id_eleve = id_eleve;
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


}
