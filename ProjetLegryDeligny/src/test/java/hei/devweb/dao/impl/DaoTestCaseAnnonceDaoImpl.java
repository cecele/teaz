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

public class DaoTestCaseAnnonceDaoImpl {
	
private AnnonceDao daoAnnonce= new AnnonceDaoImpl();

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
		stmt.executeUpdate("INSERT INTO `tea` (`cle_tea`, `date_tea_realisee`, `nbheure_realisee`, `statut_valide`, `date_validation`, `cle_offre`, `id_eleve`) VALUES (1, '2014-05-05', 2, 0, NULL, 1, '11111')");		
		stmt.executeUpdate("INSERT INTO `classe` (`cle_classe`, `classe`, `annee`, `nb_tea`) VALUES(1, 'H1A', '2014', NULL)");
		stmt.executeUpdate("INSERT INTO `classe` (`cle_classe`, `classe`, `annee`, `nb_tea`) VALUES(2, 'H2B', '2014', 3)");
		stmt.executeUpdate("INSERT INTO `appartenir` (`id_eleve`, `cle_classe`) VALUES ('10153', 1)");
		stmt.executeUpdate("INSERT INTO `appartenir` (`id_eleve`, `cle_classe`) VALUES ('11111', 2)");
		stmt.close();
		connection.close();
	}

//------------------------------------------------------------------------------------------------------------------------------	
	//------------------------------------------------------------------------------------------------------------------------------
	// test AnnonceDAOIMPL
	//------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------

	
	//------------------------------------------------------------------------------------------------------------------------------
		 @Test
     public void testajouterAnnonce () throws Exception {
		 
		 String StringDatedepot = "2014-05-09";
		 
		 String StringDatetea = "2014-05-15";
		 
		 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");                                  
		 Date datedepot = null; 
		 Date datemiseenligne=null;
		 Date datetea=null;
		 
		 try {                         
		 datedepot = sdf.parse(StringDatedepot);      
		 datemiseenligne=new Date();
		 datetea =sdf.parse(StringDatetea);
		 } 
		 catch (ParseException e) 
		 {                         
		 // TODO Auto-generated catch block                         
		 e.printStackTrace();                 
		 }
            Offre offre = new Offre(2,datedepot,datemiseenligne,datetea,"08", "11", 0, "description", "FF@hei.fr","titre", 1, 2,"INTEGRALE-VP", "LEGRY","Céline", 1);
                    		
           daoAnnonce.ajouterAnnonce(offre);

             Connection connection = DataSourceProvider.getDataSource()
                             .getConnection();
             Statement stmt = connection.createStatement();
             ResultSet results = stmt.executeQuery("SELECT * FROM `offre` WHERE `cle_offre`=2");
             Assert.assertTrue(results.next());
             Assert.assertNotNull(results.getInt("cle_offre"));
             Assert.assertEquals(offre.getDate_depot(), results.getDate("date_depot"));
             Assert.assertEquals(offre.getDate_miseenligne(),results.getDate("date_miseenligne"));
             Assert.assertEquals(offre.getDate_tea(), results.getDate("date_tea"));
             Assert.assertEquals(offre.getHeure_debut(), results.getString("heure_debut"));
             Assert.assertEquals(offre.getHeure_fin(), results.getString("heure_fin"));
             Assert.assertEquals((int)offre.getStatut(), results.getInt("statut"));
             Assert.assertEquals(offre.getOffre_description(), results.getString("offre_description"));
             Assert.assertEquals(offre.getEleve_mail(), results.getString("eleve_mail"));
             Assert.assertEquals(offre.getOffre_titre(), results.getString("offre_titre"));
             Assert.assertEquals((int)offre.getOffre_place(), results.getInt("offre_place"));
             Assert.assertEquals((int)offre.getCle_structure(), results.getInt("cle_structure"));

             results.close();
             stmt.close();
             connection.close();
     }
	
	
	//------------------------------------------------------------------------------------------------------------------------------
