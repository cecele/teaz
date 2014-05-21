-- phpMyAdmin SQL Dump
-- version 3.5.8.1
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le: Mer 21 Mai 2014 à 12:03
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
('09999', 1),
('09999', 2),
('09999', 3),
('09999', 4),
('10000', 5),
('10000', 6),
('10000', 7),
('10000', 8),
('11111', 9),
('11111', 10),
('12222', 10),
('11111', 11),
('12222', 11),
('14444', 12),
('14444', 13),
('14444', 14),
('14444', 15);

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
  `annee` char(4) DEFAULT NULL,
  `nb_tea` int(11) DEFAULT NULL,
  PRIMARY KEY (`cle_classe`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Contenu de la table `classe`
--

INSERT INTO `classe` (`cle_classe`, `classe`, `annee`, `nb_tea`) VALUES
(1, 'H1E', '2009', 0),
(2, 'h2E', '2010', 3),
(3, 'h33', '2011', 6),
(4, 'h41', '2012', 6),
(5, 'H1A', '2010', 0),
(6, 'h2B', '2011', 3),
(7, 'h32', '2012', 6),
(8, 'h41', '2013', 6),
(9, 'h1C', '2011', 0),
(10, 'h2F', '2012', 3),
(11, 'h31', '2013', 6),
(12, 'h1G', '2010', 0),
(13, 'h2F', '2011', 3),
(14, 'h31', '2012', 6),
(15, 'h42', '2013', 6),
(16, 'h1A', '2013', 0);

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
  `eleve_profil` int(11) DEFAULT NULL,
  `diplome` int(11) DEFAULT NULL,
  `motdepasse` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_eleve`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `eleve`
--

INSERT INTO `eleve` (`id_eleve`, `eleve_nom`, `eleve_prenom`, `date_naissance`, `numrue`, `nomrue`, `codepostal`, `ville`, `date_entree`, `cotisant`, `eleve_profil`, `diplome`, `motdepasse`) VALUES
('09999', 'profilzerodiplome', 'prenom0diplome', '1989-09-09', NULL, NULL, NULL, NULL, 2009, 1, 0, 1, 'motdepasse'),
('10000', 'zero', 'prenomzero', '1990-10-10', NULL, NULL, NULL, NULL, 2010, 1, 0, 0, 'motdepasse'),
('11111', 'profil1', 'Céline', '1991-06-14', NULL, NULL, NULL, NULL, 2011, 1, 1, 0, 'motdepasse'),
('12222', 'profil2', 'martin', '1992-12-12', NULL, NULL, NULL, NULL, 2012, 1, 2, 0, 'motdepasse'),
('13333', 'profil3', 'louis', '1993-02-03', NULL, NULL, NULL, NULL, 2011, 1, 3, 0, 'motdepasse'),
('14444', 'profil4', 'marin', '1994-04-04', NULL, NULL, NULL, NULL, 2010, 0, 4, 0, 'motdepasse');

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE IF NOT EXISTS `offre` (
  `cle_offre` int(11) NOT NULL AUTO_INCREMENT,
  `date_depot` date DEFAULT NULL,
  `date_miseenligne` date DEFAULT NULL,
  `date_tea` date DEFAULT NULL,
  `heure_debut` char(2) DEFAULT NULL,
  `heure_fin` char(2) DEFAULT NULL,
  `statut` int(11) DEFAULT NULL,
  `offre_description` varchar(1000) DEFAULT NULL,
  `eleve_mail` varchar(200) DEFAULT NULL,
  `offre_titre` varchar(50) DEFAULT NULL,
  `offre_place` int(11) DEFAULT NULL,
  `cle_structure` int(11) NOT NULL,
  PRIMARY KEY (`cle_offre`),
  KEY `FK_OFFRE_cle_structure` (`cle_structure`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `offre`
--

INSERT INTO `offre` (`cle_offre`, `date_depot`, `date_miseenligne`, `date_tea`, `heure_debut`, `heure_fin`, `statut`, `offre_description`, `eleve_mail`, `offre_titre`, `offre_place`, `cle_structure`) VALUES
(1, '2014-05-04', NULL, '2014-05-22', '01', '02', 0, 'caissier', 'mail@hei.fr', 'rock hei', 3, 1),
(2, '2014-05-04', '2014-05-05', '2014-05-06', '02', '03', 1, 'tea passée', 'mailpasse@hei.fr', 'titre tea passée', 2, 1);

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
('12222', 1, '2014-05-01', '2014-05-28');

-- --------------------------------------------------------

--
-- Structure de la table `structure`
--

CREATE TABLE IF NOT EXISTS `structure` (
  `cle_structure` int(11) NOT NULL AUTO_INCREMENT,
  `structure_nom` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`cle_structure`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `structure`
--

INSERT INTO `structure` (`cle_structure`, `structure_nom`) VALUES
(1, 'Integrale-Président'),
(2, 'DECLIC-président');

-- --------------------------------------------------------

--
-- Structure de la table `tea`
--

CREATE TABLE IF NOT EXISTS `tea` (
  `cle_tea` int(11) NOT NULL AUTO_INCREMENT,
  `date_tea_realisee` date DEFAULT NULL,
  `nbheure_realisee` int(11) DEFAULT NULL,
  `statut_valide` int(11) DEFAULT NULL,
  `date_validation` date DEFAULT NULL,
  `nbheure_validee` int(11) DEFAULT NULL,
  `cle_offre` int(11) NOT NULL,
  `id_eleve` char(6) NOT NULL,
  PRIMARY KEY (`cle_tea`),
  KEY `FK_TEA_cle_offre` (`cle_offre`),
  KEY `FK_TEA_id_eleve` (`id_eleve`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `tea`
--

INSERT INTO `tea` (`cle_tea`, `date_tea_realisee`, `nbheure_realisee`, `statut_valide`, `date_validation`, `nbheure_validee`, `cle_offre`, `id_eleve`) VALUES
(1, '2014-05-06', 2, 0, NULL, 2, 1, '10000'),
(2, '2014-05-06', 2, 1, '2014-05-07', 2, 1, '12222');

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
  ADD CONSTRAINT `FK_TEA_id_eleve` FOREIGN KEY (`id_eleve`) REFERENCES `eleve` (`id_eleve`),
  ADD CONSTRAINT `FK_TEA_cle_offre` FOREIGN KEY (`cle_offre`) REFERENCES `offre` (`cle_offre`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
