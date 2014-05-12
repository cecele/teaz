package hei.devweb.dao;

import hei.devweb.model.Eleve;
import hei.devweb.model.Offre;

import java.util.List;

public interface EleveDao {

	List<Eleve> getEleveTotal();

	Eleve getEleveById(String champId);

	List<Eleve> getEleveResponsables(int i);

	void eleveChgtProfil(String id, Integer i);

	void CreateEleve(Eleve eleve);

	List<Eleve> getEleveAjour();
	
	Integer getCleStructureById(String ideleve);
	
	String getElevePrenomById(String ideleve);
	
	String getPromotion(String ideleve);
	
	String getEleveNomById(String ideleve);
	
	boolean president(String ideleve);
	
	Integer getCleClasse(String ideleve);
	

}
