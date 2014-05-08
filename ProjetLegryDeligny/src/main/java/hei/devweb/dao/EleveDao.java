package hei.devweb.dao;

import hei.devweb.model.Eleve;

import java.util.List;

public interface EleveDao {

	List<Eleve> getEleveTotal();

	Eleve getEleveById(String champId);

	List<Eleve> getEleveResponsables(int i);

	void eleveChgtProfil(String id, Integer i);

}
