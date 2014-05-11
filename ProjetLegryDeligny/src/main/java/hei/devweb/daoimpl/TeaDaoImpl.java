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
import hei.devweb.model.Offre;
import hei.devweb.model.Tea;

public class TeaDaoImpl implements TeaDao {
	//-----------------------------------------------------------------------------------------------------------------
		// AJOUT/SUPPRESION
	//-----------------------------------------------------------------------------------------------------------------
		
	//-----------------------------------------------------------------------------------------------------------------
	// cr�ation d 'tea en fonction d'une clé d'offre passée en paramètre cle_offre, statut non valide 
	// acces en �criture
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
							StructureDaoImpl.getNomStructureStatic(results.getInt("cle_structure")),
							StructureDaoImpl.getPresidentNomById(results.getInt("cle_structure")),
							StructureDaoImpl.getPresidentPrenomById(results.getInt("cle_structure")),
							TeaDaoImpl.getNbPlacePourvue(results.getInt("cle_offre"))
							
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
			
			
	//-----------------------------------------------------------------------------------------------------------------
	// Suppresion d'une tea  
	// acces en �criture
			public Boolean getStatutTea (Integer cleoffre ){
			
				Boolean rep = false;
				
				try {
	                Connection connection = DataSourceProvider.getDataSource()
	                                .getConnection();
	                
	                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT statut_valide FROM tea WHERE cle_offre = ? ");
	                stmt.setInt(1,cleoffre);
	                ResultSet results = stmt.executeQuery();

	            results.next();
	           Integer statutvalide= results.getInt("statut_valide");
	           if (statutvalide == 1) rep=true;

	                // Fermer la connexion
	                results.close();
	                stmt.close();
	                connection.close();

	        } catch (SQLException e) {
	                e.printStackTrace();
	        }
				
				
				return rep;
			}
			
			
			public Boolean DeleteTea (Integer cleoffre){
				Boolean rep= getStatutTea(cleoffre);
				
				if (rep=false){
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						PreparedStatement stmt = (PreparedStatement) connection
								.prepareStatement("DELETE  FROM tea WHERE cle_offre=?");
						stmt.setInt(1,cleoffre);
						stmt.executeUpdate();
						// Fermer la connexion

						stmt.close();
						connection.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return rep;
			}
			
	//-----------------------------------------------------------------------------------------------------------------
	// MODIFICATION ET MISE A JOUR
	//-----------------------------------------------------------------------------------------------------------------
	

			//-----------------------------------------------------------------------------------------------------------------
			//mise au statut valide de l'annonce et donc modification de la date de mise en ligne ce processus est eff�ctu� par le reponsable TEA et provoque l'affichage de l'annonce dans la liste
			//acces en �criture

			public void teaValidationByStructure(Integer cletea, Integer clestructure, String ideleve){
				
				if(StructureDaoImpl.getPresidentIdById(clestructure).equals(ideleve)){
								
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
				
			//-----------------------------------------------------------------------------------------------------------------
			//mise au statut valide de l'annonce et donc modification de la date de mise en ligne ce processus est eff�ctu� par le reponsable TEA et provoque l'affichage de l'annonce dans la liste
			//acces en �criture

			public void teaValidationByResponsable(Integer cletea){
				
				
								
				try {
					Connection connection = DataSourceProvider.getDataSource()
							.getConnection();
					
					java.util.Date utilDate = new java.util.Date();
				    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement("UPDATE tea SET statut_valide=2, date_validation=? WHERE cle_tea=? ");
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
			
				
			
	//-----------------------------------------------------------------------------------------------------------------
	// AFFICHAGE
	//-----------------------------------------------------------------------------------------------------------------
	
	//-----------------------------------------------------------------------------------------------------------------
	//calcul du nombre d'heure de tea effectuée par un élève
	//acc�s en lecture
	
	public int getNbHeureTeaRealisee(String ideleve){
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
	//-----------------------------------------------------------------------------------------------------------------
	//recupération tea qui correspondent à une offre 
	//acc�s en lecture
	public List<Tea> getTeaByOffre(Integer cleoffre){
		List<Tea> teas = new ArrayList<Tea>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tea  WHERE cle_offre=?");
			stmt.setInt(1,cleoffre);
			ResultSet results = stmt.executeQuery();
			
			while (results.next()) {
				Tea tea =new Tea(
			results.getInt("cle_tea"),
			results.getDate("date_tea_realisee"),
			results.getInt("nbheure_realisee"),
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
			StructureDaoImpl.getNomStructureStatic(results.getInt("cle_structure")),
			StructureDaoImpl.getPresidentNomById(results.getInt("cle_structure")),
			StructureDaoImpl.getPresidentPrenomById(results.getInt("cle_structure")),
			EleveDaoImpl.getEleveNomById(results.getString("id_eleve")),
			EleveDaoImpl.getElevePrenomById(results.getString("id_eleve"))
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
	
	//-----------------------------------------------------------------------------------------------------------------
			//recupération des heures de TEA d'un eleve en fonction son id
			//acc�s en lecture
	
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
			StructureDaoImpl.getNomStructureStatic(results.getInt("cle_structure")),
			StructureDaoImpl.getPresidentNomById(results.getInt("cle_structure")),
			StructureDaoImpl.getPresidentPrenomById(results.getInt("cle_structure")),
			EleveDaoImpl.getEleveNomById(results.getString("id_eleve")),
			EleveDaoImpl.getElevePrenomById(results.getString("id_eleve"))
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
	
	//-----------------------------------------------------------------------------------------------------------------
	//recupération des heures de TEA d'une structure en attente
	//acc�s en lecture

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
	StructureDaoImpl.getNomStructureStatic(results.getInt("cle_structure")),
	StructureDaoImpl.getPresidentNomById(results.getInt("cle_structure")),
	StructureDaoImpl.getPresidentPrenomById(results.getInt("cle_structure")),
	EleveDaoImpl.getEleveNomById(results.getString("id_eleve")),
	EleveDaoImpl.getElevePrenomById(results.getString("id_eleve"))
	
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

//-----------------------------------------------------------------------------------------------------------------
//recupération des heures de TEA à valider par le resp TEA
//acc�s en lecture
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
	StructureDaoImpl.getNomStructureStatic(results.getInt("cle_structure")),
	StructureDaoImpl.getPresidentNomById(results.getInt("cle_structure")),
	StructureDaoImpl.getPresidentPrenomById(results.getInt("cle_structure")),
	EleveDaoImpl.getEleveNomById(results.getString("id_eleve")),
	EleveDaoImpl.getElevePrenomById(results.getString("id_eleve"))
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

	//-----------------------------------------------------------------------------------------------------------------
	//recupération des heures de TEA valide d'un eleve en fonction son id
	//acc�s en lecture
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
	StructureDaoImpl.getNomStructureStatic(results.getInt("cle_structure")),
	StructureDaoImpl.getPresidentNomById(results.getInt("cle_structure")),
	StructureDaoImpl.getPresidentPrenomById(results.getInt("cle_structure")),
	EleveDaoImpl.getEleveNomById(results.getString("id_eleve")),
	EleveDaoImpl.getElevePrenomById(results.getString("id_eleve"))
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
//-----------------------------------------------------------------------------------------------------------------
//recupération des heures de TEA non-valide d'un eleve en fonction son id
//acc�s en lecture
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
StructureDaoImpl.getNomStructureStatic(results.getInt("cle_structure")),
StructureDaoImpl.getPresidentNomById(results.getInt("cle_structure")),
StructureDaoImpl.getPresidentPrenomById(results.getInt("cle_structure")),
EleveDaoImpl.getEleveNomById(results.getString("id_eleve")),
EleveDaoImpl.getElevePrenomById(results.getString("id_eleve"))
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
//-----------------------------------------------------------------------------------------------------------------
		//calcul du nombre d'heure de tea en attente 
		//acc�s en lecture
	
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
	//-----------------------------------------------------------------------------------------------------------------
			//calcul du nombre d'heure de tea valides par élève
			//acc�s en lecture
		
		public static int getNbHeureTeaValide(String ideleve){
			int nbtotal=0;
			
			
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT SUM(nbheure_realisee) as total FROM tea WHERE id_eleve=? AND statut_valide=1");
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
	//-----------------------------------------------------------------------------------------------------------------
		//calcul du nombre de tea dues pour un élève en fonction de son id
		//acc�s en lecture
		
		public static int getNbHeureDues(String ideleve){
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
		//-----------------------------------------------------------------------------------------------------------------
		//calcul du nombre de tea dues pour un élève en fonction de son id
		//acc�s en lecture
		
		public static int getNbHeureEnAttente(String ideleve){
			int nbtotal=0;
			
			
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
		//-----------------------------------------------------------------------------------------------------------------
				//calcul du nombre de tea en attente de validation
				//acc�s en lecture
				
				public int getTeaEnAttente(){
					int nbtotal=0;
					
					
					try {
						Connection connection = DataSourceProvider.getDataSource()
								.getConnection();

						
						PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT COUNT(cle_tea) as total FROM tea WHERE statut_valide=0");
						
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
				//calcul du nombre de personne ayant postuler à une offre en fonction de la clé de l'offre
				//acc�s en lecture
				
				public static int getNbPlacePourvue(Integer cleoffre){
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

				public List<Tea> listerTeaAValider() {
					// TODO Auto-generated method stub
					return null;
				}

				

				

		
}
