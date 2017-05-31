-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 172.26.147.131    Database: s145
-- ------------------------------------------------------
-- Server version	5.6.35

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
-- Table structure for table `computer`
--

DROP TABLE IF EXISTS `computer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computer` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `Computername` varchar(50) NOT NULL,
  `SERIES` varchar(50) NOT NULL,
  `PRICE` int(6) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer`
--

LOCK TABLES `computer` WRITE;
/*!40000 ALTER TABLE `computer` DISABLE KEYS */;
INSERT INTO `computer` VALUES (7,'T440s','Thinkpad',578),(8,'T450s','Thinkpad',693),(9,'T460s','Thinkpad',887),(10,'New X1 Carbon','Thinkpad',576),(11,'X250','Thinkpad',789),(12,'E450','Thinkpad',723),(13,'E455','Thinkpad',983),(14,'E550','Thinkpad',997),(15,'E555','Thinkpad',1038),(16,'L450','Thinkpad',1299),(17,'W530 ','Thinkpad',1019),(18,'Yoga 14','Thinkpad',726);
/*!40000 ALTER TABLE `computer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer_display`
--

DROP TABLE IF EXISTS `computer_display`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computer_display` (
  `computer_ID` int(6) unsigned NOT NULL,
  `display_ID` int(6) unsigned NOT NULL,
  KEY `computerdisplay` (`computer_ID`),
  KEY `display` (`display_ID`),
  CONSTRAINT `computerdisplay` FOREIGN KEY (`computer_ID`) REFERENCES `computer` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `display` FOREIGN KEY (`display_ID`) REFERENCES `display` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer_display`
--

LOCK TABLES `computer_display` WRITE;
/*!40000 ALTER TABLE `computer_display` DISABLE KEYS */;
INSERT INTO `computer_display` VALUES (7,1),(8,2),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,3),(16,1),(17,1),(18,3);
/*!40000 ALTER TABLE `computer_display` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer_memory`
--

DROP TABLE IF EXISTS `computer_memory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computer_memory` (
  `computer_ID` int(6) unsigned NOT NULL,
  `memory_ID` int(6) unsigned NOT NULL,
  KEY `computermemory` (`computer_ID`),
  KEY `memory` (`memory_ID`),
  CONSTRAINT `computermemory` FOREIGN KEY (`computer_ID`) REFERENCES `computer` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `memory` FOREIGN KEY (`memory_ID`) REFERENCES `memory` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer_memory`
--

LOCK TABLES `computer_memory` WRITE;
/*!40000 ALTER TABLE `computer_memory` DISABLE KEYS */;
INSERT INTO `computer_memory` VALUES (7,1),(8,2),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,3),(16,1),(17,1),(18,3);
/*!40000 ALTER TABLE `computer_memory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer_processor`
--

DROP TABLE IF EXISTS `computer_processor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computer_processor` (
  `COMPUTER_ID` int(6) unsigned NOT NULL,
  `PROCESSOR_ID` int(6) unsigned NOT NULL,
  KEY `computer` (`COMPUTER_ID`),
  KEY `PROCESSOR` (`PROCESSOR_ID`),
  CONSTRAINT `PROCESSOR` FOREIGN KEY (`PROCESSOR_ID`) REFERENCES `processor` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `computer` FOREIGN KEY (`COMPUTER_ID`) REFERENCES `computer` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer_processor`
--

LOCK TABLES `computer_processor` WRITE;
/*!40000 ALTER TABLE `computer_processor` DISABLE KEYS */;
INSERT INTO `computer_processor` VALUES (7,1),(8,4),(9,2),(10,1),(11,1),(12,1),(13,1),(14,2),(15,1),(16,1),(17,4),(18,3);
/*!40000 ALTER TABLE `computer_processor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer_system`
--

DROP TABLE IF EXISTS `computer_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computer_system` (
  `computer_ID` int(6) unsigned NOT NULL,
  `system_ID` int(6) unsigned NOT NULL,
  KEY `computersystem` (`computer_ID`),
  KEY `system` (`system_ID`),
  CONSTRAINT `computersystem` FOREIGN KEY (`computer_ID`) REFERENCES `computer` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `system` FOREIGN KEY (`system_ID`) REFERENCES `system` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer_system`
--

LOCK TABLES `computer_system` WRITE;
/*!40000 ALTER TABLE `computer_system` DISABLE KEYS */;
INSERT INTO `computer_system` VALUES (7,1),(8,3),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,4);
/*!40000 ALTER TABLE `computer_system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computernumber`
--

DROP TABLE IF EXISTS `computernumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computernumber` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `computerId` int(6) NOT NULL,
  `number` int(6) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computernumber`
--

LOCK TABLES `computernumber` WRITE;
/*!40000 ALTER TABLE `computernumber` DISABLE KEYS */;
INSERT INTO `computernumber` VALUES (3,7,16),(4,9,18),(5,9,15),(6,9,13),(7,9,15),(8,12,20),(9,9,15),(10,10,16),(11,8,15),(12,10,17),(13,12,18),(14,10,12),(15,10,15),(16,18,13),(17,11,25),(18,13,30),(19,9,23),(20,10,35),(21,8,18),(22,14,15),(23,14,13),(24,18,12),(25,10,29),(26,18,11),(27,9,25),(28,10,16);
/*!40000 ALTER TABLE `computernumber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contactperson`
--

DROP TABLE IF EXISTS `contactperson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contactperson` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Birthday` date NOT NULL,
  `habit` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactperson`
--

LOCK TABLES `contactperson` WRITE;
/*!40000 ALTER TABLE `contactperson` DISABLE KEYS */;
/*!40000 ALTER TABLE `contactperson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_computer`
--

DROP TABLE IF EXISTS `customer_computer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_computer` (
  `customer_id` int(6) unsigned NOT NULL,
  `computer_id` int(6) unsigned NOT NULL,
  KEY `customer_id` (`customer_id`),
  KEY `computer_id` (`computer_id`),
  CONSTRAINT `customer_computer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `customer_computer_ibfk_2` FOREIGN KEY (`computer_id`) REFERENCES `computer` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_computer`
--

LOCK TABLES `customer_computer` WRITE;
/*!40000 ALTER TABLE `customer_computer` DISABLE KEYS */;
INSERT INTO `customer_computer` VALUES (3,7),(4,8),(5,9),(6,8),(7,8),(8,12),(9,10);
/*!40000 ALTER TABLE `customer_computer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_industry`
--

DROP TABLE IF EXISTS `customer_industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_industry` (
  `customer_ID` int(6) unsigned NOT NULL,
  `industry_ID` int(6) unsigned NOT NULL,
  KEY `customer_ID` (`customer_ID`),
  KEY `industry_ID` (`industry_ID`),
  CONSTRAINT `customer_industry_ibfk_1` FOREIGN KEY (`customer_ID`) REFERENCES `customers` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `customer_industry_ibfk_2` FOREIGN KEY (`industry_ID`) REFERENCES `industries` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_industry`
--

LOCK TABLES `customer_industry` WRITE;
/*!40000 ALTER TABLE `customer_industry` DISABLE KEYS */;
INSERT INTO `customer_industry` VALUES (3,8),(4,3),(5,3),(6,7),(7,3),(8,7),(9,4);
/*!40000 ALTER TABLE `customer_industry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_level`
--

DROP TABLE IF EXISTS `customer_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_level` (
  `customer_ID` int(6) unsigned NOT NULL,
  `level_ID` int(6) unsigned NOT NULL,
  KEY `customer_ID` (`customer_ID`),
  KEY `level_ID` (`level_ID`),
  CONSTRAINT `customer_level_ibfk_1` FOREIGN KEY (`customer_ID`) REFERENCES `customers` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `customer_level_ibfk_2` FOREIGN KEY (`level_ID`) REFERENCES `levels` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_level`
--

LOCK TABLES `customer_level` WRITE;
/*!40000 ALTER TABLE `customer_level` DISABLE KEYS */;
INSERT INTO `customer_level` VALUES (3,1),(4,3),(5,1),(6,1),(7,2),(8,3),(9,3);
/*!40000 ALTER TABLE `customer_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `customername` varchar(50) NOT NULL,
  `EMPLOYEE_NUM` int(6) unsigned NOT NULL,
  `LEADNAME` varchar(50) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `habit` varchar(100) DEFAULT NULL,
  `Telephone` varchar(25) DEFAULT NULL,
  `ordernumber` int(6) DEFAULT NULL,
  `targetprice` int(6) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (3,'Works Applications',227,'Liu Shuojun','BLK 506 ','2015-11-17','golf and bowling','93567821',1,873),(4,'ASM Technology Singapore',237,'Liu Wei','BLK 506 Choa chu Kang St 51','1984-02-08','coffee, golf','90374502',3,914),(5,'Siemens Singapore',800,' Allice','60 MacPherson Rd, Singapore 348615','1980-07-15',' bowling','6490 6000',2,789),(6,'Garena',317,'Grace','1 Fusionopolis Way, # 17-10','1989-06-15','Hiking ,Cycling,bowling ','93147520',1,812),(7,'Gemalto Singapore',158,'Maryon James','12 Ayer Rajah Crescent, 139941','1989-06-16','Hiking ,swimming and movie','6317 3333',2,856),(8,'Autodesk Asia Pte Ltd',360,'Aliex Rorrence','3 Fusionopolis Way, #10-21','1988-07-14','Go hinking ,swimming and coffee ','65281596',0,956),(9,'Micron Semiconductor ',800,'Alice Bush',' 1 Woodlands Industrial Park D Street 1','1988-10-19','coffee,dancing','65248712',0,912);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `display`
--

DROP TABLE IF EXISTS `display`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `display` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(50) NOT NULL,
  `PRICE` int(6) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `display`
--

LOCK TABLES `display` WRITE;
/*!40000 ALTER TABLE `display` DISABLE KEYS */;
INSERT INTO `display` VALUES (1,'14.0\" HD (1366 x 768), Anti-Glare',180),(2,'14.0\" HD (1366 x 768), Anti-Glare, with WWAN',190),(3,'14.0\"FHD (1920 x 1080), IPS, AntiGlare',250);
/*!40000 ALTER TABLE `display` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `industries`
--

DROP TABLE IF EXISTS `industries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `industries` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `industryname` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `industries`
--

LOCK TABLES `industries` WRITE;
/*!40000 ALTER TABLE `industries` DISABLE KEYS */;
INSERT INTO `industries` VALUES (1,'Agriculture and Fishing'),(2,'Mining and Quarrying'),(3,'Manufacturing'),(4,'Electricity, Gas and Steam'),(5,'Construction'),(6,'Wholesale and Retail Trade'),(7,'Information and Communications'),(8,'Financial and Insurance Activities'),(9,'Real Estate Activities'),(10,'Professional, Scientific and Technical Activities'),(11,'Education'),(12,'Public Administration and Defence'),(13,'Health and Social Services');
/*!40000 ALTER TABLE `industries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `levels`
--

DROP TABLE IF EXISTS `levels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `levels` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `levelname` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `levels`
--

LOCK TABLES `levels` WRITE;
/*!40000 ALTER TABLE `levels` DISABLE KEYS */;
INSERT INTO `levels` VALUES (1,'Very important'),(2,'Important'),(3,'Common');
/*!40000 ALTER TABLE `levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memory`
--

DROP TABLE IF EXISTS `memory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `memory` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(50) NOT NULL,
  `PRICE` int(6) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memory`
--

LOCK TABLES `memory` WRITE;
/*!40000 ALTER TABLE `memory` DISABLE KEYS */;
INSERT INTO `memory` VALUES (1,'2GB PC3-12800 DDR3L SDRAM 1600MHz SODIMM',100),(2,'4GB PC3-12800 DDR3L SDRAM 1600MHz SODIMM',140),(3,'8GB PC3-12800 DDR3L (2 DIMM)',180);
/*!40000 ALTER TABLE `memory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `office`
--

DROP TABLE IF EXISTS `office`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `office` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `office`
--

LOCK TABLES `office` WRITE;
/*!40000 ALTER TABLE `office` DISABLE KEYS */;
INSERT INTO `office` VALUES (1,'Singapore'),(2,'Tokyo'),(3,'Shanghai'),(4,'Osaka');
/*!40000 ALTER TABLE `office` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_computer`
--

DROP TABLE IF EXISTS `order_computer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_computer` (
  `order_id` int(6) unsigned NOT NULL,
  `computer_id` int(6) unsigned NOT NULL,
  KEY `order_id` (`order_id`),
  KEY `computer_id` (`computer_id`),
  CONSTRAINT `order_computer_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_computer_ibfk_2` FOREIGN KEY (`computer_id`) REFERENCES `computer` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_computer`
--

LOCK TABLES `order_computer` WRITE;
/*!40000 ALTER TABLE `order_computer` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_computer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_computernumber`
--

DROP TABLE IF EXISTS `order_computernumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_computernumber` (
  `order_id` int(6) unsigned NOT NULL,
  `computernumber_id` int(6) unsigned NOT NULL,
  KEY `order_id` (`order_id`),
  KEY `computernumber_id` (`computernumber_id`),
  CONSTRAINT `order_computernumber_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_computernumber_ibfk_2` FOREIGN KEY (`computernumber_id`) REFERENCES `computernumber` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_computernumber`
--

LOCK TABLES `order_computernumber` WRITE;
/*!40000 ALTER TABLE `order_computernumber` DISABLE KEYS */;
INSERT INTO `order_computernumber` VALUES (35,5),(35,6),(39,13),(39,14),(34,3),(34,4),(37,9),(37,10),(44,23),(44,24),(43,21),(43,22),(45,25),(45,26),(38,11),(38,12);
/*!40000 ALTER TABLE `order_computernumber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_customer`
--

DROP TABLE IF EXISTS `order_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_customer` (
  `order_id` int(6) unsigned NOT NULL,
  `customer_id` int(6) unsigned NOT NULL,
  KEY `order_id` (`order_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `order_customer_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_customer_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_customer`
--

LOCK TABLES `order_customer` WRITE;
/*!40000 ALTER TABLE `order_customer` DISABLE KEYS */;
INSERT INTO `order_customer` VALUES (34,4),(35,3),(37,4),(38,7),(39,7),(43,5),(44,6),(45,4),(47,3);
/*!40000 ALTER TABLE `order_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_status` (
  `order_id` int(6) unsigned NOT NULL,
  `status_id` int(6) unsigned NOT NULL,
  KEY `order_id` (`order_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `order_status_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_status_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `status` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (34,5),(35,5),(37,4),(38,4),(39,5),(43,5),(44,1),(45,5),(47,1);
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_user`
--

DROP TABLE IF EXISTS `order_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_user` (
  `order_id` int(6) unsigned NOT NULL,
  `user_id` int(6) unsigned NOT NULL,
  KEY `order_id` (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `order_user_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_user`
--

LOCK TABLES `order_user` WRITE;
/*!40000 ALTER TABLE `order_user` DISABLE KEYS */;
INSERT INTO `order_user` VALUES (35,6),(39,8),(34,8),(37,8),(38,8),(43,8),(45,6);
/*!40000 ALTER TABLE `order_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `builddate` date DEFAULT NULL,
  `price` int(6) DEFAULT NULL,
  `reason` varchar(300) DEFAULT NULL,
  `number` int(6) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (34,'2015-12-10',25214,'',0),(35,'2015-12-08',24836,'',0),(37,'2015-12-08',22521,'',0),(38,'2015-12-07',20187,'',0),(39,'2015-12-07',19926,'',0),(43,'2015-12-16',27429,'',0),(44,'2015-12-16',21673,'',0),(45,'2015-12-16',24690,'',0),(47,'2015-12-16',0,'',0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `PERMISSIONNAME` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PERMISSIONNAME` (`PERMISSIONNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (17,'CTRL_PERM_ADD_POST'),(20,'CTRL_PERM_DELETE_GET'),(18,'CTRL_PERM_EDIT_GET'),(19,'CTRL_PERM_EDIT_POST'),(16,'CTRL_PERM_LIST_GET'),(12,'CTRL_ROLE_ADD_POST'),(15,'CTRL_ROLE_DELETE_GET'),(13,'CTRL_ROLE_EDIT_GET'),(14,'CTRL_ROLE_EDIT_POST'),(11,'CTRL_ROLE_LIST_GET'),(2,'CTRL_STRATEGY_ADD_POST'),(5,'CTRL_STRATEGY_DELETE_GET'),(3,'CTRL_STRATEGY_EDIT_GET'),(4,'CTRL_STRATEGY_EDIT_POST'),(1,'CTRL_STRATEGY_LIST_GET'),(7,'CTRL_USER_ADD_POST'),(10,'CTRL_USER_DELETE_GET'),(8,'CTRL_USER_EDIT_GET'),(9,'CTRL_USER_EDIT_POST'),(6,'CTRL_USER_LIST_GET');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problem_type`
--

DROP TABLE IF EXISTS `problem_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `problem_type` (
  `problem_ID` int(6) unsigned NOT NULL,
  `type_ID` int(6) unsigned NOT NULL,
  KEY `problem_ID` (`problem_ID`),
  KEY `type_ID` (`type_ID`),
  CONSTRAINT `problem_type_ibfk_1` FOREIGN KEY (`problem_ID`) REFERENCES `problems` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `problem_type_ibfk_2` FOREIGN KEY (`type_ID`) REFERENCES `types` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem_type`
--

LOCK TABLES `problem_type` WRITE;
/*!40000 ALTER TABLE `problem_type` DISABLE KEYS */;
INSERT INTO `problem_type` VALUES (2,1),(3,1),(4,1),(5,2),(6,1);
/*!40000 ALTER TABLE `problem_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problems`
--

DROP TABLE IF EXISTS `problems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `problems` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(500) NOT NULL,
  `solution` varchar(500) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problems`
--

LOCK TABLES `problems` WRITE;
/*!40000 ALTER TABLE `problems` DISABLE KEYS */;
INSERT INTO `problems` VALUES (2,'Your computer shuts down before the battery status indicator shows empty, -or- Your computer operates after the battery status indicator shows empty. ','Discharge and recharge the battery pack.'),(3,' The operating time for a fully charged battery pack is short.','Discharge and recharge the battery pack. If your battery\'s operating time is still short, use a new battery pack.'),(4,'The computer does not operate with a fully charged battery pack.','The surge protector in the battery pack might be active. Turn off the computer for one minute to reset the protector; then turn on the computer again.'),(5,'The battery pack cannot be charged.',' You cannot charge the battery when it is too hot. If the battery feels hot, remove it from the computer and allow it to cool to room temperature. After it cools, reinstall it and recharge the battery. If it still cannot be charged, have it serviced'),(6,'dgsd','dshsdfh');
/*!40000 ALTER TABLE `problems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processor`
--

DROP TABLE IF EXISTS `processor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processor` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(100) NOT NULL,
  `PRICE` int(6) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processor`
--

LOCK TABLES `processor` WRITE;
/*!40000 ALTER TABLE `processor` DISABLE KEYS */;
INSERT INTO `processor` VALUES (1,'Intel Core i3-5005U Processor (3MB cache, 2.0GHz) Onboard',88),(2,'Intel Core i5-5200U Processor (3MB Cache, up to 2.70GHz)',167),(3,'Intel Core i5-5300U Processor (3MB Cache, up to 2.90GHz)',280),(4,'Intel Core i7-5500U Processor (4MB Cache, up to 3.00GHz)',426);
/*!40000 ALTER TABLE `processor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL,
  `solution` varchar(500) DEFAULT NULL,
  `orderId` int(6) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (1,'dddd','dddd',21),(2,'The customer want to buy T440s','',21),(3,'The customer want to buy T450s','',20),(4,'The customers are not interested in our computers','',20),(5,'They want lower price for the computers','',19),(6,'The customer want to buy computer as soon as possible ','',20),(7,'name is good',NULL,21),(8,'just to test the report id',NULL,21),(9,'The report id would be shown in the calendar','just to check the feedback in the ordermenu and update the information',21),(10,'Plan to meet the customer tomorrow and the customer seems very interested about our products','Try to do your best, please remember to bring the documents before you go to the customer side.',22),(11,'The customer want to buy another products','The customer is a big customer and try to satisfied all the requirement of the customer.If they buy more than 100 ,you can give them 10% off',26),(12,'meet ','hfhfaslhfaslkhfakshfaf',27),(13,'The customer is not satified',NULL,27),(14,'The customer think the price is too high and they want a discount for the computer','You can give the customer 10% off for this order',30),(15,'hjkojioiujuioi',NULL,34),(16,'The customer want 10% off for the order, can i appprove??',NULL,45);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permissions`
--

DROP TABLE IF EXISTS `role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permissions` (
  `ROLE_ID` int(6) unsigned NOT NULL,
  `PERMISSION_ID` int(6) unsigned NOT NULL,
  KEY `ROLE_ID` (`ROLE_ID`),
  KEY `PERMISSION_ID` (`PERMISSION_ID`),
  CONSTRAINT `role_permissions_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_permissions_ibfk_2` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `permissions` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permissions`
--

LOCK TABLES `role_permissions` WRITE;
/*!40000 ALTER TABLE `role_permissions` DISABLE KEYS */;
INSERT INTO `role_permissions` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20);
/*!40000 ALTER TABLE `role_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `ROLENAME` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ROLENAME` (`ROLENAME`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(4,'ROLE_ASSISTANT'),(2,'ROLE_SALE'),(3,'ROLE_SERVICE');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'waiting for approval'),(2,'reject'),(3,'proceeding'),(4,'fail'),(5,'success');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strategy`
--

DROP TABLE IF EXISTS `strategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strategy` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(50) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategy`
--

LOCK TABLES `strategy` WRITE;
/*!40000 ALTER TABLE `strategy` DISABLE KEYS */;
INSERT INTO `strategy` VALUES (1,'Iron Condor','High Prob ICxxs'),(2,'Iron Condor','Low Prob IC'),(3,'Iron Condor','High Prob Hedged ICe'),(4,'Butterfly','Bullish Leaning Butter'),(5,'Calendar','Double Cal'),(6,'Double Diagonal','Bullish'),(7,'123','shuojun'),(8,'dbqwkjhdjqk','wsfdqwfqw');
/*!40000 ALTER TABLE `strategy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system`
--

DROP TABLE IF EXISTS `system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(50) NOT NULL,
  `PRICE` int(6) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system`
--

LOCK TABLES `system` WRITE;
/*!40000 ALTER TABLE `system` DISABLE KEYS */;
INSERT INTO `system` VALUES (1,'Windows 8.1 64',16),(2,'Windows 10 Home 64',16),(3,'Windows 7 Professional 32',16),(4,'Windows 7 Professional 64',16),(5,'Windows 8.1 Pro 64',16),(6,'Windows 10 Pro 64',16);
/*!40000 ALTER TABLE `system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `target_user`
--

DROP TABLE IF EXISTS `target_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `target_user` (
  `target_id` int(6) unsigned NOT NULL,
  `user_id` int(6) unsigned NOT NULL,
  KEY `target_id` (`target_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `target_user_ibfk_1` FOREIGN KEY (`target_id`) REFERENCES `targets` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `target_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `target_user`
--

LOCK TABLES `target_user` WRITE;
/*!40000 ALTER TABLE `target_user` DISABLE KEYS */;
INSERT INTO `target_user` VALUES (26,5),(27,7),(28,7),(29,5),(30,5),(31,8),(32,5),(33,5),(34,6),(35,5),(36,5),(37,5),(38,5),(39,7),(40,6),(41,5),(42,6),(43,6),(44,6),(45,8),(46,7),(47,5),(48,8),(49,5),(50,5),(51,5),(52,5),(53,5),(54,5),(55,5),(56,8),(57,5),(58,8),(59,8),(60,6),(61,8),(62,5),(63,5),(64,5),(65,5),(66,5);
/*!40000 ALTER TABLE `target_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `targets`
--

DROP TABLE IF EXISTS `targets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `targets` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime DEFAULT NULL,
  `feedback` varchar(500) DEFAULT NULL,
  `details` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `targets`
--

LOCK TABLES `targets` WRITE;
/*!40000 ALTER TABLE `targets` DISABLE KEYS */;
INSERT INTO `targets` VALUES (26,'order Id 21 has new progress report','2015-11-25 14:16:00',NULL,'just to check the feedback in the ordermenu and update the information','Report 9 : The report id would be shown in the calendar'),(27,'Meeting to talk about order 21 in Meeting room 4','2015-11-27 13:00:00','2015-11-27 14:00:00','','Please prepare the document before the meeting'),(28,'order Id 22 start to follow','2015-11-25 16:20:07',NULL,NULL,'please start to follow order 22 for customer Works Applications'),(29,'order Id 22 has new progress report','2015-11-25 16:23:00',NULL,'Try to do your best, please remember to bring the documents before you go to the customer side.','Report 10 : Plan to meet the customer tomorrow and the customer seems very interested about our products'),(30,'order Id 22failed','2015-11-25 16:29:00',NULL,'','The order Id 22 for customer Works Applications is failed'),(31,'order Id 26 start to follow','2015-11-25 16:56:36',NULL,NULL,'please start to follow order 26 for customer Gemalto Singapore'),(32,'order Id 26 has new progress report','2015-11-25 16:59:00',NULL,'The customer is a big customer and try to satisfied all the requirement of the customer.If they buy more than 100 ,you can give them 10% off','Report 11 : The customer want to buy another products'),(33,'order Id 26 succeed','2015-11-25 17:02:00',NULL,'','The order Id 26 for customer Gemalto Singapore is successful'),(34,'order Id 27 start to follow','2015-11-25 17:36:13',NULL,NULL,'please start to follow order 27 for customer Gemalto Singapore'),(35,'order Id 27 has new progress report','2015-11-25 17:37:00',NULL,'hfhfaslhfaslkhfakshfaf','Report 12 : meet '),(36,'order Id 27 failed','2015-11-25 17:38:10',NULL,NULL,'The order Id 27 for customer Gemalto Singapore is failed'),(37,'order Id 20 succeed','2015-11-25 17:59:33',NULL,NULL,'The order Id 20 for customer Works Applications is successful'),(38,'order Id 27 has new progress report','2015-11-26 13:07:40',NULL,NULL,'Report 13 : The customer is not satified'),(39,'order Id 29 start to follow','2015-11-26 13:15:33',NULL,NULL,'please start to follow order 29 for customer Garena'),(40,'order Id 30 start to follow','2015-11-26 13:42:27',NULL,NULL,'please start to follow order 30 for customer Siemens Singapore'),(41,'order Id 30 has new progress report','2015-11-26 14:07:00',NULL,'You can give the customer 10% off for this order','Report 14 : The customer think the price is too high and they want a discount for the computer'),(42,'Meeting customer at one north MRT','2015-11-27 13:00:00','2015-11-27 14:00:00',NULL,'Bring the document '),(43,'order Id 35 start to follow','2015-12-07 09:44:36',NULL,NULL,'please start to follow order 35 for customer Works Applications'),(44,'order Id 36 start to follow','2015-12-07 09:47:24',NULL,NULL,'please start to follow order 36 for customer Autodesk Asia Pte Ltd'),(45,'order Id 39 start to follow','2015-12-07 14:27:31',NULL,NULL,'please start to follow order 39 for customer Gemalto Singapore'),(46,'order Id 40 start to follow','2015-12-08 09:34:35',NULL,NULL,'please start to follow order 40 for customer Autodesk Asia Pte Ltd'),(47,'order Id 40 succeed','2015-12-08 09:35:06',NULL,NULL,'The order Id 40 for customer Autodesk Asia Pte Ltd is successful'),(48,'order Id 34 start to follow','2015-12-08 09:50:15',NULL,NULL,'please start to follow order 34 for customer ASM Technology Singapore'),(49,'order Id 35 succeed','2015-12-08 09:50:56',NULL,NULL,'The order Id 35 for customer Works Applications is successful'),(50,'order Id 36 succeed','2015-12-08 09:52:43',NULL,NULL,'The order Id 36 for customer Autodesk Asia Pte Ltd is successful'),(51,'order Id 39 succeed','2015-12-08 09:52:51',NULL,NULL,'The order Id 39 for customer Gemalto Singapore is successful'),(52,'order Id 40 succeed','2015-12-08 09:52:56',NULL,NULL,'The order Id 40 for customer Autodesk Asia Pte Ltd is successful'),(53,'order Id 34 succeed','2015-12-08 09:52:59',NULL,NULL,'The order Id 34 for customer ASM Technology Singapore is successful'),(54,'order Id 34 succeed','2015-12-08 09:53:06',NULL,NULL,'The order Id 34 for customer ASM Technology Singapore is successful'),(55,'order Id 34 succeed','2015-12-08 09:53:14',NULL,NULL,'The order Id 34 for customer ASM Technology Singapore is successful'),(56,'order Id 37 start to follow','2015-12-08 10:01:20',NULL,NULL,'please start to follow order 37 for customer ASM Technology Singapore'),(57,'order Id 37 failed','2015-12-08 10:01:45',NULL,NULL,'The order Id 37 for customer ASM Technology Singapore is failed'),(58,'order Id 38 start to follow','2015-12-08 10:05:06',NULL,NULL,'please start to follow order 38 for customer Gemalto Singapore'),(59,'order Id 43 start to follow','2015-12-08 10:05:11',NULL,NULL,'please start to follow order 43 for customer Siemens Singapore'),(60,'order Id 45 start to follow','2015-12-08 10:05:18',NULL,NULL,'please start to follow order 45 for customer ASM Technology Singapore'),(61,'order Id 46 start to follow','2015-12-08 10:05:27',NULL,NULL,'please start to follow order 46 for customer Siemens Singapore'),(62,'order Id 43 succeed','2015-12-08 10:06:04',NULL,NULL,'The order Id 43 for customer Siemens Singapore is successful'),(63,'order Id 45 succeed','2015-12-08 10:06:17',NULL,NULL,'The order Id 45 for customer ASM Technology Singapore is successful'),(64,'order Id 38 failed','2015-12-08 10:06:24',NULL,NULL,'The order Id 38 for customer Gemalto Singapore is failed'),(65,'order Id 34 has new progress report','2015-12-15 19:44:58',NULL,NULL,'Report 15 : hjkojioiujuioi'),(66,'order Id 45 has new progress report','2015-12-15 19:54:13',NULL,NULL,'Report 16 : The customer want 10% off for the order, can i appprove??');
/*!40000 ALTER TABLE `targets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `types` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `types`
--

LOCK TABLES `types` WRITE;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` VALUES (1,'battery issues'),(2,'Bluetooth issues');
/*!40000 ALTER TABLE `types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `USER_ID` int(6) unsigned NOT NULL,
  `ROLE_ID` int(6) unsigned NOT NULL,
  KEY `USER` (`USER_ID`),
  KEY `ROLE` (`ROLE_ID`),
  CONSTRAINT `ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `USER` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (3,3),(4,4),(5,1),(6,2),(7,2),(8,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `ID` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'servicestaff','password',1),(4,'assistant','password',1),(5,'manager','password',1),(6,'Salestaff-1','password',1),(7,'Salestaff-2','password',1),(8,'Salestaff-3','password',1);
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

-- Dump completed on 2017-04-10 12:05:44
