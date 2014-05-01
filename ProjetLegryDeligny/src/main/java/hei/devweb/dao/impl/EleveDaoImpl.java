package hei.devweb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import hei.devweb.dao.EleveDao;
import hei.devweb.model.Eleve;

public class EleveDaoImpl implements EleveDao {
	
	//-----------------------------------------------------------------------------------------------------------------
	//récupération des information d'un eleve en fonction de id (son id est numero de matricule sans le h)
	//accès en lecture
	
	public Eleve getEleveById(String ideleve){
		Eleve eleve = new Eleve(null,null,null, null, null, null, null, null, null, null, null,null,null);
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE id_eleve=?");
			stmt.setString(1,ideleve);
			ResultSet results = stmt.executeQuery();
			
			results.next();
			eleve = new Eleve(
			results.getString("id_eleve"),
			results.getString("eleve_nom"),
			results.getString("eleve_prenom"),
			results.getDate("date_naissance"),
			results.getInt("numrue"),
			results.getString("nomrue"),
			results.getString("codepostal"),
			results.getString("ville"),
			results.getDate("date_entree"),
			results.getInt("cotisant"),
			results.getInt("eleve_profil"),
			results.getInt("eleve_profil"),
			results.getString("motdepasse"));
				
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
		}
			catch (SQLException e) {
								e.printStackTrace();
							}
			return eleve;	
	}
	//-----------------------------------------------------------------------------------------------------------------
		//recherche d'élève par nom retourne la liste des élèves dont le nom contient la recherche
		//accès en lecture
	public List<Eleve> rechercheEleveByNom(String nom){
		List<Eleve> eleves = new ArrayList<Eleve>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE Contains(structure_nom, ?)>0");
			stmt.setString(1,nom);
			ResultSet results = stmt.executeQuery();
			
			while (results.next()) {
				Eleve eleve =new Eleve(
			results.getString("id_eleve"),
			results.getString("eleve_nom"),
			results.getString("eleve_prenom"),
			results.getDate("date_naissance"),
			results.getInt("numrue"),
			results.getString("nomrue"),
			results.getString("codepostal"),
			results.getString("ville"),
			results.getDate("date_entree"),
			results.getInt("cotisant"),
			results.getInt("eleve_profil"),
			results.getInt("diplome"),
			results.getString("motdepasse"));
			
				eleves.add(eleve);	
				
			}
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
		}
			catch (SQLException e) {
								e.printStackTrace();
							}
			return eleves;	
	}
	//-----------------------------------------------------------------------------------------------------------------
			//recherche d'élève par classe retourne la liste des elèves de cette classe quelque soit l'année
			//accès en lecture
		public List<Eleve> rechercheEleveByClasse(String classe){
			List<Eleve> eleves = new ArrayList<Eleve>();
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE classe=?");
				stmt.setString(1,classe);
				ResultSet results = stmt.executeQuery();
				
				while (results.next()) {
					Eleve eleve =new Eleve(
				results.getString("id_eleve"),
				results.getString("eleve_nom"),
				results.getString("eleve_prenom"),
				results.getDate("date_naissance"),
				results.getInt("numrue"),
				results.getString("nomrue"),
				results.getString("codepostal"),
				results.getString("ville"),
				results.getDate("date_entree"),
				results.getInt("cotisant"),
				results.getInt("eleve_profil"),
				results.getInt("diplome"),
				results.getString("motdepasse"));
				
					eleves.add(eleve);	
					
				}
					// Fermer la connexion
					results.close();
					stmt.close();
					connection.close();
					
			}
				catch (SQLException e) {
									e.printStackTrace();
								}
				return eleves;	
		}
		//-----------------------------------------------------------------------------------------------------------------
		//recherche d'élève par classe et année retourne la liste des elèves de cette classe quelque soit l'année
		//accès en lecture
	public List<Eleve> rechercheEleveByClasse(String classe, String annee){
		List<Eleve> eleves = new ArrayList<Eleve>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE classe=? AND annee=?");
			stmt.setString(1,classe);
			stmt.setString(2, annee);
			ResultSet results = stmt.executeQuery();
			
			while (results.next()) {
				Eleve eleve =new Eleve(
			results.getString("id_eleve"),
			results.getString("eleve_nom"),
			results.getString("eleve_prenom"),
			results.getDate("date_naissance"),
			results.getInt("numrue"),
			results.getString("nomrue"),
			results.getString("codepostal"),
			results.getString("ville"),
			results.getDate("date_entree"),
			results.getInt("cotisant"),
			results.getInt("eleve_profil"),
			results.getInt("diplome"),
			results.getString("motdepasse"));
			
				eleves.add(eleve);	
				
			}
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
		}
			catch (SQLException e) {
								e.printStackTrace();
							}
			return eleves;	
	}
	//-----------------------------------------------------------------------------------------------------------------
		//récupération de la liste totale  des eleves de l'école
		//accès en lecture
	public List<Eleve> getEleveTotal(){
		List<Eleve> eleves = new ArrayList<Eleve>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve");
			ResultSet results = stmt.executeQuery();
			
			while (results.next()) {
				Eleve eleve =new Eleve(
			results.getString("id_eleve"),
			results.getString("eleve_nom"),
			results.getString("eleve_prenom"),
			results.getDate("date_naissance"),
			results.getInt("numrue"),
			results.getString("nomrue"),
			results.getString("codepostal"),
			results.getString("ville"),
			results.getDate("date_entree"),
			results.getInt("cotisant"),
			results.getInt("eleve_profil"),
			results.getInt("diplome"),
			results.getString("motdepasse"));
			
				eleves.add(eleve);	
				
			}
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
		}
			catch (SQLException e) {
								e.printStackTrace();
							}
			return eleves;	
	}
	//-----------------------------------------------------------------------------------------------------------------
			//récupération de la liste totale  des eleves de l'école encore en activité à l'école (non diplomé)
			//accès en lecture
		public List<Eleve> getEleveTotalEnCours(){
			List<Eleve> eleves = new ArrayList<Eleve>();
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE dilpome=0");
				ResultSet results = stmt.executeQuery();
				
				while (results.next()) {
					Eleve eleve =new Eleve(
				results.getString("id_eleve"),
				results.getString("eleve_nom"),
				results.getString("eleve_prenom"),
				results.getDate("date_naissance"),
				results.getInt("numrue"),
				results.getString("nomrue"),
				results.getString("codepostal"),
				results.getString("ville"),
				results.getDate("date_entree"),
				results.getInt("cotisant"),
				results.getInt("eleve_profil"),
				results.getInt("diplome"),
				results.getString("motdepasse"));
				
					eleves.add(eleve);	
					
				}
					// Fermer la connexion
					results.close();
					stmt.close();
					connection.close();
					
			}
				catch (SQLException e) {
									e.printStackTrace();
								}
				return eleves;	
		}
		//-----------------------------------------------------------------------------------------------------------------
		//récupération de la liste totale  des eleves de l'école sorti de l'école ( diplomé)
		//accès en lecture
	public List<Eleve> getEleveTotalDiplome(){
		List<Eleve> eleves = new ArrayList<Eleve>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE dilpome=1");
			ResultSet results = stmt.executeQuery();
			
			while (results.next()) {
				Eleve eleve =new Eleve(
			results.getString("id_eleve"),
			results.getString("eleve_nom"),
			results.getString("eleve_prenom"),
			results.getDate("date_naissance"),
			results.getInt("numrue"),
			results.getString("nomrue"),
			results.getString("codepostal"),
			results.getString("ville"),
			results.getDate("date_entree"),
			results.getInt("cotisant"),
			results.getInt("eleve_profil"),
			results.getInt("diplome"),
			results.getString("motdepasse"));
			
				eleves.add(eleve);	
				
			}
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
		}
			catch (SQLException e) {
								e.printStackTrace();
							}
			return eleves;	
	}
	//-----------------------------------------------------------------------------------------------------------------
			//Calcul de la promotion de l'élève : recuperation de classe en cours pour le calcul
			//accès en lecture
	
	public String getPromotion(String ideleve){
		String classeencours="";
		int cleclasse=0;
		
		// recuperation de la clé classe la plus recente
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT cle_classe FROM appartenir WHERE id_eleve=? ORDER BY cle_classe DESC LIMIT 1");
			stmt.setString(1,ideleve);
			ResultSet results = stmt.executeQuery();
			
			cleclasse=results.getInt("cle_classe");
			
			// Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
			
			}
		catch (SQLException e) {
							e.printStackTrace();
						}
		// recuperation de la classe en cours 
		try {
			Connection connection2 = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt2 = (PreparedStatement) connection2.prepareStatement("SELECT classe FROM classe WHERE cle_classe=?");
			stmt2.setInt(1,cleclasse);
			ResultSet results2 = stmt2.executeQuery();
			
			classeencours=results2.getString("classe");
			
			// Fermer la connexion
			results2.close();
			stmt2.close();
			connection2.close();
			
			}
		catch (SQLException e) {
							e.printStackTrace();
						}
		
			
		return classeencours;
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	//Changement du profil d'un étudiant (0=;1=;2=;) par défaut le profil est étudiant de base =0
	//accès en ecriture (update)
	public void eleveChgtProfil (Integer ideleve, Integer profil){
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("UPDATE eleve SET eleve_profil=? WHERE id_eleve=?");
			stmt.setInt(1,profil);
			stmt.setInt(2,ideleve);	
			stmt.executeUpdate();
			// Fermer la connexion

			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
