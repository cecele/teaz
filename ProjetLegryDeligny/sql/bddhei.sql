-- phpMyAdmin SQL Dump
-- version 3.5.8.1
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le: Dim 11 Mai 2014 à 00:01
-- Version du serveur: 5.6.11-log
-- Version de PHP: 5.4.14

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `bddhei`
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
-

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `offre`
--


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
  PRIMARY KEY (`cle_tea`),
  KEY `FK_TEA_cle_offre` (`cle_offre`),
  KEY `FK_TEA_id_eleve` (`id_eleve`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `tea`
--


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

