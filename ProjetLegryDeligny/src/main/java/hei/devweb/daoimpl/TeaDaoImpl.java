package hei.devweb.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import hei.devweb.dao.TeaDao;
import hei.devweb.metier.Manager;
import hei.devweb.model.Offre;
import hei.devweb.model.Tea;

public class TeaDaoImpl implements TeaDao {
	/**
	 * Récupère une offre en fonction de sa clé
	 * @param cleoffre
	 * 					cle de l'objet offre faisant référence à l'annonce que l'utilisateur a posté
	
	 * @return l'objet offre ayant la cleoffre comme clé de référence
	 */
		public Offre ajouterTeaRecupererOffre (Integer cleoffre ){
			Offre offre= null;
					
					try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement("SELECT * FROM offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE cle_offre=?");
				stmt.setInt(1, cleoffre);
				ResultSet results = stmt.executeQuery();
				results.next();
				offre =new Offre(results.getInt("cle_offre"),
							results.getDate("date_depot"),
							results.getDate("date_miseenligne"),
							results.getDate("date_tea"),
							results.getString("heure_debut"),
							results.getString("heure_fin"),
							results.getInt("statut"),
							results.getString("offre_description"),
							results.getString("eleve_mail"),
							results.getString("offre_titre"),
							results.getInt("cle_structure"),
							results.getInt("offre_place"),
							Manager.getInstance().getNomStructure(results.getInt("cle_structure")),
							Manager.getInstance().getPresidentNomById(results.getInt("cle_structure")),
							Manager.getInstance().getPresidentPrenomById(results.getInt("cle_structure")),
							Manager.getInstance().getNbPlacePourvue(results.getInt("cle_offre"))
							
							);
				
			
			// Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
			}
			
