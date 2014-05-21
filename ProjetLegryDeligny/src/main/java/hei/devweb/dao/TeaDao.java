package hei.devweb.dao;

import hei.devweb.model.Tea;

import java.util.Date;
import java.util.List;
/**
 * Interface qui permet de faire le lien entre le manager des méthodes et la daoImpl des tea.
 * 
 * Elle permet d'utiliser des résultats de requetes de base de données qui concernent les annonces avec les tea.
 * 
 * @author Projet
 *
 */
public interface TeaDao {

	void ajouterTea(Integer id, String matricule);

	List<Tea> getTeaByEleve(String string);

	List<Tea> getTeaAValiderByStructure(Integer clestructure, Date datedujour);

	List<Tea> getTeaAValiderByRespTea();

	void teaValidationByStructure(Integer cleTea, Integer cleStructure,
			String matricule);

	void teaValidationByResponsable(Integer cletea);

	Integer getTeaEnAttente();

	Integer getTeaDuesEnCours(String matricule);

	Integer getNbTeaAValiderByStructure(Integer cle_structure, Date date);

	List<Tea> getTeaByOffre(Integer cleoffre);
	
	Integer getNbPlacePourvue(Integer cleoffre);
	
	Integer getNbHeureEnAttente(String ideleve);
	
	Integer getNbHeureTeaValide(String ideleve);
	
	Integer getNbHeureDues(String ideleve);

	List<Tea> getTeaByStructure(Integer clestructure);

	List<Tea> getTeaValideByEleve(String ideleve);

	List<Tea> getTeaNonValideByEleve(String ideleve);

	String getIdeleveByCleTea(Integer cletea);

	Integer getNbHeureTeaRealiseeByTea(Integer cletea);
	
	

}
