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
	// AJOUT/SUPPRESION
//-----------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------
	//création d'un élève
	//acc�s en ecriture (update)
	
	public void CreateEleve(Eleve eleve){
		if (eleve.getId_eleve() !=null || eleve.getEleve_nom() !=null ||eleve.getEleve_prenom() !=null ||eleve.getDate_naissance() !=null ||eleve.getNumrue()!=null ||eleve.getNomrue() !=null ||eleve.getCodepostal() !=null || eleve.getVille()!=null ||eleve.getDate_entree() !=null ){
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();
				java.util.Date utilDate_naissance = eleve.getDate_naissance();
			    java.sql.Date sqlDateNaissance = new java.sql.Date(utilDate_naissance.getTime());
			    
				java.util.Date utilDate_entree = eleve.getDate_entree();
			    java.sql.Date sqlDateEntree = new java.sql.Date(utilDate_entree.getTime());
			    
			    
				// Utiliser la connexion
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement("INSERT INTO `Eleve`(`id_eleve`,`eleve_nom`,`eleve_prenom`,`date_naissance`,`numrue`,`nomrue`,`codepostal`,`ville`,`date_entree`,`cotisant`,`eleve_profil`,`diplome`,`motdepasse`) VALUES(?,?,?,?,?,?,?,?,?,0,0,0,'motdepasse')");
						stmt.setString(1, eleve.getId_eleve());
						stmt.setString(2, eleve.getEleve_nom());
						stmt.setString(3, eleve.getEleve_prenom());
						stmt.setDate(4, sqlDateNaissance);
						stmt.setInt(5, eleve.getNumrue());
						stmt.setString(6, eleve.getNomrue());
						stmt.setString(7, eleve.getCodepostal());
						stmt.setString(8, eleve.getVille());
						stmt.setDate(9, sqlDateEntree);
											
						stmt.executeUpdate();

						// Fermer la connexion
						stmt.close();
						connection.close();

						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
		}
		
	//-----------------------------------------------------------------------------------------------------------------
		//  MISE A JOUR
	//-----------------------------------------------------------------------------------------------------------------
		
//-----------------------------------------------------------------------------------------------------------------
		//Changement du profil d'un �tudiant (0=;1=;2=;) par d�faut le profil est �tudiant de base =0
		//acc�s en ecriture (update)
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
	//-----------------------------------------------------------------------------------------------------------------
	//mise à jour du nombre d'heure de tea due
	//acc�s en ecriture (update)
		public void majTeaByClasse (){
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement("UPDATE classe SET nb_tea=3 WHERE classe LIKE `H2%`");
				stmt.executeUpdate();
				// Fermer la connexion

				stmt.close();
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement("UPDATE classe SET nb_tea=6 WHERE classe LIKE `H3%` OR classe LIKE `H4%` ");
				stmt.executeUpdate();
				// Fermer la connexion

				stmt.close();
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement("UPDATE classe SET nb_tea=0 WHERE classe LIKE `H1%` OR classe LIKE `H5%` OR classe LIKE `CES` ");
				stmt.executeUpdate();
				// Fermer la connexion

				stmt.close();
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
//-----------------------------------------------------------------------------------------------------------------
	// AFFICHAGE
//-----------------------------------------------------------------------------------------------------------------


	//-----------------------------------------------------------------------------------------------------------------
	//r�cup�ration des information d'un eleve en fonction de id (son id est numero de matricule sans le h)
	//acc�s en lecture
	
	public Eleve getEleveById(String ideleve){
		Eleve eleve = new Eleve(null,null,null, null, null, null, null, null, null, null, null,null,null,null, null, null, null);
				
		Connection connection;
		try {
			connection = DataSourceProvider.getDataSource()
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
			results.getString("motdepasse"),
			getPromotion(results.getString("id_eleve")), null, null, null
			
			
			
			);
				
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return eleve;	
	}
	//-----------------------------------------------------------------------------------------------------------------
		//recherche d'�l�ve par nom retourne la liste des �l�ves dont le nom contient la recherche
		//acc�s en lecture
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
			results.getString("motdepasse"),
			getPromotion(results.getString("id_eleve")), null, null, null);
			
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
			//recherche d'�l�ve par classe retourne la liste des el�ves de cette classe quelque soit l'ann�e
			//acc�s en lecture
		public List<Eleve> rechercheEleveByClasse(String classe){
			List<Eleve> eleves = new ArrayList<Eleve>();
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE classe=?");
				stmt.setString(1,classe);
				ResultSet results = stmt.executeQuery();
				
				
				results.next();
				
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
				results.getString("motdepasse"),
				getPromotion(results.getString("id_eleve")), null, null, null);
				
					eleves.add(eleve);	
					
				
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
		//recherche d'�l�ve par classe et ann�e retourne la liste des el�ves de cette classe quelque soit l'ann�e
		//acc�s en lecture
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
			results.getString("motdepasse"),
			getPromotion(results.getString("id_eleve")), null, null, null);
			
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
		//r�cup�ration de la liste totale  des eleves de l'�cole
		//acc�s en lecture
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
			results.getString("motdepasse"),
			getPromotion(results.getString("id_eleve")), null, null, null);
			
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
			//r�cup�ration de la liste totale  des eleves de l'�cole encore en activit� � l'�cole (non diplom�)
			//acc�s en lecture
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
				results.getString("motdepasse"),
				getPromotion(results.getString("id_eleve")), null, null, null);
				
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
		//r�cup�ration de la liste totale  des eleves de l'�cole sorti de l'�cole ( diplom�)
		//acc�s en lecture
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
			results.getString("motdepasse"),
			getPromotion(results.getString("id_eleve")), null, null, null);
			
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
			//Calcul de la promotion de l'�l�ve : recuperation de classe en cours pour le calcul
			//acc�s en lecture
	
	public String getPromotion(String ideleve){
		String classeencours="";
		int cleclasse=0;
		
		// recuperation de la cl� classe la plus recente
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
	
	
}
