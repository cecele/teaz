package hei.devweb.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import hei.devweb.dao.StructureDao;
import hei.devweb.model.Structure;

public class StructureDaoImpl implements StructureDao {
//-----------------------------------------------------------------------------------------------------------------
	// AJOUT/SUPPRESION
//-----------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------
		// creation de mandature pour une structure
		// acces en ecriture
					
					public void StructureChangement (String idelev, Integer clestructure,Date datedebut, Date datefin ){
						// creation de la mandature
						
						java.util.Date utilDate_debut = datedebut;
					    java.sql.Date sqlDatedebut = new java.sql.Date(utilDate_debut.getTime());
					    
					    java.util.Date utilDate_fin = datefin;
					    java.sql.Date sqlDatefin = new java.sql.Date(utilDate_fin.getTime()); 
					    
						try {
							Connection connection = DataSourceProvider.getDataSource()
									.getConnection();

							PreparedStatement stmt = (PreparedStatement) connection
									.prepareStatement("INSERT INTO `presider`(`id_eleve`,`cle_structure`,`date_debut`,`date_fin`) VALUES(?,?,?,?) ");
							stmt.setString(1,idelev);
							stmt.setInt(2,clestructure);
							stmt.setDate(3, sqlDatedebut);
							stmt.setDate(4, sqlDatefin);
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
									.prepareStatement("INSERT INTO `structure`(`structure_nom`) VALUES(?) ");
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
	// recuperation du de la clé structure en fonction du niom de la structure
	// acces en lecture
		public  Integer getCleByNom(String structurenom){
						int res=0;
							try {
								Connection connection = DataSourceProvider.getDataSource()
										.getConnection();

								
								
								PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT cle_structure FROM structure WHERE cle_structure=? ");
								stmt.setString(1,structurenom);
								ResultSet results = stmt.executeQuery();
								results.next();
								res= results.getInt("cle_structure");
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
		// recuperation du prenom du préseident en fonction de la cle_structure
		// acces en lecture
		public  String getPresidentIdById(Integer clestructure){
							String res="";
								try {
									Connection connection = DataSourceProvider.getDataSource()
											.getConnection();

									
									
									PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT id_eleve FROM presider WHERE presider.cle_structure=? ");
									stmt.setInt(1,clestructure);
									ResultSet results = stmt.executeQuery();
									results.next();
									res= results.getString("id_eleve");
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
		// recuperation du nom de la structure
		// acces en lecture
			public  String getNomStructure(Integer clestructure){
							String res="";
								try {
									Connection connection = DataSourceProvider.getDataSource()
											.getConnection();

									
									
									PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT structure_nom FROM structure WHERE cle_structure=? ");
									stmt.setInt(1,clestructure);
									ResultSet results = stmt.executeQuery();
									results.next();
									res= results.getString("structure_nom");
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
			public static  String getNomStructureStatic(Integer clestructure){
				String res="";
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						
						
						PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT structure_nom FROM structure WHERE cle_structure=? ");
						stmt.setInt(1,clestructure);
						ResultSet results = stmt.executeQuery();
						results.next();
						res= results.getString("structure_nom");
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
			getPresidentPrenomById(results.getInt("cle_structure")),
			getPresidentNomById(results.getInt("cle_structure")));
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
						getPresidentPrenomById(results.getInt("cle_structure")),
						getPresidentNomById(results.getInt("cle_structure")));
							
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
	
			// Récup de la structure en fonction de sa clé
				
				public Structure getStructureByCle(int cleStructure){
					Structure structure = new Structure(null,null,null,null);
					
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						
						PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM structure WHERE cle_structure=?");
						stmt.setInt(1,cleStructure);
						ResultSet results = stmt.executeQuery();
						
						results.next();
						structure = new Structure(
						results.getInt("cle_structure"),
						results.getString("structure_nom"),						
						getPresidentPrenomById(results.getInt("cle_structure")),
						getPresidentNomById(results.getInt("cle_structure")));
							
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
				//r�cup�ration des noms et pr�sident de la structure pour une offre particuli�re. Dans le cas d'un professeur le pr�sident de la structure sera l'enseignant et une structure enseignant est cr�e
				//acc�s en lecture
				public Structure getStructure(Integer cle_offre){
					Structure structure = new Structure(null,null,null,null);
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						
						PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT structure_nom,structure_president FROM structure INNER JOIN offre ON structure.cle_offre=offre.cle_offre  WHERE cle_offre=?");
						stmt.setInt(1,cle_offre);
						ResultSet results = stmt.executeQuery();
						
						results.next();
						structure = new Structure(
						results.getInt("cle_structure"),
						results.getString("structure_nom"),
						StructureDaoImpl.getPresidentNomById(results.getInt("cle_structure")),
						StructureDaoImpl.getPresidentPrenomById(results.getInt("cle_structure"))
							);
							
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
