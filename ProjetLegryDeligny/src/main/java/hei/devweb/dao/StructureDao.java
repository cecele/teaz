package hei.devweb.dao;

import hei.devweb.model.Structure;

import java.util.List;

public interface StructureDao {

	List<Structure> getStructure_OrdreNom();

	void CreateStructure(String nom);

	String getNomStructure(Integer cle_structure);

	Structure getStructureByCle(int id);


}
