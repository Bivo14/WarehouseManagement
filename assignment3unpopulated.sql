-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Gazdă: 127.0.0.1
-- Timp de generare: apr. 14, 2020 la 12:53 PM
-- Versiune server: 10.4.11-MariaDB
-- Versiune PHP: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Bază de date: `assignment3`
--
CREATE DATABASE IF NOT EXISTS `assignment3` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `assignment3`;

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `client`
--

CREATE TABLE `client` (
  `idClient` int(11) NOT NULL,
  `nume` varchar(50) DEFAULT NULL,
  `adresa` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `comanda`
--

CREATE TABLE `comanda` (
  `idOrder` int(11) NOT NULL,
  `numeClient` varchar(50) DEFAULT NULL,
  `numeProdus` varchar(50) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `link`
--

CREATE TABLE `link` (
  `idComanda` int(11) NOT NULL,
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `link2`
--

CREATE TABLE `link2` (
  `idComanda` int(11) NOT NULL,
  `idProdus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `product`
--

CREATE TABLE `product` (
  `idProdus` int(11) NOT NULL,
  `nume` varchar(50) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `pret` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexuri pentru tabele eliminate
--

--
-- Indexuri pentru tabele `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`);

--
-- Indexuri pentru tabele `comanda`
--
ALTER TABLE `comanda`
  ADD PRIMARY KEY (`idOrder`);

--
-- Indexuri pentru tabele `link`
--
ALTER TABLE `link`
  ADD PRIMARY KEY (`idComanda`,`idClient`),
  ADD KEY `idClient` (`idClient`);

--
-- Indexuri pentru tabele `link2`
--
ALTER TABLE `link2`
  ADD PRIMARY KEY (`idComanda`,`idProdus`),
  ADD KEY `idProdus` (`idProdus`);

--
-- Indexuri pentru tabele `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`idProdus`);

--
-- Constrângeri pentru tabele eliminate
--

--
-- Constrângeri pentru tabele `link`
--
ALTER TABLE `link`
  ADD CONSTRAINT `link_ibfk_1` FOREIGN KEY (`idComanda`) REFERENCES `comanda` (`idOrder`),
  ADD CONSTRAINT `link_ibfk_2` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`);

--
-- Constrângeri pentru tabele `link2`
--
ALTER TABLE `link2`
  ADD CONSTRAINT `link2_ibfk_1` FOREIGN KEY (`idComanda`) REFERENCES `comanda` (`idOrder`),
  ADD CONSTRAINT `link2_ibfk_2` FOREIGN KEY (`idProdus`) REFERENCES `product` (`idProdus`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
