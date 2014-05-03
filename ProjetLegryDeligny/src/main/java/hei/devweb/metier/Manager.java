package hei.devweb.metier;

import java.util.Date;
import java.util.List;

import hei.devweb.dao.AnnonceDao;
import hei.devweb.dao.EleveDao;
import hei.devweb.dao.TeaDao;
import hei.devweb.dao.impl.AnnonceDaoImpl;
import hei.devweb.dao.impl.EleveDaoImpl;
import hei.devweb.dao.impl.TeaDaoImpl;
import hei.devweb.model.Offre;
import hei.devweb.model.Eleve;
import hei.devweb.model.Tea;

public class Manager {
private static Manager instance;
	
	private AnnonceDao AnnonceDao = new AnnonceDaoImpl();
	private EleveDao EleveDao = new EleveDaoImpl();
	private TeaDao TeaDao = new TeaDaoImpl();

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}
	
	public List<Offre> listerOffre(){
		System.out.println("Dans m�thode: listerOffres");
		return AnnonceDao.listerOffre();
	}
	
	public List<Eleve> getEleveTotal(){
		System.out.println("Dans m�thode: getEleveTotal");
		return EleveDao.getEleveTotal();
	}
	
	public void ajouterAnnonce(Offre offre){
		if(offre == null){
			throw new IllegalArgumentException("Erreur dans l'offre");
		}
		System.out.println("Dans m�thode ajouterAnnonce");
		AnnonceDao.ajouterAnnonce(offre);
	}

	public List<Offre> listerOffreNonValide() {
		System.out.println("Dans m�thode: listerOffres");
		return AnnonceDao.listerOffreNonValide();
	}

	public void annonce_validation(Integer id, Date date) {
		System.out.println("Dans m�thode: annonce_validation");
		AnnonceDao.annonce_validation(id,date);
		
	}

	public void offre_placemoins(Integer id) {
		System.out.println("Dans m�thode: offre_placemoins");
		AnnonceDao.offre_placemoins(id);
		
	}

	public int getNbPlaces(Integer id) {
		System.out.println("Dans m�thode: getNbPlaces");
		return AnnonceDao.getNbPlaces(id);
	}

	public void annonce_miseHorsLigne(Integer id) {
		System.out.println("Dans m�thode: annonce mise hors ligne");
		AnnonceDao.annonce_miseHorsLigne(id);
	}

	public void ajouterTea(Integer id, String matricule) {
		System.out.println("Dans m�thode: ajouter tea");
		TeaDao.ajouterTea(id,matricule);
	}

	public List<Tea> getTeaByEleve(String string) {
		System.out.println("Dans m�thode: getTeaByEleve");
		return TeaDao.getTeaByEleve(string);
	}

	public Eleve getEleveById(String champId) {
		System.out.println("Dans m�thode: GetEleveById");
		return EleveDao.getEleveById(champId);
	}



}
