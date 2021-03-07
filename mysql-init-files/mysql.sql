set @@global.sql_mode = "";

CREATE DATABASE `idus` CHARACTER SET utf8;

USE `idus`;

set global time_zone = 'Asia/Seoul';
set time_zone = 'Asia/Seoul';

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
  `roles` varchar(50) NOT NULL DEFAULT 'ROLE_USER',
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

CREATE USER `write`;
ALTER USER `write` IDENTIFIED WITH mysql_native_password BY 'write';
GRANT SELECT, INSERT, UPDATE, DELETE ON *.* TO `write`;

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`,`roles`) values
('admin','admin','admin','{bcrypt}$2a$10$w071.YfVeUe7pantEN95iOuuFRjpTrB0WrD1flxZIaesXUQKed54G','0000','admin@idus.com', 'ROLE_ADMIN');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`,`roles`) values
('user','user','user','{bcrypt}$2a$10$e8KKOD7572GQvZFE15sTpeBJN6d6jZEcxhZs9YJbnG/3iFjY3.voe','0000','user@idus.com', 'ROLE_USER');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`,`gender`,`roles`) values
('testusera','testusera','testusera','{bcrypt}$2a$10$8f.bxwdk1ysDnkqnp6HKCO7MaOPVoSPYsubwb5JNob/IwNJ7UMgIe','0000','user@idus.com', 'male', 'ROLE_USER');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`,`gender`,`roles`) values
('testuserb','testuserb','testuserb','{bcrypt}$2a$10$8f.bxwdk1ysDnkqnp6HKCO7MaOPVoSPYsubwb5JNob/IwNJ7UMgIe','0000','user@idus.com', 'male', 'ROLE_USER');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`,`gender`,`roles`) values
('testuserc','testuserc','testuserc','{bcrypt}$2a$10$8f.bxwdk1ysDnkqnp6HKCO7MaOPVoSPYsubwb5JNob/IwNJ7UMgIe','0000','user@idus.com', 'male', 'ROLE_USER');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`,`gender`,`roles`) values
('testuserd','testuserd','testuserd','{bcrypt}$2a$10$8f.bxwdk1ysDnkqnp6HKCO7MaOPVoSPYsubwb5JNob/IwNJ7UMgIe','0000','user@idus.com', 'male', 'ROLE_USER');

insert  into `members`(`memberId`,`memberName`,`nickName`,`password`,`phone`,`email`,`gender`,`roles`) values
('testusere','testusere','testusere','{bcrypt}$2a$10$8f.bxwdk1ysDnkqnp6HKCO7MaOPVoSPYsubwb5JNob/IwNJ7UMgIe','0000','user@idus.com', 'male', 'ROLE_USER');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10100,'Apple','2003-01-13','testusera');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10101,'Radio','2003-01-12','testusera');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10102,'Orange','2003-01-13','testusera');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10103,'Orange','2003-01-13','testuserb');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10104,'Orange','2003-01-13','testuserb');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10105,'Orange','2003-01-13','testuserb');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10106,'Orange','2003-01-13','testuserc');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10107,'Orange','2003-01-13','testuserc');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10108,'Orange','2003-01-13','testuserc');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10109,'Orange','2003-01-13','testuserd');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10110,'Orange','2003-01-13','testuserd');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10111,'Orange','2003-01-13','testuserd');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10112,'Orange','2003-01-13','testusere');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10113,'Orange','2003-01-13','testusere');

insert  into `orders`(`orderNumber`,`productName`,`paymentDate`,`memberId`) values
(10114,'Orange','2003-01-13','testusere');