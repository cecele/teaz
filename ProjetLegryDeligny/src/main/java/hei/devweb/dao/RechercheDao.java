package hei.devweb.dao;

import hei.devweb.model.Eleve;

import java.util.List;
/**
 * Interface qui permet de faire le lien entre le manager des méthodes et la daoImpl des recherches.
 * 
 * Elle permet d'utiliser des résultats de requetes de base de données qui concernent les recherches avec les servlets.
 * 
 * @author Projet
 *
 */
public interface RechercheDao {

	List<Eleve> rechercheByParameter(String ideleve, String nom, String prenom, String classe, String orderBy, Boolean diplome, Boolean etudiant, Boolean ajour, Boolean retard);

	Integer sizeReponse(List<Eleve> eleves);

}
