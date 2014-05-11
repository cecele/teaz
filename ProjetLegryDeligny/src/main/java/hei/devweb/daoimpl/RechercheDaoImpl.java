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

public List<Eleve> rechercheByParameter(String ideleve, String nom, String prenom, String classe, String orderBy, Boolean diplome, Boolean etudiant, Boolean ajour, Boolean retard){
List<Eleve> elevescas1 = new ArrayList<Eleve>();
List<Eleve> elevescas2 = new ArrayList<Eleve>();
List<Eleve> elevescas3 = new ArrayList<Eleve>();
List<Eleve> elevescas4 = new ArrayList<Eleve>();
List<Eleve> elevescas5 = new ArrayList<Eleve>();
List<Eleve> elevescas6 = new ArrayList<Eleve>();
List<Eleve> elevescas7 = new ArrayList<Eleve>();
List<Eleve> elevescas8 = new ArrayList<Eleve>();
List<Eleve> elevescas9 = new ArrayList<Eleve>();
List<Eleve> elevesreturn = new ArrayList<Eleve>();

if(orderBy==""){orderBy="eleve_nom";}

System.out.println("entrée ds le try");

	try {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();
		PreparedStatement stmt = null;

		System.out.println("recherche par");
		
		// tous les élèves
		if(ideleve.equals("") && nom.equals("") && prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve");
		
		 System.out.println("tous les élèves " +stmt);
		}


		// recherche effectuée par matricule uniquement : il doit être exact!
		if(!ideleve.equals("") && nom.equals("") && prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve WHERE id_eleve=? ORDER BY ?");
		stmt.setString(1,ideleve);
		stmt.setString(2,orderBy);
		System.out.println("matricule " +stmt);
		}
		
		
		
		// recherche par nom uniquement
		if(ideleve.equals("") && !nom.equals("") && prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve WHERE eleve_nom LIKE ? ORDER BY ?");
		stmt.setString(1,nom);
		stmt.setString(2,orderBy);
		 System.out.println("nom " +stmt);}
		
		
		// recherche par prenom
		if(ideleve.equals("") && nom.equals("") && !prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve WHERE eleve_prenom LIKE ? ORDER BY ?");
		stmt.setString(1,prenom);
		stmt.setString(2,orderBy);
		 System.out.println("prenom " +stmt);
		}
				
		// recherche par classe
		if(ideleve.equals("") && nom.equals("") && prenom.equals("") && !classe.equals("tous")  ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve INNER JOIN appartenir ON eleve.id_eleve=appartenir.id_eleve INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE classe=? ORDER BY ?");
		stmt.setString(1,classe);
		stmt.setString(2,orderBy);
		 System.out.println("classe " +stmt);
		}
		
		
		// recherche par nom prenom
		if(ideleve.equals("") && !nom.equals("") && !prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve WHERE eleve_nom LIKE ? AND eleve_prenom LIKE ? ORDER BY ?");
		stmt.setString(1,nom);
		stmt.setString(2,prenom);
		stmt.setString(3,orderBy);
		 System.out.println("nom prenom " +stmt);
		}
		
		
		// recherche par nom classe
		if(ideleve.equals("") && !nom.equals("") && prenom.equals("") && !classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve INNER JOIN appartenir ON eleve.id_eleve=appartenir.id_eleve INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE classe=? AND eleve_nom LIKE ?ORDER BY ?");
		stmt.setString(1,classe);
		stmt.setString(2,nom);
		stmt.setString(3,orderBy);
		 System.out.println("nom classe " +stmt);
		}
		
		// recherche par prenom classe
		if(ideleve.equals("") && nom.equals("") && !prenom.equals("") && !classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve INNER JOIN appartenir ON eleve.id_eleve=appartenir.id_eleve INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE classe=? AND eleve_prenom LIKE ?ORDER BY ?");
		stmt.setString(1,classe);
		stmt.setString(2,prenom);
		stmt.setString(3,orderBy);
		 System.out.println("prenom classe " +stmt);
		}
				
		// recherche par nom prenom et  classe
		if(ideleve.equals("") && !nom.equals("") && !prenom.equals("") && !classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve INNER JOIN appartenir ON eleve.id_eleve=appartenir.id_eleve INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE classe=? AND eleve_nom LIKE ? AND eleve_prenom LIKE ? ORDER BY ?");
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
					getTeaDuesEnCours(results.getString("id_eleve")),
					TeaDaoImpl.getNbHeureEnAttente(results.getString("id_eleve")),
					null
					);
		
			int teadues=getTeaDuesEnCours(results.getString("id_eleve"));
			if(EleveDaoImpl.president(results.getString("id_eleve"))){ eleve.setCle_structure(EleveDaoImpl.getCleStructureById(results.getString("id_eleve")));}
		
			
			if(diplome && etudiant && ajour && retard)elevescas1.add(eleve);
			if(diplome && etudiant && ajour==false && teadues>0 && retard)elevescas3.add(eleve);
			if(diplome && etudiant && ajour && teadues==0 && retard==false)elevescas2.add(eleve);
		
			
			if(diplome==false && results.getInt("diplome")==0  && etudiant && ajour && retard )elevescas4.add(eleve);
			if(diplome==false && results.getInt("diplome")==0  && etudiant && ajour==false && teadues>0 && retard )elevescas5.add(eleve);
			if(diplome==false && results.getInt("diplome")==0  && etudiant && ajour && teadues==0 && retard==false )elevescas6.add(eleve);
			
			if(diplome && results.getInt("diplome")==1  && etudiant==false && ajour && retard )elevescas7.add(eleve);
			if(diplome && results.getInt("diplome")==1  && etudiant==false && ajour==false && teadues>0 && retard )elevescas8.add(eleve);
			if(diplome && results.getInt("diplome")==1  && etudiant==false && ajour && teadues==0 && retard==false )elevescas9.add(eleve);
					

		

	}
		// Fermer la connexion
		results.close();
		stmt.close();
		connection.close();
		

	}
		catch (SQLException e) {
							e.printStackTrace();
}
	System.out.println("choix du cas " + diplome+  " " + etudiant+ " " + " " + ajour+ " " + retard);
	
	if(diplome && etudiant && ajour && retard)elevesreturn=elevescas1;
	if(diplome && etudiant && ajour==false && retard)elevesreturn=elevescas2;
	if(diplome && etudiant && ajour && retard==false)elevesreturn=elevescas3;
	if(diplome==false && etudiant && ajour && retard )elevesreturn=elevescas4;
	if(diplome==false && etudiant && ajour==false && retard )elevesreturn=elevescas5;
	if(diplome==false && etudiant && ajour && retard==false )elevesreturn=elevescas6;
	if(diplome==false && etudiant && ajour && retard==false )elevesreturn=elevescas7;
	if(diplome && etudiant==false && ajour && retard )elevesreturn=elevescas8;
	if(diplome && etudiant==false && ajour==false  && retard )elevesreturn=elevescas9;
	
	
	return elevesreturn;
}

public int sizeReponse(List<Eleve> eleve){
	return eleve.size();
}

public static  Integer getTeaDuesEnCours(String ideleve){
	int nbtotal=0;
	nbtotal=TeaDaoImpl.getNbHeureDues(ideleve)-TeaDaoImpl.getNbHeureTeaValide(ideleve);
	if(nbtotal<=0)return 0;	return nbtotal;
	
}

}

