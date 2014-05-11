package hei.devweb.dao;

import hei.devweb.model.Tea;

import java.util.Date;
import java.util.List;

public interface TeaDao {

	void ajouterTea(Integer id, String matricule);

	List<Tea> getTeaByEleve(String string);

	List<Tea> listerTeaAValider();

	List<Tea> getTeaAValiderByStructure(Integer clestructure, Date datedujour);

	List<Tea> getTeaAValiderByRespTea();

	void teaValidationByStructure(Integer cleTea, Integer cleStructure,
			String matricule);

	void teaValidationByResponsable(Integer cletea);

}
