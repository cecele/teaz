package hei.devweb.dao;

import hei.devweb.model.Offre;

import java.util.Date;
import java.util.List;

/**
 * Interface qui permet de faire le lien entre le manager des méthodes et la daoImpl des annonces.
 * 
 * Elle permet d'utiliser des résultats de requetes de base de données qui concernent les annonces avec les servlets.
 * 
 * @author Projet
 *
 */
public interface AnnonceDao {

	List<Offre> listerOffre();

	void ajouterAnnonce(Offre offre);

	List<Offre> listerOffreNonValide();

	void annonce_validation(Integer id, Date date);

	void offre_placemoins(Integer id);

	int getNbPlaces(int id);

	void annonce_miseHorsLigne(Integer id);

	List<Offre> listerOffreByEleve(String matricule);

	List<Offre> listerOffreByStructure(Integer clestructure);

	void AnnonceModification(Offre offre);

	Offre getOffreById(Integer cleoffre);

	Integer getOffreEnAttente();

	
	Boolean getPostulerOffre(Integer cleoffre, String ideleve, Integer clestructure );
}
