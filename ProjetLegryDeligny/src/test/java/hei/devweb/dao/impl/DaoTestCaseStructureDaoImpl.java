package hei.devweb.dao.impl;
import hei.devweb.dao.AnnonceDao;
import hei.devweb.dao.ArticleDao;
import hei.devweb.dao.EleveDao;
import hei.devweb.dao.RechercheDao;
import hei.devweb.dao.StructureDao;
import hei.devweb.dao.TeaDao;
import hei.devweb.daoimpl.AnnonceDaoImpl;
import hei.devweb.daoimpl.ArticleDaoImpl;
import hei.devweb.daoimpl.DataSourceProvider;
import hei.devweb.daoimpl.EleveDaoImpl;
import hei.devweb.daoimpl.RechercheDaoImpl;
import hei.devweb.daoimpl.StructureDaoImpl;
import hei.devweb.daoimpl.TeaDaoImpl;
import hei.devweb.metier.Manager;
import hei.devweb.model.Eleve;
import hei.devweb.model.Offre;
import hei.devweb.model.Structure;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;


public class DaoTestCaseStructureDaoImpl {

	private StructureDao daoStructure=new StructureDaoImpl();
	
	@Before
	public void purgeBDD() throws Exception {
		Connection connection = DataSourceProvider.getDataSource()
				.getConnection();
		Statement stmt = connection.createStatement();
		
		stmt.executeUpdate("DELETE FROM tea");
		stmt.executeUpdate("DELETE FROM article");
		stmt.executeUpdate("DELETE FROM ecrire");
		stmt.executeUpdate("DELETE FROM presider");
		stmt.executeUpdate("DELETE FROM appartenir");	
		stmt.executeUpdate("DELETE FROM classe");
	
				
		stmt.executeUpdate("DELETE FROM offre");
		
		
		stmt.executeUpdate("DELETE FROM structure");
		stmt.executeUpdate("DELETE FROM eleve");
		

		stmt.executeUpdate("INSERT INTO `eleve` (`id_eleve`, `eleve_nom`, `eleve_prenom`, `date_naissance`, `numrue`, `nomrue`, `codepostal`, `ville`, `date_entree`, `cotisant`, `eleve_profil`, `diplome`, `motdepasse`) VALUES ('10153', 'LEGRY', 'Céline', '1991-06-14', 59, 'rue des stations', '59000', 'Lille', 2009, 0, 0, 0, 'motdepasse')");
		stmt.executeUpdate("INSERT INTO `eleve` (`id_eleve`, `eleve_nom`, `eleve_prenom`, `date_naissance`, `numrue`, `nomrue`, `codepostal`, `ville`, `date_entree`, `cotisant`, `eleve_profil`, `diplome`, `motdepasse`) VALUES ('11111', 'DELIGNY', 'MARTIN', '1991-03-11', 12, 'RUE DU PORT', '59000', 'LILLE', 2010, NULL, 1, 0, 'motdepasse')");
		stmt.executeUpdate("INSERT INTO `structure` (`cle_structure`, `structure_nom`) VALUES (1, 'INTEGRALE-VP')");
		stmt.executeUpdate("INSERT INTO `structure` (`cle_structure`, `structure_nom`) VALUES (2, 'INTEGRALE-P')");
		stmt.executeUpdate("INSERT INTO `presider` (`id_eleve`, `cle_structure`, `date_debut`, `date_fin`) VALUES ('11111', 1, '2014-05-01', '2015-06-15')");
		// attention penser à modifier la date de mise en ligne à la date du jour
		stmt.executeUpdate("INSERT INTO `offre` (`cle_offre`, `date_depot`, `date_miseenligne`, `date_tea`, `heure_debut`, `heure_fin`, `statut`, `offre_description`, `eleve_mail`, `offre_titre`, `offre_place`, `cle_structure`) VALUES (1, '2014-05-09', '2014-05-11', '2014-05-19', '12', '13', 1, 'DESCRIPTION CHIANTE', 'cc@hei.fr', 'TITRE INTERESSANT', 3, 1)");
		stmt.executeUpdate("INSERT INTO `offre` (`cle_offre`, `date_depot`, `date_miseenligne`, `date_tea`, `heure_debut`, `heure_fin`, `statut`, `offre_description`, `eleve_mail`, `offre_titre`, `offre_place`, `cle_structure`) VALUES (2, '2014-05-09', '2014-05-10', '2014-05-19', '12', '13', 0, 'DESCRIPTION CHIANTE', 'cc@hei.fr', 'TITRE INTERESSANT', 3, 1)");
		stmt.executeUpdate("INSERT INTO `tea` (`cle_tea`, `date_tea_realisee`, `nbheure_realisee`, `nbheure_validee`,`statut_valide`, `date_validation`, `cle_offre`, `id_eleve`) VALUES (1, '2014-05-05', 2, 0,0, NULL, 1, '11111')");		
		stmt.executeUpdate("INSERT INTO `classe` (`cle_classe`, `classe`, `annee`, `nb_tea`) VALUES(1, 'H1A', '2014', NULL)");
		stmt.executeUpdate("INSERT INTO `classe` (`cle_classe`, `classe`, `annee`, `nb_tea`) VALUES(2, 'H2B', '2014', 3)");
		stmt.executeUpdate("INSERT INTO `appartenir` (`id_eleve`, `cle_classe`) VALUES ('10153', 1)");
		stmt.executeUpdate("INSERT INTO `appartenir` (`id_eleve`, `cle_classe`) VALUES ('11111', 2)");
		stmt.close();
		
		connection.close();
	}

//------------------------------------------------------------------------------------------------------------------------------	
	//------------------------------------------------------------------------------------------------------------------------------
	// test StructureDaoImpl
	//------------------------------------------------------------------------------------------------------------------------------
	
	
	//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testStructureChangement () throws Exception {

String StringDateDebut = "2014-05-01";       
 String StringDateFin = "2014-06-01";       
 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");                              
 Date dateDebut = null;       
 Date dateFin = null;             
  try {dateDebut = sdf.parse(StringDateDebut); } catch (ParseException e) { e.printStackTrace();}     
try { dateFin = sdf.parse(StringDateFin); } catch (ParseException e) { e.printStackTrace();}

			  
		