			catch (SQLException e) {
								e.printStackTrace();
							}
			return offre;
			
		}

		/**
		 * Ajout d'une tea à la base 
		 * @param cleoffre
		 * 					cle de l'objet offre faisant référence à l'annonce que l'utilisateur a posté
		 * @param offre
		 * 					offre auquelle la tea va être rattachée
		 * @return ajout d'une tea
		 */
			public void ajouterTea ( Integer cleoffre ,String ideleve ){
					
			Offre offre=  ajouterTeaRecupererOffre (cleoffre);	
			System.out.println(offre.getCle_offre());
			try {
					int hdeb = Integer.parseInt(offre.getHeure_debut());
					int hfin = Integer.parseInt(offre.getHeure_fin());
					int nbtea = hfin - hdeb;
					
						
					Connection connection2 = DataSourceProvider.getDataSource()
							.getConnection();
									    
					// Utiliser la connexion
					
					PreparedStatement stmt2 = (PreparedStatement) connection2
							.prepareStatement("INSERT INTO `tea`(`date_tea_realisee`,`nbheure_realisee`,`statut_valide`,`cle_offre`,`id_eleve`) VALUES(?,?,0,?,?)");
							stmt2.setDate(1, (Date) offre. getDate_tea());
							stmt2.setInt(2, nbtea);
							stmt2.setInt(3, offre.getCle_offre());
							stmt2.setString(4, ideleve);
							stmt2.executeUpdate();

							// Fermer la connexion
							
							stmt2.close();
							
							connection2.close();
			
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
			

			/**
			 * Passage au statut=1 d'une tea non valide par le responsable de la structure
			
			 * @param ideleve 
			 * 					matricule sans le h permettant d'identifier l'élève qui est responsable de la structure quu
			 * @param clestructure
			 * 					cle primaire de la table structure permettant d'identifier la structure
			 * @param cletea
			 * 					cle de référence d'une tea
			 * 
			
			 */
			public void teaValidationByStructure(Integer cletea, Integer clestructure, String ideleve){
				
				if(Manager.getInstance().getPresidentIdById(clestructure).equals(ideleve)){
				
								
				try {
					Connection connection = DataSourceProvider.getDataSource()
							.getConnection();
					
					java.util.Date utilDate = new java.util.Date();
				    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement("UPDATE tea SET statut_valide=1, date_validation=? WHERE cle_tea=? ");
							
					stmt.setDate(1, sqlDate);
					stmt.setInt(2,cletea);	
					stmt.executeUpdate();
					// Fermer la connexion

					stmt.close();
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
			}
			

			/**
			 * Passage au statut=2 d'une tea validé par la structure 
			
			
			 * @param cletea
			 * 					cle de référence d'une tea
			 * 
			
			 */

			public void teaValidationByResponsable(Integer cletea){
				
				int nbtea=getNbHeureTeaRealiseeByTea( cletea);
				int nbteareel=0;
				int nbteadues=getTeaDuesEnCours(getIdeleveByCleTea(cletea));
				
				if(nbteadues>nbtea){nbteareel=nbtea;}
				else {if(nbteadues<nbtea){nbteareel=nbteadues;}
					else{if(nbteadues==0){nbteareel=0;}}};
					
				
								
				try {
					Connection connection = DataSourceProvider.getDataSource()
							.getConnection();
					
					java.util.Date utilDate = new java.util.Date();
				    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					
					
					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement("UPDATE tea SET statut_valide=2, date_validation=?, nbheure_validee=? WHERE cle_tea=? ");
					stmt.setDate(1, sqlDate);
					stmt.setInt(3,cletea);
					stmt.setInt(2, nbteareel);
					stmt.executeUpdate();
					// Fermer la connexion

					stmt.close();
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
			
				

			/**
			 * Retourne la liste des tea liées à une offre
			 * @param cleoffre
			 * 					cle de l'objet offre faisant référence à l'annonce que l'utilisateur a posté
			
			 * @return Liste des teas rattachées à une offre
			 */
			public List<Tea> getTeaByOffre(Integer cleoffre){
				List<Tea> teas = new ArrayList<Tea>();
				try {
					Connection connection = DataSourceProvider.getDataSource()
							.getConnection();

					
					PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre  WHERE tea.cle_offre=?");
					stmt.setInt(1,cleoffre);
					ResultSet results = stmt.executeQuery();
					
					while (results.next()) {
						Tea tea =new Tea(
					results.getInt("cle_tea"),
					results.getDate("date_tea_realisee"),
					results.getInt("nbheure_realisee"),
					results.getInt("nbheure_validee"),
					results.getInt("statut_valide"),
					results.getDate("date_validation"),
					results.getInt("cle_offre"),
					results.getString("id_eleve"),
					results.getDate("date_depot"),
					results.getDate("date_miseenligne"),
					results.getDate("date_tea"),
					results.getString("heure_debut"),
					results.getString("heure_fin"),
					results.getInt("statut"),
					results.getString("offre_description"),
					results.getString("eleve_mail"),
					results.getString("offre_titre"),
					results.getInt("cle_structure"),
					results.getInt("offre_place"),
					Manager.getInstance().getNomStructure(results.getInt("cle_structure")),
					Manager.getInstance().getPresidentNomById(results.getInt("cle_structure")),
					Manager.getInstance().getPresidentPrenomById(results.getInt("cle_structure")),
					Manager.getInstance().getEleveNomById(results.getString("id_eleve")),
					Manager.getInstance().getElevePrenomById(results.getString("id_eleve"))
					);
					
						teas.add(tea);	
						
					}
						// Fermer la connexion
						results.close();
						stmt.close();
						connection.close();
						
				}
					catch (SQLException e) {
										e.printStackTrace();
									}
				
				return teas;
				
			}
			

			/**
			 * Liste des teas rattachées à un élève
			
			 * @param ideleve 
			 * 					matricule sans le h permettant d'identifier l'élève
			
			 * 	@return Liste des teas rattachées à un élève
			 */
			
			public List<Tea> getTeaByEleve(String ideleve){
				List<Tea> teas = new ArrayList<Tea>();
				try {
					Connection connection = DataSourceProvider.getDataSource()
							.getConnection();

					
					PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE id_eleve=?");
					stmt.setString(1,ideleve);
					ResultSet results = stmt.executeQuery();
					
					while (results.next()) {
						Tea tea =new Tea(
					results.getInt("cle_tea"),
					results.getDate("date_tea_realisee"),
					results.getInt("nbheure_realisee"),
					results.getInt("nbheure_validee"),
					results.getInt("statut_valide"),
					results.getDate("date_validation"),
					results.getInt("cle_offre"),
					results.getString("id_eleve"),
					results.getDate("date_depot"),
					results.getDate("date_miseenligne"),
					results.getDate("date_tea"),
					results.getString("heure_debut"),
					results.getString("heure_fin"),
					results.getInt("statut"),
					results.getString("offre_description"),
					results.getString("eleve_mail"),
					results.getString("offre_titre"),
					results.getInt("cle_structure"),
					results.getInt("offre_place"),
					Manager.getInstance().getNomStructure(results.getInt("cle_structure")),
					Manager.getInstance().getPresidentNomById(results.getInt("cle_structure")),
					Manager.getInstance().getPresidentPrenomById(results.getInt("cle_structure")),
					Manager.getInstance().getEleveNomById(results.getString("id_eleve")),
					Manager.getInstance().getElevePrenomById(results.getString("id_eleve"))
					);
					
						teas.add(tea);	
						
					}
						// Fermer la connexion
						results.close();
						stmt.close();
						connection.close();
						
				}
					catch (SQLException e) {
										e.printStackTrace();
									}
					return teas;	
			}
			
			/**
			 * Liste des teas non valides rattachées à une structure (dont le statut est à 0)
			
			* @param clestructure
			*				cle primaire de la table structure permettant d'identifier la structure
			
			 * 	@return Liste des teas non valide rattachées à une structure
			 */

		public List<Tea> getTeaAValiderByStructure(Integer clestructure, java.util.Date datedujour){
		List<Tea> teas = new ArrayList<Tea>();

		java.util.Date utilDate = datedujour;
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre WHERE cle_structure=? AND statut_valide=0 AND date_tea_realisee<?");
			stmt.setInt(1,clestructure);
			stmt.setDate(2,sqlDate);
			ResultSet results = stmt.executeQuery();
			
			while (results.next()) {
				Tea tea =new Tea(
			results.getInt("cle_tea"),
			results.getDate("date_tea_realisee"),
			results.getInt("nbheure_realisee"),
			results.getInt("nbheure_validee"),
			results.getInt("statut_valide"),
			results.getDate("date_validation"),
			results.getInt("cle_offre"),
			results.getString("id_eleve"),
			results.getDate("date_depot"),
			results.getDate("date_miseenligne"),
			results.getDate("date_tea"),
			results.getString("heure_debut"),
			results.getString("heure_fin"),
			results.getInt("statut"),
			results.getString("offre_description"),
			results.getString("eleve_mail"),
			results.getString("offre_titre"),
			results.getInt("cle_structure"),
			results.getInt("offre_place"),
			Manager.getInstance().getNomStructure(results.getInt("cle_structure")),
			Manager.getInstance().getPresidentNomById(results.getInt("cle_structure")),
			Manager.getInstance().getPresidentPrenomById(results.getInt("cle_structure")),
			Manager.getInstance().getEleveNomById(results.getString("id_eleve")),
			Manager.getInstance().getElevePrenomById(results.getString("id_eleve"))
			);
				System.out.println("getTeaAValiderByStructure : requete " + results.getInt("cle_tea"));
			
				teas.add(tea);	
				
			}
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
		}
			catch (SQLException e) {
								e.printStackTrace();
							}
			return teas;	
		}
		/**
		 * Liste des teas  rattachées à une structure 
		
		* @param clestructure
		*				cle primaire de la table structure permettant d'identifier la structure
		
		 * 	@return Liste des teas rattachées à une structure
		 */


	public List<Tea> getTeaByStructure(Integer clestructure){
	List<Tea> teas = new ArrayList<Tea>();

	try {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();

		
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre WHERE cle_structure=? ");
		stmt.setInt(1,clestructure);
		
		ResultSet results = stmt.executeQuery();
		
		while (results.next()) {
			Tea tea =new Tea(
		results.getInt("cle_tea"),
		results.getDate("date_tea_realisee"),
		results.getInt("nbheure_realisee"),
		results.getInt("nbheure_validee"),
		results.getInt("statut_valide"),
		results.getDate("date_validation"),
		results.getInt("cle_offre"),
		results.getString("id_eleve"),
		results.getDate("date_depot"),
		results.getDate("date_miseenligne"),
		results.getDate("date_tea"),
		results.getString("heure_debut"),
		results.getString("heure_fin"),
		results.getInt("statut"),
		results.getString("offre_description"),
		results.getString("eleve_mail"),
		results.getString("offre_titre"),
		results.getInt("cle_structure"),
		results.getInt("offre_place"),
		Manager.getInstance().getNomStructure(results.getInt("cle_structure")),
		Manager.getInstance().getPresidentNomById(results.getInt("cle_structure")),
		Manager.getInstance().getPresidentPrenomById(results.getInt("cle_structure")),
		Manager.getInstance().getEleveNomById(results.getString("id_eleve")),
		Manager.getInstance().getElevePrenomById(results.getString("id_eleve"))
		);
			System.out.println("getTeaAValiderByStructure : requete " + results.getInt("cle_tea"));
		
			teas.add(tea);	
			
		}
			// Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
			
	}
		catch (SQLException e) {
							e.printStackTrace();
						}
		return teas;	
	}

	/**
	 * Liste des teas non valides par le responsable tea
	
	
	 * 	@return Liste des teas non validé par le responsable tea
	 */

		public List<Tea> getTeaAValiderByRespTea(){
		List<Tea> teas = new ArrayList<Tea>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre WHERE statut_valide=1");
			
			ResultSet results = stmt.executeQuery();
			
			while (results.next()) {
				Tea tea =new Tea(
			results.getInt("cle_tea"),
			results.getDate("date_tea_realisee"),
			results.getInt("nbheure_realisee"),
			results.getInt("nbheure_validee"),
			results.getInt("statut_valide"),
			results.getDate("date_validation"),
			results.getInt("cle_offre"),
			results.getString("id_eleve"),
			results.getDate("date_depot"),
			results.getDate("date_miseenligne"),
			results.getDate("date_tea"),
			results.getString("heure_debut"),
			results.getString("heure_fin"),
			results.getInt("statut"),
			results.getString("offre_description"),
			results.getString("eleve_mail"),
			results.getString("offre_titre"),
			results.getInt("cle_structure"),
			results.getInt("offre_place"),
			Manager.getInstance().getNomStructure(results.getInt("cle_structure")),
			Manager.getInstance().getPresidentNomById(results.getInt("cle_structure")),
			Manager.getInstance().getPresidentPrenomById(results.getInt("cle_structure")),
			Manager.getInstance().getEleveNomById(results.getString("id_eleve")),
			Manager.getInstance().getElevePrenomById(results.getString("id_eleve"))
			);

			
				teas.add(tea);	
				
			}
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
		}
			catch (SQLException e) {
								e.printStackTrace();
							}
			return teas;	
		}

		/**
		 * Liste des teas  valides rattachées d'un élève
			
		* @param ideleve 
		 * 					matricule sans le h permettant d'identifier l'élève
		
		 * 	@return Liste des teas  valide d'un élève
		 */

		public List<Tea> getTeaValideByEleve(String ideleve){
		List<Tea> teas = new ArrayList<Tea>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE id_eleve=?AND statut_valide=1");
			stmt.setString(1,ideleve);
			ResultSet results = stmt.executeQuery();
			
			while (results.next()) {
				Tea tea =new Tea(
			results.getInt("cle_tea"),
			results.getDate("date_tea_realisee"),
			results.getInt("nbheure_realisee"),
			results.getInt("nbheure_validee"),
			results.getInt("statut_valide"),
			results.getDate("date_validation"),
			results.getInt("cle_offre"),
			results.getString("id_eleve"),
			results.getDate("date_depot"),
			results.getDate("date_miseenligne"),
			results.getDate("date_tea"),
			results.getString("heure_debut"),
			results.getString("heure_fin"),
			results.getInt("statut"),
			results.getString("offre_description"),
			results.getString("eleve_mail"),
			results.getString("offre_titre"),
			results.getInt("cle_structure"),
			results.getInt("offre_place"),
			Manager.getInstance().getNomStructure(results.getInt("cle_structure")),
			Manager.getInstance().getPresidentNomById(results.getInt("cle_structure")),
			Manager.getInstance().getPresidentPrenomById(results.getInt("cle_structure")),
			Manager.getInstance().getEleveNomById(results.getString("id_eleve")),
			Manager.getInstance().getElevePrenomById(results.getString("id_eleve"))
			);
			
				teas.add(tea);	
				
			}
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
		}
			catch (SQLException e) {
								e.printStackTrace();
							}
			return teas;	
		}
		/**
		 * Liste des teas non valides rattachées d'un élève
			
		* @param ideleve 
		 * 					matricule sans le h permettant d'identifier l'élève
		
		 * 	@return Liste des teas non valide d'un élève
		 */
		public List<Tea> getTeaNonValideByEleve(String ideleve){
		List<Tea> teas = new ArrayList<Tea>();
		try {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();


		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE id_eleve=?AND statut_valide=0");
		stmt.setString(1,ideleve);
		ResultSet results = stmt.executeQuery();

		while (results.next()) {
			Tea tea =new Tea(
		results.getInt("cle_tea"),
		results.getDate("date_tea_realisee"),
		results.getInt("nbheure_realisee"),
		results.getInt("nbheure_validee"),
		results.getInt("statut_valide"),
		results.getDate("date_validation"),
		results.getInt("cle_offre"),
		results.getString("id_eleve"),
		results.getDate("date_depot"),
		results.getDate("date_miseenligne"),
		results.getDate("date_tea"),
		results.getString("heure_debut"),
		results.getString("heure_fin"),
		results.getInt("statut"),
		results.getString("offre_description"),
		results.getString("eleve_mail"),
		results.getString("offre_titre"),
		results.getInt("cle_structure"),
		results.getInt("offre_place"),
		Manager.getInstance().getNomStructure(results.getInt("cle_structure")),
		Manager.getInstance().getPresidentNomById(results.getInt("cle_structure")),
		Manager.getInstance().getPresidentPrenomById(results.getInt("cle_structure")),
		Manager.getInstance().getEleveNomById(results.getString("id_eleve")),
		Manager.getInstance().getElevePrenomById(results.getString("id_eleve"))
		);

			teas.add(tea);	
			
		}
			// Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
			
		}
		catch (SQLException e) {
							e.printStackTrace();
						}
		return teas;	
		}
		
		
				
		
		/**
		 * recupere le matricule de l'élève qui réalise la tea
			
		* * @param cletea
			 * 					cle de référence d'une tea
		
		 * 	@return ideleve 
		 */
			
			public String getIdeleveByCleTea(Integer cletea){
				String ideleve="";
				
				// recuperation de la cl� classe la plus recente
				try {
					Connection connection = DataSourceProvider.getDataSource()
							.getConnection();

					
					PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT id_eleve  FROM tea WHERE cle_tea=?");
					stmt.setInt(1, cletea);
					ResultSet results = stmt.executeQuery();
					results.next();
					ideleve=results.getString("id_eleve");
					
					// Fermer la connexion
					results.close();
					stmt.close();
					connection.close();
					
					}
				catch (SQLException e) {
									e.printStackTrace();
								}
				
					
				return ideleve;
			}
			/**
			 * calcul le nombre d'heure réalisée pour une tea
			* * @param cletea
				 * 					cle de référence d'une tea
			
			 * 	@return nombre d'heure réalisée pour une tea
			 */
			
			public Integer getNbHeureTeaRealiseeByTea(Integer cletea){
				int nbtotal=0;
				
				// recuperation de la cl� classe la plus recente
				try {
					Connection connection = DataSourceProvider.getDataSource()
							.getConnection();

					
					PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT nbheure_realisee  FROM tea WHERE cle_tea=?");
					stmt.setInt(1,cletea);
					ResultSet results = stmt.executeQuery();
					results.next();
					nbtotal=results.getInt("nbheure_realisee");
					
					// Fermer la connexion
					results.close();
					stmt.close();
					connection.close();
					
					}
				catch (SQLException e) {
									e.printStackTrace();
								}
				
					
				return nbtotal;
			}
		
			/**
			 * calcul le nombre d'heure réalisée pour un élève donné
			 * @param ideleve 
			 * 					matricule sans le h permettant d'identifier l'élève
			
			 * 	@return nombre d'heure réalisée par un élève
			 */
	
	public Integer getNbHeureTeaRealisee(String ideleve){
		int nbtotal=0;
		
		// recuperation de la cl� classe la plus recente
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT SUM(nbheure_realisee) as total FROM tea WHERE id_eleve=?");
			stmt.setString(1,ideleve);
			ResultSet results = stmt.executeQuery();
			results.next();
			nbtotal=results.getInt("total");
			
			// Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
			
			}
		catch (SQLException e) {
							e.printStackTrace();
						}
		
			
		return nbtotal;
	}
	
	/**
	 * calcul le nombre d'heure en attente réalisée pour un élève donné
	 * @param ideleve 
	 * 					matricule sans le h permettant d'identifier l'élève
	
	 * 	@return nombre d'heure en attente réalisée par un élève
	 */
	
	public int getNbHeureTeaEnAttente(){
		int nbtotal=0;
		
		// recuperation de la cl� classe la plus recente
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT SUM(nbheure_realisee) as total FROM tea WHERE statut_valide=0");
			
			ResultSet results = stmt.executeQuery();
			results.next();
			nbtotal=results.getInt("total");
			
			// Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
			
			}
		catch (SQLException e) {
							e.printStackTrace();
						}
		
			
		return nbtotal;
	}
	/**
	 * calcul le nombre d'heure valide réalisée pour un élève donné
	 * @param ideleve 
	 * 					matricule sans le h permettant d'identifier l'élève
	
	 * 	@return nombre d'heure valide réalisée par un élève
	 */
		
		public Integer getNbHeureTeaValide(String ideleve){
			int nbtotal=0;
			
			
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT SUM(nbheure_validee) as total FROM tea WHERE id_eleve=? AND statut_valide=2");
				stmt.setString(1,ideleve);
				ResultSet results = stmt.executeQuery();
				results.next();
				nbtotal=results.getInt("total");
				
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
				}
			catch (SQLException e) {
								e.printStackTrace();
							}
			
				
			return nbtotal;
		}
		/**
		 * calcul le nombre d'heure due à l'école pour un élève donné
		 * @param ideleve 
		 * 					matricule sans le h permettant d'identifier l'élève
		
		 * 	@return nombre d'heure dues à l'école réalisée par un élève
		 */
		
		public Integer getNbHeureDues(String ideleve){
			int nbtotal=0;
			
			
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT SUM(classe.nb_tea) as total FROM appartenir INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE appartenir.id_eleve=?");
				stmt.setString(1,ideleve);
				ResultSet results = stmt.executeQuery();
				results.next();
				nbtotal=results.getInt("total");
				
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
				}
			catch (SQLException e) {
								e.printStackTrace();
							}
			
				
			return nbtotal;
		}
		/**
		 * calcul le nombre d'heure en attente réalisée pour un élève donné
		 * @param ideleve 
		 * 					matricule sans le h permettant d'identifier l'élève
		
		 * 	@return nombre d'heure en attente réalisée par un élève
		 */
		
		public Integer getNbHeureEnAttente(String ideleve){
			int nbtotal=0;
			
			
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT SUM(nbheure_realisee) as total FROM tea WHERE id_eleve=? AND (statut_valide=1 OR statut_valide=0)");
				stmt.setString(1,ideleve);
				ResultSet results = stmt.executeQuery();
				results.next();
				nbtotal=results.getInt("total");
				
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
				}
			catch (SQLException e) {
								e.printStackTrace();
							}
			
				
			return nbtotal;
		}
		/**
		 * calcul le nombre d'heure en attente réalisée
		
		
		 * 	@return nombre d'heure en attente 
		 */
				
				public Integer getTeaEnAttente(){
					int nbtotal=0;
					
					
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						
						PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT COUNT(cle_tea) as total FROM tea WHERE statut_valide=1");
						
						ResultSet results = stmt.executeQuery();
						results.next();
						nbtotal=results.getInt("total");
						
						// Fermer la connexion
						results.close();
						stmt.close();
						connection.close();
						
						}
					catch (SQLException e) {
										e.printStackTrace();
									}
					
						
					return nbtotal;
				}
				
				/**
				 * calcul le nombre d'heure en attente réalisée pour un élève donné
				 * @param clestructure
				 * 					cle primaire de la table structure permettant d'identifier la structure
				
				 * 	@return nombre d'heure en attente réalisée pour une structure
				 */

			public Integer getNbTeaAValiderByStructure(Integer clestructure, java.util.Date datedujour){
				Integer nbtotal=0;

			java.util.Date utilDate = datedujour;
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT COUNT(cle_tea) as total FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre WHERE cle_structure=? AND statut_valide=0 AND date_tea_realisee<?");
				stmt.setInt(1,clestructure);
				stmt.setDate(2,sqlDate);
				ResultSet results = stmt.executeQuery();
				results.next();
				nbtotal=results.getInt("total");
				
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				
				}
			catch (SQLException e) {
								e.printStackTrace();
							}
			
				
			return nbtotal;
			}
			

			/**
			 * calcul du nombre de place pourvue pour une offre
			 * @param cleoffre
		 * 					cle de l'objet offre faisant référence à l'annonce que l'utilisateur a posté
			
			 * 	@return calcul du nombre de place pourvue pour une offre
			 */
				
				public Integer getNbPlacePourvue(Integer cleoffre){
					int nbtotal=0;
					
					
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						
						PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT COUNT(cle_tea) as total FROM tea WHERE cle_offre=?");
						stmt.setInt(1,cleoffre);
						ResultSet results = stmt.executeQuery();
						results.next();
						nbtotal=results.getInt("total");
						
						// Fermer la connexion
						results.close();
						stmt.close();
						connection.close();
						
						}
					catch (SQLException e) {
										e.printStackTrace();
									}
					
						
					return nbtotal;
				}

				//-----------------------------------------------------------------------------------------------------------------
				//calcul du nombre d'heure de TEA dues en cours
				//acc�s en lecture
				
				public  Integer getTeaDuesEnCours(String ideleve){
					int nbtotal=0;
					nbtotal=Manager.getInstance().getNbHeureDues(ideleve)-Manager.getInstance().getNbHeureTeaValide(ideleve);
					if(nbtotal<=0)return 0;	return nbtotal;
					
				}
				


			
				

		
}
