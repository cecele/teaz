package hei.devweb.dao;

import hei.devweb.model.Offre;

import java.util.Date;
import java.util.List;

public interface AnnonceDao {

	List<Offre> listerOffre();

	void ajouterAnnonce(Offre offre);

	List<Offre> listerOffreNonValide();

	void annonce_validation(Integer id, Date date);

	void offre_placemoins(Integer id);

	int getNbPlaces(int id);

	void annonce_miseHorsLigne(Integer id);

	List<Offre> listerOffreByEleve(String matricule);

}