//	@Test
//    public void testdeleteOffre() throws Exception {
//		 daoAnnonce.deleteOffre(2);
//
//            Connection connection = DataSourceProvider.getDataSource()
//                            .getConnection();
//            Statement stmt = connection.createStatement();
//            ResultSet results = stmt.executeQuery("SELECT * FROM `offre` WHERE `cle_offre`=5");
//            Assert.assertFalse(results.next());
//            results.close();
//            stmt.close();
//            connection.close();
//    }

	
	//------------------------------------------------------------------------------------------------------------------------------
			 @Test
	     public void testannonce_validation() throws Exception {
			 
				 java.util.Date utilDate = new Date();
				                     		
	           daoAnnonce.annonce_validation(1,utilDate);
	
	             Connection connection = DataSourceProvider.getDataSource()
	                             .getConnection();
	             Statement stmt = connection.createStatement();
	             ResultSet results = stmt.executeQuery("SELECT statut FROM `offre` WHERE `cle_offre`=1");
	             Assert.assertTrue(results.next());
	             Assert.assertEquals(1, results.getInt("statut"));
	             
	             results.close();
	             stmt.close();
	             connection.close();
	     }
	
	//------------------------------------------------------------------------------------------------------------------------------
				 @Test
		     public void testoffre_placemoins() throws Exception {
					 
					 
					 Connection connection2 = DataSourceProvider.getDataSource()
                             .getConnection();
             Statement stmt2 = connection2.createStatement();
             ResultSet results2 = stmt2.executeQuery("SELECT offre_place FROM `offre` WHERE `cle_offre`=1");
             results2.next();
            int nbplace= results2.getInt("offre_place");
             
             results2.close();
             stmt2.close();
             connection2.close();
     
					 
					                     		
		           daoAnnonce.offre_placemoins(1);
		
		             Connection connection = DataSourceProvider.getDataSource()
		                             .getConnection();
		             Statement stmt = connection.createStatement();
		             ResultSet results = stmt.executeQuery("SELECT offre_place FROM `offre` WHERE `cle_offre`=1");
		             Assert.assertTrue(results.next());
		             Assert.assertNotEquals(nbplace, results.getInt("offre_place"));
		             
		             results.close();
		             stmt.close();
		             connection.close();
		     }
		
	//------------------------------------------------------------------------------------------------------------------------------
				 @Test
		     public void testannonce_miseHorsLigne() throws Exception {
				 
					
		           daoAnnonce.annonce_miseHorsLigne(1);
		
		             Connection connection = DataSourceProvider.getDataSource()
		                             .getConnection();
		             Statement stmt = connection.createStatement();
		             ResultSet results = stmt.executeQuery("SELECT statut FROM `offre` WHERE `cle_offre`=1");
		             Assert.assertTrue(results.next());
		             Assert.assertEquals(0, results.getInt("statut"));
		             
		             results.close();
		             stmt.close();
		             connection.close();
		     }
		
	//------------------------------------------------------------------------------------------------------------------------------
