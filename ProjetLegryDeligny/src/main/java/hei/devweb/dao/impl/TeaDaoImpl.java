package hei.devweb.dao.impl;

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
	// cr�ation d 'tea en fonction d'une clé d'offre passée en paramètre cle_offre, statut non valide 
	// acces en �criture
		public void ajouterTea (Integer cleoffre, String ideleve ){
			
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement("SELECT * FROM offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE cle_offre=?");
				stmt.setInt(1, cleoffre);
				ResultSet results = stmt.executeQuery();
				
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
					
						
					System.out.println(results.getString("structure_nom"));
				
					int hdeb = Integer.parseInt(offre.getHeure_debut());
					int hfin = Integer.parseInt(offre.getHeure_fin());
					int nbtea = hfin - hdeb;
					
					Connection connection2 = DataSourceProvider.getDataSource()
							.getConnection();
									    
					// Utiliser la connexion
					PreparedStatement stmt2 = (PreparedStatement) connection
							.prepareStatement("INSERT INTO `tea`(`date_tea_realisee`,`nbheure_realisee`,`statut_valide`,`date_validation`,`cle_offre`,`id_eleve`) VALUES(?,?,0,?,?,?)");
							stmt2.setDate(1, (Date) offre. getDate_tea());
							stmt2.setInt(2, nbtea);// la je sais pas pr reporter le calcul d'heure pac les parse!
							stmt2.setDate(4, null); // par défaut
							stmt2.setInt(5, offre.getCle_offre());
							stmt2.setString(6, ideleve);
							stmt2.executeUpdate();

							// Fermer la connexion
							stmt.close();
							stmt2.close();
							connection.close();
							connection2.close();

							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
			
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
			//recupération des heures de TEA d'un eleve en fonction son id
			//acc�s en lecture
	
	public List<Tea> getTeaByEleve(String ideleve){
		List<Tea> teas = new ArrayList<Tea>();
		try {
			Connection connection = DataSourceProvider.getDataSource()
					.getConnection();

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tea WHERE id_eleve=?");
			stmt.setString(1,ideleve);
			ResultSet results = stmt.executeQuery();
			
			while (results.next()) {
				Tea tea =new Tea(
			results.getInt("cle_tea"),
			results.getDate("date_tea_realisee"),
			results.getInt("nbheure_realisee"),
			results.getInt("statut_valdie"),
			results.getDate("date_validation"),
			results.getInt("cle_offre"),
			results.getString("id_eleve"));
			
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

	
	PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tea WHERE id_eleve=?AND statut_valide=1");
	stmt.setString(1,ideleve);
	ResultSet results = stmt.executeQuery();
	
	while (results.next()) {
		Tea tea =new Tea(
	results.getInt("cle_tea"),
	results.getDate("date_tea_realisee"),
	results.getInt("nbheure_realisee"),
	results.getInt("statut_valdie"),
	results.getDate("date_validation"),
	results.getInt("cle_offre"),
	results.getString("id_eleve"));
	
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


PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM tea WHERE id_eleve=?AND statut_valide=0");
stmt.setString(1,ideleve);
ResultSet results = stmt.executeQuery();

while (results.next()) {
	Tea tea =new Tea(
results.getInt("cle_tea"),
results.getDate("date_tea_realisee"),
results.getInt("nbheure_realisee"),
results.getInt("statut_valdie"),
results.getDate("date_validation"),
results.getInt("cle_offre"),
results.getString("id_eleve"));

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
		
		public int getNbHeureTeaValide(String ideleve){
			int nbtotal=0;
			
			
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT SUM(nbheure_realisee) as total FROM tea WHERE id_eleve=? AND statut_valide=1");
				stmt.setString(1,ideleve);
				ResultSet results = stmt.executeQuery();
				
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
		
		public int getNbHeureDues(String ideleve){
			int nbtotal=0;
			
			
			try {
				Connection connection = DataSourceProvider.getDataSource()
						.getConnection();

				
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT SUM(classe.nb_tea) as total FROM appartenir INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE appartenir.id_eleve=?");
				stmt.setString(1,ideleve);
				ResultSet results = stmt.executeQuery();
				
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
