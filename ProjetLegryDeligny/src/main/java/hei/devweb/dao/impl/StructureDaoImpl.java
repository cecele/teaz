package hei.devweb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import hei.devweb.dao.StructureDao;
import hei.devweb.model.Structure;

public class StructureDaoImpl implements StructureDao {
	
	//-----------------------------------------------------------------------------------------------------------------
	// recuperation des structures enregistrées à hei par ordre alphabetique de nom de structure
	// acces en lecture
	
	public Structure getStructure_OrdreNom(){
		Structure structure = new Structure(null,null,null);
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM structure ORDER BY structure_nom ASC");
			
			ResultSet results = stmt.executeQuery();
			
			results.next();
			structure = new Structure(
			results.getInt("cle_structure"),
			results.getString("structure_nom"),
			results.getString("structure_president"));
				
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
	
	//-----------------------------------------------------------------------------------------------------------------
		// recuperation des structures enregistrées à hei par ordre alphabetique de nom de président
		// acces en lecture
		
		public Structure getStructure_OrdrePresident(){
			Structure structure = new Structure(null,null,null);
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM structure ORDER BY structure_president ASC");
				
				ResultSet results = stmt.executeQuery();
				
				results.next();
				structure = new Structure(
				results.getInt("cle_structure"),
				results.getString("structure_nom"),
				results.getString("structure_president"));
					
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
//-----------------------------------------------------------------------------------------------------------------
// recuperation de la structure en fonction de l'élève
// acces en lecture
				
				public Structure getStructure_ElevePresident(String ideleve){
					Structure structure = new Structure(null,null,null);
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
						results.getString("structure_president"));
							
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
	//-----------------------------------------------------------------------------------------------------------------
	// creation de mandature pour une structure
	// acces en ecriture
				
				public void StructureChangement (String idelev, Integer clestructure,String annee ){
					// creation de la mandature
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						PreparedStatement stmt = (PreparedStatement) connection
								.prepareStatement("INSERT INTO `presider`(ìd_eleve`,`cle_structure`,`annee_appartenance`) VALUES(?,?,?) ");
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
					
					//mise à jour du nom du président de la structure PUTAIN DE DOUBLE JOINTURE
				}
//-----------------------------------------------------------------------------------------------------------------
//mise à jour du nom du president de la structure 

// acces en ecriture
							
											
	
}
