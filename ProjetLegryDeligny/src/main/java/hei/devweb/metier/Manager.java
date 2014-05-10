package hei.devweb.metier;

import java.util.Date;
import java.util.List;

import hei.devweb.dao.AnnonceDao;
import hei.devweb.daoimpl.AnnonceDaoImpl;
import hei.devweb.dao.EleveDao;
import hei.devweb.daoimpl.EleveDaoImpl;
import hei.devweb.daoimpl.RechercheDaoImpl;
import hei.devweb.daoimpl.StructureDaoImpl;
import hei.devweb.dao.TeaDao;
import hei.devweb.dao.StructureDao;
import hei.devweb.dao.RechercheDao;
import hei.devweb.daoimpl.TeaDaoImpl;
import hei.devweb.model.Offre;
import hei.devweb.model.Eleve;
import hei.devweb.model.Structure;
import hei.devweb.model.Tea;

public class Manager {
private static Manager instance;
	
	private AnnonceDao AnnonceDao = new AnnonceDaoImpl();
	private EleveDao EleveDao = new EleveDaoImpl();
	private TeaDao TeaDao = new TeaDaoImpl();
	private StructureDao StructureDao = new StructureDaoImpl();
	private RechercheDao RechercheDao = new RechercheDaoImpl();

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

	public List<Tea> listerTeaAValider() {
		System.out.println("dans méthode listerTeaAValider");
		return TeaDao.listerTeaAValider();
	}
	
	public List<Tea> listerTeaAFaire() {
		System.out.println("dans méthode listerTeaAFaire");
		return TeaDao.listerTeaAFaire();
	}

	public List<Eleve> getEleveResponsables(int i) {
		System.out.println("dans méthode getEleveResponsables");
		return EleveDao.getEleveResponsables(i);
	}

	public void retirerDroits(String id) {
		System.out.println("dans méthode retirerDroits");
		EleveDao.eleveChgtProfil(id,0);
	}

	public void ajouterDroits(String id, int droit) {
		System.out.println("dans méthode ajouterDroits");
		EleveDao.eleveChgtProfil(id,droit);
		
	}

	public List<Structure> getStructure_OrdreNom() {
		System.out.println("dans méthode getStructure_OrdreNom");
		return StructureDao.getStructure_OrdreNom();
	}

	public void CreateStructure(String nom) {
		System.out.println("dans méthode creerStructure");
		StructureDao.CreateStructure(nom);
		
	}

	public List<Eleve> rechercheByParameter(String matricule, String nom,
			String prenom, String classe, String orderBy) {
		System.out.println("dans méthode recherche");
		return RechercheDao.rechercheByParameter(matricule,nom,prenom,classe,orderBy);
	}



}
