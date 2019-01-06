-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 04, 2016 at 03:09 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gamestorem`
--

-- --------------------------------------------------------

--
-- Table structure for table `action`
--

CREATE TABLE `action` (
  `game` varchar(200) NOT NULL,
  `price` int(10) NOT NULL,
  `copy` int(10) NOT NULL,
  `mail` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `action`
--

INSERT INTO `action` (`game`, `price`, `copy`, `mail`) VALUES
('CALL OF DUTY 2', 40, 4, 'boss420@gmail.com'),
('CALL OF DUTY 3', 40, 3, 'boss420@gmail.com'),
('CALL OF DUTY 4', 120, 3, 'boss420@gmail.com'),
('LOL', 120, 12, 'boss420@gmail.com'),
('OREE', 120, 8, 'boss420@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `adventure`
--

CREATE TABLE `adventure` (
  `game` varchar(200) NOT NULL,
  `price` int(10) NOT NULL,
  `copy` int(10) NOT NULL,
  `mail` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `racing`
--

CREATE TABLE `racing` (
  `game` varchar(200) NOT NULL,
  `price` int(10) NOT NULL,
  `copy` int(10) NOT NULL,
  `mail` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `rev` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`rev`) VALUES
('This Application is Beautifull');

-- --------------------------------------------------------

--
-- Table structure for table `rpg`
--

CREATE TABLE `rpg` (
  `game` varchar(200) NOT NULL,
  `price` int(10) NOT NULL,
  `copy` int(10) NOT NULL,
  `mail` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `stealth`
--

CREATE TABLE `stealth` (
  `game` varchar(200) NOT NULL,
  `price` int(10) NOT NULL,
  `copy` int(10) NOT NULL,
  `mail` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `storem`
--

CREATE TABLE `storem` (
  `name` varchar(250) NOT NULL,
  `store` varchar(300) NOT NULL,
  `mail` varchar(120) NOT NULL,
  `pass` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `storem`
--

INSERT INTO `storem` (`name`, `store`, `mail`, `pass`) VALUES
('Boss', 'Boss Game Store', 'boss420@gmail.com', 'boss420');

-- --------------------------------------------------------

--
-- Table structure for table `strategy`
--

CREATE TABLE `strategy` (
  `game` varchar(200) NOT NULL,
  `price` int(10) NOT NULL,
  `copy` int(10) NOT NULL,
  `mail` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
