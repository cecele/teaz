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
import hei.devweb.model.Eleve;
import hei.devweb.model.Offre;

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


public class DaoTestCaseEleveDaoImpl {
	
	private EleveDao daoEleve = new EleveDaoImpl();
	
	
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
	// test EleveDaoImpl
	//------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------
	
//------------------------------------------------------------------------------------------------------------------------------
	 @Test
public void testCreateEleve () throws Exception {
		 String StringDatenaissance = "1999-01-01";
		 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");                                  
		 Date datenaissance = null; 
		 try {                         
			 datenaissance = sdf.parse(StringDatenaissance);   
			 

			 } 
			 catch (ParseException e) 
			 {                         
			 // TODO Auto-generated catch block                         
			 e.printStackTrace();                 
			 }
		 java.util.Date utilDate_naissance =datenaissance;
		  java.sql.Date sqlDateNaissance = new java.sql.Date(utilDate_naissance.getTime());
		  
	Eleve eleve= new Eleve("12222", "NOM", "Prénom", sqlDateNaissance, 11, "rue du 11","59111", "Onze", "2011",0, 0, 0,"motdepasse", "H1A", 0,0,0,null);
     
	daoEleve.CreateEleve(eleve);

        Connection connection = DataSourceProvider.getDataSource()
                        .getConnection();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM `eleve` WHERE `id_eleve`='12222'");
        Assert.assertTrue(results.next());
        Assert.assertEquals(eleve.getEleve_nom(), results.getString("eleve_nom"));
        Assert.assertEquals(eleve.getEleve_prenom(),results.getString("eleve_prenom"));
        Assert.assertEquals(eleve.getDate_naissance(), results.getDate("date_naissance"));
        Assert.assertEquals((int)eleve.getNumrue(), results.getInt("numrue"));
        Assert.assertEquals(eleve.getNomrue(), results.getString("nomrue"));
        Assert.assertEquals(eleve.getCodepostal(), results.getString("codepostal"));
        Assert.assertEquals(eleve.getVille(), results.getString("ville"));
        Assert.assertEquals(eleve.getDate_entree(), results.getString("date_entree"));
        Assert.assertEquals((int)eleve.getCotisant(), results.getInt("cotisant"));
        Assert.assertEquals((int)eleve.getEleve_profil(), results.getInt("eleve_profil"));
        Assert.assertEquals((int)eleve.getDiplome(), results.getInt("diplome"));
        Assert.assertEquals(eleve.getMotdepasse(), results.getString("motdepasse"));

        results.close();
        stmt.close();
        connection.close();
}
		
	//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testeleveChgtProfil () throws Exception {
			 
		daoEleve.eleveChgtProfil("10153",2);

	        Connection connection = DataSourceProvider.getDataSource()
	                        .getConnection();
	        Statement stmt = connection.createStatement();
	        ResultSet results = stmt.executeQuery("SELECT eleve_profil FROM `eleve` WHERE `id_eleve`='10153'");
	        Assert.assertTrue(results.next());
	        Assert.assertEquals(2, results.getInt("eleve_profil"));

	        results.close();
	        stmt.close();
	        connection.close();
	}
		//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testgetEleveById () throws Exception {
			 
		Eleve eleve= daoEleve.getEleveById("10153");

		 Connection connection = DataSourceProvider.getDataSource()
                 .getConnection();
		 Statement stmt = connection.createStatement();
		 ResultSet results = stmt.executeQuery("SELECT * FROM `eleve` WHERE `id_eleve`='10153'");
 	Assert.assertTrue(results.next());
 	Assert.assertEquals(eleve.getEleve_nom(), results.getString("eleve_nom"));
 	Assert.assertEquals(eleve.getEleve_prenom(),results.getString("eleve_prenom"));
 	Assert.assertEquals(eleve.getDate_naissance(), results.getDate("date_naissance"));
 	Assert.assertEquals((int)eleve.getNumrue(), results.getInt("numrue"));
 	Assert.assertEquals(eleve.getNomrue(), results.getString("nomrue"));
 	Assert.assertEquals(eleve.getCodepostal(), results.getString("codepostal"));
 	Assert.assertEquals(eleve.getVille(), results.getString("ville"));
 	Assert.assertEquals(eleve.getDate_entree(), results.getString("date_entree"));
 	Assert.assertEquals((int)eleve.getCotisant(), results.getInt("cotisant"));
 	Assert.assertEquals((int)eleve.getEleve_profil(), results.getInt("eleve_profil"));
 	Assert.assertEquals((int)eleve.getDiplome(), results.getInt("diplome"));
 	Assert.assertEquals(eleve.getMotdepasse(), results.getString("motdepasse"));
 
	        results.close();
	        stmt.close();
	        connection.close();
	}
		 
