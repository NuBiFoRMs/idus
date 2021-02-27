CREATE DATABASE `idus` CHARACTER SET utf8;

USE `idus`;

DROP TABLE IF EXISTS `members`;

CREATE TABLE `members` (
  `memberId` varchar(50) NOT NULL,
  `memberName` varchar(50) NOT NULL,
  `nickName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `gender` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`memberId`)
) ENGINE=InnoDB;

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values

('nubiform','SangQ','NuBiFoRM','1234','1234','nubiform@abc.com');

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `orderNumber` int(12) NOT NULL,
  `paymentDate` timestamp NOT NULL,
  `product` varchar(50) NOT NULL,
  `memberId` varchar(50) NOT NULL,
  PRIMARY KEY (`orderNumber`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `orders_fk_1` FOREIGN KEY (`memberId`) REFERENCES `members` (`memberId`)
) ENGINE=InnoDB;

insert  into `orders`(`orderNumber`,`paymentDate`,`product`,`memberId`) values

(10100,'2003-01-13','Apple','nubiform');
