package hei.devweb.model;

import java.sql.Blob;
import java.util.Date;

public class Article {

	
	Integer cle_article;
	Blob image;
	String titre;
	String Article;
	Date Date_ecriture;
	String id_eleve;
	String eleve_nom;
	String eleve_prenom;
	
	
	public Article(Integer cle_article, Blob image, String titre,
			String article, Date date_ecriture, String id_eleve,
			String eleve_nom, String eleve_prenom) {
		super();
		this.cle_article = cle_article;
		this.image = image;
		this.titre = titre;
		Article = article;
		Date_ecriture = date_ecriture;
		this.id_eleve = id_eleve;
		this.eleve_nom = eleve_nom;
		this.eleve_prenom = eleve_prenom;
	}
	public Integer getCle_article() {
		return cle_article;
	}
	public void setCle_article(Integer cle_article) {
		this.cle_article = cle_article;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getArticle() {
		return Article;
	}
	public void setArticle(String article) {
		Article = article;
	}
	public Date getDate_ecriture() {
		return Date_ecriture;
	}
	public void setDate_ecriture(Date date_ecriture) {
		Date_ecriture = date_ecriture;
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
	
	
	
	
}
