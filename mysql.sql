set @@global.sql_mode = "";

CREATE DATABASE `idus` CHARACTER SET utf8;

USE `idus`;

SELECT @@global.sql_mode;

DROP TABLE IF EXISTS `members`;

CREATE TABLE `members` (
  `memberId` varchar(50) NOT NULL,
  `memberName` varchar(50) NOT NULL,
  `nickName` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `gender` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`memberId`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `orderNumber` int(12) NOT NULL,
  `productName` varchar(50) NOT NULL,
  `paymentDate` timestamp NOT NULL,
  `memberId` varchar(50) NOT NULL,
  PRIMARY KEY (`orderNumber`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `orders_fk_1` FOREIGN KEY (`memberId`) REFERENCES `members` (`memberId`)
) ENGINE=InnoDB;

CREATE USER `readonly`;
ALTER USER `readonly` IDENTIFIED WITH mysql_native_password BY 'readonly';
GRANT SELECT ON *.* TO `readonly`;

/*
insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values
('nubiform00','SangQChoi','NuBiFoRM','{SHA-256}f9fc2c5b17b3ae07f1837531db364c8134755dfdde3badc52d26d1142ffc03c2','12345678','nubiform@abc.com');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values
('nubiform01','SangQChoi','NuBiFoRM','{SHA-256}f9fc2c5b17b3ae07f1837531db364c8134755dfdde3badc52d26d1142ffc03c2','12345678','nubiform@abc.com');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values
('nubiform02','SangQChoi','NuBiFoRM','{SHA-256}f9fc2c5b17b3ae07f1837531db364c8134755dfdde3badc52d26d1142ffc03c2','12345678','nubiform@abc.com');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values
('nubiform03','SangQChoi','NuBiFoRM','{SHA-256}f9fc2c5b17b3ae07f1837531db364c8134755dfdde3badc52d26d1142ffc03c2','12345678','nubiform@abc.com');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values
('nubiform04','SangQChoi','NuBiFoRM','{SHA-256}f9fc2c5b17b3ae07f1837531db364c8134755dfdde3badc52d26d1142ffc03c2','12345678','nubiform@abc.com');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values
('nubiform05','SangQChoi','NuBiFoRM','{SHA-256}f9fc2c5b17b3ae07f1837531db364c8134755dfdde3badc52d26d1142ffc03c2','12345678','nubiform@abc.com');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values
('nubiform06','SangQChoi','NuBiFoRM','{SHA-256}f9fc2c5b17b3ae07f1837531db364c8134755dfdde3badc52d26d1142ffc03c2','12345678','nubiform@abc.com');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values
('nubiform07','SangQChoi','NuBiFoRM','{SHA-256}f9fc2c5b17b3ae07f1837531db364c8134755dfdde3badc52d26d1142ffc03c2','12345678','nubiform@abc.com');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values
('nubiform08','SangQChoi','NuBiFoRM','{SHA-256}f9fc2c5b17b3ae07f1837531db364c8134755dfdde3badc52d26d1142ffc03c2','12345678','nubiform@abc.com');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values
('nubiform09','SangQChoi','NuBiFoRM','{SHA-256}f9fc2c5b17b3ae07f1837531db364c8134755dfdde3badc52d26d1142ffc03c2','12345678','nubiform@abc.com');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values
('nubiform10','SangQChoi','NuBiFoRM','{SHA-256}f9fc2c5b17b3ae07f1837531db364c8134755dfdde3badc52d26d1142ffc03c2','12345678','nubiform@abc.com');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`) values
('nubiform11','SangQChoi','NuBiFoRM','{SHA-256}f9fc2c5b17b3ae07f1837531db364c8134755dfdde3badc52d26d1142ffc03c2','12345678','nubiform@abc.com');
*/

/*
insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10100,'Apple','2003-01-13','nubiform00');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10101,'Radio','2003-01-12','nubiform01');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10102,'Orange','2003-01-13','nubiform02');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10103,'Orange','2003-01-13','nubiform03');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10104,'Orange','2003-01-13','nubiform04');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10105,'Orange','2003-01-13','nubiform05');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10106,'Orange','2003-01-13','nubiform06');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10107,'Orange','2003-01-13','nubiform07');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10108,'Orange','2003-01-13','nubiform08');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10109,'Orange','2003-01-13','nubiform09');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10110,'Orange','2003-01-13','nubiform10');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10111,'Orange','2003-01-13','nubiform11');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10112,'Orange','2003-01-13','nubiform11');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10113,'Orange','2003-01-13','nubiform11');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10114,'Orange','2003-01-13','nubiform11');
*/
