-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 29-Nov-2018 às 19:42
-- Versão do servidor: 5.7.21
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `upflow`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `campanha`
--

DROP TABLE IF EXISTS `campanha`;
CREATE TABLE IF NOT EXISTS `campanha` (
  `id_campanha` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `rede_social` varchar(50) DEFAULT NULL,
  `objetivo` varchar(50) DEFAULT NULL,
  `localizacao` varchar(200) DEFAULT NULL,
  `idade_min` int(11) DEFAULT NULL,
  `idade_max` int(11) DEFAULT NULL,
  `genero` varchar(20) DEFAULT NULL,
  `tipo_orcamento` varchar(50) DEFAULT NULL,
  `valor` float DEFAULT NULL,
  `url_patrocinada` varchar(150) DEFAULT NULL,
  `data_inicio` varchar(15) DEFAULT NULL,
  `data_termino` varchar(15) DEFAULT NULL,
  `descricao` tinytext,
  PRIMARY KEY (`id_campanha`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `empresa`
--

DROP TABLE IF EXISTS `empresa`;
CREATE TABLE IF NOT EXISTS `empresa` (
  `id_empresa` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `user_fb` varchar(45) DEFAULT NULL,
  `user_insta` varchar(45) DEFAULT NULL,
  `url_site` varchar(200) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `endereco` varchar(150) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `email_contato` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_empresa`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `empresa`
--

INSERT INTO `empresa` (`id_empresa`, `nome`, `user_fb`, `user_insta`, `url_site`, `telefone`, `endereco`, `cidade`, `email_contato`) VALUES
(1, 'Virtual Make', 'www.facebook.com/VirtualMakeOficial', 'www.instagram.com/virtual.make', 'www.virtualmake.net', '(17) 98828-3702', 'Av. Cap. Felicio Gomes', 'Barretos, SP', 'contato@virtualmake.net');

-- --------------------------------------------------------

--
-- Estrutura da tabela `lead`
--

DROP TABLE IF EXISTS `lead`;
CREATE TABLE IF NOT EXISTS `lead` (
  `id_lead` int(11) NOT NULL,
  `nome` varchar(150) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `user_fb` varchar(45) DEFAULT NULL,
  `user_insta` varchar(45) DEFAULT NULL,
  `code_empresa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_lead`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `lead`
--

INSERT INTO `lead` (`id_lead`, `nome`, `email`, `user_fb`, `user_insta`, `code_empresa`) VALUES
(1, 'Victor Hugo Malheiro', 'victorhugobarretos@gmail.com', 'www.facebook.com/vicmalheiro', 'www.instagram.com/vicmalheiro', 1),
(2, 'Bruno Antonio da Silva', 'brunoant.s@gmail.com', 'www.facebook.com/Bruno.as', 'www.instagram.com/brunoantonio', 1),
(3, 'Juliana Borges Barros', 'julianab.barros@gmail.com', 'www.facebook.com/juliana.b', 'www.instagram.com/juba.b', 2),
(4, 'Vinicius Eduardo Malheiro', 'viniciusedu007sk8@gmail.com', 'www.facebook.com/vinidudu', 'www.instagram.com/vini.eduardo', 1),
(5, 'Anthony George', 'anthony.g10@gmail.com', 'www.facebook.com/anthonygeo', NULL, 1),
(6, 'Filipe Camilo', 'filipecamilo@gmail.com', NULL, 'www.instagram.com/filipe_camilo', 1),
(7, 'Rafael Pacheco', 'rafa.pacheco@gmail.com', 'www.facebook.com/rafapacheco', NULL, 1),
(8, 'Karina Torres', 'k.mtorres@gmail.com', 'www.facebook.com/k.torres', NULL, 1),
(9, 'João da Silva', 'joaozinho@gmail.com', 'www.facebook.com/joaosilva10', NULL, 2),
(10, 'André Luiz dos Reis', 'andrebts@gmail.com', 'www.facebook.com/andluiz', NULL, 2),
(11, 'Ana Laura Tancredo', 'anatanc@gmail.com', 'www.facebook.com/anatanc1', NULL, 1),
(12, 'Robson Joares', 'robsonjo@gmail.com', 'www.facebook.com/joarob', NULL, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `senha` varchar(16) DEFAULT NULL,
  `code_empresa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `username`, `senha`, `code_empresa`) VALUES
(1, 'bina', 'bina123', NULL),
(2, 'victor', 'vic8828', NULL),
(3, 'bruno', 'bruno123', NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
