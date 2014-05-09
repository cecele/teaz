package hei.devweb.dao;

import hei.devweb.model.Eleve;

import java.util.List;

public interface EleveDao {

	List<Eleve> getEleveTotal();

	Eleve getEleveById(String champId);

	List<Eleve> getEleveResponsables(int i);

	void eleveChgtProfil(String id, Integer i);

	List<Eleve> rechercheEleveByNom(String nom);

	List<Eleve> rechercheEleveByClasse(String classe);

}
