package hei.devweb.dao.impl;

import hei.devweb.daoimpl.DataSourceProvider;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Before;

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

public class DaoTestCaseRechercheDaoImpl {
	private RechercheDao daoRecherche = new RechercheDaoImpl();
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
	// test RechercheDaoImpl
	//------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------
	
}
