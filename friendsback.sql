-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : ven. 22 avr. 2022 à 15:57
-- Version du serveur :  5.7.32
-- Version de PHP : 7.4.16

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

CREATE TABLE `channel` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `channel_friend_table`
--

CREATE TABLE `channel_friend_table` (
  `friend_id` bigint(20) NOT NULL,
  `channel_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `etat_proposition`
--

CREATE TABLE `etat_proposition` (
  `id` bigint(20) NOT NULL,
  `etat` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etat_proposition`
--

INSERT INTO `etat_proposition` (`id`, `etat`) VALUES
(1, 'Créé'),
(2, 'En cours de résolution/développement'),
(3, 'Non traité'),
(4, 'Résolu');

-- --------------------------------------------------------

--
-- Structure de la table `etat_proposition_propositions`
--

CREATE TABLE `etat_proposition_propositions` (
  `etat_proposition_id` bigint(20) NOT NULL,
  `propositions_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `friend`
--

CREATE TABLE `friend` (
  `id` bigint(20) NOT NULL,
  `code_mdp` varchar(255) DEFAULT NULL,
  `date_expiration` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `mdp_provisoire` tinyint(1) DEFAULT '0',
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `profile_image` longblob,
  `uid` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `friend`
--

INSERT INTO `friend` (`id`, `code_mdp`, `date_expiration`, `email`, `login`, `mdp_provisoire`, `password`, `prenom`, `profile_image`, `uid`) VALUES
(1, NULL, NULL, 'max@gmail.com', 'max@gmail.com', 0, '$2a$10$h9HEn82/OSoMfkgm.8iMy.wQiGBwqNoHsohNzUqgQL5WflE6/MKB6', 'max', NULL, 'h09by5e3b6dSuv4lzItQQPRTLP43'),
(2, NULL, NULL, 'emulateur@gmail.com', 'emulateur@gmail.com', 0, '$2a$10$uz14TTbJTRMQsqTbsu9PHuJCWJPRsdNDTYQS6bxKIFOk4Q32LfEzu', 'emulateur', NULL, 'K3FYx4pD7mfvPsuANuthxD4bAQr1'),
(4, NULL, NULL, 'mmdeveloper59@gmail.com', 'mmdeveloper59@gmail.com', 0, '$2a$10$9fc3a0aoMb5zAZs/ghPoFOGkWrVr6VpHPkOkCcBFxLWnn3x7Xoxle', 'admin', NULL, 'N21xgaWYUEOkSwOSioYELugMcdn1');

-- --------------------------------------------------------

--
-- Structure de la table `last_message`
--

CREATE TABLE `last_message` (
  `channel_id` bigint(20) NOT NULL,
  `friend_id` bigint(20) NOT NULL,
  `date_last_message_read` datetime DEFAULT NULL,
  `date_last_message_sent` datetime DEFAULT NULL,
  `last_message_id_read` bigint(20) DEFAULT NULL,
  `last_message_id_sent` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `delivered_at` datetime DEFAULT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `friend_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `participant`
--

CREATE TABLE `participant` (
  `sortie_id` bigint(20) NOT NULL,
  `friend_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `participant`
--

INSERT INTO `participant` (`sortie_id`, `friend_id`) VALUES
(1, 1),
(1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `proposition_evaluation`
--

CREATE TABLE `proposition_evaluation` (
  `id` bigint(20) NOT NULL,
  `date_proposition` datetime DEFAULT NULL,
  `demande` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `type_proposition_id` bigint(20) DEFAULT NULL,
  `titre_demande` varchar(255) DEFAULT NULL,
  `etat_proposition_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `proposition_evaluation`
--

INSERT INTO `proposition_evaluation` (`id`, `date_proposition`, `demande`, `nom`, `type_proposition_id`, `titre_demande`, `etat_proposition_id`) VALUES
(1, '2022-03-25 09:44:19', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam.', 'max', 2, 'Attention lors du redemarrage Java', 1),
(2, '2022-04-17 08:51:36', 'Test fonctionnement envoie', 'max', 1, 'TEST', 1),
(3, '2022-04-22 12:45:19', 'Et bah pourquoi pas ?? :p ', 'Administrateur', 3, 'Ajout d\'un jeu', 1);

-- --------------------------------------------------------

--
-- Structure de la table `sortie`
--

CREATE TABLE `sortie` (
  `id` bigint(20) NOT NULL,
  `date_propose` datetime DEFAULT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `propose_par` varchar(255) DEFAULT NULL,
  `typesortie_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `sortie`
--

INSERT INTO `sortie` (`id`, `date_propose`, `intitule`, `lieu`, `propose_par`, `typesortie_id`) VALUES
(1, '2022-03-28 00:00:00', 'test max firebase', 'appart', NULL, 3);

-- --------------------------------------------------------

--
-- Structure de la table `type_proposition`
--

CREATE TABLE `type_proposition` (
  `id` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `type_proposition`
--

INSERT INTO `type_proposition` (`id`, `type`) VALUES
(1, 'Amélioration de l\'app'),
(2, 'Bug trouvé'),
(3, 'Nouvelle fonctionnalité');

-- --------------------------------------------------------

--
-- Structure de la table `type_proposition_propositions`
--

CREATE TABLE `type_proposition_propositions` (
  `type_proposition_id` bigint(20) NOT NULL,
  `propositions_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `type_sortie`
--

CREATE TABLE `type_sortie` (
  `id` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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

CREATE TABLE `type_sortie_sorties` (
  `type_sortie_id` bigint(20) NOT NULL,
  `sorties_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `channel`
--
ALTER TABLE `channel`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `channel_friend_table`
--
ALTER TABLE `channel_friend_table`
  ADD KEY `FKq1eq96fppbhey7dtc8djnkiqy` (`channel_id`),
  ADD KEY `FK_channel_friend` (`friend_id`);

--
-- Index pour la table `etat_proposition`
--
ALTER TABLE `etat_proposition`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etat_proposition_propositions`
--
ALTER TABLE `etat_proposition_propositions`
  ADD UNIQUE KEY `UK_6yfpgdpetjnhh7cr293qj9385` (`propositions_id`),
  ADD KEY `FKgfkdi1m36v8xxww9f5khem70j` (`etat_proposition_id`);

--
-- Index pour la table `friend`
--
ALTER TABLE `friend`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `last_message`
--
ALTER TABLE `last_message`
  ADD PRIMARY KEY (`channel_id`,`friend_id`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKiimr93ytmcuira5le0sldvvma` (`channel_id`),
  ADD KEY `FKhf5bj8qr11u9j71my2kuhqimb` (`friend_id`);

--
-- Index pour la table `participant`
--
ALTER TABLE `participant`
  ADD PRIMARY KEY (`sortie_id`,`friend_id`),
  ADD KEY `FKk4pyrjts4g6hnx7hfgho1e0nq` (`friend_id`);

--
-- Index pour la table `proposition_evaluation`
--
ALTER TABLE `proposition_evaluation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKstu9qt8ggtx5ad3c7g51pxh0` (`type_proposition_id`),
  ADD KEY `FKrx8hhh6kv75s27rqbb25dpk3f` (`etat_proposition_id`);

--
-- Index pour la table `sortie`
--
ALTER TABLE `sortie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd6l6i6slustq6c02aot2x2wd2` (`typesortie_id`);

--
-- Index pour la table `type_proposition`
--
ALTER TABLE `type_proposition`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `type_proposition_propositions`
--
ALTER TABLE `type_proposition_propositions`
  ADD UNIQUE KEY `UK_iu1wc0b1pdbochpxea25qdpd0` (`propositions_id`),
  ADD KEY `FK66m2ad2acs8gyhdtoud8s2vap` (`type_proposition_id`);

--
-- Index pour la table `type_sortie`
--
ALTER TABLE `type_sortie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `type_sortie_sorties`
--
ALTER TABLE `type_sortie_sorties`
  ADD UNIQUE KEY `UK_4woiaypcsl4vse2mopske1ihy` (`sorties_id`),
  ADD KEY `FKsgs36cqefu2r5i4b49h1isb77` (`type_sortie_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `channel`
--
ALTER TABLE `channel`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `etat_proposition`
--
ALTER TABLE `etat_proposition`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `friend`
--
ALTER TABLE `friend`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `proposition_evaluation`
--
ALTER TABLE `proposition_evaluation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `sortie`
--
ALTER TABLE `sortie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `type_proposition`
--
ALTER TABLE `type_proposition`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `type_sortie`
--
ALTER TABLE `type_sortie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
