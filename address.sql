-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 26/04/2024 às 05:41
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `dbneurotech`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `state` varchar(2) NOT NULL,
  `city` varchar(50) NOT NULL,
  `neighborhood` varchar(100) NOT NULL,
  `street` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `address`
--

INSERT INTO `address` (`id`, `cep`, `state`, `city`, `neighborhood`, `street`) VALUES
(51, '53620714', 'PE', 'Igarassu', 'Posto de Monta', 'Rua Canadá (Lot. Posto de Monta)'),
(52, '53050260', 'PE', 'Olinda', 'Jardim Atlântico', 'Avenida São João Batista'),
(53, '53620715', 'PE', 'Igarassu', 'Posto de Monta', 'Rua California'),
(54, '76900081', 'RO', 'Ji-Paraná', 'Centro', 'Avenida Marechal Rondon - de 869 a 1157 - lado ímpar'),
(55, '77828542', 'TO', 'Araguaína', 'Loteamento Jardins Siena', 'Alameda das Hortências'),
(56, '49048070', 'SE', 'Aracaju', 'Luzia', 'Rua Deputado Matos Teles'),
(57, '50960220', 'PE', 'Recife', 'Várzea', 'Rua Caburaí'),
(58, '50960220', 'PE', 'Recife', 'Várzea', 'Rua Caburaí'),
(59, '79610110', 'MS', 'Três Lagoas', 'Jardim Alvorada', 'Rua Coronel Josino da Cunha Viana - de 2201/2202 a 3099/3100'),
(60, '86035775', 'PR', 'Londrina', 'Jardim Santa Fé', 'Rua José Roberto Barion');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
