package hei.devweb.model;

public class Structure {

	Integer cle_structure;
	String structure_nom;
	String structure_president_prenom;
	String structure_president_nom;
	
	
	public Structure(Integer cle_structure, String structure_nom,
			String structure_president_prenom, String structure_president_nom) {
		super();
		this.cle_structure = cle_structure;
		this.structure_nom = structure_nom;
		this.structure_president_prenom = structure_president_prenom;
		this.structure_president_nom = structure_president_nom;
	}


	public Integer getCle_structure() {
		return cle_structure;
	}


	public void setCle_structure(Integer cle_structure) {
		this.cle_structure = cle_structure;
	}


	public String getStructure_nom() {
		return structure_nom;
	}


	public void setStructure_nom(String structure_nom) {
		this.structure_nom = structure_nom;
	}


	public String getStructure_president_prenom() {
		return structure_president_prenom;
	}


	public void setStructure_president_prenom(String structure_president_prenom) {
		this.structure_president_prenom = structure_president_prenom;
	}


	public String getStructure_president_nom() {
		return structure_president_nom;
	}


	public void setStructure_president_nom(String structure_president_nom) {
		this.structure_president_nom = structure_president_nom;
	}
	
}