//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testgetEleveResponsables () throws Exception {
		List<Eleve> eleves = new ArrayList<Eleve>();
		eleves= daoEleve.getEleveResponsables(0);
		
		
		Connection connection = DataSourceProvider.getDataSource()
                .getConnection();
Statement stmt = connection.createStatement();

	ResultSet results = stmt.executeQuery("SELECT * FROM Eleve WHERE eleve_profil=0");
	int i=0;
while (results.next()){
	Assert.assertEquals(eleves.get(i).getEleve_nom(), results.getString("eleve_nom"));
	Assert.assertEquals(eleves.get(i).getEleve_prenom(),results.getString("eleve_prenom"));
	Assert.assertEquals(eleves.get(i).getDate_naissance(), results.getDate("date_naissance"));
	Assert.assertEquals((int)eleves.get(i).getNumrue(), results.getInt("numrue"));
	Assert.assertEquals(eleves.get(i).getNomrue(), results.getString("nomrue"));
	Assert.assertEquals(eleves.get(i).getCodepostal(), results.getString("codepostal"));
	Assert.assertEquals(eleves.get(i).getVille(), results.getString("ville"));
	Assert.assertEquals(eleves.get(i).getDate_entree(), results.getString("date_entree"));
	Assert.assertEquals((int)eleves.get(i).getCotisant(), results.getInt("cotisant"));
	Assert.assertEquals((int)eleves.get(i).getEleve_profil(), results.getInt("eleve_profil"));
	Assert.assertEquals((int)eleves.get(i).getDiplome(), results.getInt("diplome"));
	Assert.assertEquals(eleves.get(i).getMotdepasse(), results.getString("motdepasse"));
	i=i+1;
}
results.close();
stmt.close();
connection.close();
}
		 
//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	public void testgetEleveTotal () throws Exception {
		List<Eleve> eleves = new ArrayList<Eleve>();
		eleves= daoEleve.getEleveTotal();
		
		
		Connection connection = DataSourceProvider.getDataSource()
                .getConnection();
Statement stmt = connection.createStatement();

ResultSet results = stmt.executeQuery("SELECT * FROM Eleve ");
int i=0;
while (results.next()){
	Assert.assertEquals(eleves.get(i).getEleve_nom(), results.getString("eleve_nom"));
	Assert.assertEquals(eleves.get(i).getEleve_prenom(),results.getString("eleve_prenom"));
	Assert.assertEquals(eleves.get(i).getDate_naissance(), results.getDate("date_naissance"));
	Assert.assertEquals((int)eleves.get(i).getNumrue(), results.getInt("numrue"));
	Assert.assertEquals(eleves.get(i).getNomrue(), results.getString("nomrue"));
	Assert.assertEquals(eleves.get(i).getCodepostal(), results.getString("codepostal"));
	Assert.assertEquals(eleves.get(i).getVille(), results.getString("ville"));
	Assert.assertEquals(eleves.get(i).getDate_entree(), results.getString("date_entree"));
	Assert.assertEquals((int)eleves.get(i).getCotisant(), results.getInt("cotisant"));
	Assert.assertEquals((int)eleves.get(i).getEleve_profil(), results.getInt("eleve_profil"));
	//Assert.assertEquals((int)eleves.get(i).getDiplome(), results.getInt("diplome"));
	Assert.assertEquals(eleves.get(i).getMotdepasse(), results.getString("motdepasse"));
	
	i=i+1;
}
results.close();
stmt.close();
connection.close();
}
}
		 
		