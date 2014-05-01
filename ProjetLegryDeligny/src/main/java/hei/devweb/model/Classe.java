package hei.devweb.model;

 public class Classe {
	 public Classe(Integer cle_classe, String classe,String annee) {
		super();
		this.cle_classe = cle_classe;
		this.classe = classe;
		this.annee=annee;
		
	}
	Integer cle_classe;
	 String classe;
	 String domaine;
	 String annee;
	public Integer getCle_classe() {
		return cle_classe;
	}
	public void setCle_classe(Integer cle_classe) {
		this.cle_classe = cle_classe;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}

 }
