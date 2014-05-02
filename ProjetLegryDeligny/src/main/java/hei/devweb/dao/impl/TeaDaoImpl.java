package hei.devweb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import hei.devweb.dao.TeaDao;
import hei.devweb.model.Tea;

public class TeaDaoImpl implements TeaDao {
	
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
		//calcul du nombre d'heure de tea valides par élève
		//acc�s en lecture
	
	public int getNbHeureTeaValide(String ideleve){
		int nbtotal=0;
		
		// recuperation de la cl� classe la plus recente
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

			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("SELECT SUM(nbheure_realisee) as total FROM tea");
			
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
