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
import hei.devweb.model.Tea;

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


public class DaoTestCaseTeaDaoImpl {
	
private TeaDao daoTea = new TeaDaoImpl();
	
	
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
	// test TeaDaoImpl
	//------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------
	 @Test
public void testajouterTea() throws Exception {
		 
		 String StringDatedepot = "2014-05-09";
		 
		 String StringDatetea = "2014-05-15";
		 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");                                  
		 Date datedepot = null; 
		 Date datemiseenligne=null;
		 Date datetea=null;
		 
		 try {                         
		 datedepot = sdf.parse(StringDatedepot);      
		
		 datetea =sdf.parse(StringDatetea);
		 } 
		 catch (ParseException e) 
		 {                         
		                          
		 e.printStackTrace();                 
		 }
		 
		 Offre offre = new Offre(1,datedepot,datemiseenligne,datetea,"12", "13", 0, "DESCRIPTION CHIANTE", "cc_le@hei.fr","TITRE INTERESSANT", 1, 3,"INTEGRALE-VP", "LEGRY","Céline", 0);

	daoTea.ajouterTea (1,"10153");

       Connection connection = DataSourceProvider.getDataSource()
                       .getConnection();
       Statement stmt = connection.createStatement();
       ResultSet results = stmt.executeQuery("SELECT * FROM tea WHERE cle_tea=3 ");
       Assert.assertTrue(results.next());
		  Assert.assertEquals(offre.getDate_tea(),  results.getDate("date_tea_realisee"));

	      Assert.assertEquals((int)offre.getStatut(),results.getInt("statut_valide"));		      
	      Assert.assertEquals(null,results.getDate("date_validation"));
	      Assert.assertEquals("10153", results.getString("id_eleve"));
	      
	      Assert.assertEquals((int)offre.getCle_offre(), results.getInt("cle_offre"));
  
       results.close();
       stmt.close();
       connection.close();
}
	//------------------------------------------------------------------------------------------------------------------------------
	 @Test
 public void testteaValidationByStructure( ) throws Exception {
	 
		 
		
      daoTea.teaValidationByStructure(1,1,"11111" );

         Connection connection = DataSourceProvider.getDataSource()
                         .getConnection();
         Statement stmt = connection.createStatement();
         ResultSet results = stmt.executeQuery("SELECT statut_valide FROM tea WHERE cle_tea = 1  ");
         Assert.assertTrue(results.next());
         Assert.assertEquals(1, results.getInt("statut_valide"));
         results.close();
         stmt.close();
         connection.close();
       
         
 }
	 
	//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	 public void testteaValidationByResponsable( ) throws Exception {
		 
			 
			
	      daoTea.teaValidationByResponsable(1);

	         Connection connection = DataSourceProvider.getDataSource()
	                         .getConnection();
	         Statement stmt = connection.createStatement();
	         ResultSet results = stmt.executeQuery("SELECT statut_valide FROM tea WHERE cle_tea = 1  ");
	         Assert.assertTrue(results.next());
	         Assert.assertEquals(2, results.getInt("statut_valide"));
	         results.close();
	         stmt.close();
	         connection.close();
	       
	         
	 }
			//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	 public void testgetIdeleveByCleTea( ) throws Exception {
		 
			 
			
	      daoTea.getIdeleveByCleTea(1);

	         Connection connection = DataSourceProvider.getDataSource()
	                         .getConnection();
	         Statement stmt = connection.createStatement();
	         ResultSet results = stmt.executeQuery("SELECT id_eleve  FROM tea WHERE cle_tea=1 ");
	         Assert.assertTrue(results.next());
	         Assert.assertEquals("10153", results.getString("id_eleve"));
	         results.close();
	         stmt.close();
	         connection.close();
	       
	         
	 }
		 
		//------------------------------------------------------------------------------------------------------------------------------
		 @Test
	 public void testgetNbHeureTeaRealiseeByTea( ) throws Exception {
		 
			 
			
	    int total=  daoTea.getNbHeureTeaRealiseeByTea(1);

	         Connection connection = DataSourceProvider.getDataSource()
	                         .getConnection();
	         Statement stmt = connection.createStatement();
	         ResultSet results = stmt.executeQuery("SELECT COUNT(cle_tea) as total  FROM tea WHERE cle_offre=1 ");
	         Assert.assertTrue(results.next());
	         Assert.assertEquals(total, results.getString("total"));
	         results.close();
	         stmt.close();
	         connection.close();
	       
	         
	 }
