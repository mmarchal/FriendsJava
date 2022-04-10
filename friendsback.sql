-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 25 mars 2022 à 20:37
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `friendsback`
--

-- --------------------------------------------------------

--
-- Structure de la table `channel`
--

DROP TABLE IF EXISTS `channel`;
CREATE TABLE IF NOT EXISTS `channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `channel_friend_table`
--

DROP TABLE IF EXISTS `channel_friend_table`;
CREATE TABLE IF NOT EXISTS `channel_friend_table` (
  `friend_id` bigint(20) NOT NULL,
  `channel_id` bigint(20) NOT NULL,
  KEY `FKq1eq96fppbhey7dtc8djnkiqy` (`channel_id`),
  KEY `FK_channel_friend` (`friend_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `friend`
--

DROP TABLE IF EXISTS `friend`;
CREATE TABLE IF NOT EXISTS `friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_mdp` varchar(255) DEFAULT NULL,
  `date_expiration` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `mdp_provisoire` tinyint(1) DEFAULT '0',
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `profile_image` longblob,
  `uid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `friend`
--

INSERT INTO `friend` (`id`, `code_mdp`, `date_expiration`, `email`, `login`, `mdp_provisoire`, `password`, `prenom`, `profile_image`, `uid`) VALUES
(1, NULL, NULL, 'max@gmail.com', NULL, 0, '$2a$10$h9HEn82/OSoMfkgm.8iMy.wQiGBwqNoHsohNzUqgQL5WflE6/MKB6', 'max', NULL, 'h09by5e3b6dSuv4lzItQQPRTLP43'),
(2, NULL, NULL, 'emulateur@gmail.com', NULL, 0, '$2a$10$uz14TTbJTRMQsqTbsu9PHuJCWJPRsdNDTYQS6bxKIFOk4Q32LfEzu', 'emulateur', NULL, 'K3FYx4pD7mfvPsuANuthxD4bAQr1');

-- --------------------------------------------------------

--
-- Structure de la table `last_message`
--

DROP TABLE IF EXISTS `last_message`;
CREATE TABLE IF NOT EXISTS `last_message` (
  `channel_id` bigint(20) NOT NULL,
  `friend_id` bigint(20) NOT NULL,
  `date_last_message_read` datetime DEFAULT NULL,
  `date_last_message_sent` datetime DEFAULT NULL,
  `last_message_id_read` bigint(20) DEFAULT NULL,
  `last_message_id_sent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`channel_id`,`friend_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `delivered_at` datetime DEFAULT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `friend_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiimr93ytmcuira5le0sldvvma` (`channel_id`),
  KEY `FKhf5bj8qr11u9j71my2kuhqimb` (`friend_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `participant`
--

DROP TABLE IF EXISTS `participant`;
CREATE TABLE IF NOT EXISTS `participant` (
  `sortie_id` bigint(20) NOT NULL,
  `friend_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sortie_id`,`friend_id`),
  KEY `FKk4pyrjts4g6hnx7hfgho1e0nq` (`friend_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `participant`
--

INSERT INTO `participant` (`sortie_id`, `friend_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `proposition_evaluation`
--

DROP TABLE IF EXISTS `proposition_evaluation`;
CREATE TABLE IF NOT EXISTS `proposition_evaluation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_proposition` datetime DEFAULT NULL,
  `demande` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `type_proposition_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKstu9qt8ggtx5ad3c7g51pxh0` (`type_proposition_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `proposition_evaluation`
--

INSERT INTO `proposition_evaluation` (`id`, `date_proposition`, `demande`, `nom`, `type_proposition_id`) VALUES
(1, '2022-03-25 09:44:19', 'attention lors du redemarrage Java, les types de sorties sautent.', 'max', 2);

-- --------------------------------------------------------

--
-- Structure de la table `sortie`
--

DROP TABLE IF EXISTS `sortie`;
CREATE TABLE IF NOT EXISTS `sortie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_propose` datetime DEFAULT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `propose_par` varchar(255) DEFAULT NULL,
  `typesortie_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd6l6i6slustq6c02aot2x2wd2` (`typesortie_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `sortie`
--

INSERT INTO `sortie` (`id`, `date_propose`, `intitule`, `lieu`, `propose_par`, `typesortie_id`) VALUES
(1, '2022-03-28 00:00:00', 'test max firebase', 'appart', NULL, 3);

-- --------------------------------------------------------

--
-- Structure de la table `type_proposition`
--

DROP TABLE IF EXISTS `type_proposition`;
CREATE TABLE IF NOT EXISTS `type_proposition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `type_proposition_propositions`
--

DROP TABLE IF EXISTS `type_proposition_propositions`;
CREATE TABLE IF NOT EXISTS `type_proposition_propositions` (
  `type_proposition_id` bigint(20) NOT NULL,
  `propositions_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_iu1wc0b1pdbochpxea25qdpd0` (`propositions_id`),
  KEY `FK66m2ad2acs8gyhdtoud8s2vap` (`type_proposition_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `type_sortie`
--

DROP TABLE IF EXISTS `type_sortie`;
CREATE TABLE IF NOT EXISTS `type_sortie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `type_sortie`
--

INSERT INTO `type_sortie` (`id`, `type`) VALUES
(1, 'Cinéma'),
(2, 'Sport'),
(3, 'Activité');

-- --------------------------------------------------------

--
-- Structure de la table `type_sortie_sorties`
--

DROP TABLE IF EXISTS `type_sortie_sorties`;
CREATE TABLE IF NOT EXISTS `type_sortie_sorties` (
  `type_sortie_id` bigint(20) NOT NULL,
  `sorties_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_4woiaypcsl4vse2mopske1ihy` (`sorties_id`),
  KEY `FKsgs36cqefu2r5i4b49h1isb77` (`type_sortie_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
