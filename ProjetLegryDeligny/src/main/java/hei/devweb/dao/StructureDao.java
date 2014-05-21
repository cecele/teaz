package hei.devweb.dao;

import hei.devweb.model.Structure;
import hei.devweb.model.Tea;

import java.util.Date;
import java.util.List;
/**
 * Interface qui permet de faire le lien entre le manager des méthodes et la daoImpl des structures.
 * 
 * Elle permet d'utiliser des résultats de requetes de base de données qui concernent les annonces avec les structures.
 * 
 * @author Projet
 *
 */
public interface StructureDao {

	List<Structure> getStructure_OrdreNom();

	void CreateStructure(String nom);

	String getNomStructure(Integer cle_structure);

	Structure getStructureByCle(int id);

	void StructureChangement(String ideleve, Integer idstruct, Date dateDebut,
			Date dateFin);

	Structure getStructure_ElevePresident(String ideleve);

	Integer getCleByNom(String nom);
	
	String getPresidentIdById(Integer clestructure);
	
	String getPresidentNomById(Integer clestructure);
	
	String getPresidentPrenomById(Integer clestructure);

	

	

}
