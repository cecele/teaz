package hei.devweb.dao.impl;
import hei.devweb.dao.AnnonceDao;


import hei.devweb.model.Offre;
import hei.devweb.model.Structure;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AnnonceDaoImpl implements AnnonceDao {


//-----------------------------------------------------------------------------------------------------------------
// création d 'une annonce en statut non validé pour mise en ligne et date_miseenligne null par défaut
// acces en écriture
	public void ajouterAnnonce (Offre offre ){
		if (offre.getDate_tea() != null || offre.getHeure_debut() != null || offre.getHeure_fin() != null  || offre.getEleve_mail() != null  || offre.getOffre_titre() != null){
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();
				java.util.Date utilDate_depot = offre.getDate_depot();
			    java.sql.Date sqlDateDepot = new java.sql.Date(utilDate_depot.getTime());
			    
				java.util.Date utilDate_tea = offre.getDate_tea();
			    java.sql.Date sqlDateTea = new java.sql.Date(utilDate_tea.getTime());
			    
			    
				// Utiliser la connexion
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement("INSERT INTO `offre`(`date_depot`,`date_tea`,`heure_debut`,`heure_fin`,`statut`,`offre_description`,`eleve_mail`,`offre_titre`,`offre_place`,`cle_structure`) VALUES(?,?,?,?,0,?,?,?,?,1)");
						stmt.setDate(1, sqlDateDepot);
						stmt.setDate(2, sqlDateTea);
						stmt.setString(3, offre.getHeure_debut());
						stmt.setString(4, offre.getHeure_fin());
						stmt.setString(5, offre.getOffre_description());
						stmt.setString(6, offre.getEleve_mail());
						stmt.setString(7, offre.getOffre_titre());
						stmt.setInt(8,offre.getOffre_place());
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
//mise au statut valide de l'annonce et donc modification de la date de mise en ligne ce processus est efféctué par le reponsable TEA et provoque l'affichage de l'annonce dans la liste
//acces en écriture

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
// decrementation de l'offre
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
//mise à 0 du statut pour enlever l'affichage
//acces en ecriture--- a effectuer quand le nombre de place est à 0


public void annonce_miseHorsLigne (Integer cle_offre){
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
//récupération des annonces  valide par ordres decroissant de dates sans structure
//accès en lecture
	
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
						results.getString("structure_president")
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
	//récupération des annonces non valide par ordres decroissant 
	//accès en lecture
		
	public List<Offre> listerOffreNonValide(){
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
						results.getString("structure_president")
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
	//récupération des noms et président de la structure pour une offre particulière. Dans le cas d'un professeur le président de la structure sera l'enseignant et une structure enseignant est crée
	//accès en lecture
	public Structure getStructure(Integer cle_offre){
		Structure structure = new Structure(null,null,null);
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
		///suppression d'une offre non pourvue ou qui n'est plus nécessaire (attention verification offre non pourvue)
		//accès en ecriture
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
	///Nombre de places dispos pour une offre donnee
	//accès en lecture
	
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
	
	
	//-----------------------------------------------------------------------------------------------------------------
			///Nombre d'heure de TEA validées sur l'année
			//accès en lecture

	

}



