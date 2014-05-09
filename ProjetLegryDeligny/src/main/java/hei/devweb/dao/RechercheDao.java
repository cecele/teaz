package hei.devweb.dao;

import hei.devweb.model.Eleve;

import java.util.List;

public interface RechercheDao {

	List<Eleve> rechercheByParameter(String matricule, String nom,
			String prenom, String classe, String orderBy);


}
