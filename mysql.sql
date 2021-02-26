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
  `orderDate` timestamp NOT NULL,
  `memberNumber` int(12) NOT NULL,
  PRIMARY KEY (`orderNumber`),
  KEY `memberNumber` (`memberNumber`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`memberNumber`) REFERENCES `members` (`memberNumber`)
) ENGINE=InnoDB;

insert  into `orders`(`orderNumber`,`orderDate`,`requiredDate`,`shippedDate`,`status`,`comments`,`memberNumber`) values 

(10100,'2003-01-06','2003-01-13','2003-01-10','Shipped',NULL,103),

(10101,'2003-01-09','2003-01-18','2003-01-11','Shipped','Check on availability.',112);

CREATE TABLE `orderdetails` (
  `orderNumber` int(12) NOT NULL,
  `detailNumber` int(12) NOT NULL,
  `product` varchar(50) NOT NULL,
  PRIMARY KEY (`orderNumber`, `orderNumber`),
  KEY `memberNumber` (`memberNumber`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`memberNumber`) REFERENCES `members` (`memberNumber`)
) ENGINE=InnoDB;