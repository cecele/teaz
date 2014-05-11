-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 11 Mai 2014 à 12:35
-- Version du serveur :  5.6.15-log
-- Version de PHP :  5.5.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `bddhei`
--

-- --------------------------------------------------------

--
-- Structure de la table `appartenir`
--

CREATE TABLE IF NOT EXISTS `appartenir` (
  `id_eleve` char(6) NOT NULL,
  `cle_classe` int(11) NOT NULL,
  PRIMARY KEY (`id_eleve`,`cle_classe`),
  KEY `FK_APPARTENIR_cle_classe` (`cle_classe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `appartenir`
--

INSERT INTO `appartenir` (`id_eleve`, `cle_classe`) VALUES
('12222', 1),
('13333', 3),
('10153', 4),
('11111', 4),
('13333', 7);

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE IF NOT EXISTS `article` (
  `cle_article` int(11) NOT NULL AUTO_INCREMENT,
  `image` blob,
  `titre` varchar(200) DEFAULT NULL,
  `article` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`cle_article`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE IF NOT EXISTS `classe` (
  `cle_classe` int(11) NOT NULL AUTO_INCREMENT,
  `classe` char(3) DEFAULT NULL,
  `annee` date DEFAULT NULL,
  `nb_tea` int(11) DEFAULT NULL,
  PRIMARY KEY (`cle_classe`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `classe`
--

INSERT INTO `classe` (`cle_classe`, `classe`, `annee`, `nb_tea`) VALUES
(1, 'H1A', '2014-05-30', NULL),
(2, 'H2B', '2014-05-30', 3),
(3, 'H33', '2014-05-30', 6),
(4, 'H44', '2014-05-30', 6),
(5, 'CES', '2014-05-30', 0),
(6, 'HCA', '2014-05-30', 0),
(7, 'H2A', '2013-05-30', 3);

-- --------------------------------------------------------

--
-- Structure de la table `ecrire`
--

CREATE TABLE IF NOT EXISTS `ecrire` (
  `id_eleve` char(6) NOT NULL,
  `cle_article` int(11) NOT NULL,
  `ecrire_date` date DEFAULT NULL,
  PRIMARY KEY (`id_eleve`,`cle_article`),
  KEY `FK_ECRIRE_cle_article` (`cle_article`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

CREATE TABLE IF NOT EXISTS `eleve` (
  `id_eleve` char(6) NOT NULL,
  `eleve_nom` varchar(150) DEFAULT NULL,
  `eleve_prenom` varchar(150) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `numrue` int(11) DEFAULT NULL,
  `nomrue` varchar(1000) DEFAULT NULL,
  `codepostal` char(5) DEFAULT NULL,
  `ville` varchar(200) DEFAULT NULL,
  `date_entree` year(4) DEFAULT NULL,
  `cotisant` tinyint(1) DEFAULT NULL,
  `eleve_profil` int(11) NOT NULL DEFAULT '0',
  `diplome` int(11) NOT NULL DEFAULT '0',
  `motdepasse` varchar(50) NOT NULL DEFAULT 'motdepasse',
  PRIMARY KEY (`id_eleve`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `eleve`
--

INSERT INTO `eleve` (`id_eleve`, `eleve_nom`, `eleve_prenom`, `date_naissance`, `numrue`, `nomrue`, `codepostal`, `ville`, `date_entree`, `cotisant`, `eleve_profil`, `diplome`, `motdepasse`) VALUES
('10153', 'LEGRY', 'Céline', '1991-06-14', 59, 'rue des stations', '59000', 'Lille', 2009, 0, 1, 0, 'motdepasse'),
('11111', 'DELIGNY', 'MARTIN', '1991-03-11', 12, 'RUE DU PORT', '59000', 'LILLE', 2010, NULL, 3, 0, 'motdepasse'),
('12222', 'FANCHINI', 'THEO', '1900-12-12', 12, 'RUE DES EMMERDEURS', '59000', 'LILLE', 2012, NULL, 2, 0, 'motdepasse'),
('13333', 'BERNAERT', 'MARIN', '1993-01-01', 12, 'RUE DES OBSEDES', '59000', 'LILLE', 2012, NULL, 4, 0, 'motdepasse'),
('14444', 'PETIT', 'TOUT', '1992-12-12', 33, 'rue colbert', '59000', 'LILLE', 2013, NULL, 0, 0, 'motdepasse'),
('99999', 'SUPER', 'ADMIN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, 0, 'motdepasse');

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE IF NOT EXISTS `offre` (
  `cle_offre` int(11) NOT NULL AUTO_INCREMENT,
  `date_depot` date DEFAULT NULL,
  `date_miseenligne` date DEFAULT NULL,
  `date_tea` date DEFAULT NULL,
  `heure_debut` char(6) DEFAULT NULL,
  `heure_fin` char(6) DEFAULT NULL,
  `statut` int(11) NOT NULL DEFAULT '0',
  `offre_description` varchar(1000) DEFAULT NULL,
  `eleve_mail` varchar(200) DEFAULT NULL,
  `offre_titre` varchar(50) DEFAULT NULL,
  `offre_place` int(11) NOT NULL DEFAULT '1',
  `cle_structure` int(11) NOT NULL,
  PRIMARY KEY (`cle_offre`),
  KEY `FK_OFFRE_cle_structure` (`cle_structure`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `offre`
--

INSERT INTO `offre` (`cle_offre`, `date_depot`, `date_miseenligne`, `date_tea`, `heure_debut`, `heure_fin`, `statut`, `offre_description`, `eleve_mail`, `offre_titre`, `offre_place`, `cle_structure`) VALUES
(1, '2014-05-09', '2014-05-11', '2014-05-19', '12', '13', 1, 'DESCRIPTION CHIANTE', 'cc', 'TITRE INTERESSANT', 51, 2),
(2, '2014-05-08', '2014-05-11', '2014-05-28', '08', '09', 1, 'DESCPRZFSDKFGN?sfgnGJBNsfnSFVBsdjfbSDB', 'mm@hei.fr', 'zdfqdfqfsDSdfgSFQSDFVSDFS', 4, 2),
(3, '2014-05-10', '2014-05-11', '2014-05-11', '12', '14', 1, 'qdqsdqsdqsd', 'martin.deligny', 'ayaaaaa', 3, 1),
(4, '2014-05-10', '2014-05-11', '2014-05-12', '11', '13', 1, 'Le but est de faire un projet de validation des TEA pour le comité', 'celine.legry', 'projet', 2, 1),
(5, '2014-05-10', '2014-05-11', '2014-05-31', '13', '14', 1, 'Beaucoup trop stylé !', 'marin.bernaert', 'Projet dev web annuel', 1, 1),
(6, '2014-05-10', '2014-05-11', '2014-05-01', '1', '3', 1, 'qdqsdqdqsdqsd', 'coucou', 'heure de teal fanchini', 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `presider`
--

CREATE TABLE IF NOT EXISTS `presider` (
  `id_eleve` char(6) NOT NULL,
  `cle_structure` int(11) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  PRIMARY KEY (`id_eleve`,`cle_structure`),
  KEY `FK_PRESIDER_cle_structure` (`cle_structure`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `presider`
--

INSERT INTO `presider` (`id_eleve`, `cle_structure`, `date_debut`, `date_fin`) VALUES
('10153', 3, '2014-05-01', '2014-05-31'),
('12222', 2, '2014-05-01', '2015-06-15'),
('13333', 1, '2014-05-01', '2015-06-15');

-- --------------------------------------------------------

--
-- Structure de la table `structure`
--

CREATE TABLE IF NOT EXISTS `structure` (
  `cle_structure` int(11) NOT NULL AUTO_INCREMENT,
  `structure_nom` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`cle_structure`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `structure`
--

INSERT INTO `structure` (`cle_structure`, `structure_nom`) VALUES
(1, 'INTEGRALE-VP'),
(2, 'DECLIC-président'),
(3, 'Comite');

-- --------------------------------------------------------

--
-- Structure de la table `tea`
--

CREATE TABLE IF NOT EXISTS `tea` (
  `cle_tea` int(11) NOT NULL AUTO_INCREMENT,
  `date_tea_realisee` date DEFAULT NULL,
  `nbheure_realisee` int(11) NOT NULL DEFAULT '1',
  `statut_valide` int(11) DEFAULT NULL,
  `date_validation` date DEFAULT NULL,
  `cle_offre` int(11) NOT NULL,
  `id_eleve` char(6) NOT NULL,
  `nbheure_validee` int(11) NOT NULL,
  PRIMARY KEY (`cle_tea`),
  KEY `FK_TEA_cle_offre` (`cle_offre`),
  KEY `FK_TEA_id_eleve` (`id_eleve`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Contenu de la table `tea`
--

INSERT INTO `tea` (`cle_tea`, `date_tea_realisee`, `nbheure_realisee`, `statut_valide`, `date_validation`, `cle_offre`, `id_eleve`, `nbheure_validee`) VALUES
(20, '2014-05-31', 1, 0, NULL, 5, '10153', 0),
(21, '2014-05-19', 1, 0, NULL, 1, '10153', 0),
(22, '2014-05-12', 2, 0, NULL, 4, '10153', 0),
(23, '2014-05-01', 2, 2, '2014-05-11', 6, '10153', 0);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `appartenir`
--
ALTER TABLE `appartenir`
  ADD CONSTRAINT `FK_APPARTENIR_cle_classe` FOREIGN KEY (`cle_classe`) REFERENCES `classe` (`cle_classe`),
  ADD CONSTRAINT `FK_APPARTENIR_id_eleve` FOREIGN KEY (`id_eleve`) REFERENCES `eleve` (`id_eleve`);

--
-- Contraintes pour la table `ecrire`
--
ALTER TABLE `ecrire`
  ADD CONSTRAINT `FK_ECRIRE_cle_article` FOREIGN KEY (`cle_article`) REFERENCES `article` (`cle_article`),
  ADD CONSTRAINT `FK_ECRIRE_id_eleve` FOREIGN KEY (`id_eleve`) REFERENCES `eleve` (`id_eleve`);

--
-- Contraintes pour la table `offre`
--
ALTER TABLE `offre`
  ADD CONSTRAINT `FK_OFFRE_cle_structure` FOREIGN KEY (`cle_structure`) REFERENCES `structure` (`cle_structure`);

--
-- Contraintes pour la table `presider`
--
ALTER TABLE `presider`
  ADD CONSTRAINT `FK_PRESIDER_cle_structure` FOREIGN KEY (`cle_structure`) REFERENCES `structure` (`cle_structure`),
  ADD CONSTRAINT `FK_PRESIDER_id_eleve` FOREIGN KEY (`id_eleve`) REFERENCES `eleve` (`id_eleve`);

--
-- Contraintes pour la table `tea`
--
ALTER TABLE `tea`
  ADD CONSTRAINT `FK_TEA_cle_offre` FOREIGN KEY (`cle_offre`) REFERENCES `offre` (`cle_offre`),
  ADD CONSTRAINT `FK_TEA_id_eleve` FOREIGN KEY (`id_eleve`) REFERENCES `eleve` (`id_eleve`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
