package hei.devweb.daoimpl;

import hei.devweb.dao.RechercheDao;
import hei.devweb.metier.Manager;
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
List<Eleve> elevescas10 = new ArrayList<Eleve>();
List<Eleve> elevescas11 = new ArrayList<Eleve>();
List<Eleve> elevescas12 = new ArrayList<Eleve>();
List<Eleve> elevescas13 = new ArrayList<Eleve>();
List<Eleve> elevescas14 = new ArrayList<Eleve>();
List<Eleve> elevescas15 = new ArrayList<Eleve>();
List<Eleve> elevescas16 = new ArrayList<Eleve>();
List<Eleve> elevesreturn = new ArrayList<Eleve>();

if(orderBy==""){orderBy="eleve_nom";}

System.out.println("entrée ds le try parametre ideleve= " +ideleve+ " nom="+nom+" prenom "+ prenom+" classe"+ orderBy+ " diplome "+diplome+ " etudiant"+ " ajour" + ajour+ " retard " +retard );

	try {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();
		PreparedStatement stmt = null;

		System.out.println("recherche par");
		
		// tous les élèves
		if(ideleve.equals("") && nom.equals("") && prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve ORDER BY ? DESC");
		stmt.setString(1,orderBy);
		 System.out.println("tous les élèves " +stmt);
		}


		// recherche effectuée par matricule uniquement : il doit être exact!
		if(!ideleve.equals("") && nom.equals("") && prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve WHERE id_eleve=? ORDER BY ? DESC");
		stmt.setString(1,ideleve);
		stmt.setString(2,orderBy);
		System.out.println("matricule " +stmt);
		}
		
		
		
		// recherche par nom uniquement
		if(ideleve.equals("") && !nom.equals("") && prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve WHERE eleve_nom LIKE ? ORDER BY ? DESC");
		stmt.setString(1,nom);
		stmt.setString(2,orderBy);
		 System.out.println("nom " +stmt);}
		
		
		// recherche par prenom
		if(ideleve.equals("") && nom.equals("") && !prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve WHERE eleve_prenom LIKE ? ORDER BY ? DESC");
		stmt.setString(1,prenom);
		stmt.setString(2,orderBy);
		 System.out.println("prenom " +stmt);
		}
				
		// recherche par classe
		if(ideleve.equals("") && nom.equals("") && prenom.equals("") && !classe.equals("tous")  ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve INNER JOIN appartenir ON eleve.id_eleve=appartenir.id_eleve INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE classe=? ORDER BY ? DESC");
		stmt.setString(1,classe);
		stmt.setString(2,orderBy);
		 System.out.println("classe " +stmt);
		}
		
		
		// recherche par nom prenom
		if(ideleve.equals("") && !nom.equals("") && !prenom.equals("") && classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve WHERE eleve_nom LIKE ? AND eleve_prenom LIKE ? ORDER BY ? DESC");
		stmt.setString(1,nom);
		stmt.setString(2,prenom);
		stmt.setString(3,orderBy);
		 System.out.println("nom prenom " +stmt);
		}
		
		
		// recherche par nom classe
		if(ideleve.equals("") && !nom.equals("") && prenom.equals("") && !classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve INNER JOIN appartenir ON eleve.id_eleve=appartenir.id_eleve INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE classe=? AND eleve_nom LIKE ?ORDER BY ? DESC");
		stmt.setString(1,classe);
		stmt.setString(2,nom);
		stmt.setString(3,orderBy);
		 System.out.println("nom classe " +stmt);
		}
		
		// recherche par prenom classe
		if(ideleve.equals("") && nom.equals("") && !prenom.equals("") && !classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve INNER JOIN appartenir ON eleve.id_eleve=appartenir.id_eleve INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE classe=? AND eleve_prenom LIKE ?ORDER BY ? DESC");
		stmt.setString(1,classe);
		stmt.setString(2,prenom);
		stmt.setString(3,orderBy);
		 System.out.println("prenom classe " +stmt);
		}
				
		// recherche par nom prenom et  classe
		if(ideleve.equals("") && !nom.equals("") && !prenom.equals("") && !classe.equals("tous") ){
		stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM eleve INNER JOIN appartenir ON eleve.id_eleve=appartenir.id_eleve INNER JOIN classe ON appartenir.cle_classe=classe.cle_classe WHERE classe=? AND eleve_nom LIKE ? AND eleve_prenom LIKE ? ORDER BY ? DESC");
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
					Manager.getInstance().getPromotion(results.getString("id_eleve")),
					Manager.getInstance().getNbHeureTeaValide(results.getString("id_eleve")),
					Manager.getInstance().getTeaDuesEnCours(results.getString("id_eleve")),
					Manager.getInstance().getNbHeureEnAttente(results.getString("id_eleve")),
					null
					);
		
			int teadues=Manager.getInstance().getTeaDuesEnCours(results.getString("id_eleve"));
			if(Manager.getInstance().president(results.getString("id_eleve"))){ eleve.setCle_structure(Manager.getInstance().getCleStructureById(results.getString("id_eleve")));}
		
			if(!results.getString("id_eleve").equals("99999"))	{
				if(results.getInt("diplome")==1 || results.getInt("diplome")==0 || teadues==0 || teadues >0)elevescas1.add(eleve);
				if(results.getInt("diplome")==1 || results.getInt("diplome")==0 || teadues==0 )elevescas2.add(eleve);
				if(results.getInt("diplome")==1 || results.getInt("diplome")==0 || teadues >0 )elevescas3.add(eleve);
				if(results.getInt("diplome")==1 || results.getInt("diplome")==0 )elevescas4.add(eleve);
				if(results.getInt("diplome")==1 || teadues==0 || teadues>0)elevescas5.add(eleve);
				if(results.getInt("diplome")==1 || teadues==0 )elevescas6.add(eleve);
				if(results.getInt("diplome")==1 || teadues >0 )elevescas7.add(eleve);
				if(results.getInt("diplome")==1 )elevescas8.add(eleve);			
				if(results.getInt("diplome")==0 || teadues==0 || teadues>0)elevescas9.add(eleve);
				if(results.getInt("diplome")==0 || teadues==0 )elevescas10.add(eleve);
				if(results.getInt("diplome")==0 || teadues >0 )elevescas11.add(eleve);
				if(results.getInt("diplome")==0 )elevescas12.add(eleve);
				if(teadues==0 || teadues>0)elevescas13.add(eleve);
				if(teadues==0 )elevescas14.add(eleve);
				if(teadues >0 )elevescas15.add(eleve);
				if(results.getInt("diplome")==1 || results.getInt("diplome")==0 || teadues==0 || teadues >0)elevescas16.add(eleve);
			}
			

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
	if(diplome && etudiant && ajour && retard==false)elevesreturn=elevescas2;
	if(diplome && etudiant && ajour==false && retard)elevesreturn=elevescas3;
	if(diplome && etudiant && ajour==false && retard==false)elevesreturn=elevescas4;
	if(diplome && etudiant==false && ajour && retard)elevesreturn=elevescas5;
	if(diplome && etudiant==false && ajour && retard==false)elevesreturn=elevescas6;
	if(diplome && etudiant==false && ajour==false && retard)elevesreturn=elevescas7;
	if(diplome && etudiant==false && ajour==false && retard==false)elevesreturn=elevescas8;
	if(diplome==false && etudiant && ajour && retard)elevesreturn=elevescas9;
	if(diplome==false && etudiant && ajour && retard==false)elevesreturn=elevescas10;
	if(diplome==false && etudiant && ajour==false && retard)elevesreturn=elevescas11;
	if(diplome==false && etudiant && ajour==false && retard==false)elevesreturn=elevescas12;
	if(diplome==false && etudiant==false && ajour && retard)elevesreturn=elevescas13;
	if(diplome==false && etudiant==false && ajour && retard==false)elevesreturn=elevescas14;
	if(diplome==false && etudiant==false && ajour==false && retard)elevesreturn=elevescas15;
	if(diplome==false && etudiant==false && ajour==false && retard==false)elevesreturn=elevescas16;
	
	
	
	return elevesreturn;
}

public Integer sizeReponse(List<Eleve> eleve){
	return eleve.size();
}

}

