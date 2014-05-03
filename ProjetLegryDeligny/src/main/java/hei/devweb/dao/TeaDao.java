package hei.devweb.dao;

import hei.devweb.model.Tea;

import java.util.List;

public interface TeaDao {

	void ajouterTea(Integer id, String matricule);

	List<Tea> getTeaByEleve(String string);

}
