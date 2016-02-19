-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: blogj
-- ------------------------------------------------------
-- Server version	5.5.47-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `ut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `enable` tinyint(1) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_article_id` (`user_id`),
  KEY `article_category_id_idx` (`category_id`),
  CONSTRAINT `fk_article_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (4,'box-klichko-fury',1,2,'2016-01-29 15:31:31',1,36),(5,'Castrum',1,1,'2015-11-28 21:16:09',1,27),(6,'russian',1,0,'2015-11-28 21:18:00',1,24),(7,'pacquiao',1,1,'2015-11-28 21:19:47',1,24),(8,'nbc_text',1,120,'2016-01-29 15:34:15',1,39),(13,'java_first_article_en',3,100,'2016-02-19 09:15:54',1,40),(14,'test_hr',1,0,'2016-02-19 16:28:46',1,24);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_tag_link`
--

DROP TABLE IF EXISTS `article_tag_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_tag_link` (
  `article_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `article_tag_id` (`tag_id`),
  KEY `article_id` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_tag_link`
--

LOCK TABLES `article_tag_link` WRITE;
/*!40000 ALTER TABLE `article_tag_link` DISABLE KEYS */;
INSERT INTO `article_tag_link` VALUES (6,27,19),(7,24,20),(7,28,21),(4,24,25),(4,25,26),(4,26,27),(8,29,28),(8,30,29),(8,1,30),(13,31,31),(13,32,32);
/*!40000 ALTER TABLE `article_tag_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enable` tinyint(1) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `weight` int(11) DEFAULT '1',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_id` (`user_id`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (24,1,'petroff',99,1),(27,1,'test',100,1),(36,1,'super',100,1),(37,1,'pahom',10,1),(38,1,'',0,1),(39,1,'n_b_c',100,1),(40,1,'java_for_all',100,3);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `ut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `article_id` int(11) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_id` (`user_id`),
  CONSTRAINT `fk_comment_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,NULL,'2015-12-06 21:26:06',4,1,'test@ya.ru'),(2,NULL,'2015-12-06 21:31:12',4,1,'anonim@gmail.com'),(3,1,'2015-12-06 21:34:58',4,1,NULL),(4,1,'2015-12-06 21:35:57',4,1,NULL),(5,1,'2015-12-06 21:36:52',4,1,NULL),(6,NULL,'2016-01-09 19:30:23',4,1,'eret@ert.com'),(7,NULL,'2016-01-09 19:31:21',4,1,'test@ya.ru'),(8,1,'2016-01-25 15:09:27',4,1,NULL),(9,1,'2016-01-25 15:11:25',4,1,NULL),(10,1,'2016-01-25 15:12:48',4,1,NULL),(11,NULL,'2016-01-29 14:59:48',4,1,'petroff@gmail.com'),(12,1,'2016-01-29 15:00:24',4,1,NULL),(13,1,'2016-01-29 15:07:32',4,1,NULL),(14,1,'2016-01-29 15:08:56',4,1,NULL),(15,1,'2016-01-29 15:09:25',4,1,NULL),(16,1,'2016-01-29 15:10:23',4,0,NULL),(26,1,'2016-01-29 15:26:52',4,0,NULL),(27,NULL,'2016-02-05 13:53:20',4,0,'soros@fav.ru'),(28,NULL,'2016-02-05 14:04:40',4,0,'perttt@ert.com'),(29,NULL,'2016-02-05 14:09:13',4,1,'wym@favbet.com'),(30,NULL,'2016-02-05 14:10:07',4,1,'petroffssdfsd@favbet.com'),(31,NULL,'2016-02-05 14:12:20',4,1,'petroff345@favbet.com'),(32,3,'2016-02-19 09:17:34',13,1,NULL),(33,3,'2016-02-19 09:17:52',13,1,NULL),(34,NULL,'2016-02-19 09:18:31',13,1,'annonim@yaa.ru');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` mediumtext,
  `lang` varchar(3) DEFAULT NULL,
  `object_id` int(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `ut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `content_object_id_idx` (`object_id`),
  KEY `content_type_idx` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=327 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` VALUES (1,'test en2!','en',24,'category',NULL,'0000-00-00 00:00:00'),(2,'test ru22!','ru',24,'category',NULL,'0000-00-00 00:00:00'),(7,'test en1','en',27,'category',NULL,'0000-00-00 00:00:00'),(8,'test ru1','ru',27,'category',NULL,'0000-00-00 00:00:00'),(25,'super','en',36,'category',NULL,'0000-00-00 00:00:00'),(26,'super ru','ru',36,'category',NULL,'0000-00-00 00:00:00'),(27,'pahom en','en',37,'category',NULL,'0000-00-00 00:00:00'),(28,'pahom ru','ru',37,'category',NULL,'0000-00-00 00:00:00'),(29,'test en','en',38,'category',NULL,'0000-00-00 00:00:00'),(30,'test ru','ru',38,'category',NULL,'0000-00-00 00:00:00'),(81,'test title en','en',22,'article_t',NULL,'0000-00-00 00:00:00'),(82,'test title ru','ru',22,'article_t',NULL,'0000-00-00 00:00:00'),(83,'test body en','en',22,'article_b',NULL,'0000-00-00 00:00:00'),(84,'test body ru','ru',22,'article_b',NULL,'0000-00-00 00:00:00'),(85,'test title en','en',23,'article_t',NULL,'0000-00-00 00:00:00'),(86,'test title ru','ru',23,'article_t',NULL,'0000-00-00 00:00:00'),(87,'test body en','en',23,'article_b',NULL,'0000-00-00 00:00:00'),(88,'test body ru','ru',23,'article_b',NULL,'0000-00-00 00:00:00'),(89,'tyery','ru',24,'article_t',NULL,'0000-00-00 00:00:00'),(90,'erty','en',24,'article_t',NULL,'0000-00-00 00:00:00'),(91,'ery','ru',24,'article_b',NULL,'0000-00-00 00:00:00'),(92,'ery','en',24,'article_b',NULL,'0000-00-00 00:00:00'),(93,'tyery','ru',25,'article_t',NULL,'0000-00-00 00:00:00'),(94,'erty','en',25,'article_t',NULL,'0000-00-00 00:00:00'),(95,'ery','ru',25,'article_b',NULL,'0000-00-00 00:00:00'),(96,'ery','en',25,'article_b',NULL,'0000-00-00 00:00:00'),(97,'tyery','ru',26,'article_t',NULL,'0000-00-00 00:00:00'),(98,'erty','en',26,'article_t',NULL,'0000-00-00 00:00:00'),(99,'ery','ru',26,'article_b',NULL,'0000-00-00 00:00:00'),(100,'ery','en',26,'article_b',NULL,'0000-00-00 00:00:00'),(101,'tyery','ru',27,'article_t',NULL,'0000-00-00 00:00:00'),(102,'erty','en',27,'article_t',NULL,'0000-00-00 00:00:00'),(103,'ery','ru',27,'article_b',NULL,'0000-00-00 00:00:00'),(104,'ery','en',27,'article_b',NULL,'0000-00-00 00:00:00'),(105,'tyery','ru',28,'article_t',NULL,'0000-00-00 00:00:00'),(106,'erty','en',28,'article_t',NULL,'0000-00-00 00:00:00'),(107,'ery','ru',28,'article_b',NULL,'0000-00-00 00:00:00'),(108,'ery','en',28,'article_b',NULL,'0000-00-00 00:00:00'),(109,'tyery','ru',29,'article_t',NULL,'0000-00-00 00:00:00'),(110,'erty','en',29,'article_t',NULL,'0000-00-00 00:00:00'),(111,'ery','ru',29,'article_b',NULL,'0000-00-00 00:00:00'),(112,'ery','en',29,'article_b',NULL,'0000-00-00 00:00:00'),(113,'tyery','ru',30,'article_t',NULL,'0000-00-00 00:00:00'),(114,'erty','en',30,'article_t',NULL,'0000-00-00 00:00:00'),(115,'ery','ru',30,'article_b',NULL,'0000-00-00 00:00:00'),(116,'ery','en',30,'article_b',NULL,'0000-00-00 00:00:00'),(117,'tyery','ru',31,'article_t',NULL,'0000-00-00 00:00:00'),(118,'erty','en',31,'article_t',NULL,'0000-00-00 00:00:00'),(119,'ery','ru',31,'article_b',NULL,'0000-00-00 00:00:00'),(120,'ery','en',31,'article_b',NULL,'0000-00-00 00:00:00'),(121,'tyery','ru',32,'article_t',NULL,'0000-00-00 00:00:00'),(122,'erty','en',32,'article_t',NULL,'0000-00-00 00:00:00'),(123,'ery','ru',32,'article_b',NULL,'0000-00-00 00:00:00'),(124,'ery','en',32,'article_b',NULL,'0000-00-00 00:00:00'),(125,'tyery','ru',33,'article_t',NULL,'0000-00-00 00:00:00'),(126,'erty','en',33,'article_t',NULL,'0000-00-00 00:00:00'),(127,'ery','ru',33,'article_b',NULL,'0000-00-00 00:00:00'),(128,'ery','en',33,'article_b',NULL,'0000-00-00 00:00:00'),(129,'tyery','ru',34,'article_t',NULL,'0000-00-00 00:00:00'),(130,'erty','en',34,'article_t',NULL,'0000-00-00 00:00:00'),(131,'ery','ru',34,'article_b',NULL,'0000-00-00 00:00:00'),(132,'ery','en',34,'article_b',NULL,'0000-00-00 00:00:00'),(133,'tyery','ru',35,'article_t',NULL,'0000-00-00 00:00:00'),(134,'erty','en',35,'article_t',NULL,'0000-00-00 00:00:00'),(135,'ery','ru',35,'article_b',NULL,'0000-00-00 00:00:00'),(136,'ery','en',35,'article_b',NULL,'0000-00-00 00:00:00'),(137,'tyery','ru',36,'article_t',NULL,'0000-00-00 00:00:00'),(138,'erty','en',36,'article_t',NULL,'0000-00-00 00:00:00'),(139,'ery','ru',36,'article_b',NULL,'0000-00-00 00:00:00'),(140,'ery','en',36,'article_b',NULL,'0000-00-00 00:00:00'),(141,'tyery','ru',45,'article_t',NULL,'0000-00-00 00:00:00'),(142,'erty','en',45,'article_t',NULL,'0000-00-00 00:00:00'),(143,'ery','ru',45,'article_b',NULL,'0000-00-00 00:00:00'),(144,'ery','en',45,'article_b',NULL,'0000-00-00 00:00:00'),(145,'erytwer','ru',46,'article_t',NULL,'0000-00-00 00:00:00'),(146,'ertwert','en',46,'article_t',NULL,'0000-00-00 00:00:00'),(147,'wetwert','ru',46,'article_b',NULL,'0000-00-00 00:00:00'),(148,'eter','en',46,'article_b',NULL,'0000-00-00 00:00:00'),(149,'erytwer','ru',47,'article_t',NULL,'0000-00-00 00:00:00'),(150,'ertwert','en',47,'article_t',NULL,'0000-00-00 00:00:00'),(151,'wetwert','ru',47,'article_b',NULL,'0000-00-00 00:00:00'),(152,'eter','en',47,'article_b',NULL,'0000-00-00 00:00:00'),(153,'wertwer','ru',48,'article_t',NULL,'0000-00-00 00:00:00'),(154,'wrwer','en',48,'article_t',NULL,'0000-00-00 00:00:00'),(155,'werwe','ru',48,'article_b',NULL,'0000-00-00 00:00:00'),(156,'werwe','en',48,'article_b',NULL,'0000-00-00 00:00:00'),(157,'wertwer','ru',49,'article_t',NULL,'0000-00-00 00:00:00'),(158,'wrwer','en',49,'article_t',NULL,'0000-00-00 00:00:00'),(159,'werwe','ru',49,'article_b',NULL,'0000-00-00 00:00:00'),(160,'werwe','en',49,'article_b',NULL,'0000-00-00 00:00:00'),(161,'wertwer','ru',50,'article_t',NULL,'0000-00-00 00:00:00'),(162,'wrwer','en',50,'article_t',NULL,'0000-00-00 00:00:00'),(163,'werwe','ru',50,'article_b',NULL,'0000-00-00 00:00:00'),(164,'werwe','en',50,'article_b',NULL,'0000-00-00 00:00:00'),(165,'wertwer','ru',51,'article_t',NULL,'0000-00-00 00:00:00'),(166,'wrwer','en',51,'article_t',NULL,'0000-00-00 00:00:00'),(167,'werwe','ru',51,'article_b',NULL,'0000-00-00 00:00:00'),(168,'werwe','en',51,'article_b',NULL,'0000-00-00 00:00:00'),(173,'wetrwe','ru',53,'article_t',NULL,'0000-00-00 00:00:00'),(174,'werwe','en',53,'article_t',NULL,'0000-00-00 00:00:00'),(175,'werwe','ru',53,'article_b',NULL,'0000-00-00 00:00:00'),(176,'werwer','en',53,'article_b',NULL,'0000-00-00 00:00:00'),(177,'wetrwe','ru',54,'article_t',NULL,'0000-00-00 00:00:00'),(178,'werwe','en',54,'article_t',NULL,'0000-00-00 00:00:00'),(179,'werwe','ru',54,'article_b',NULL,'0000-00-00 00:00:00'),(180,'werwer','en',54,'article_b',NULL,'0000-00-00 00:00:00'),(181,'tesfgsd','ru',55,'article_t',NULL,'0000-00-00 00:00:00'),(182,'dfgwerter','en',55,'article_t',NULL,'0000-00-00 00:00:00'),(183,'tytywy','ru',55,'article_b',NULL,'0000-00-00 00:00:00'),(184,'etweter','en',55,'article_b',NULL,'0000-00-00 00:00:00'),(185,'tesfgsd','ru',56,'article_t',NULL,'0000-00-00 00:00:00'),(186,'dfgwerter','en',56,'article_t',NULL,'0000-00-00 00:00:00'),(187,'tytywy','ru',56,'article_b',NULL,'0000-00-00 00:00:00'),(188,'etweter','en',56,'article_b',NULL,'0000-00-00 00:00:00'),(189,'tesfgsd','ru',57,'article_t',NULL,'0000-00-00 00:00:00'),(190,'dfgwerter','en',57,'article_t',NULL,'0000-00-00 00:00:00'),(191,'tytywy','ru',57,'article_b',NULL,'0000-00-00 00:00:00'),(192,'etweter','en',57,'article_b',NULL,'0000-00-00 00:00:00'),(193,'tesfgsd','ru',58,'article_t',NULL,'0000-00-00 00:00:00'),(194,'dfgwerter','en',58,'article_t',NULL,'0000-00-00 00:00:00'),(195,'qwrwr','ru',58,'article_b',NULL,'0000-00-00 00:00:00'),(196,'sdfwerw','en',58,'article_b',NULL,'0000-00-00 00:00:00'),(197,'tesfgsd','ru',59,'article_t',NULL,'0000-00-00 00:00:00'),(198,'dfgwerter','en',59,'article_t',NULL,'0000-00-00 00:00:00'),(199,'qwrwr','ru',59,'article_b',NULL,'0000-00-00 00:00:00'),(200,'sdfwerw','en',59,'article_b',NULL,'0000-00-00 00:00:00'),(201,'tesfgsd','ru',60,'article_t',NULL,'0000-00-00 00:00:00'),(202,'dfgwerter','en',60,'article_t',NULL,'0000-00-00 00:00:00'),(203,'qwrwr','ru',60,'article_b',NULL,'0000-00-00 00:00:00'),(204,'sdfwerw','en',60,'article_b',NULL,'0000-00-00 00:00:00'),(205,'tesfgsd','ru',61,'article_t',NULL,'0000-00-00 00:00:00'),(206,'dfgwerter','en',61,'article_t',NULL,'0000-00-00 00:00:00'),(207,'qwrwr','ru',61,'article_b',NULL,'0000-00-00 00:00:00'),(208,'sdfwerw','en',61,'article_b',NULL,'0000-00-00 00:00:00'),(209,'tesfgsd','ru',62,'article_t',NULL,'0000-00-00 00:00:00'),(210,'dfgwerter','en',62,'article_t',NULL,'0000-00-00 00:00:00'),(211,'qwrwr','ru',62,'article_b',NULL,'0000-00-00 00:00:00'),(212,'sdfwerw','en',62,'article_b',NULL,'0000-00-00 00:00:00'),(213,'tesfgsd','ru',63,'article_t',NULL,'0000-00-00 00:00:00'),(214,'dfgwerter','en',63,'article_t',NULL,'0000-00-00 00:00:00'),(215,'qwrwr','ru',63,'article_b',NULL,'0000-00-00 00:00:00'),(216,'sdfwerw','en',63,'article_b',NULL,'0000-00-00 00:00:00'),(217,'tesfgsd','ru',64,'article_t',NULL,'0000-00-00 00:00:00'),(218,'dfgwerter','en',64,'article_t',NULL,'0000-00-00 00:00:00'),(219,'qwrwr','ru',64,'article_b',NULL,'0000-00-00 00:00:00'),(220,'sdfwerw','en',64,'article_b',NULL,'0000-00-00 00:00:00'),(221,'tesfgsd','ru',65,'article_t',NULL,'0000-00-00 00:00:00'),(222,'dfgwerter','en',65,'article_t',NULL,'0000-00-00 00:00:00'),(223,'qwrwr','ru',65,'article_b',NULL,'0000-00-00 00:00:00'),(224,'sdfwerw','en',65,'article_b',NULL,'0000-00-00 00:00:00'),(225,'tesfgsd','ru',66,'article_t',NULL,'0000-00-00 00:00:00'),(226,'dfgwerter','en',66,'article_t',NULL,'0000-00-00 00:00:00'),(227,'qwrwr','ru',66,'article_b',NULL,'0000-00-00 00:00:00'),(228,'sdfwerw','en',66,'article_b',NULL,'0000-00-00 00:00:00'),(241,'!box vlodimir klicho vs tyson F','en',4,'article_t',NULL,'0000-00-00 00:00:00'),(242,'??? ?????? ?? ?????','ru',4,'article_t',NULL,'0000-00-00 00:00:00'),(243,'Tyson Fury\'s world heavyweight clash with Wladimir Klitschko will be worth over £30million.\r\n\r\nAround 40,000 tickets for the October 24 showdown have already been sold and the fight is on course to be a 55,000 sell-out at the Esprit Arena in Dusseldorf.\r\n\r\nThe unbeaten Fury will challenge heavyweight king Klitschko for his WBA Super, IBF and WBO crowns and the Traveller\'s promoter Mick Hennessy says interest in the fight is huge.\r\n\r\n\"We\'re already close to selling 40,000 tickets and it is guaranteed to be a 55,000 sell-out,\" he said.\r\n\r\n\"It\'s selling brilliantly and worldwide this fight could be worth more than £30million.\"\r\nHennessy claims interest will further rise when Fury and Klitschko meet in London for a second head-to-head in mid-September.\r\n\r\nHennessy claims Fury\'s training camp is going well and says he and trainer Peter Fury have dropped plans to go to Ireland next month.\r\n\r\nFury had planned to move camp to Castlebar in Mayo, where he has trained before.','en',4,'article_b',NULL,'0000-00-00 00:00:00'),(244,'??????? ? ???????????? (????????) ??????? ???? ? ???????????? ???? ?? ??????? WBA, WBO, IBF, IBO ? ??????? The Ring ???????? ?????? (64-3, 53 KOs) ???????? ????????? ?????? ????? ??????? ? ???????? ?????? ???????? ??????? ????? (24-0, 18 KOs).\r\n\r\n??? ?????? ?????? ??? ??????????? ??????? 19-? ?????? ? ???????. ?????? ?? ?????????? ???????????????? ????? ???????????? ?????? ??????????? ?????????? ??? ?????, ???????????? ? 1940-1950 ?????, ??????? 25 ??? ??????? ??????? ?????.\r\n\r\n????? ????? 13-? ?????????? ????????? ??????, ??????? ?? ??????? ? ????????? ?? ?????????? ?? ?????? ???. 12 ?????????? ???????? ?????????? ?????? ??????? – ?????????? ??????? ?????????, ?????? ???? ? ????? ?????, ???????? ????????? ???????? ? ?????? ?????????, ???????? ?????? ?????, ????????? ????????? ???????, ????? ?????? ???, ????? ?????? ??????, ???????? ??????? ?????, ??????? ?????? ???????? ? ?????????? ????? ?????? ?????????? ?? ?????? ?????? ????????? ?? ???????????????? ?????.\r\n\r\n?????? ????? – ?????? ????? – ????? ???????????? ???????? ???????? ? ??? ???????. ???? ???????? ?????????? 206 ??, ? ?? ?????, ??? ? ?????? – 198 ??. ?? ????? ????? ??????? ?????????? ???????? ??? ?????? ??? ? ?????? 202 ??, ??????? ?????? ?????? ???????? ??? 12 ???????, ?? ????? ??? ????????????????? ?? ???????????? ???????.\r\n\r\n?????????????, ??? ??? ???????? ???????????? ?????????? ??????, ???????????? ? ??? ?? ?????????, ??????? ??????????? ??? ?????????. ?????????? ???? ??????? (196 ??) ?????????? ? ????????? ?????? – ? 2008 ? 2012 ????? ? ??????? ???????? – ? 11-? ? ?????? ??????? ??????????????. ?????????? ??? ?????? (198 ??) ???????? ?????? ? 2007 ???? ???????????? ?? ?????? ??????.\r\n\r\n????? ???????? ?????? ???????????? ??????????? ?? ??? ? ?????? ? ?????? 2014 ????, ??????? ????????? ?????? ? ??????? ?????? ??? ????????????????? – ??????? ???????. ????? ????? ???????? ?????? ???? ???????? ?????? ????????????????????? ?????? WBO, ???????? 28 ??????? 2015 ???? ? ??????? ?????????? ?? ??????????? ??????????? ??? ????????? ??????? ?? ????????.\r\n\r\n?????? ????? ? 2015 ???? ??????? ? ???? ???? ???. 25 ?????? ? ???-????? ???????? ???????????? ???????? ????? ??????? ?????????? ???????? ??????????.\r\n\r\n???????? ????? ?????????? ?????? ? ???????? ????? ?????? ??? ?????????? 24 ???????, ?? ??? ????????? ?? ????? ??-?? ?????? ??????????? ???????. ????? ??????? ???????? ??????? – ??????????????? \"??????? ?????\", ??????? ?????? ?? ??? 55 ????? ????????.\r\n\r\n?? ???????????? ???????? ??????????? ???????? ? ???????? ???????? ??????????? ?????????? ???. ?????? ???????????? 111,5 ??, ? ????? - 112 ??. ???????, ??? ????? ??????? ?????????? ? ??????? ? ????????? ???????? ????? 146 ??.\r\n\r\n? ??? ?????? – ????? ???? ???????? ??????????? ????????? ?????? «Dr.Steelhammer. ?????????? ????». ? ??? ????? ???????????? ???????? ? ???????.\r\n\r\n?????????? ??? ?????? - ?????\r\n\r\n21:30 – \"?????\" \r\n\r\n\r\n\r\n???????? — Sport.ua\r\n\r\n \r\n????????? ?? ????\r\n????? ?????: «???????? ?? ????????? ??????????? ??????» - ????•28.11.2015 11:40\r\n????? ?????: «?????? ?? 100% ?????? ? ????? ??????» - ????•28.11.2015 10:21\r\n??????? ??????: «? ???? ????? ??????? ???? ? ????» - ????•28.11.2015 09:48\r\n???? ???? — ?????? ???????? ?????? - ????? - ????•27.11.2015 23:44\r\n','ru',4,'article_b',NULL,'0000-00-00 00:00:00'),(245,'Castrum en','en',5,'article_t',NULL,'0000-00-00 00:00:00'),(246,'Castrum ru','ru',5,'article_t',NULL,'0000-00-00 00:00:00'),(247,'Castrum doloris (Latin for Castle of grief) is a name for the structure and decorations sheltering or accompanying the catafalque or bier that signify the prestige or high estate of the deceased. A Castrum doloris might feature an elaborate baldachin and would include candles, possibly flowers, and in most cases coats of arms, epitaphs and possibly allegorical statues. Many extensive castra doloris can be traced to the customs of 17th century and 18th century or even earlier, since Pope Sixtus V\'s funeral arrangements included a castrum doloris in the mid 14th Century.\r\n\r\nNotable examples include coffin portrait (Polish: portret trumienny) of Polish-Lithuanian Commonwealth.','en',5,'article_b',NULL,'0000-00-00 00:00:00'),(248,'Castrum doloris (Latin for Castle of grief) is a name for the structure and decorations sheltering or accompanying the catafalque or bier that signify the prestige or high estate of the deceased. A Castrum doloris might feature an elaborate baldachin and would include candles, possibly flowers, and in most cases coats of arms, epitaphs and possibly allegorical statues. Many extensive castra doloris can be traced to the customs of 17th century and 18th century or even earlier, since Pope Sixtus V\'s funeral arrangements included a castrum doloris in the mid 14th Century.\r\n\r\nNotable examples include coffin portrait (Polish: portret trumienny) of Polish-Lithuanian Commonwealth.','ru',5,'article_b',NULL,'0000-00-00 00:00:00'),(249,'russian','en',6,'article_t',NULL,'0000-00-00 00:00:00'),(250,'russian ru','ru',6,'article_t',NULL,'0000-00-00 00:00:00'),(251,'Russian (???????? ?????, russkiy yazyk, pronounced [?rusk??j j??z?k] ( listen)) is an East Slavic language and an official language in Russia, Belarus, and Kyrgyzstan. It is an unofficial but widely spoken language in Ukraine, Moldova, Latvia, Estonia, and to a lesser extent, the other countries that were once constituent republics of the Soviet Union and former participants of the Eastern Bloc.[24][25] Russian belongs to the family of Indo-European languages and is one of the three living members of the East Slavic languages. Written examples of Old East Slavonic are attested from the 10th century onwards.\r\n\r\nIt is the most geographically widespread language of Eurasia and the most widely spoken of the Slavic languages. It is also the largest native language in Europe, with 144 million native speakers in Russia, Ukraine and Belarus. Russian is the eighth most spoken language in the world by number of native speakers and the seventh by total number of speakers.[26] The language is one of the six official languages of the United Nations.\r\n\r\nRussian distinguishes between consonant phonemes with palatal secondary articulation and those without, the so-called soft and hard sounds. This distinction is found between pairs of almost all consonants and is one of the most distinguishing features of the language. Another important aspect is the reduction of unstressed vowels. Stress, which is unpredictable, is not normally indicated orthographically[27] though an optional acute accent (???? ????????, znak udareniya) may be used to mark stress, such as to distinguish between homographic words, for example ?????? (zamok, meaning a lock) and ?????? (zamok, meaning a castle), or to indicate the proper pronunciation of uncommon words or names.','en',6,'article_b',NULL,'0000-00-00 00:00:00'),(252,'Ru Russian (???????? ?????, russkiy yazyk, pronounced [?rusk??j j??z?k] ( listen)) is an East Slavic language and an official language in Russia, Belarus, and Kyrgyzstan. It is an unofficial but widely spoken language in Ukraine, Moldova, Latvia, Estonia, and to a lesser extent, the other countries that were once constituent republics of the Soviet Union and former participants of the Eastern Bloc.[24][25] Russian belongs to the family of Indo-European languages and is one of the three living members of the East Slavic languages. Written examples of Old East Slavonic are attested from the 10th century onwards.\r\n\r\nIt is the most geographically widespread language of Eurasia and the most widely spoken of the Slavic languages. It is also the largest native language in Europe, with 144 million native speakers in Russia, Ukraine and Belarus. Russian is the eighth most spoken language in the world by number of native speakers and the seventh by total number of speakers.[26] The language is one of the six official languages of the United Nations.\r\n\r\nRussian distinguishes between consonant phonemes with palatal secondary articulation and those without, the so-called soft and hard sounds. This distinction is found between pairs of almost all consonants and is one of the most distinguishing features of the language. Another important aspect is the reduction of unstressed vowels. Stress, which is unpredictable, is not normally indicated orthographically[27] though an optional acute accent (???? ????????, znak udareniya) may be used to mark stress, such as to distinguish between homographic words, for example ?????? (zamok, meaning a lock) and ?????? (zamok, meaning a castle), or to indicate the proper pronunciation of uncommon words or names.','ru',6,'article_b',NULL,'0000-00-00 00:00:00'),(253,'Pacquiao','en',7,'article_t',NULL,'0000-00-00 00:00:00'),(254,'Ppacquiao RU','ru',7,'article_t',NULL,'0000-00-00 00:00:00'),(255,'The Fight of the Century is over... but you can still book a repeat!\r\n\r\nWe are showing five repeats of the massive fight between Floyd Mayweather and Manny Pacquiao from the MGM Grand in Las Vegas, as well as two undercard bouts.\r\n\r\nOur three-hour repeats will begin with the main event and you can still book them throughout Sunday, May 3,\r\n\r\nThey are on at 10.00am, 1.00pm, 4.00pm, 7.00pm and 10.00pm.\r\n\r\nRepeat booking information\r\n\r\nThere are repeat viewings on Sunday, May 3, at 10.00am, 1.00pm, 4.00pm, 7.00pm and 10.00pm. They are 3hrs long and the main fight between Mayweather v Pacquiao will be first.\r\n\r\nThe repeats will be on Sky Sports Box Office (Channel 491) and Sky Sports HD Box Office (Channel 492).\r\n\r\nFrom 7.00am, Sunday, May 3, the event price will revert back to £19.95 / €24.95 across all forms of booking.\r\n\r\nVia remote\r\n\r\nPress box office, Select sports and events. Use the arrow buttons to highlight the event. Press select and the buy screen will be displayed. Press select to confirm your order. Enter your pin if prompted.\r\n\r\nOnline\r\n\r\nTo book online go to www.sky.com/orderboxoffice\r\n\r\nBy phone \r\n\r\nTo order the event via telephone call 03442 410888 (there may be a £2 booking fee for telephone bookings). There is a £5/€5 surcharge for bookings made via the phone.\r\n\r\nCable customers\r\n\r\nPlease contact your operator.','en',7,'article_b',NULL,'0000-00-00 00:00:00'),(256,'Ru The Fight of the Century is over... but you can still book a repeat!\r\n\r\nWe are showing five repeats of the massive fight between Floyd Mayweather and Manny Pacquiao from the MGM Grand in Las Vegas, as well as two undercard bouts.\r\n\r\nOur three-hour repeats will begin with the main event and you can still book them throughout Sunday, May 3,\r\n\r\nThey are on at 10.00am, 1.00pm, 4.00pm, 7.00pm and 10.00pm.\r\n\r\nRepeat booking information\r\n\r\nThere are repeat viewings on Sunday, May 3, at 10.00am, 1.00pm, 4.00pm, 7.00pm and 10.00pm. They are 3hrs long and the main fight between Mayweather v Pacquiao will be first.\r\n\r\nThe repeats will be on Sky Sports Box Office (Channel 491) and Sky Sports HD Box Office (Channel 492).\r\n\r\nFrom 7.00am, Sunday, May 3, the event price will revert back to £19.95 / €24.95 across all forms of booking.\r\n\r\nVia remote\r\n\r\nPress box office, Select sports and events. Use the arrow buttons to highlight the event. Press select and the buy screen will be displayed. Press select to confirm your order. Enter your pin if prompted.\r\n\r\nOnline\r\n\r\nTo book online go to www.sky.com/orderboxoffice\r\n\r\nBy phone \r\n\r\nTo order the event via telephone call 03442 410888 (there may be a £2 booking fee for telephone bookings). There is a £5/€5 surcharge for bookings made via the phone.\r\n\r\nCable customers\r\n\r\nPlease contact your operator.','ru',7,'article_b',NULL,'0000-00-00 00:00:00'),(278,'first aninum message','en',1,'comment',NULL,'0000-00-00 00:00:00'),(279,'second anonim message','en',2,'comment',NULL,'0000-00-00 00:00:00'),(280,'auth user first message','en',3,'comment',NULL,'0000-00-00 00:00:00'),(281,'second auth user message','en',4,'comment',NULL,'0000-00-00 00:00:00'),(282,'third auth message user','en',5,'comment',NULL,'0000-00-00 00:00:00'),(283,'wefwefcas','en',6,'comment',NULL,'0000-00-00 00:00:00'),(284,'vbdfbdf','en',7,'comment',NULL,'0000-00-00 00:00:00'),(285,'test','en',8,'comment',NULL,'0000-00-00 00:00:00'),(286,'new test','en',9,'comment',NULL,'2016-01-25 15:11:25'),(287,'test new 3','en',10,'comment',NULL,'2016-01-25 15:12:48'),(288,'xaxa test horva','en',11,'comment',NULL,'2016-01-29 14:59:48'),(289,'yx yx test horvatia','en',12,'comment',NULL,'2016-01-29 15:00:24'),(290,'test fgdf eter dfbdf','en',13,'comment',NULL,'2016-01-29 15:07:32'),(291,'vb rfhwshsh fghfggh','en',14,'comment',NULL,'2016-01-29 15:08:56'),(292,'vnv eydhdgh dfghdfgdf','en',15,'comment',NULL,'2016-01-29 15:09:25'),(293,'sdvs fbfgf fghfgh dddddddddd','en',16,'comment',NULL,'2016-01-29 15:10:23'),(294,'vc eryt 45 ghrt 455er eer','en',26,'comment',1,'2016-01-29 15:26:52'),(295,'new best category ru','ru',39,'category',1,'2016-01-29 15:30:55'),(296,'new best category en','en',39,'category',1,'2016-01-29 15:30:55'),(297,'new category title article ru','ru',8,'article_t',1,'2016-01-29 15:34:15'),(298,'new category title article en','en',8,'article_t',1,'2016-01-29 15:34:15'),(299,'new category text article article ru','ru',8,'article_b',1,'2016-01-29 15:34:15'),(300,'new category text article article en!','en',8,'article_b',1,'2016-01-29 15:34:15'),(301,'dfd bnfg fgh','en',27,'comment',0,'2016-02-05 13:53:20'),(302,'dsfffffff','en',28,'comment',0,'2016-02-05 14:04:40'),(303,'podwym i vsriv granat i traseri letayt','en',29,'comment',0,'2016-02-05 14:09:13'),(304,'1','en',30,'comment',0,'2016-02-05 14:10:07'),(305,'2','en',31,'comment',0,'2016-02-05 14:12:20'),(306,'java for all ru','ru',40,'category',3,'2016-02-19 09:09:24'),(307,'java for all en','en',40,'category',3,'2016-02-19 09:09:24'),(316,'java first article ru','ru',13,'article_t',3,'2016-02-19 09:15:54'),(317,'java first article en','en',13,'article_t',3,'2016-02-19 09:15:54'),(318,'all will be on russian','ru',13,'article_b',3,'2016-02-19 09:15:54'),(319,'Object-oriented\r\n\r\nMany older languages, like C and Pascal, were procedural languages. Procedures (also called functions) were blocks of code that were part of a module or application. Procedures passed parameters (primitive data types like integers, characters, strings, and floating point numbers). Code was treated separately to data. You had to pass around data structures, and procedures could easily modify their contents. This was a source of problems, as parts of a program could have unforeseen effects in other parts. Tracking down which procedure was at fault wasted a great deal of time and effort, particularly with large programs.\r\n\r\nIn some procedural language, you could even obtain the memory location of a data structure. Armed with this location, you could read and write to the data at a later time, or accidentally overwrite the contents.\r\n\r\nJava is an object-oriented language. An object-oriented language deals with objects. Objects contain both data (member variables) and code (methods). Each object belongs to a particular class, which is a blueprint describing the member variables and methods an object offers. In Java, almost every variable is an object of some type or another - even strings. Object-oriented programming requires a different way of thinking, but is a better way to design software than procedural programming.\r\n\r\nThere are many popular object-oriented languages available today. Some like Smalltalk and Java are designed from the beginning to be object-oriented. Others, like C++, are partially object-oriented, and partially procedural. In C++, you can still overwrite the contents of data structures and objects, causing the application to crash. Thankfully, Java prohibits direct access to memory contents, leading to a more robust system.\r\n\r\nPortable\r\n\r\nMost programming languages are designed for a specific operating system and processor architecture. When source code (the instructions that make up a program) are compiled, it is converted to machine code which can be executed only on one type of machine. This process produces native code, which is extremely fast.\r\n\r\nAnother type of language is one that is interpreted. Interpreted code is read by a software application (the interpreter), which performs the specified actions. Interpreted code often doesn\'t need to be compiled - it is translated as it is run. For this reason, interpreted code is quite slow, but often portable across different operating systems and processor architectures.\r\n\r\nJava takes the best of both techniques. Java code is compiled into a platform-neutral machine code, which is called Java bytecode. A special type of interpreter, known as a Java Virtual Machine (JVM), reads the bytecode, and processes it. Figure One shows a disassembly of a small Java application. The bytecode, indicated by the arrow, is represented in text form here, but when compiled it is represented as bytes to conserve space.','en',13,'article_b',3,'2016-02-19 09:15:54'),(320,'firs my comment','en',32,'comment',3,'2016-02-19 09:17:34'),(321,'second comment','en',33,'comment',3,'2016-02-19 09:17:52'),(322,'annonim comment','en',34,'comment',0,'2016-02-19 09:18:31'),(323,'test hr en','ru',14,'article_t',1,'2016-02-19 16:28:46'),(324,'test hr ru','en',14,'article_t',1,'2016-02-19 16:28:46'),(325,'тест тело ру','ru',14,'article_b',1,'2016-02-19 16:28:46'),(326,'test hr en body','en',14,'article_b',1,'2016-02-19 16:28:46');
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) DEFAULT NULL,
  `descriptor` text,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'ADMINS','Admin site','petroff'),(2,'USERS','default','andi-fm'),(3,'USERS','default','andi-fm2');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `fk_tag_id` (`user_id`),
  CONSTRAINT `fk_tag_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,1,'one'),(2,1,'two'),(13,1,'three'),(14,1,'four'),(15,1,'andrey'),(16,1,'olya'),(17,1,' mixa'),(18,1,'test'),(19,1,'test1'),(20,1,'kill'),(21,1,'ter'),(22,1,'faker'),(23,1,'dog'),(24,1,'box'),(25,1,'klichko'),(26,1,'fury'),(27,1,'russian'),(28,1,' pacquiao'),(29,1,'viper'),(30,1,'xoy'),(31,3,'java'),(32,3,' article');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'petroff','petroff1@gmail.com','827ccb0eea8a706c4c34a16891f84e7b'),(2,'andi-fm','andi-fm@ya.ru','827ccb0eea8a706c4c34a16891f84e7b'),(3,'andi-fm2','andi-fm2@ya.ru','827ccb0eea8a706c4c34a16891f84e7b');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-19 18:30:48