		daoStructure.StructureChangement ("10153",2,dateDebut,dateFin);

	        Connection connection = DataSourceProvider.getDataSource()
	                        .getConnection();
	        Statement stmt = connection.createStatement();
	        ResultSet results = stmt.executeQuery("SELECT * FROM presider WHERE id_eleve='10153' AND cle_structure=2 ");
	        Assert.assertTrue(results.next());
	        Assert.assertEquals(dateDebut, results.getString("date_debut"));
	        Assert.assertEquals(dateFin, results.getString("date_fin"));
	  

	        results.close();
	        stmt.close();
	        connection.close();
	}
		 
		//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testCreateStructure() throws Exception {


		daoStructure.CreateStructure("nomdelastructuredemerde");

	        Connection connection = DataSourceProvider.getDataSource()
	                        .getConnection();
	        Statement stmt = connection.createStatement();
	        ResultSet results = stmt.executeQuery("SELECT * FROM structure WHERE cle_structure=3 ");
	        Assert.assertTrue(results.next());
	        Assert.assertEquals("nomdelastructuredemerde", results.getString("structure_nom"));
	       
	  

	        results.close();
	        stmt.close();
	        connection.close();
	}
			//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testgetPresidentNomById () throws Exception {
			 
			String prez =daoStructure.getPresidentNomById(1);

	        Connection connection = DataSourceProvider.getDataSource()
	                        .getConnection();
	        Statement stmt = connection.createStatement();
	        ResultSet results = stmt.executeQuery("SELECT eleve_nom FROM eleve INNER JOIN presider ON eleve.id_eleve=presider.id_eleve WHERE presider.cle_structure=1 ");
	        Assert.assertTrue(results.next());
	        Assert.assertEquals(prez, results.getString("eleve_nom"));

	        results.close();
	        stmt.close();
	        connection.close();
	}
	//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testgetCleByNom () throws Exception {
			 
			int cle =daoStructure.getCleByNom("INTEGRALE-VP");

	        Connection connection = DataSourceProvider.getDataSource()
	                        .getConnection();
	        Statement stmt = connection.createStatement();
	        ResultSet results = stmt.executeQuery("SELECT cle_structure FROM structure WHERE structure_nom='INTEGRALE-VP' ");
	        Assert.assertTrue(results.next());
	        Assert.assertEquals(cle, results.getInt("cle_structure"));

	        results.close();
	        stmt.close();
	        connection.close();
	}
		//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testgetPresidentPrenomById () throws Exception {
			 
			String prez =daoStructure.getPresidentPrenomById(1);

	        Connection connection = DataSourceProvider.getDataSource()
	                        .getConnection();
	        Statement stmt = connection.createStatement();
	        ResultSet results = stmt.executeQuery("SELECT eleve_prenom FROM eleve INNER JOIN presider ON eleve.id_eleve=presider.id_eleve WHERE presider.cle_structure=1 ");
	        Assert.assertTrue(results.next());
	        Assert.assertEquals(prez, results.getString("eleve_prenom"));

	        results.close();
	        stmt.close();
	        connection.close();
	}	
		//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testgetPresidentIdById () throws Exception {
			 
			String prez =daoStructure.getPresidentIdById(1);

	        Connection connection = DataSourceProvider.getDataSource()
	                        .getConnection();
	        Statement stmt = connection.createStatement();
	        ResultSet results = stmt.executeQuery("SELECT id_eleve FROM presider WHERE presider.cle_structure=1 ");
	        Assert.assertTrue(results.next());
	        Assert.assertEquals(prez, results.getString("id_eleve"));

	        results.close();
	        stmt.close();
	        connection.close();
	}	
		//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testgetNomStructure () throws Exception {
			 
			String prez =daoStructure.getNomStructure(1);

	        Connection connection = DataSourceProvider.getDataSource()
	                        .getConnection();
	        Statement stmt = connection.createStatement();
	        ResultSet results = stmt.executeQuery("SELECT structure_nom FROM structure WHERE cle_structure=1 ");
	        Assert.assertTrue(results.next());
	        Assert.assertEquals(prez,results.getString("structure_nom"));

	        results.close();
	        stmt.close();
	        connection.close();
	}	
		//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testgetStructure_OrdreNom() throws Exception {
			List<Structure> structures = new ArrayList<Structure>();
		structures= daoStructure.getStructure_OrdreNom();
		
		
		Connection connection = DataSourceProvider.getDataSource()
                .getConnection();
