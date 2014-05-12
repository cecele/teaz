package hei.devweb.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import hei.devweb.dao.StructureDao;
import hei.devweb.metier.Manager;
import hei.devweb.model.Structure;
import hei.devweb.model.Tea;

public class StructureDaoImpl implements StructureDao {
	/**
	 * Permet de créer un lien entre un élève et une structure
	 
	 * @param ideleve 
	 * 					matricule sans le h permettant d'identifier l'élève
	 *  @param clestructure
	*				  	cle primaire de la table structure permettant d'identifier la structure
	* @param datedebut
	* 					date de début de prise de fonction au sein de l'association
	* @param datefin
	* 					date de fin de fonction au sein de l'association
	 */
					
					public void StructureChangement (String ideleve, Integer clestructure,Date datedebut, Date datefin ){
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
							stmt.setString(1,ideleve);
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
					



					/**
					 * Retourne vrai ou faux afin de savoir si un élève a déjà postulé à une offre
					*@param nom 
					*			nom de la commission à créer tiré du poste exemple Integrale-Président
					 */
					
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
						
					
					

/**
 * Retourne le nom du president d'une association

 * @param clestructure
 * 					cle primaire de la table structure permettant d'identifier la structure
 * @return le nom du président d'une comission
 */

	public  String getPresidentNomById(Integer clestructure){
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
	/**
	 * Retourne la clé de la structure dont on connait le nom 

	 * @return la clé de la structure dont on connait le nom
	 */
	
		public  Integer getCleByNom(String structurenom){
						int res=0;
							try {
								Connection connection = DataSourceProvider.getDataSource()
										.getConnection();

								
								
								PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT cle_structure FROM structure WHERE structure_nom=? ");
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
		/**
		 * Retourne le prenom du président d'une commission dont on connait la clé
		
		 * @param clestructure
		 * 					cle primaire de la table structure permettant d'identifier la structure
		 * @return le prénom du président d'une structure
		 */
		
		public String getPresidentPrenomById(Integer clestructure){
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
		

		/**
		 * Retourne le matricule du président d'une commission dont on connait la clé
		
		 * @param clestructure
		 * 					cle primaire de la table structure permettant d'identifier la structure
		 * @return le matricule du président d'une structure
		 */
		
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
		

		/**
		 * Retourne le nom d'une structure d'une commission dont on connait la clé
		
		 * @param clestructure
		 * 					cle primaire de la table structure permettant d'identifier la structure
		 * @return le nom de cette structure
		 */
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
			/**
			 * Retourne la liste des structures dans la base
			 
			 * @return la liste des strucutres de la base
			 */
	
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
	

	/**
	 * Retourne la structure rattachée à au matricule d'un élève

	 * @return Retourne la structure rattachée à au matricule d'un élève
	 */
				
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
											

}
