package hei.devweb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import hei.devweb.dao.StructureDao;
import hei.devweb.model.Eleve;
import hei.devweb.model.Structure;

public class StructureDaoImpl implements StructureDao {
//-----------------------------------------------------------------------------------------------------------------
	// AJOUT/SUPPRESION
//-----------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------
		// creation de mandature pour une structure
		// acces en ecriture
					
					public void StructureChangement (String idelev, Integer clestructure,String annee ){
						// creation de la mandature
						try {
							Connection connection = DataSourceProvider.getDataSource()
									.getConnection();

							PreparedStatement stmt = (PreparedStatement) connection
									.prepareStatement("INSERT INTO `presider`(id_eleve`,`cle_structure`,`annee_appartenance`) VALUES(?,?,?) ");
							stmt.setString(1,idelev);
							stmt.setInt(2,clestructure);
							stmt.setString(3, annee);
							stmt.executeUpdate();
							// Fermer la connexion

							stmt.close();
							connection.close();

						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						
					}
					
	//-----------------------------------------------------------------------------------------------------------------
	// creation d'une structure
	// acces en ecriture		
					
					public void CreateStructure(String nom){
						try {
							Connection connection = DataSourceProvider.getDataSource()
									.getConnection();

							PreparedStatement stmt = (PreparedStatement) connection
									.prepareStatement("INSERT INTO `structure`(structure_nom`) VALUES(?) ");
							stmt.setString(1,nom);
							stmt.executeUpdate();
							// Fermer la connexion

							stmt.close();
							connection.close();

						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						
					}
						
					
					
//-----------------------------------------------------------------------------------------------------------------
	// MODIFICATION ET MISE A JOUR
//-----------------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------------------------
//mise à jour du nom d'une comission en fonction de sa clé_structure
//acc�s en ecriture (update)
					public void StructureChangeNom (Integer cle_structure, String nom){
						try {
							Connection connection = DataSourceProvider.getDataSource()
									.getConnection();

							PreparedStatement stmt = (PreparedStatement) connection
									.prepareStatement("UPDATE structure SET structure_nom=? WHERE cle_structure=?");
							stmt.setString(1,nom);
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
// recuperation du nom du préseident en fonction de la cle_structure
// acces en lecture
	public static String getPresidentNomById(Integer clestructure){
					String res="";
						try {
							Connection connection = DataSourceProvider.getDataSource()
									.getConnection();

							
							
							PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT eleve_nom FROM eleve INNER JOIN presider ON eleve.id_eleve=presider.id_eleve WHERE presider.cle_structure=? ");
							stmt.setInt(1,clestructure);
							ResultSet results = stmt.executeQuery();
							results.next();
							res= results.getString("eleve_nom");
							// Fermer la connexion
							results.close();
							stmt.close();
							connection.close();
							
					}
						catch (SQLException e) {
											e.printStackTrace();
										}
						return res;
						
					}
	
	//-----------------------------------------------------------------------------------------------------------------
	// recuperation du prenom du préseident en fonction de la cle_structure
	// acces en lecture
		public static String getPresidentPrenomById(Integer clestructure){
						String res="";
							try {
								Connection connection = DataSourceProvider.getDataSource()
										.getConnection();

								
								
								PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT eleve_prenom FROM eleve INNER JOIN presider ON eleve.id_eleve=presider.id_eleve WHERE presider.cle_structure=? ");
								stmt.setInt(1,clestructure);
								ResultSet results = stmt.executeQuery();
								results.next();
								res= results.getString("eleve_prenom");
								// Fermer la connexion
								results.close();
								stmt.close();
								connection.close();
								
						}
							catch (SQLException e) {
												e.printStackTrace();
											}
							return res;
							
						}
		
									
	//-----------------------------------------------------------------------------------------------------------------
	// recuperation des structures enregistr�es � hei par ordre alphabetique de nom de structure
	// acces en lecture
	
	public List<Structure> getStructure_OrdreNom(){
		List<Structure> structures = new ArrayList<Structure>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM structure ORDER BY structure_nom ASC");
			
			ResultSet results = stmt.executeQuery();
			
			while (results.next()) {
			Structure structure = new Structure(
			results.getInt("cle_structure"),
			results.getString("structure_nom"),
			getPresidentNomById(results.getInt("cle_structure")),
			getPresidentPrenomById(results.getInt("cle_structure")));
			
			structures.add(structure);
			}
			
				
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
		}
			catch (SQLException e) {
								e.printStackTrace();
							}
			return structures;	
	}
	

//-----------------------------------------------------------------------------------------------------------------
// recuperation de la structure en fonction de l'�l�ve
// acces en lecture
				
				public Structure getStructure_ElevePresident(String ideleve){
					Structure structure = new Structure(null,null,null,null);
					Integer clestruct= 0;
					
					try {
						Connection connection2 = DataSourceProvider.getDataSource()
								.getConnection();

						
						PreparedStatement stmt2 = (PreparedStatement) connection2.prepareStatement("SELECT * FROM presider WHERE id_eleve=?");
						stmt2.setString(1,ideleve);
						ResultSet results2 = stmt2.executeQuery();
						
						results2.next();
						clestruct = results2.getInt("cle_structure");
						
							
							// Fermer la connexion
							results2.close();
							stmt2.close();
							connection2.close();
							
					}
						catch (SQLException e) {
											e.printStackTrace();
										}
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						
						PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM structure WHERE cle_structure=?");
						stmt.setInt(1,clestruct);
						ResultSet results = stmt.executeQuery();
						
						results.next();
						structure = new Structure(
						results.getInt("cle_structure"),
						results.getString("structure_nom"),
						getPresidentNomById(results.getInt("cle_structure")),
						getPresidentPrenomById(results.getInt("cle_structure")));
							
							// Fermer la connexion
							results.close();
							stmt.close();
							connection.close();
							
					}
						catch (SQLException e) {
											e.printStackTrace();
										}
						return structure;	
				}
	
			
											
	
}
