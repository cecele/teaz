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


public class AnnonceDaoImpl implements AnnonceDao {
	
//-----------------------------------------------------------------------------------------------------------------
// AJOUT/SUPPRESION
//-----------------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------------------------
// cr�ation d 'une annonce en statut non valid� pour mise en ligne et date_miseenligne null par d�faut
// acces en �criture
	public void ajouterAnnonce (Offre offre ){
		GregorianCalendar calendar = new java.util.GregorianCalendar(); 
		Date date=new Date();
		calendar.setTime( date ); 
		// Initialisé avec une instance de Date. 
		calendar.add (Calendar.DATE, +1);
		
		if (offre.getDate_tea() != null && offre.getHeure_debut() != null && offre.getHeure_fin() != null  && offre.getEleve_mail() != null  && offre.getOffre_titre() != null && !date.before(offre.getDate_tea())){
			
			
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
	//-----------------------------------------------------------------------------------------------------------------
	///suppression d'une offre non pourvue ou qui n'est plus n�cessaire (attention verification offre non pourvue)
	//acc�s en ecriture
public void deleteOffre(Integer cleoffre) {
	
	try {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement("DELETE  FROM offre WHERE cle_offre=?");
		stmt.setInt(1,cleoffre);
		stmt.executeUpdate();
		// Fermer la connexion

		stmt.close();
		connection.close();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	

}

//-----------------------------------------------------------------------------------------------------------------
//  MISE A JOUR
//-----------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------
//mise au statut valide de l'annonce et donc modification de la date de mise en ligne ce processus est eff�ctu� par le reponsable TEA et provoque l'affichage de l'annonce dans la liste
//acces en �criture

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
//-----------------------------------------------------------------------------------------------------------------
//decrementation de l'offre
//acces en ecriture -----ATTENTION---- VERIFIER QUE OFFRE_PLACE>0 avant de decrementer

public void offre_placemoins (Integer cle_offre){
	try {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement("UPDATE offre SET offre_place=offre_place-1 WHERE cle_offre=?");
		stmt.setInt(1,cle_offre);	
		stmt.executeUpdate();
		// Fermer la connexion

		stmt.close();
		connection.close();

	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	//-----------------------------------------------------------------------------------------------------------------
	//mise � 0 du statut pour enlever l'affichage
	//acces en ecriture--- a effectuer quand le nombre de place est � 0


	public void annonce_miseHorsLigne (Offre offre){
		
		java.util.Date utilDate_depot = offre.getDate_depot();
	    java.sql.Date sqlDateDepot = new java.sql.Date(utilDate_depot.getTime());
	    
		java.util.Date utilDate_tea = offre.getDate_tea();
	    java.sql.Date sqlDateTea = new java.sql.Date(utilDate_tea.getTime());
	    
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("UPDATE offre SET date_tea=?,heure_debut=?,heure_fin=?,statut=0,offre_descritpion=?,eleve_mail=?, offre_titre=?, offre_place=? WHERE cle_offre=?");
			stmt.setDate(1, sqlDateTea);
			stmt.setString(2, offre.getHeure_debut());
			stmt.setString(3, offre.getHeure_fin());
			stmt.setString(4, offre.getOffre_description());
			stmt.setString(5, offre.getEleve_mail());
			stmt.setString(6, offre.getOffre_titre());
			stmt.setInt(7,offre.getOffre_place());
			stmt.executeUpdate();
			// Fermer la connexion

			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//-----------------------------------------------------------------------------------------------------------------
		//modification de l'offre par le propriétaire de l'offre
		//acces en ecriture--- 
	
	public void AnnonceModification (Integer cle_offre){
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

//-----------------------------------------------------------------------------------------------------------------
		// AFFICHAGE
//-----------------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------------------------
//r�cup�ration des annonces  valide par ordres decroissant de dates avec structure ou les offres auquels un élève a postulé n'apparait plus
//acc�s en lecture NON FONCTIONNELLE
	
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
						StructureDaoImpl.getPresidentNomById(results.getInt("cle_structure")),
						StructureDaoImpl.getPresidentPrenomById(results.getInt("cle_structure"))
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
	//-----------------------------------------------------------------------------------------------------------------
	//test si uen offre a été postulée
	// acces en lecture
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
				String idEleveResponsable = StructureDaoImpl.getPresidentIdById(clestructure);
				
				// le comparer à celui en cours
				if(ideleve.equals(idEleveResponsable)) rep=true;
				return rep;
			}
			
	//-----------------------------------------------------------------------------------------------------------------
	//r�cup�ration des annonces  valide par ordres decroissant de dates sans structure
	//acc�s en lecture
		
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
							results.getString("structure_nom"),
							StructureDaoImpl.getPresidentNomById(results.getInt("cle_structure")),
							StructureDaoImpl.getPresidentPrenomById(results.getInt("cle_structure"))
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
	//-----------------------------------------------------------------------------------------------------------------
	//r�cup�ration des annonces non valide par ordres decroissant 
	//acc�s en lecture
		
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
						results.getString("structure_nom"),
						StructureDaoImpl.getPresidentNomById(results.getInt("cle_structure")),
						StructureDaoImpl.getPresidentPrenomById(results.getInt("cle_structure"))
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

	//-----------------------------------------------------------------------------------------------------------------
	///Nombre de places dispos pour une offre donnee
	//acc�s en lecture
	
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
	
	public void annonce_miseHorsLigne(Integer id) {
		
	}
	
	

}