//					 @Test
//			     public void testAnnonceModification() throws Exception {
//						 
//						 String StringDatedepot = "2014-05-09";
//						 
//						 String StringDatetea = "2014-05-15";
//						 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");                                  
//						 Date datedepot = null; 
//						 Date datemiseenligne=null;
//						 Date datetea=null;
//						 
//						 try {                         
//						 datedepot = sdf.parse(StringDatedepot);      
//						
//						 datetea =sdf.parse(StringDatetea);
//						 } 
//						 catch (ParseException e) 
//						 {                         
//						 // TODO Auto-generated catch block                         
//						 e.printStackTrace();                 
//						 }
//				Offre offre1 = new Offre(1,datedepot,datemiseenligne,datetea,"12", "13", 0, "DESCRIPTION CHIANTE", "cc_le@hei.fr","TITRE INTERESSANT", 1, 3,"INTEGRALE-VP", "LEGRY","Céline", 0);
//
//			           daoAnnonce.AnnonceModification(offre1);
//			
//			           Connection connection = DataSourceProvider.getDataSource()
//                             .getConnection();
//             Statement stmt = connection.createStatement();
//             ResultSet results = stmt.executeQuery("SELECT eleve_mail,cle_offre FROM `offre` WHERE `cle_offre`=1");
//             Assert.assertTrue(results.next());
//             Assert.assertEquals(1, results.getInt("cle_offre"));
//             Assert.assertEquals("cc_le@hei.fr", results.getString("eleve_mail"));
//            
//			             
//			             results.close();
//			             stmt.close();
//			             connection.close();
//			     }
//	
	//------------------------------------------------------------------------------------------------------------------------------
			
	    @Test
	    public void testlisterOffreByEleve() throws Exception {
	    	
	    	List<Offre> offres = new ArrayList<Offre>();

	           offres = daoAnnonce.listerOffreByEleve("10153");

	            Connection connection = DataSourceProvider.getDataSource()
	                            .getConnection();
	            Statement stmt = connection.createStatement();
	            
	            ResultSet results = stmt.executeQuery("SELECT * FROM offre WHERE statut=1 AND offre_place>0 ORDER BY date_tea DESC ");
	            
	            int i=0;
	            while (results.next()){
	           
	  Assert.assertEquals(offres.get(i).getDate_depot(), results.getDate("date_depot"));
      Assert.assertEquals(offres.get(i).getDate_miseenligne(),results.getDate("date_miseenligne"));
      Assert.assertEquals(offres.get(i).getDate_tea(), results.getDate("date_tea"));
      Assert.assertEquals(offres.get(i).getHeure_debut(), results.getString("heure_debut"));
      Assert.assertEquals(offres.get(i).getHeure_fin(), results.getString("heure_fin"));
      Assert.assertEquals((int)offres.get(i).getStatut(),results.getInt("statut"));
      Assert.assertEquals(offres.get(i).getOffre_description(), results.getString("offre_description"));
      Assert.assertEquals(offres.get(i).getEleve_mail(), results.getString("eleve_mail"));
      Assert.assertEquals(offres.get(i).getOffre_titre(), results.getString("offre_titre"));
      Assert.assertEquals((int)offres.get(i).getOffre_place(), results.getInt("offre_place"));
      Assert.assertEquals((int)offres.get(i).getCle_structure(), results.getInt("cle_structure"));
	          
	          i=i+1;
	            }

	            stmt.close();
	            connection.close();
	            
	    }
	//------------------------------------------------------------------------------------------------------------------------------
		
    @Test
    public void testlisterOffre() throws Exception {
    	
    	List<Offre> offres = new ArrayList<Offre>();

           offres = daoAnnonce.listerOffre();

            Connection connection = DataSourceProvider.getDataSource()
                            .getConnection();
            Statement stmt = connection.createStatement();
            
            ResultSet results = stmt.executeQuery("SELECT * FROM `offre`WHERE statut=1 ");
            
            int i=0;
            while (results.next()){
           
	  Assert.assertEquals(offres.get(i).getDate_depot(), results.getDate("date_depot"));
      Assert.assertEquals(offres.get(i).getDate_miseenligne(),results.getDate("date_miseenligne"));
      Assert.assertEquals(offres.get(i).getDate_tea(), results.getDate("date_tea"));
      Assert.assertEquals(offres.get(i).getHeure_debut(), results.getString("heure_debut"));
      Assert.assertEquals(offres.get(i).getHeure_fin(), results.getString("heure_fin"));
      Assert.assertEquals((int)offres.get(i).getStatut(),results.getInt("statut"));
      Assert.assertEquals(offres.get(i).getOffre_description(), results.getString("offre_description"));
      Assert.assertEquals(offres.get(i).getEleve_mail(), results.getString("eleve_mail"));
      Assert.assertEquals(offres.get(i).getOffre_titre(), results.getString("offre_titre"));
      Assert.assertEquals((int)offres.get(i).getOffre_place(), results.getInt("offre_place"));
      Assert.assertEquals((int)offres.get(i).getCle_structure(), results.getInt("cle_structure"));
          i=i+1;
            }

            stmt.close();
            connection.close();
            
    }
	//------------------------------------------------------------------------------------------------------------------------------
		
    @Test
    public void testlisterOffreNonValide() throws Exception {
    	
    	List<Offre> offres = new ArrayList<Offre>();

           offres = daoAnnonce.listerOffreNonValide();

            Connection connection = DataSourceProvider.getDataSource()
                            .getConnection();
            Statement stmt = connection.createStatement();
            
            ResultSet results = stmt.executeQuery("SELECT * FROM offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE statut=0 AND offre_place>0 ORDER BY date_tea DESC ");
            
            int i=0;
            while (results.next()){
           
	  Assert.assertEquals(offres.get(i).getDate_depot(), results.getDate("date_depot"));
      Assert.assertEquals(offres.get(i).getDate_miseenligne(),results.getDate("date_miseenligne"));
      Assert.assertEquals(offres.get(i).getDate_tea(), results.getDate("date_tea"));
      Assert.assertEquals(offres.get(i).getHeure_debut(), results.getString("heure_debut"));
      Assert.assertEquals(offres.get(i).getHeure_fin(), results.getString("heure_fin"));
      Assert.assertEquals((int)offres.get(i).getStatut(),results.getInt("statut"));
      Assert.assertEquals(offres.get(i).getOffre_description(), results.getString("offre_description"));
      Assert.assertEquals(offres.get(i).getEleve_mail(), results.getString("eleve_mail"));
      Assert.assertEquals(offres.get(i).getOffre_titre(), results.getString("offre_titre"));
      Assert.assertEquals((int)offres.get(i).getOffre_place(), results.getInt("offre_place"));
      Assert.assertEquals((int)offres.get(i).getCle_structure(), results.getInt("cle_structure"));
          
          i=i+1;
            }

            stmt.close();
            connection.close();
            
    }
	//------------------------------------------------------------------------------------------------------------------------------
		
    @Test
    public void testlisterOffreByStructure() throws Exception {
    	
    	List<Offre> offres = new ArrayList<Offre>();

           offres = daoAnnonce.listerOffreByStructure(1);

            Connection connection = DataSourceProvider.getDataSource()
                            .getConnection();
            Statement stmt = connection.createStatement();
            
            ResultSet results = stmt.executeQuery("SELECT * FROM offre INNER JOIN structure ON offre.cle_structure=structure.cle_structure WHERE offre.cle_structure=1 ORDER BY date_tea DESC");
            
            int i=0;
            while (results.next()){
           
          Assert.assertEquals(offres.get(i).getDate_depot(), results.getDate("date_depot"));
          Assert.assertEquals(offres.get(i).getDate_miseenligne(),results.getDate("date_miseenligne"));
          Assert.assertEquals(offres.get(i).getDate_tea(), results.getDate("date_tea"));
          Assert.assertEquals(offres.get(i).getHeure_debut(), results.getString("heure_debut"));
          Assert.assertEquals(offres.get(i).getHeure_fin(), results.getString("heure_fin"));
          Assert.assertEquals((int)offres.get(i).getStatut(),results.getInt("statut"));
          Assert.assertEquals(offres.get(i).getOffre_description(), results.getString("offre_description"));
          Assert.assertEquals(offres.get(i).getEleve_mail(), results.getString("eleve_mail"));
          Assert.assertEquals(offres.get(i).getOffre_titre(), results.getString("offre_titre"));
          Assert.assertEquals((int)offres.get(i).getOffre_place(), results.getInt("offre_place"));
          Assert.assertEquals((int)offres.get(i).getCle_structure(), results.getInt("cle_structure"));
          
          i=i+1;
            }

            stmt.close();
            connection.close();
            
    }
	
	//------------------------------------------------------------------------------------------------------------------------------
	 @Test
public void testgetNbPlaces() throws Exception {
	 
		
		 int nbplace=daoAnnonce.getNbPlaces(1);

        Connection connection = DataSourceProvider.getDataSource()
                        .getConnection();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery("SELECT offre_place FROM `offre` WHERE `cle_offre`=1");
        Assert.assertTrue(results.next());
        Assert.assertEquals(nbplace, results.getInt("offre_place"));
        
        results.close();
        stmt.close();
        connection.close();
}
}