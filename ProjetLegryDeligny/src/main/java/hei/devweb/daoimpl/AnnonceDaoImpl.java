package hei.devweb.daoimpl;
import hei.devweb.dao.AnnonceDao;


import hei.devweb.metier.Manager;
import hei.devweb.model.Offre;
import hei.devweb.model.Structure;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * <b> La Classe AnnonceDaoImpl gère l'ensemble des classes permettant l'accès à la BDD afin de modifier les annonces (autrement appelées offres dans la BDD).</b>
 * 
 * @author Celine
 */
public class AnnonceDaoImpl implements AnnonceDao {
	
/**
 * L'ajout d'une annonce se fait lorsqu'un responsable de destructure la dépose sur le site via un formulaire
 * @param offre 
 * 					objet offre crée grâce au remplissage du formulaire
 * 
 * @see  DeposerAnnonceServlet 
 * 
 * 
 */
	public void ajouterAnnonce (Offre offre ){
	
		Date date =new Date();
		
		if (offre.getDate_tea() != null && offre.getHeure_debut() != null && offre.getHeure_fin() != null  && offre.getEleve_mail() != null  && offre.getOffre_titre() != null ){
			
			
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();
				java.util.Date utilDate_depot = offre.getDate_depot();
			    java.sql.Date sqlDateDepot = new java.sql.Date(utilDate_depot.getTime());
			    
				java.util.Date utilDate_tea = offre.getDate_tea();
			    java.sql.Date sqlDateTea = new java.sql.Date(utilDate_tea.getTime());
			    
			    
				// Utiliser la connexion
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement("INSERT INTO `offre`(`date_depot`,`date_tea`,`heure_debut`,`heure_fin`,`statut`,`offre_description`,`eleve_mail`,`offre_titre`,`offre_place`,`cle_structure`) VALUES(?,?,?,?,0,?,?,?,?,?)");
						stmt.setDate(1, sqlDateDepot);
						stmt.setDate(2, sqlDateTea);
						stmt.setString(3, offre.getHeure_debut());
						stmt.setString(4, offre.getHeure_fin());
						stmt.setString(5, offre.getOffre_description());
						stmt.setString(6, offre.getEleve_mail());
						stmt.setString(7, offre.getOffre_titre());
						stmt.setInt(8,offre.getOffre_place());
						stmt.setInt(9, offre.getCle_structure());
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
	 * Validation de l'offre par le responsable TEA afin qu'elle soit visible par les autres utilisateurs
	 * @param cle_offre 
	 * 					cle de l'objet offre faisant référence à l'annonce que l'utilisateur a posté
	 *@param datedujour 
	 *					paramètre indiquant la date du jour
	 * 
	 * 
	 */
public void annonce_validation (Integer cle_offre,Date datedujour){
	try {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();
		
		java.util.Date utilDate = datedujour;
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement("UPDATE offre SET statut=1, date_miseenligne=? WHERE cle_offre=?");
		stmt.setDate(1, sqlDate);
		stmt.setInt(2,cle_offre);	
		stmt.executeUpdate();
		// Fermer la connexion

		stmt.close();
		connection.close();

	} catch (SQLException e) {
		e.printStackTrace();
	}
}
/**
 * Mise à jour d'une offre lorsque l'utilisateur estime nécessaire de modifier les informations qu'il a saisi
 * @param offre 
 * 					objet Offre contentant l'ensemble des informations (les anciennes et celles modifiées)
 * 
 */
	

	public void AnnonceModification (Offre offre){
		
		java.util.Date utilDate_depot = offre.getDate_depot();
	    java.sql.Date sqlDateDepot = new java.sql.Date(utilDate_depot.getTime());
	    
		java.util.Date utilDate_tea = offre.getDate_tea();
	    java.sql.Date sqlDateTea = new java.sql.Date(utilDate_tea.getTime());
	    if( Manager.getInstance().getNbPlacePourvue(offre.getCle_offre())==0){
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("UPDATE offre SET date_tea=?,heure_debut=?,heure_fin=?,statut=0,offre_description=?,eleve_mail=?, offre_titre=?, offre_place=? WHERE cle_offre=? ");
			stmt.setDate(1, sqlDateTea);
			stmt.setString(2, offre.getHeure_debut());
			stmt.setString(3, offre.getHeure_fin());
			stmt.setString(4, offre.getOffre_description());
			stmt.setString(5, offre.getEleve_mail());
			stmt.setString(6, offre.getOffre_titre());
			stmt.setInt(7,offre.getOffre_place());
			stmt.setInt(8,offre.getCle_offre());
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
	 * Mise hors ligne d'une annonce cad passage de son statut à 0
	 * @param cle_offre 
	 * 					cle de l'objet offre faisant référence à l'annonce que l'utilisateur a posté
	 * 
	 */
	
	public void  annonce_miseHorsLigne(Integer cle_offre){
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("UPDATE offre SET statut=0 WHERE cle_offre=?");
			stmt.setInt(1,cle_offre);	
			stmt.executeUpdate();
			// Fermer la connexion

			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Decrementation du champs offre_place cad du nombre de place disponible pour les utilisateurs
	 * @param cle_offre 
	 * 					cle de l'objet offre faisant référence à l'annonce que l'utilisateur a posté
	 * 
	 */
	public void offre_placemoins (Integer cle_offre){
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("UPDATE offre SET offre_place=offre_place-1 WHERE cle_offre=? AND offre_place>0");
			stmt.setInt(1,cle_offre);	
			stmt.executeUpdate();
			// Fermer la connexion

			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * Retourne la liste des offres que l'utilisateurs peut visualiser
	 * @param ideleve 
	 * 					matricule sans le h permettant d'identifier l'élève
	 * @return La liste des offres dont l'élève n'est pas à l'origine et auquelles i ln'a pas postulé, classées par ordre de date de la plus urgente à la moins urgente
	 * 
	 */
		
		public List<Offre> listerOffreByEleve(String ideleve){
			List<Offre> offres = new ArrayList<Offre>();
			
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();
				
				
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM offre WHERE statut=1 AND offre_place>0 ORDER BY date_tea DESC");
				ResultSet results = stmt.executeQuery();
				
				while (results.next()) {
					Offre offre =new Offre(results.getInt("cle_offre"),
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
					if(getPostulerOffre(results.getInt("cle_offre"), ideleve,results.getInt("cle_structure"))==false)
						

						{offres.add(offre);	
					}
				}
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}

				return offres;
		}
				/**
				 * Retourne l'ensemble des offres
				 * @return L'ensemble des offres disponibles (quelque soit leurs statuts)
				 * 
				 */
			public List<Offre> listerOffre(){
				List<Offre> offres = new ArrayList<Offre>();
				try {
					Connection connection = DataSourceProvider.getDataSource()
							.getConnection();

					Statement stmt = connection.createStatement();
					ResultSet results = stmt.executeQuery("SELECT * FROM offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE statut=1 ORDER BY date_tea DESC");
					
					while (results.next()) {
						
						Offre offre =new Offre(results.getInt("cle_offre"),
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
					
						offres.add(offre);	
					}
					// Fermer la connexion
					results.close();
					stmt.close();
					connection.close();
					}catch (SQLException e) {
						e.printStackTrace();
					}

					return offres;
				}
			/**
			 * Retourne la liste des offres non valide c'est à dire qui dont le statut=0
			 * @return La liste des offres dont le statut est égal à 0 c'est à dire non valide (car non validées par le responsable TEA ou à problème)
			 * 
			 */
			
		public List<Offre> listerOffreNonValide(){
			List<Offre> offres = new ArrayList<Offre>();
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

			
				
				Statement stmt = connection.createStatement();
				ResultSet results = stmt.executeQuery("SELECT * FROM offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE statut=0 AND offre_place>0 ORDER BY date_tea DESC");
				
				while (results.next()) {
					Offre offre =new Offre(results.getInt("cle_offre"),
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
					
					offres.add(offre);	
					System.out.println(results.getString("eleve_mail"));
				}
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}

				return offres;
			}
		
		/**
		 * Retourne la liste des offres générées par une seule et même structure
		 * @param clestructure
		 * 					cle primaire de la table structure permettant d'identifier la structure
		 * @return La liste des offres rattachées à uen seule et même structure afin de lui en permettre l'administration
		 */
			
		public List<Offre> listerOffreByStructure(Integer clestructure){
			List<Offre> offres = new ArrayList<Offre>();
			
				try {
	                Connection connection = DataSourceProvider.getDataSource()
	                                .getConnection();
	                
	                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE offre.cle_structure=? ORDER BY date_tea DESC");
				 stmt.setInt(1,clestructure);
				 ResultSet results = stmt.executeQuery();
				while (results.next()) {
					Offre offre =new Offre(results.getInt("cle_offre"),
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
					
					offres.add(offre);	
					System.out.println(results.getString("eleve_mail"));
				}
				// Fermer la connexion
				results.close();
				stmt.close();
				connection.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}

				return offres;
			}

		/**
		 * Récupère une Offre 
		 * @param cleoffre
		 * 					cle de l'objet offre faisant référence à l'annonce que l'utilisateur a posté
		 * @return L'objet Offre contenant les informations de l'annonce recherchée
		 */
			
			public Offre getOffreById(Integer cleoffre){
				
				Offre offre=null;
				
					try {
		                Connection connection = DataSourceProvider.getDataSource()
		                                .getConnection();
		                
		             PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE offre.cle_offre=? ");
					 stmt.setInt(1,cleoffre);
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
						
						
						System.out.println(results.getString("eleve_mail"));
					
					// Fermer la connexion
					results.close();
					stmt.close();
					connection.close();
					}catch (SQLException e) {
						e.printStackTrace();
					}

					return offre;
				}
			
		
			/**
			 * Rècupere le nombre de place disponible pour une offre
			 * @param idOffre
			 * 					cle de l'objet offre faisant référence à l'annonce que l'utilisateur a posté
			 * @return Le nombre de place disponible pour une offre donnée
			 */
		
		public int getNbPlaces(int idOffre) {
	        
	        int nbPlaces = 0;
	        
	        try {
	                Connection connection = DataSourceProvider.getDataSource()
	                                .getConnection();
	                
	                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT offre_place FROM offre WHERE cle_offre = ? ");
	                stmt.setInt(1,idOffre);
	                ResultSet results = stmt.executeQuery();

	            results.next();
	            nbPlaces = results.getInt("offre_place");

	                // Fermer la connexion
	                results.close();
	                stmt.close();
	                connection.close();

	        } catch (SQLException e) {
	                e.printStackTrace();
	        }
	        return nbPlaces;
	}		
		
		/**
		 * Retourne vrai ou faux afin de savoir si un élève a déjà postulé à une offre
		 * @param cleoffre
		 * 					cle de l'objet offre faisant référence à l'annonce que l'utilisateur a posté
		 * @param ideleve 
		 * 					matricule sans le h permettant d'identifier l'élève
		 * @param clestructure
		 * 					cle primaire de la table structure permettant d'identifier la structure
		 * @return Booleen retournant true si un élève a deja postulé, false sinon
		 */
			public Boolean getPostulerOffre(Integer cleoffre, String ideleve, Integer clestructure ){
			
				Boolean rep = false;
				
				try {
	                Connection connection = DataSourceProvider.getDataSource()
	                                .getConnection();
	                
	                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT id_eleve FROM tea WHERE cle_offre = ? ");
	                stmt.setInt(1,cleoffre);
	                ResultSet results = stmt.executeQuery();
	                
	           while  (results.next()&& rep==false){
	           String ideleveenbase= results.getString("id_eleve");
	           
	            if(ideleve.equals(ideleveenbase)) {rep=true; }
	           }
	                // Fermer la connexion
	                results.close();
	                stmt.close();
	                connection.close();

	        } catch (SQLException e) {
	                e.printStackTrace();
	        }
				
				// récupérer l'id de l'eleve responsable
				String idEleveResponsable = Manager.getInstance().getPresidentIdById(clestructure);
				
				// le comparer à celui en cours
				if(ideleve.equals(idEleveResponsable)) rep=true;
				return rep;
			}
			
	
	
			/**
			 * Retourne le nombre d'offre en attente
			
			 * @return retourne le nombre d'offre en attente de validation par le responsable tea
			 */
	
	public Integer getOffreEnAttente(){
		int nbtotal=0;
		
		
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT COUNT(cle_offre) as total FROM offre WHERE statut=0");
			
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





}



