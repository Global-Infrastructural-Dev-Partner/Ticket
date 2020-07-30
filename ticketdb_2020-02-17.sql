# ************************************************************
# Sequel Pro SQL dump
# Version 481
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.26)
# Database: ticketdb
# Generation Time: 2020-02-17 08:45:08 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table notifications
# ------------------------------------------------------------

CREATE TABLE `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `subject` varchar(255) NOT NULL,
  `from_user_id` int(11) DEFAULT NULL,
  `to_user_id` int(11) DEFAULT NULL,
  `body` longtext,
  `status` varchar(20) DEFAULT 'Pending',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;

INSERT INTO `notifications` (`id`, `date`, `time`, `subject`, `from_user_id`, `to_user_id`, `body`, `status`)
VALUES
	(1,'2020-02-17','09:33:28','PeinMoney Subscriber Account Created',1,1,'Congratulations!!! Your account has been created successfully for the PeinMoney Event.','Pending'),
	(2,'2020-02-17','09:44:24','PeinMoney Subscriber Account Created',1,2,'Congratulations!!! Your account has been created successfully for the PeinMoney Event.','Pending');

/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table parameters
# ------------------------------------------------------------

CREATE TABLE `parameters` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `secretkey` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `parameters` WRITE;
/*!40000 ALTER TABLE `parameters` DISABLE KEYS */;

INSERT INTO `parameters` (`id`, `secretkey`)
VALUES
	(1,'sk_test_f993802764425512854b7b26f7b1ec558331e4bc');

/*!40000 ALTER TABLE `parameters` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table payments
# ------------------------------------------------------------

CREATE TABLE `payments` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `amount_paid` bigint(30) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `ticket_id` int(11) DEFAULT NULL,
  `reference_code` varchar(50) DEFAULT NULL,
  `transaction_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table session
# ------------------------------------------------------------

CREATE TABLE `session` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sessionid` text,
  `userid` int(11) DEFAULT NULL,
  `time` time DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;

INSERT INTO `session` (`id`, `sessionid`, `userid`, `time`, `date`)
VALUES
	(1,'A485DE9B9003BE0570E17D1460D4FAE8',2,'09:44:30','2020-02-17');

/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ticket_history
# ------------------------------------------------------------

CREATE TABLE `ticket_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ticket_id` int(11) DEFAULT NULL,
  `ticket_number` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table ticket_type
# ------------------------------------------------------------

CREATE TABLE `ticket_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ticket_type` WRITE;
/*!40000 ALTER TABLE `ticket_type` DISABLE KEYS */;

INSERT INTO `ticket_type` (`id`, `name`)
VALUES
	(1,'Single'),
	(2,'Five-In-One'),
	(3,'Ten-In-One'),
	(4,'Free');

/*!40000 ALTER TABLE `ticket_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tickets
# ------------------------------------------------------------

CREATE TABLE `tickets` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `ticket_type_id` int(11) DEFAULT NULL,
  `number_of_ticket_bought` int(11) DEFAULT NULL,
  `amount_paid` bigint(30) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `ticket_paid_for` int(11) DEFAULT NULL,
  `free_ticket` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table users
# ------------------------------------------------------------

CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `usertype` varchar(20) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `referral_code` varchar(20) DEFAULT NULL,
  `referral_count` int(11) DEFAULT '0',
  `referral_userid` int(11) DEFAULT NULL,
  `date_registered` date DEFAULT NULL,
  `time_registered` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UConstraint` (`referral_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id`, `usertype`, `firstname`, `lastname`, `email`, `phone`, `password`, `referral_code`, `referral_count`, `referral_userid`, `date_registered`, `time_registered`)
VALUES
	(1,'Admin','Admin','Admin','admin@ticketapp.com','07082828244','admin','96B81VLTD',0,0,'2020-02-17','09:33:02'),
	(2,'Subscriber','Saint','Deemene','visitsaint@gmail.com','07067483120','saint','653RE8PQM',0,0,'2020-02-17','09:44:24');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table wallet_type
# ------------------------------------------------------------

CREATE TABLE `wallet_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `wallet_type` WRITE;
/*!40000 ALTER TABLE `wallet_type` DISABLE KEYS */;

INSERT INTO `wallet_type` (`id`, `name`)
VALUES
	(1,'Main'),
	(2,'Deposit');

/*!40000 ALTER TABLE `wallet_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table wallets
# ------------------------------------------------------------

CREATE TABLE `wallets` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `balance` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `wallets` WRITE;
/*!40000 ALTER TABLE `wallets` DISABLE KEYS */;

INSERT INTO `wallets` (`id`, `userid`, `balance`)
VALUES
	(1,1,'1:0;2:0'),
	(2,2,'1:0;2:0');

/*!40000 ALTER TABLE `wallets` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