Statement stmt = connection.createStatement();

	ResultSet results = stmt.executeQuery("SELECT * FROM structure ORDER BY structure_nom ASC");
	int i=0;
while (results.next()){
	Assert.assertEquals((int)structures.get(i).getCle_structure(), results.getInt("cle_structure"));
	Assert.assertEquals(structures.get(i).getStructure_nom() , results.getString("structure_nom"));
	Assert.assertEquals(structures.get(i).getStructure_president_nom(), Manager.getInstance().getPresidentNomById(results.getInt("cle_structure")));
	Assert.assertEquals(structures.get(i).getStructure_president_prenom(), Manager.getInstance().getPresidentPrenomById(results.getInt("cle_structure")));
	
	i=i+1;
}
results.close();
stmt.close();
connection.close();
}
		 

	//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testgetStructure_ElevePresident() throws Exception {
			 
			Structure structure= daoStructure.getStructure_ElevePresident("11111");
		
		
		Connection connection = DataSourceProvider.getDataSource()
                .getConnection();
Statement stmt = connection.createStatement();

	ResultSet results = stmt.executeQuery("SELECT * FROM structure WHERE cle_structure=1");
	
	Assert.assertTrue(results.next());
	Assert.assertEquals((int)structure.getCle_structure(), results.getInt("cle_structure"));
	Assert.assertEquals(structure.getStructure_nom() , results.getString("structure_nom"));
	Assert.assertEquals(structure.getStructure_president_nom(), Manager.getInstance().getPresidentNomById(results.getInt("cle_structure")));
	Assert.assertEquals(structure.getStructure_president_prenom(), Manager.getInstance().getPresidentPrenomById(results.getInt("cle_structure")));


results.close();
stmt.close();
connection.close();
}
}
