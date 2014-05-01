package hei.devweb.model;

public class Structure {

	Integer cle_structure;
	String structure_nom;
	String structure_president;
	
	public Structure(Integer cle_structure, String structure_nom,
			String structure_president) {
		super();
		this.cle_structure = cle_structure;
		this.structure_nom = structure_nom;
		this.structure_president = structure_president;
		
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
	public String getStructure_president() {
		return structure_president;
	}
	public void setStructure_president(String structure_president) {
		this.structure_president = structure_president;
	}
	
	
	
}
