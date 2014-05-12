package hei.devweb.dao;

import hei.devweb.model.Eleve;

import java.util.List;

public interface RechercheDao {

	List<Eleve> rechercheByParameter(String ideleve, String nom, String prenom, String classe, String orderBy, Boolean diplome, Boolean etudiant, Boolean ajour, Boolean retard);

	Integer sizeReponse(List<Eleve> eleves);

}