//		//------------------------------------------------------------------------------------------------------------------------------
//		 @Test
//	 public void testgetNbHeureTeaRealiseeBy2Tea( ) throws Exception {
//		 
//			 
//			
//	    int total=  daoTea.getNbHeureTeaRealiseeByTea(1);
//
//	         Connection connection = DataSourceProvider.getDataSource()
//	                         .getConnection();
//	         Statement stmt = connection.createStatement();
//	         ResultSet results = stmt.executeQuery("SELECT COUNT(cle_tea) as total  FROM tea WHERE cle_offre=1 ");
//	         Assert.assertTrue(results.next());
//	         Assert.assertEquals(total, results.getString("total"));
//	         results.close();
//	         stmt.close();
//	         connection.close();
//	       
//	         
//	 }
//		 
			//------------------------------------------------------------------------------------------------------------------------------
			
		    @Test
		    public void testgetTeaByOffre() throws Exception {
		    	
		    	List<Tea> teas = new ArrayList<Tea>();

		           teas = daoTea.getTeaByOffre(1);

		            Connection connection = DataSourceProvider.getDataSource()
		                            .getConnection();
		            Statement stmt = connection.createStatement();
		           
		            ResultSet results = stmt.executeQuery("SELECT * FROM tea WHERE cle_offre=1 ");
		            
		            int i=0;
		            while (results.next()){
		           
			  Assert.assertEquals(teas.get(i).getDate_tea_realisee(),  results.getDate("date_tea_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_realisee(),results.getInt("nbheure_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_validee(), results.getInt("nbheure_validee"));
		      Assert.assertEquals((int)teas.get(i).getStatut_valide(),results.getInt("statut_valide"));		      
		      Assert.assertEquals(teas.get(i).getDate_validation(),results.getDate("date_validation"));
		      Assert.assertEquals(teas.get(i).getId_eleve(), results.getString("id_eleve"));
		      
		      Assert.assertEquals((int)teas.get(i).getCle_offre(), results.getInt("cle_offre"));

		          i=i+1;
		            }

		            stmt.close();
		            connection.close();
		            
		    }
		    
//------------------------------------------------------------------------------------------------------------------------------
			
		    @Test
		    public void testgetTeaByEleve() throws Exception {
		    	
		    	List<Tea> teas = new ArrayList<Tea>();

		           teas = daoTea.getTeaByEleve("10153");

		            Connection connection = DataSourceProvider.getDataSource()
		                            .getConnection();
		            Statement stmt = connection.createStatement();
		           
		            ResultSet results = stmt.executeQuery("SELECT * FROM tea WHERE id_eleve='10153' ");
		            
		            int i=0;
		            while (results.next()){
		           
			  Assert.assertEquals(teas.get(i).getDate_tea_realisee(),  results.getDate("date_tea_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_realisee(),results.getInt("nbheure_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_validee(), results.getInt("nbheure_validee"));
		      Assert.assertEquals((int)teas.get(i).getStatut_valide(),results.getInt("statut_valide"));		      
		      Assert.assertEquals(teas.get(i).getDate_validation(),results.getDate("date_validation"));
		      Assert.assertEquals(teas.get(i).getId_eleve(), results.getString("id_eleve"));
		      
		      Assert.assertEquals((int)teas.get(i).getCle_offre(), results.getInt("cle_offre"));

		          i=i+1;
		            }

		            stmt.close();
		            connection.close();
		            
		    }
//------------------------------------------------------------------------------------------------------------------------------
			
		    @Test
		    public void testgetTeaAValiderByStructure() throws Exception {
		    	
		    	List<Tea> teas = new ArrayList<Tea>();
		    	Date date= new Date();
		           teas = daoTea.getTeaAValiderByStructure(1,date);

		            Connection connection = DataSourceProvider.getDataSource()
		                            .getConnection();
		            Statement stmt = connection.createStatement();
		           
		            ResultSet results = stmt.executeQuery("SELECT * FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre WHERE cle_structure=1 AND statut_valide=0 AND date_tea_realisee<2014-05-12 ");
		            
		            int i=0;
		            while (results.next()){
		           
			  Assert.assertEquals(teas.get(i).getDate_tea_realisee(),  results.getDate("date_tea_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_realisee(),results.getInt("nbheure_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_validee(), results.getInt("nbheure_validee"));
		      Assert.assertEquals((int)teas.get(i).getStatut_valide(),results.getInt("statut_valide"));		      
		      Assert.assertEquals(teas.get(i).getDate_validation(),results.getDate("date_validation"));
		      Assert.assertEquals(teas.get(i).getId_eleve(), results.getString("id_eleve"));
		      
		      Assert.assertEquals((int)teas.get(i).getCle_offre(), results.getInt("cle_offre"));

		          i=i+1;
		            }

		            stmt.close();
		            connection.close();
		            
		    }
		    
//------------------------------------------------------------------------------------------------------------------------------
			
		    @Test
		    public void testgetTeaByStructure() throws Exception {
		    	
		    	List<Tea> teas = new ArrayList<Tea>();
		    	
		           teas = daoTea.getTeaByStructure(1);

		            Connection connection = DataSourceProvider.getDataSource()
		                            .getConnection();
		            Statement stmt = connection.createStatement();
		           
		            ResultSet results = stmt.executeQuery("SELECT * FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre WHERE cle_structure=1 ");
		            
		            int i=0;
		            while (results.next()){
		           
			  Assert.assertEquals(teas.get(i).getDate_tea_realisee(),  results.getDate("date_tea_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_realisee(),results.getInt("nbheure_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_validee(), results.getInt("nbheure_validee"));
		      Assert.assertEquals((int)teas.get(i).getStatut_valide(),results.getInt("statut_valide"));		      
		      Assert.assertEquals(teas.get(i).getDate_validation(),results.getDate("date_validation"));
		      Assert.assertEquals(teas.get(i).getId_eleve(), results.getString("id_eleve"));
		      
		      Assert.assertEquals((int)teas.get(i).getCle_offre(), results.getInt("cle_offre"));

		          i=i+1;
		            }

		            stmt.close();
		            connection.close();
		            
		    }    
//------------------------------------------------------------------------------------------------------------------------------
			
		    @Test
		    public void testgetTeaAValiderByRespTea() throws Exception {
		    	
		    	List<Tea> teas = new ArrayList<Tea>();
		    	
		           teas = daoTea.getTeaAValiderByRespTea();

		            Connection connection = DataSourceProvider.getDataSource()
		                            .getConnection();
		            Statement stmt = connection.createStatement();
		           
		            ResultSet results = stmt.executeQuery("SELECT * FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre WHERE statut_valide=1");
		            
		            int i=0;
		            while (results.next()){
		           
			  Assert.assertEquals(teas.get(i).getDate_tea_realisee(),  results.getDate("date_tea_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_realisee(),results.getInt("nbheure_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_validee(), results.getInt("nbheure_validee"));
		      Assert.assertEquals((int)teas.get(i).getStatut_valide(),results.getInt("statut_valide"));		      
		      Assert.assertEquals(teas.get(i).getDate_validation(),results.getDate("date_validation"));
		      Assert.assertEquals(teas.get(i).getId_eleve(), results.getString("id_eleve"));
		      
		      Assert.assertEquals((int)teas.get(i).getCle_offre(), results.getInt("cle_offre"));

		          i=i+1;
		            }

		            stmt.close();
		            connection.close();
		            
		    }    
//------------------------------------------------------------------------------------------------------------------------------
			
		    @Test
		    public void testgetTeaValideByEleve() throws Exception {
		    	
		    	List<Tea> teas = new ArrayList<Tea>();
		    	
		           teas = daoTea.getTeaValideByEleve("10153");

		            Connection connection = DataSourceProvider.getDataSource()
		                            .getConnection();
		            Statement stmt = connection.createStatement();
		           
		            ResultSet results = stmt.executeQuery("SELECT * FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE id_eleve='10153'AND statut_valide=1");
		            
		            int i=0;
		            while (results.next()){
		           
			  Assert.assertEquals(teas.get(i).getDate_tea_realisee(),  results.getDate("date_tea_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_realisee(),results.getInt("nbheure_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_validee(), results.getInt("nbheure_validee"));
		      Assert.assertEquals((int)teas.get(i).getStatut_valide(),results.getInt("statut_valide"));		      
		      Assert.assertEquals(teas.get(i).getDate_validation(),results.getDate("date_validation"));
		      Assert.assertEquals(teas.get(i).getId_eleve(), results.getString("id_eleve"));
		      
		      Assert.assertEquals((int)teas.get(i).getCle_offre(), results.getInt("cle_offre"));

		          i=i+1;
		            }

		            stmt.close();
		            connection.close();
		            
		    }    
		    
//------------------------------------------------------------------------------------------------------------------------------
			
		    @Test
		    public void testgetTeaNonValideByEleve() throws Exception {
		    	
		    	List<Tea> teas = new ArrayList<Tea>();
		    	
		           teas = daoTea.getTeaNonValideByEleve("10153");

		            Connection connection = DataSourceProvider.getDataSource()
		                            .getConnection();
		            Statement stmt = connection.createStatement();
		           
		            ResultSet results = stmt.executeQuery("SELECT * FROM tea INNER JOIN offre ON tea.cle_offre=offre.cle_offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE id_eleve='10153'AND statut_valide=0");
		            
		            int i=0;
		            while (results.next()){
		           
			  Assert.assertEquals(teas.get(i).getDate_tea_realisee(),  results.getDate("date_tea_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_realisee(),results.getInt("nbheure_realisee"));
		      Assert.assertEquals((int)teas.get(i).getNbheure_validee(), results.getInt("nbheure_validee"));
		      Assert.assertEquals((int)teas.get(i).getStatut_valide(),results.getInt("statut_valide"));		      
		      Assert.assertEquals(teas.get(i).getDate_validation(),results.getDate("date_validation"));
		      Assert.assertEquals(teas.get(i).getId_eleve(), results.getString("id_eleve"));
		      
		      Assert.assertEquals((int)teas.get(i).getCle_offre(), results.getInt("cle_offre"));

		          i=i+1;
		            }

		            stmt.close();
		            connection.close();
		            
		    }    
		    
		    
		    
}
