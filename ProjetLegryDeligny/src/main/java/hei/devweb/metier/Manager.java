package hei.devweb.metier;

import java.util.Date;
import java.util.List;

import hei.devweb.dao.AnnonceDao;
import hei.devweb.dao.EleveDao;
import hei.devweb.dao.impl.AnnonceDaoImpl;
import hei.devweb.dao.impl.EleveDaoImpl;
import hei.devweb.model.Offre;
import hei.devweb.model.Eleve;

public class Manager {
private static Manager instance;
	
	private AnnonceDao AnnonceDao = new AnnonceDaoImpl();
	private EleveDao EleveDao = new EleveDaoImpl();

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}
	
	public List<Offre> listerOffre(){
		System.out.println("Dans méthode: listerOffres");
		return AnnonceDao.listerOffre();
	}
	
	public List<Eleve> getEleveTotal(){
		System.out.println("Dans méthode: getEleveTotal");
		return EleveDao.getEleveTotal();
	}
	
	public void ajouterAnnonce(Offre offre){
		if(offre == null){
			throw new IllegalArgumentException("Erreur dans l'offre");
		}
		System.out.println("Dans méthode ajouterAnnonce");
		AnnonceDao.ajouterAnnonce(offre);
	}

	public List<Offre> listerOffreNonValide() {
		System.out.println("Dans méthode: listerOffres");
		return AnnonceDao.listerOffreNonValide();
	}

	public void annonce_validation(Integer id, Date date) {
		System.out.println("Dans méthode: annonce_validation");
		AnnonceDao.annonce_validation(id,date);
		
	}

	public void offre_placemoins(Integer id) {
		System.out.println("Dans méthode: offre_placemoins");
		AnnonceDao.offre_placemoins(id);
		
	}

	public int getNbPlaces(Integer id) {
		System.out.println("Dans méthode: getNbPlaces");
		return AnnonceDao.getNbPlaces(id);
	}

	public void annonce_miseHorsLigne(Integer id) {
		System.out.println("Dans méthode: annonce mise hors ligne");
		AnnonceDao.annonce_miseHorsLigne(id);
	}



}
