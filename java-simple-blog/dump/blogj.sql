-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: blogj
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_tag_link`
--

LOCK TABLES `article_tag_link` WRITE;
/*!40000 ALTER TABLE `article_tag_link` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (24,1,'petroff',100,1),(27,1,'test',100,1),(36,1,'super',100,1),(37,1,'pahom',10,1),(38,1,'',0,1);
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
  `message` text,
  `user_id` int(11) DEFAULT NULL,
  `ut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `article_id` int(11) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_id` (`user_id`),
  CONSTRAINT `fk_comment_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
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
  `text` text,
  `lang` varchar(3) DEFAULT NULL,
  `object_id` int(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `content_object_id_idx` (`object_id`),
  KEY `content_type_idx` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` VALUES (1,'test en2','en',24,'category'),(2,'test ru22','ru',24,'category'),(7,'test en1','en',27,'category'),(8,'test ru1','ru',27,'category'),(25,'petroff','en',36,'category'),(26,'Ð¿ÐµÑ?Ñ?Ð¾Ñ?Ñ?','ru',36,'category'),(27,'pahom en','en',37,'category'),(28,'pahom ru','ru',37,'category'),(29,'test en','en',38,'category'),(30,'test ru','ru',38,'category'),(81,'test title en','en',22,'article_t'),(82,'test title ru','ru',22,'article_t'),(83,'test body en','en',22,'article_b'),(84,'test body ru','ru',22,'article_b'),(85,'test title en','en',23,'article_t'),(86,'test title ru','ru',23,'article_t'),(87,'test body en','en',23,'article_b'),(88,'test body ru','ru',23,'article_b'),(89,'tyery','ru',24,'article_t'),(90,'erty','en',24,'article_t'),(91,'ery','ru',24,'article_b'),(92,'ery','en',24,'article_b'),(93,'tyery','ru',25,'article_t'),(94,'erty','en',25,'article_t'),(95,'ery','ru',25,'article_b'),(96,'ery','en',25,'article_b'),(97,'tyery','ru',26,'article_t'),(98,'erty','en',26,'article_t'),(99,'ery','ru',26,'article_b'),(100,'ery','en',26,'article_b'),(101,'tyery','ru',27,'article_t'),(102,'erty','en',27,'article_t'),(103,'ery','ru',27,'article_b'),(104,'ery','en',27,'article_b'),(105,'tyery','ru',28,'article_t'),(106,'erty','en',28,'article_t'),(107,'ery','ru',28,'article_b'),(108,'ery','en',28,'article_b'),(109,'tyery','ru',29,'article_t'),(110,'erty','en',29,'article_t'),(111,'ery','ru',29,'article_b'),(112,'ery','en',29,'article_b'),(113,'tyery','ru',30,'article_t'),(114,'erty','en',30,'article_t'),(115,'ery','ru',30,'article_b'),(116,'ery','en',30,'article_b'),(117,'tyery','ru',31,'article_t'),(118,'erty','en',31,'article_t'),(119,'ery','ru',31,'article_b'),(120,'ery','en',31,'article_b'),(121,'tyery','ru',32,'article_t'),(122,'erty','en',32,'article_t'),(123,'ery','ru',32,'article_b'),(124,'ery','en',32,'article_b'),(125,'tyery','ru',33,'article_t'),(126,'erty','en',33,'article_t'),(127,'ery','ru',33,'article_b'),(128,'ery','en',33,'article_b'),(129,'tyery','ru',34,'article_t'),(130,'erty','en',34,'article_t'),(131,'ery','ru',34,'article_b'),(132,'ery','en',34,'article_b'),(133,'tyery','ru',35,'article_t'),(134,'erty','en',35,'article_t'),(135,'ery','ru',35,'article_b'),(136,'ery','en',35,'article_b'),(137,'tyery','ru',36,'article_t'),(138,'erty','en',36,'article_t'),(139,'ery','ru',36,'article_b'),(140,'ery','en',36,'article_b'),(141,'tyery','ru',45,'article_t'),(142,'erty','en',45,'article_t'),(143,'ery','ru',45,'article_b'),(144,'ery','en',45,'article_b'),(145,'erytwer','ru',46,'article_t'),(146,'ertwert','en',46,'article_t'),(147,'wetwert','ru',46,'article_b'),(148,'eter','en',46,'article_b'),(149,'erytwer','ru',47,'article_t'),(150,'ertwert','en',47,'article_t'),(151,'wetwert','ru',47,'article_b'),(152,'eter','en',47,'article_b'),(153,'wertwer','ru',48,'article_t'),(154,'wrwer','en',48,'article_t'),(155,'werwe','ru',48,'article_b'),(156,'werwe','en',48,'article_b'),(157,'wertwer','ru',49,'article_t'),(158,'wrwer','en',49,'article_t'),(159,'werwe','ru',49,'article_b'),(160,'werwe','en',49,'article_b'),(161,'wertwer','ru',50,'article_t'),(162,'wrwer','en',50,'article_t'),(163,'werwe','ru',50,'article_b'),(164,'werwe','en',50,'article_b'),(165,'wertwer','ru',51,'article_t'),(166,'wrwer','en',51,'article_t'),(167,'werwe','ru',51,'article_b'),(168,'werwe','en',51,'article_b'),(173,'wetrwe','ru',53,'article_t'),(174,'werwe','en',53,'article_t'),(175,'werwe','ru',53,'article_b'),(176,'werwer','en',53,'article_b'),(177,'wetrwe','ru',54,'article_t'),(178,'werwe','en',54,'article_t'),(179,'werwe','ru',54,'article_b'),(180,'werwer','en',54,'article_b'),(181,'tesfgsd','ru',55,'article_t'),(182,'dfgwerter','en',55,'article_t'),(183,'tytywy','ru',55,'article_b'),(184,'etweter','en',55,'article_b'),(185,'tesfgsd','ru',56,'article_t'),(186,'dfgwerter','en',56,'article_t'),(187,'tytywy','ru',56,'article_b'),(188,'etweter','en',56,'article_b'),(189,'tesfgsd','ru',57,'article_t'),(190,'dfgwerter','en',57,'article_t'),(191,'tytywy','ru',57,'article_b'),(192,'etweter','en',57,'article_b'),(193,'tesfgsd','ru',58,'article_t'),(194,'dfgwerter','en',58,'article_t'),(195,'qwrwr','ru',58,'article_b'),(196,'sdfwerw','en',58,'article_b'),(197,'tesfgsd','ru',59,'article_t'),(198,'dfgwerter','en',59,'article_t'),(199,'qwrwr','ru',59,'article_b'),(200,'sdfwerw','en',59,'article_b'),(201,'tesfgsd','ru',60,'article_t'),(202,'dfgwerter','en',60,'article_t'),(203,'qwrwr','ru',60,'article_b'),(204,'sdfwerw','en',60,'article_b'),(205,'tesfgsd','ru',61,'article_t'),(206,'dfgwerter','en',61,'article_t'),(207,'qwrwr','ru',61,'article_b'),(208,'sdfwerw','en',61,'article_b'),(209,'tesfgsd','ru',62,'article_t'),(210,'dfgwerter','en',62,'article_t'),(211,'qwrwr','ru',62,'article_b'),(212,'sdfwerw','en',62,'article_b'),(213,'tesfgsd','ru',63,'article_t'),(214,'dfgwerter','en',63,'article_t'),(215,'qwrwr','ru',63,'article_b'),(216,'sdfwerw','en',63,'article_b'),(217,'tesfgsd','ru',64,'article_t'),(218,'dfgwerter','en',64,'article_t'),(219,'qwrwr','ru',64,'article_b'),(220,'sdfwerw','en',64,'article_b'),(221,'tesfgsd','ru',65,'article_t'),(222,'dfgwerter','en',65,'article_t'),(223,'qwrwr','ru',65,'article_b'),(224,'sdfwerw','en',65,'article_b'),(225,'tesfgsd','ru',66,'article_t'),(226,'dfgwerter','en',66,'article_t'),(227,'qwrwr','ru',66,'article_b'),(228,'sdfwerw','en',66,'article_b');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'ADMINS','Admin site','petroff');
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,1,'one'),(2,1,'two'),(13,1,'three'),(14,1,'four'),(15,1,'andrey'),(16,1,'olya'),(17,1,' mixa');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'petroff','petroff1@gmail.com','827ccb0eea8a706c4c34a16891f84e7b');
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

-- Dump completed on 2015-11-09  0:05:49
