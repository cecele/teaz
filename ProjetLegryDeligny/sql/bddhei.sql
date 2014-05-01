-- phpMyAdmin SQL Dump
-- version 3.5.8.1
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le: Mer 30 Avril 2014 à 17:03
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
--

INSERT INTO `appartenir` (`id_eleve`, `cle_classe`) VALUES
('10153', 1);

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE IF NOT EXISTS `classe` (
  `cle_classe` int(11) NOT NULL AUTO_INCREMENT,
  `classe` char(3) DEFAULT NULL,
  `annee` char(4) DEFAULT NULL,
  PRIMARY KEY (`cle_classe`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `classe`
--

INSERT INTO `classe` (`cle_classe`, `classe`, `annee`) VALUES
(1, 'H43', '2014');

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
  `eleve_profil` int(11) DEFAULT NULL,
  `diplome` int(11) DEFAULT NULL,
  `motdepasse` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_eleve`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `eleve`
--

INSERT INTO `eleve` (`id_eleve`, `eleve_nom`, `eleve_prenom`, `date_naissance`, `numrue`, `nomrue`, `codepostal`, `ville`, `date_entree`, `cotisant`, `eleve_profil`, `diplome`, `motdepasse`) VALUES
('10153', 'LEGRY', 'Céline', '1991-06-14', 59, 'rue des stations', '59000', 'Lille', 2009, 1, 1, 0, 'motdepasse');

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
  `statut` int(11) DEFAULT NULL,
  `offre_description` varchar(1000) DEFAULT NULL,
  `eleve_mail` varchar(200) DEFAULT NULL,
  `offre_titre` varchar(50) DEFAULT NULL,
  `offre_place` int(11) DEFAULT NULL,
  `cle_structure` int(11) NOT NULL,
  PRIMARY KEY (`cle_offre`),
  KEY `FK_OFFRE_cle_structure` (`cle_structure`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `offre`
--

INSERT INTO `offre` (`cle_offre`, `date_depot`, `date_miseenligne`, `date_tea`, `heure_debut`, `heure_fin`, `statut`, `offre_description`, `eleve_mail`, `offre_titre`, `offre_place`, `cle_structure`) VALUES
(1, '2014-04-29', '2014-04-30', '2014-04-30', '00H00', '01H00', 1, 'DESCRIPTION', 'cc@hei.fr', 'TITRE', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `presider`
--

CREATE TABLE IF NOT EXISTS `presider` (
  `id_eleve` char(6) NOT NULL,
  `cle_structure` int(11) NOT NULL,
  `annee_appartenance` char(4) DEFAULT NULL,
  PRIMARY KEY (`id_eleve`,`cle_structure`),
  KEY `FK_PRESIDER_cle_structure` (`cle_structure`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `presider`
--

INSERT INTO `presider` (`id_eleve`, `cle_structure`, `annee_appartenance`) VALUES
('10153', 1, '2014');

-- --------------------------------------------------------

--
-- Structure de la table `structure`
--

CREATE TABLE IF NOT EXISTS `structure` (
  `cle_structure` int(11) NOT NULL AUTO_INCREMENT,
  `structure_nom` varchar(500) DEFAULT NULL,
  `structure_president` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`cle_structure`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `structure`
--

INSERT INTO `structure` (`cle_structure`, `structure_nom`, `structure_president`) VALUES
(1, 'Comité-Logistique', 'Céline Legry');

-- --------------------------------------------------------

--
-- Structure de la table `tea`
--

CREATE TABLE IF NOT EXISTS `tea` (
  `cle_tea` int(11) NOT NULL AUTO_INCREMENT,
  `date_tea_realisee` date DEFAULT NULL,
  `nbheure_realisee` int(11) DEFAULT NULL,
  `staut_valide` int(11) DEFAULT NULL,
  `date_validation` date DEFAULT NULL,
  `cle_offre` int(11) NOT NULL,
  `id_eleve` char(6) NOT NULL,
  PRIMARY KEY (`cle_tea`),
  KEY `FK_TEA_cle_offre` (`cle_offre`),
  KEY `FK_TEA_id_eleve` (`id_eleve`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `tea`
--

INSERT INTO `tea` (`cle_tea`, `date_tea_realisee`, `nbheure_realisee`, `staut_valide`, `date_validation`, `cle_offre`, `id_eleve`) VALUES
(1, '2014-04-30', 2, 0, NULL, 1, '10153');

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
  ADD CONSTRAINT `FK_TEA_id_eleve` FOREIGN KEY (`id_eleve`) REFERENCES `eleve` (`id_eleve`),
  ADD CONSTRAINT `FK_TEA_cle_offre` FOREIGN KEY (`cle_offre`) REFERENCES `offre` (`cle_offre`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
