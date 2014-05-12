package hei.devweb.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import hei.devweb.dao.EleveDao;
import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Offre;
/**
 * <b> La Classe EleveDaoImpl gère l'ensemble des classes permettant l'accès à la BDD afin de modifier les Eleves qui sont injectés en masse dans la BDD </b>
 * @author Celine
 */
public class EleveDaoImpl implements EleveDao {
	/**
	 * Permet d'ajouter un élève (normalement n'a pas d'utilité ici)
	 * @param Eleve
	 * 					Objet Eleve contenant toutes informations relatives à ce dernier
	 * @param cleoffre
	 * 					cle de l'objet offre faisant référence à l'annonce que l'utilisateur a posté
	 * @param ideleve 
	 * 					matricule sans le h permettant d'identifier l'élève
	 * @param clestructure
	 * 					cle primaire de la table structure permettant d'identifier la structure
	 * @return Booleen retournant true si un élève a deja postulé, false sinon
	 */

	public void CreateEleve(Eleve eleve){
		if (eleve.getId_eleve() !=null || eleve.getEleve_nom() !=null ||eleve.getEleve_prenom() !=null ||eleve.getDate_naissance() !=null ||eleve.getNumrue()!=null ||eleve.getNomrue() !=null ||eleve.getCodepostal() !=null || eleve.getVille()!=null ||eleve.getDate_entree() !=null ){
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();
				java.util.Date utilDate_naissance = eleve.getDate_naissance();
			    java.sql.Date sqlDateNaissance = new java.sql.Date(utilDate_naissance.getTime());
			    
				
			    
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
						stmt.setString(9, eleve.getDate_entree());
											
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
		//Changement du profil d'un �tudiant par d�faut le profil est �tudiant de base =0
		//acc�s en ecriture (update)
		//test junit
		public void eleveChgtProfil (String ideleve, Integer profil){
			
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement("UPDATE eleve SET eleve_profil=? WHERE id_eleve=?");
				stmt.setInt(1,profil);
				stmt.setString(2,ideleve);	
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
	//test junit non nécessaire pour l'instant
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
		// AFFICHAGE LISTE
	//-----------------------------------------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------------------------------------
				//r�cup�ration de la liste totale  des eleves de l'�cole
				//acc�s en lecture
				// test junit pas ok diplome?
						
			public List<Eleve> getEleveTotal(){
				List<Eleve> eleves = new ArrayList<Eleve>();
				try {
					Connection connection = DataSourceProvider.getDataSource()
							.getConnection();

					
					PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve");
					ResultSet results = stmt.executeQuery();
					
					while (results.next()) {
						Eleve 		eleve = new Eleve(
								results.getString("id_eleve"),
								results.getString("eleve_nom"),
								results.getString("eleve_prenom"),
								results.getDate("date_naissance"),
								results.getInt("numrue"),
								results.getString("nomrue"),
								results.getString("codepostal"),
								results.getString("ville"),
								results.getString("date_entree"),
								results.getInt("cotisant"),
								results.getInt("eleve_profil"),
								results.getInt("eleve_profil"),
								results.getString("motdepasse"),
								Manager.getInstance().getPromotion(results.getString("id_eleve")),
								Manager.getInstance().getNbHeureTeaValide(results.getString("id_eleve")),
								Manager.getInstance().getTeaDuesEnCours(results.getString("id_eleve")),
								Manager.getInstance().getNbHeureEnAttente(results.getString("id_eleve")),
								null
								);
						
						if(president(results.getString("id_eleve"))) 
							eleve.setCle_structure(getCleStructureById(results.getString("id_eleve")));
						if(!results.getString("id_eleve").equals("99999"))	
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
					//test junit 
							
				public List<Eleve> getEleveEnCours(){
					List<Eleve> eleves = new ArrayList<Eleve>();
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						
						PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE diplome=0");
						ResultSet results = stmt.executeQuery();
						
						while (results.next()) {
							Eleve 		eleve = new Eleve(
									results.getString("id_eleve"),
									results.getString("eleve_nom"),
									results.getString("eleve_prenom"),
									results.getDate("date_naissance"),
									results.getInt("numrue"),
									results.getString("nomrue"),
									results.getString("codepostal"),
									results.getString("ville"),
									results.getString("date_entree"),
									results.getInt("cotisant"),
									results.getInt("eleve_profil"),
									results.getInt("eleve_profil"),
									results.getString("motdepasse"),
									Manager.getInstance().getPromotion(results.getString("id_eleve")),
									Manager.getInstance().getNbHeureTeaValide(results.getString("id_eleve")),
									Manager.getInstance().getTeaDuesEnCours(results.getString("id_eleve")),
									Manager.getInstance().getNbHeureEnAttente(results.getString("id_eleve")),
									null
									);
							
							if(president(results.getString("id_eleve"))) 
								eleve.setCle_structure(getCleStructureById(results.getString("id_eleve")));
							if(!results.getString("id_eleve").equals("99999"))		
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
			//récupération des diplome pas à jours
			//acc�s en lecture
			// test junit
					
							
				public List<Eleve> getEleveDiplomePasAjours(){
					List<Eleve> eleves = new ArrayList<Eleve>();
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						
						PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE diplome=1");
						ResultSet results = stmt.executeQuery();
						
						while (results.next()) {
							Eleve 		eleve = new Eleve(
									results.getString("id_eleve"),
									results.getString("eleve_nom"),
									results.getString("eleve_prenom"),
									results.getDate("date_naissance"),
									results.getInt("numrue"),
									results.getString("nomrue"),
									results.getString("codepostal"),
									results.getString("ville"),
									results.getString("date_entree"),
									results.getInt("cotisant"),
									results.getInt("eleve_profil"),
									results.getInt("eleve_profil"),
									results.getString("motdepasse"),
									Manager.getInstance().getPromotion(results.getString("id_eleve")),
									Manager.getInstance().getNbHeureTeaValide(results.getString("id_eleve")),
									Manager.getInstance().getTeaDuesEnCours(results.getString("id_eleve")),
									Manager.getInstance().getNbHeureEnAttente(results.getString("id_eleve")),
									null
									);
							
							if(president(results.getString("id_eleve"))) 
								eleve.setCle_structure(getCleStructureById(results.getString("id_eleve")));
								
							if(Manager.getInstance().getTeaDuesEnCours(results.getString("id_eleve"))!=0 && !results.getString("id_eleve").equals("99999"))
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
				//récupération des élèves à jours
				//acc�s en lecture
				
						
			public  List<Eleve> getEleveAjour(){
				List<Eleve> eleves = new ArrayList<Eleve>();
				try {
					Connection connection = DataSourceProvider.getDataSource()
							.getConnection();

					
					PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve");
					ResultSet results = stmt.executeQuery();
					
					while (results.next()) {
						Eleve 		eleve = new Eleve(
								results.getString("id_eleve"),
								results.getString("eleve_nom"),
								results.getString("eleve_prenom"),
								results.getDate("date_naissance"),
								results.getInt("numrue"),
								results.getString("nomrue"),
								results.getString("codepostal"),
								results.getString("ville"),
								results.getString("date_entree"),
								results.getInt("cotisant"),
								results.getInt("eleve_profil"),
								results.getInt("eleve_profil"),
								results.getString("motdepasse"),
								Manager.getInstance().getPromotion(results.getString("id_eleve")),
								Manager.getInstance().getNbHeureTeaValide(results.getString("id_eleve")),
								Manager.getInstance().getTeaDuesEnCours(results.getString("id_eleve")),
								Manager.getInstance().getNbHeureEnAttente(results.getString("id_eleve")),
								null
								);
						
						if(president(results.getString("id_eleve"))) 
							eleve.setCle_structure(getCleStructureById(results.getString("id_eleve")));
							
						if(Manager.getInstance().getTeaDuesEnCours(results.getString("id_eleve"))==0 && !results.getString("id_eleve").equals("99999"))
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
				// AFFICHAGE OBJET ELEVE
	//-----------------------------------------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------------------------------------
		//r�cup�ration des information d'un eleve en fonction de id (son id est numero de matricule sans le h)
		//acc�s en lecture
		// test junit ok
		
		public Eleve getEleveById(String ideleve){
			Eleve eleve = new Eleve(null,null,null, null, null, null, null, null, null, null, null,null,null,null, null,null, null, null); 
				
			
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
				results.getString("date_entree"),
				results.getInt("cotisant"),
				results.getInt("eleve_profil"),
				results.getInt("eleve_profil"),
				results.getString("motdepasse"),
				Manager.getInstance().getPromotion(results.getString("id_eleve")),
				Manager.getInstance().getNbHeureTeaValide(results.getString("id_eleve")),
				Manager.getInstance().getTeaDuesEnCours(results.getString("id_eleve")),
				Manager.getInstance().getNbHeureEnAttente(results.getString("id_eleve")),
				null
				);
				if(president(results.getString("id_eleve"))) eleve.setCle_structure(getCleStructureById(results.getString("id_eleve")));
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
		//récupération des élèves responsables d'assos
		//acc�s en lecture
		
		

		public List<Eleve> getEleveResponsables(int profil){                 
			List<Eleve> eleves = new ArrayList<Eleve>();               
			try {                         
				Connection connection = DataSourceProvider.getDataSource()                                        
						.getConnection();                                                  
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE eleve_profil=?");                         stmt.setInt(1,profil);                         ResultSet results = stmt.executeQuery();                                            
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
							results.getString("date_entree"),                        
							results.getInt("cotisant"),                         
							results.getInt("eleve_profil"),                         
							results.getInt("diplome"),                         
							results.getString("motdepasse"),                         
							Manager.getInstance().getPromotion(results.getString("id_eleve")),
							Manager.getInstance().getNbHeureTeaValide(results.getString("id_eleve")),
							Manager.getInstance().getTeaDuesEnCours(results.getString("id_eleve")),
							Manager.getInstance().getNbHeureEnAttente(results.getString("id_eleve")),
							null
							);
					
					if(president(results.getString("id_eleve"))) 
						eleve.setCle_structure(getCleStructureById(results.getString("id_eleve")));
					if(!results.getString("id_eleve").equals("99999"))	                                                         
						eleves.add(eleve);                                                        
					}                                 
				// Fermer la connexion                                
				results.close();                                 
				stmt.close();                                 
				connection.close();                                                 
				}                         
			catch (SQLException e) {      
					  e.printStackTrace();  }                        
			
			
			
			return eleves;                         }

		
		

	//-----------------------------------------------------------------------------------------------------------------
		// AFFICHAGE PARAMETRE OBJET ELEVE
	//-----------------------------------------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------------------------------------
		//récupération de la cle_structure d'un élève en fonction de son id
		//acc�s en lecture
		

				public Integer getCleStructureById(String ideleve){
					int res=0;
					
					
					Date date=new Date();
					java.util.Date utilDate_syst = date;
				    java.sql.Date sqlDateSyst = new java.sql.Date(utilDate_syst.getTime());
				    
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						
						
						PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT cle_structure FROM presider WHERE id_eleve=? AND date_fin>? " );
						stmt.setString(1,ideleve);
						stmt.setDate(2, sqlDateSyst);
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
				//recuperation du nom d'un élève en fonctio nde son id
				//acc�s en lecture
				
				
				public String getEleveNomById(String ideleve){
					 String nom ="";	
					
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						
						
						PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT eleve_nom FROM Eleve WHERE id_eleve=?");
						stmt.setString(1,ideleve);
						ResultSet results = stmt.executeQuery();
						
						results.next();
						nom = results.getString("eleve_nom");
						
						// Fermer la connexion
							results.close();
							stmt.close();
							connection.close();
							
					}
						catch (SQLException e) {
											e.printStackTrace();
										}
					 
						return nom;	
				}
				
				//-----------------------------------------------------------------------------------------------------------------
						//recuperation du prenom d'un élève en fonctio nde son id
						//acc�s en lecture
						
						
						public  String getElevePrenomById(String ideleve){
							 String prenom ="";	
							 
							try {
								Connection connection = DataSourceProvider.getDataSource()
										.getConnection();

								
								
								PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT eleve_prenom FROM Eleve WHERE id_eleve=?");
								stmt.setString(1,ideleve);
								ResultSet results = stmt.executeQuery();
								
								results.next();
								prenom = results.getString("eleve_prenom");
								
								// Fermer la connexion
									results.close();
									stmt.close();
									connection.close();
									
							}
								catch (SQLException e) {
													e.printStackTrace();
												}
							 
								return prenom;	
						}
						//-----------------------------------------------------------------------------------------------------------------
						//booléen permettant de savoir si un élève est président de structure pour l'année en cours
						//acc�s en lecture
						// junit non nécessaire, requete interne
								public boolean president(String ideleve){
									boolean res= false;
									
									int rep= 0;
									java.util.Date utildate = new Date();
								    java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
									try {
										Connection connection = DataSourceProvider.getDataSource()
												.getConnection();
										PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT COUNT(cle_structure) as total FROM presider WHERE id_eleve=? AND date_fin>?" );
										stmt.setString(1,ideleve);
										stmt.setDate(2, sqlDate);
										ResultSet results = stmt.executeQuery();
										results.next();
										rep= results.getInt("total");
										if(rep>0) res=true;
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
				// CALCUL
	//-----------------------------------------------------------------------------------------------------------------
		
		

	
	//-----------------------------------------------------------------------------------------------------------------
			//Calcul de la promotion de l'�l�ve : recuperation de classe en cours pour le calcul
			//acc�s en lecture
			//test junit non nécessaire requete interne
	public Integer getCleClasse(String ideleve){
		System.out.println("Dans la méthode getCleClasse, id eleve vaut:"+ideleve);
		int cleclasse=0;
		
		// recuperation de la cl� classe la plus recente
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT cle_classe FROM appartenir WHERE id_eleve=? ORDER BY cle_classe ASC LIMIT 1");
			stmt.setString(1,ideleve);
			ResultSet results = stmt.executeQuery();
			results.next();
			cleclasse=results.getInt("cle_classe");
			
			// Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
			
			}
		catch (SQLException e) {
							e.printStackTrace();
						}
		
		return cleclasse;
	}
	public  String getPromotion(String ideleve){
		String classeencours="";
		
		Integer cleclasse= getCleClasse(ideleve);
		// recuperation de la classe en cours 
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT classe FROM classe WHERE cle_classe=?");
			stmt.setInt(1,cleclasse);
			ResultSet results = stmt.executeQuery();
			results.next();
			classeencours=results.getString("classe");
			
			// Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
			
			}
		catch (SQLException e) {
							e.printStackTrace();
						}
		
			
		return classeencours;
	}
	
	public int sizeEleveAjourEncours(){
		List<Eleve> eleve=Manager.getInstance().getEleveAjour();
		return eleve.size();
	} 

	public int sizeEleveEncours(){
		List<Eleve> eleve=getEleveEnCours();
		return eleve.size();
	} 
	
	public int sizeDiplomePasAjour(){
		List<Eleve> eleve=getEleveDiplomePasAjours();
		return eleve.size();
	} 
	 public int sizeElevePasAJour(){
		 return sizeEleveEncours()-sizeEleveEncours();
	 }

	
}
