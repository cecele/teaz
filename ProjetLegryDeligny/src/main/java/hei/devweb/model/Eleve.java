package hei.devweb.model;

import java.sql.Date;


public class Eleve{
	

	String id_eleve;
	String eleve_nom;
	String eleve_prenom;
	Date date_naissance;
	Integer numrue;
	String nomrue;
	String codepostal;
	String ville;
	String date_entree;
	Integer cotisant;
	Integer eleve_profil;
	Integer diplome;
	String motdepasse;
	String classe;
	Integer teaFaite;
	Integer teaAfaire;
	Integer teaEnAttente;
	Integer cle_structure;
	
	
	/**
	 * Eleve
	 * 
	 * Le modèle Eleve permet de gérer les élèves en fonction de leurs données.
	 * 
	 * @param id_eleve : il est unique
	 * @param eleve_nom : son nom
	 * @param eleve_prenom : son prenom
	 * @param date_naissance : sa date de naissance
	 * @param numrue : son numéro de rue
	 * @param nomrue : le nom de la rue
	 * @param codepostal : le code postal
	 * @param ville : la ville
	 * @param date_entree : sa date d'entrée à HEI
	 * @param cotisant : Integer egal à 0 s'il n'est pas cotisant et à 1 s'il l'est
	 * @param eleve_profil : Integer qui définit son profil utilisateur (0 s'il est élève, 1 s'il est responsable de structure, 2 s'il est responsable TEA, 3 s'il fait partie de l'administration et 4 s'il est super administrateur)
	 * @param diplome : Integer qui vaut 0 s'il nest pas diplomé, 1 sinon
	 * @param motdepasse : son mot de passe
	 * @param classe : sa classe
	 * @param teaFaite : Le nombre d'heures de TEA faites
	 * @param teaAfaire : le nombre d'heures de TEA à faire
	 * @param teaEnAttente : le nombre d'heures de TEA en attente de validation par le responsable TEA
	 * @param cle_structure : la clé de la structure qu'il préside s'il en préside une
	 */
	public Eleve(String id_eleve, String eleve_nom, String eleve_prenom,
			Date date_naissance, Integer numrue, String nomrue,
			String codepostal, String ville, String date_entree,
			Integer cotisant, Integer eleve_profil, Integer diplome,
			String motdepasse, String classe, Integer teaFaite,
			Integer teaAfaire, Integer teaEnAttente, Integer cle_structure) {
		super();
		this.id_eleve = id_eleve;
		this.eleve_nom = eleve_nom;
		this.eleve_prenom = eleve_prenom;
		this.date_naissance = date_naissance;
		this.numrue = numrue;
		this.nomrue = nomrue;
		this.codepostal = codepostal;
		this.ville = ville;
		this.date_entree = date_entree;
		this.cotisant = cotisant;
		this.eleve_profil = eleve_profil;
		this.diplome = diplome;
		this.motdepasse = motdepasse;
		this.classe = classe;
		this.teaFaite = teaFaite;
		this.teaAfaire = teaAfaire;
		this.teaEnAttente = teaEnAttente;
		this.cle_structure = cle_structure;
	}
	public String getId_eleve() {
		return id_eleve;
	}
	public void setId_eleve(String id_eleve) {
		this.id_eleve = id_eleve;
	}
	public String getEleve_nom() {
		return eleve_nom;
	}
	public void setEleve_nom(String eleve_nom) {
		this.eleve_nom = eleve_nom;
	}
	public String getEleve_prenom() {
		return eleve_prenom;
	}
	public void setEleve_prenom(String eleve_prenom) {
		this.eleve_prenom = eleve_prenom;
	}
	public Date getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}
	public Integer getNumrue() {
		return numrue;
	}
	public void setNumrue(Integer numrue) {
		this.numrue = numrue;
	}
	public String getNomrue() {
		return nomrue;
	}
	public void setNomrue(String nomrue) {
		this.nomrue = nomrue;
	}
	public String getCodepostal() {
		return codepostal;
	}
	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getDate_entree() {
		return date_entree;
	}
	public void setDate_entree(String date_entree) {
		this.date_entree = date_entree;
	}
	public Integer getCotisant() {
		return cotisant;
	}
	public void setCotisant(Integer cotisant) {
		this.cotisant = cotisant;
	}
	public Integer getEleve_profil() {
		return eleve_profil;
	}
	public void setEleve_profil(Integer eleve_profil) {
		this.eleve_profil = eleve_profil;
	}
	public Integer getDiplome() {
		return diplome;
	}
	public void setDiplome(Integer diplome) {
		this.diplome = diplome;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public Integer getTeaFaite() {
		return teaFaite;
	}
	public void setTeaFaite(Integer teaFaite) {
		this.teaFaite = teaFaite;
	}
	public Integer getTeaAfaire() {
		return teaAfaire;
	}
	public void setTeaAfaire(Integer teaAfaire) {
		this.teaAfaire = teaAfaire;
	}
	public Integer getTeaEnAttente() {
		return teaEnAttente;
	}
	public void setTeaEnAttente(Integer teaEnAttente) {
		this.teaEnAttente = teaEnAttente;
	}
	public Integer getCle_structure() {
		return cle_structure;
	}
	public void setCle_structure(Integer cle_structure) {
		this.cle_structure = cle_structure;
	}
	
	
	}