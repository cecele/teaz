package hei.devweb.model;

import java.sql.Date;



public class Annee{
	public Annee(Integer cle_annee, Date annee, Integer anne_encours) {
		super();
		this.cle_annee = cle_annee;
		this.annee = annee;
		this.anne_encours = anne_encours;
		
	}
	Integer cle_annee;
	Date annee;
	Integer anne_encours;
	
	public Integer getCle_annee() {
		return cle_annee;
	}
	public void setCle_annee(Integer cle_annee) {
		this.cle_annee = cle_annee;
	}
	public Date getAnnee() {
		return annee;
	}
	public void setAnnee(Date annee) {
		this.annee = annee;
	}
	public Integer getAnne_encours() {
		return anne_encours;
	}
	public void setAnne_encours(Integer anne_encours) {
		this.anne_encours = anne_encours;
	}

	
	
}