/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ugostiteljstvo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `ugostiteljstvo`;



DROP TABLE IF EXISTS `Radnik`;

CREATE TABLE `Radnik` (
  `RadnikID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  `Glavni` TINYINT(1) NOT NULL,
  PRIMARY KEY (`RadnikID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Radnik` VALUES 
(1,'Nikola','Rusimovic','nikola','nikola123', 1),
(2,'Milos','Kuzmanovic','milos','milos123', 0);



DROP TABLE IF EXISTS `Sto`;

CREATE TABLE `Sto` (
  `StoID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `BrojStola` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`StoID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Sto` VALUES 
(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6),
(7,7);


DROP TABLE IF EXISTS `Dobavljac`;

CREATE TABLE `Dobavljac` (
  `DobavljacID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `PIB` VARCHAR(20) NOT NULL,
  `NazivDobavljaca` VARCHAR(50) NOT NULL,
  `Adresa` VARCHAR(50) NOT NULL,
  `BrojTelefona` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`DobavljacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Dobavljac` VALUES 
(1,'27382718', 'Dobavljac Trade', 'Svetozara Markovica 18', '0113723829'),
(2,'72861829', 'Dobavljac Expert', 'Bulevar Kralja Aleksandra 22', '0119283728');


DROP TABLE IF EXISTS `Proizvod`;

CREATE TABLE `Proizvod` (
  `ProizvodID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivProizvoda` VARCHAR(50) NOT NULL,
  `Opis` VARCHAR(200) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `DobavljacID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ProizvodID`),
  CONSTRAINT `fk_dobavljac_id` FOREIGN KEY (`DobavljacID`) REFERENCES `Dobavljac` (`DobavljacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Proizvod` VALUES 
(1,'Voda', 'Voda za pijenje.', 190, 1),
(2,'Kisela voda', 'Kisela voda za pijenje.', 190, 1),
(3,'Coca-Cola', 'Smrt za zdravlje.', 240, 2),
(4,'Fanta', 'Smrt za zdravlje, na malo druaciji nacin.', 240, 2),
(5,'Sveze cedjena pomorandza', 'Organska pomorandza cedjena na najfiniji nacin.', 300, 1),
(6,'Sveze cedjeni nar', 'Organski nar cedjen na najfiniji nacin.', 320, 1);




DROP TABLE IF EXISTS `Racun`;

CREATE TABLE `Racun` (
  `RacunID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVreme` DATETIME NOT NULL,
  `UkupanIznos` DECIMAL(10,2) NOT NULL,
  `Obradjen` TINYINT(1) NOT NULL,
  `StoID` BIGINT(10) UNSIGNED NOT NULL,
  `RadnikID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`RacunID`),
  CONSTRAINT `fk_sto_id` FOREIGN KEY (`StoID`) REFERENCES `Sto` (`StoID`),
  CONSTRAINT `fk_radnik_id` FOREIGN KEY (`RadnikID`) REFERENCES `Radnik` (`RadnikID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT  INTO `Racun` VALUES 
(1,'2022-08-14 09:02:33',620,1,1,1);


DROP TABLE IF EXISTS `StavkaRacuna`;

CREATE TABLE `StavkaRacuna` (
  `RacunID` BIGINT(10) UNSIGNED NOT NULL,
  `RbStavke` INT(7) NOT NULL,
  `KolicinaStavke` VARCHAR(30) NOT NULL,
  `CenaStavke` VARCHAR(200) NOT NULL,
  `ProizvodID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`RacunID`,`RbStavke`),
  CONSTRAINT `fk_racun_id` FOREIGN KEY (`RacunID`) REFERENCES `Racun` (`RacunID`),
  CONSTRAINT `fk_proizvod_id` FOREIGN KEY (`ProizvodID`) REFERENCES `Proizvod` (`ProizvodID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `StavkaRacuna` VALUES 
(1,1,1,300,5),
(1,2,1,320,6);




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
