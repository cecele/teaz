package hei.devweb.dao;

import hei.devweb.model.Structure;

import java.util.Date;
import java.util.List;

public interface StructureDao {

	List<Structure> getStructure_OrdreNom();

	void CreateStructure(String nom);

	String getNomStructure(Integer cle_structure);

	Structure getStructureByCle(int id);

	void StructureChangement(String ideleve, Integer idstruct, Date dateDebut,
			Date dateFin);


}
