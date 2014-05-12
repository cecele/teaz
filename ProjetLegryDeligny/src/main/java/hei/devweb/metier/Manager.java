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
	
	public List<Tea> getTeaAValiderByStructure(Integer clestructure, Date datedujour) {
		System.out.println("dans méthode getTeaAValiderByStructure");
		return TeaDao.getTeaAValiderByStructure(clestructure,datedujour);
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
			String prenom, String classe, String orderBy, Boolean diplome, Boolean encours, Boolean ajour, Boolean pasajour) {
		System.out.println("dans méthode recherche");
		return RechercheDao.rechercheByParameter(matricule,nom,prenom,classe,orderBy, diplome, encours, ajour, pasajour);
	}

	public List<Tea> getTeaAValiderByRespTea() {
		System.out.println("dans méthode getTeaAValiderByRespTea");
		return TeaDao.getTeaAValiderByRespTea();
	}

	public String getNomStructure(Integer cle_structure) {
		System.out.println("dans méthode getNomStructure");
		return StructureDao.getNomStructure(cle_structure);
	}

	public List<Offre> listerOffreByEleve(String matricule) {
		System.out.println("Dans méthode listerOffreByEleve");
		return AnnonceDao.listerOffreByEleve(matricule);
	}

	public Structure getStructureByCle(int id) {
		System.out.println("Dans méthode GetStructureByCle");
		return StructureDao.getStructureByCle(id);
	}

	public void StructureChangement(String ideleve, Integer idstruct,
			Date dateDebut, Date dateFin) {
		System.out.println("Dans méthode StructureChangement");
		StructureDao.StructureChangement(ideleve,idstruct,dateDebut,dateFin);
		
	}

	public Structure getStructure_ElevePresident(String ideleve) {
		// TODO Auto-generated method stub
		return StructureDao.getStructure_ElevePresident(ideleve);
	}

	public void teaValidationByStructure(Integer cleTea, Integer cleStructure,
			String matricule) {
		System.out.println("Dans méthode teaValidationByStructure");
		TeaDao.teaValidationByStructure(cleTea,cleStructure,matricule);
		
	}

	public void teaValidationByResponsable(Integer cletea) {
		System.out.println("Dans méthode teaValidationByResonsable");
		System.out.println(cletea);
		TeaDao.teaValidationByResponsable(cletea);
		
	}

	public List<Offre> listerOffreByStructure(Integer clestructure) {
		System.out.println("Dans méthode listerOffreByStructure");
		return AnnonceDao.listerOffreByStructure(clestructure);
	}

	public void AnnonceModification(Offre offre) {
		System.out.println("Dans méthode annonceModification");
		AnnonceDao.AnnonceModification(offre);
		
	}

	public Offre getOffreById(Integer cle_offre) {
		System.out.println("Dans méthode getOffreById");
		return AnnonceDao.getOffreById(cle_offre);
	}

	public Integer getTeaEnAttente() {
		// TODO Auto-generated method stub
		return TeaDao.getTeaEnAttente();
	}

	public Integer getOffreEnAttente() {
		// TODO Auto-generated method stub
		return AnnonceDao.getOffreEnAttente();
	}

	public Integer getTeaDuesEnCours(String matricule) {
		// TODO Auto-generated method stub
		return TeaDao.getTeaDuesEnCours(matricule);
	}

	public Integer getCleByNom(String nom) {
		// TODO Auto-generated method stub
		return StructureDao.getCleByNom(nom);
	}

	public Integer getNbTeaAValiderByStructure(Integer cle_structure, Date date) {
		// TODO Auto-generated method stub
		return TeaDao.getNbTeaAValiderByStructure(cle_structure,date);
	}

	public List<Tea> getTeaByOffre(Integer cleoffre) {
		System.out.println("Dans Méthode getTeaByOffre");
		return TeaDao.getTeaByOffre(cleoffre);
	}
	public String getPresidentIdById(Integer clestructure){
		System.out.println("Dans Méthode getPresidentIdById");
		return StructureDao.getPresidentIdById(clestructure);
	}

	public List<Eleve> getEleveAjour() {
		System.out.println("Dans Méthode getPresidentIdById");
		return EleveDao.getEleveAjour();
	}
	
	public Integer getCleStructureById(String ideleve){
		System.out.println("Dans Méthode  getCleStructureById");
		return EleveDao.getCleStructureById(ideleve);
	}
	
	public  String getElevePrenomById(String ideleve){
		System.out.println("Dans Méthode getElevePrenomById");
		return EleveDao.getElevePrenomById(ideleve);
	}
	
	public String getPromotion(String ideleve){
		System.out.println("Dans Méthode  getPromotion");
		return EleveDao.getPromotion(ideleve);
		
	}
	
	public String getEleveNomById(String ideleve){
		System.out.println("Dans Méthode getEleveNomById");
		return EleveDao.getEleveNomById(ideleve);
	}
	
	public boolean president(String ideleve){
		System.out.println("Dans Méthode president");
		return EleveDao.president(ideleve);
	}

	public  String getPresidentNomById(Integer clestructure) {
		System.out.println("Dans Méthode getPresidentNomById");
		return StructureDao.getPresidentNomById(clestructure);
	}

	public String getPresidentPrenomById(Integer clestructure) {
		System.out.println("Dans Méthode getPresidentPrenomById");
		return StructureDao. getPresidentPrenomById(clestructure);
	}

	public Integer getNbPlacePourvue(Integer cleoffre) {
		System.out.println("Dans Méthode dentgetNbPlacePourvue");
		return TeaDao. getNbPlacePourvue(cleoffre);
	}

	public Integer getNbHeureEnAttente(String ideleve) {
		System.out.println("Dans Méthode getNbHeureEnAttente");
		return TeaDao. getNbHeureEnAttente(ideleve);
	}

	public Integer getNbHeureTeaValide(String ideleve) {
		System.out.println("Dans Méthode getNbHeureTeaValide");
		return TeaDao. getNbHeureTeaValide(ideleve);
	}

	public Integer getNbHeureDues(String ideleve) {
		System.out.println("Dans Méthode getNbHeureDues");
		return TeaDao. getNbHeureDues(ideleve);
	}

	public Integer sizeReponse(List<Eleve> eleves) {
		// TODO Auto-generated method stub
		return RechercheDao.sizeReponse(eleves);
	}
}

