-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.2.14-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ticketmonster
CREATE DATABASE IF NOT EXISTS `ticketmonster` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `ticketmonster`;

-- Dumping structure for table ticketmonster.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `cartID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `eventID` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `final_price` int(11) NOT NULL,
  `checkout` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`cartID`),
  KEY `FK__user` (`userID`),
  KEY `FK__events` (`eventID`),
  CONSTRAINT `FK8j25r9k8i3ik8nbxf91x993ty` FOREIGN KEY (`eventID`) REFERENCES `events` (`eventID`),
  CONSTRAINT `FK__events` FOREIGN KEY (`eventID`) REFERENCES `events` (`eventID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__user` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKgyygokkildi375vll3b4bjxoh` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table ticketmonster.cart: ~0 rows (approximately)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`cartID`, `userID`, `eventID`, `quantity`, `final_price`, `checkout`) VALUES
	(1, 2, 1, 4, 240, b'1'),
	(2, 2, 2, 5, 400, b'0');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- Dumping structure for table ticketmonster.events
CREATE TABLE IF NOT EXISTS `events` (
  `eventID` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `date` datetime NOT NULL,
  `tickets_left` int(11) NOT NULL DEFAULT 0,
  `price` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`eventID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table ticketmonster.events: ~2 rows (approximately)
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` (`eventID`, `description`, `date`, `tickets_left`, `price`) VALUES
	(1, 'Jazz Night', '2018-09-21 10:00:00', 96, 60),
	(2, 'Rock Concert', '2018-10-09 21:00:00', 200, 80);
/*!40000 ALTER TABLE `events` ENABLE KEYS */;

-- Dumping structure for table ticketmonster.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `cartID` int(11) NOT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `creditcard` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `purchase_date` datetime NOT NULL,
  PRIMARY KEY (`orderID`),
  KEY `FK__order` (`cartID`),
  CONSTRAINT `FK__order` FOREIGN KEY (`cartID`) REFERENCES `cart` (`cartID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKabu746tb867em95slegirmqgo` FOREIGN KEY (`cartID`) REFERENCES `cart` (`cartID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table ticketmonster.orders: ~0 rows (approximately)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`orderID`, `cartID`, `phone`, `creditcard`, `purchase_date`) VALUES
	(1, 1, '2108815293', '4423543278920000', '2018-07-14 21:01:03');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dumping structure for table ticketmonster.user
CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_role` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table ticketmonster.user: ~2 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userID`, `name`, `surname`, `username`, `password`, `email`, `user_role`) VALUES
	(1, 'Αdmin', 'Admin', 'AdminAdmin', '827ccb0eea8a706c4c34a16891f84e7b', 'admin@Intraholics.com', b'1'),
	(2, 'User', 'User', 'UserUser', '827ccb0eea8a706c4c34a16891f84e7b', 'user@Intraholics.com', b'0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
