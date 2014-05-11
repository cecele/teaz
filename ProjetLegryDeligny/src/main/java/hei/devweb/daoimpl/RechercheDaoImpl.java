package hei.devweb.daoimpl;

import hei.devweb.dao.RechercheDao;
import hei.devweb.model.Eleve;
import hei.devweb.model.Structure;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class RechercheDaoImpl implements RechercheDao {
	
	
	//-----------------------------------------------------------------------------------------------------------------
	//effectuer une recherche par paramètre d'élève (4 choix possibles, mettre null lorsque le paramètre n'est pas pris en compte!)
	//acc�s en lecture

public List<Eleve> rechercheByParameter(String ideleve, String nom, String prenom, String classe, String orderBy){
List<Eleve> eleves = new ArrayList<Eleve>();


if(orderBy==""){orderBy="eleve_nom";}

System.out.println("entrée ds le try");

	try {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();
		PreparedStatement stmt = null;

		System.out.println("recherche par");
		
		// tous les élèves
		if(ideleve.equals("") && nom.equals("") && prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve");
		
		 System.out.println("tous les élèves " +stmt);
		}
		
		
		// recherche effectuée par matricule uniquement : il doit être exact!
		if(!ideleve.equals("") && nom.equals("") && prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE id_eleve=? ORDER BY ?");
		stmt.setString(1,ideleve);
		stmt.setString(2,orderBy);
		System.out.println("matricule " +stmt);
		}
		
		
		
		// recherche par nom uniquement
		if(ideleve.equals("") && !nom.equals("") && prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE eleve_nom LIKE ? ORDER BY ?");
		stmt.setString(1,nom);
		stmt.setString(2,orderBy);
		 System.out.println("nom " +stmt);}
		
		
		// recherche par prenom
		if(ideleve.equals("") && nom.equals("") && !prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE eleve_prenom LIKE ? ORDER BY ?");
		stmt.setString(1,prenom);
		stmt.setString(2,orderBy);
		 System.out.println("prenom " +stmt);
		}
				
		// recherche par classe
		if(ideleve.equals("") && nom.equals("") && prenom.equals("") && !classe.equals("tous")  ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve INNER JOIN appartenir ON eleve.id_eleve=appartenir.id_eleve INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE classe=? ORDER BY ?");
		stmt.setString(1,classe);
		stmt.setString(2,orderBy);
		 System.out.println("classe " +stmt);
		}
		
		
		// recherche par nom prenom
		if(ideleve.equals("") && !nom.equals("") && !prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve WHERE eleve_nom LIKE ? AND eleve_prenom LIKE ? ORDER BY ?");
		stmt.setString(1,nom);
		stmt.setString(2,prenom);
		stmt.setString(3,orderBy);
		 System.out.println("nom prenom " +stmt);
		}
		
		
		// recherche par nom classe
		if(ideleve.equals("") && !nom.equals("") && prenom.equals("") && !classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve INNER JOIN appartenir ON eleve.id_eleve=appartenir.id_eleve INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE classe=? AND eleve_nom LIKE `?%`ORDER BY ?");
		stmt.setString(1,classe);
		stmt.setString(2,nom);
		stmt.setString(3,orderBy);
		 System.out.println("nom classe " +stmt);
		}
		
		// recherche par prenom classe
		if(ideleve.equals("") && nom.equals("") && !prenom.equals("") && !classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve INNER JOIN appartenir ON eleve.id_eleve=appartenir.id_eleve INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE classe=? AND eleve_prenom LIKE `?%`ORDER BY ?");
		stmt.setString(1,classe);
		stmt.setString(2,prenom);
		stmt.setString(3,orderBy);
		 System.out.println("prenom classe " +stmt);
		}
				
		// recherche par nom prenom et  classe
		if(ideleve.equals("") && !nom.equals("") && !prenom.equals("") && !classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM Eleve INNER JOIN appartenir ON eleve.id_eleve=appartenir.id_eleve INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE classe=? AND eleve_nom LIKE `?%` AND eleve_prenom LIKE`?%`ORDER BY ?");
		stmt.setString(1,classe);
		stmt.setString(2,nom);
		stmt.setString(3,prenom);
		stmt.setString(4,orderBy);
		 System.out.println("nom prenom classe " +stmt);}
		
		
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
					results.getInt("diplome"),
					results.getString("motdepasse"),
					EleveDaoImpl.getPromotion(results.getString("id_eleve")),
					TeaDaoImpl.getNbHeureTeaValide(results.getString("id_eleve")),
					TeaDaoImpl.getNbHeureDues(results.getString("id_eleve"))-TeaDaoImpl.getNbHeureTeaValide(results.getString("id_eleve")),
					TeaDaoImpl.getNbHeureEnAttente(results.getString("id_eleve")),
					null
					);
			System.out.println("AVANT " +results.getString("id_eleve"));
			if(EleveDaoImpl.president(results.getString("id_eleve"))){ eleve.setCle_structure(EleveDaoImpl.getCleStructureById(results.getString("id_eleve")));}
				
						
			 System.out.println("APRES " +results.getString("id_eleve"));
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
