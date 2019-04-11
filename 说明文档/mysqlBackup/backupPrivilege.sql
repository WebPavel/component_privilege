-- MySQL dump 10.13  Distrib 5.5.61, for Win64 (AMD64)
--
-- Host: localhost    Database: privilege
-- ------------------------------------------------------
-- Server version	5.5.61

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
-- Table structure for table `biz_privilege`
--

DROP TABLE IF EXISTS `biz_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biz_privilege` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `visitedPath` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gmtCreate` datetime DEFAULT NULL,
  `gmtModified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biz_privilege`
--

LOCK TABLES `biz_privilege` WRITE;
/*!40000 ALTER TABLE `biz_privilege` DISABLE KEYS */;
INSERT INTO `biz_privilege` VALUES (1,'添加商品','/goods/add_goods.jsp',NULL,'2019-04-11 00:29:26','2019-04-11 00:29:26'),(2,'删除商品','/goods/delete_goods.jsp','只有管理员能删除商品','2019-04-11 00:32:01','2019-04-11 00:32:01'),(3,'加入购物车','/biz/goods/add_cart.jsp','添加商品到购物车','2019-04-12 02:04:23','2019-04-12 02:04:23');
/*!40000 ALTER TABLE `biz_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biz_role`
--

DROP TABLE IF EXISTS `biz_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biz_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gmtCreate` datetime DEFAULT NULL,
  `gmtModified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biz_role`
--

LOCK TABLES `biz_role` WRITE;
/*!40000 ALTER TABLE `biz_role` DISABLE KEYS */;
INSERT INTO `biz_role` VALUES (1,'普通用户','区别于管理员','2019-04-11 01:19:25','2019-04-11 01:19:25'),(2,'管理员','区别于超级管理员','2019-04-11 01:20:05','2019-04-11 01:20:05'),(3,'超级管理员','区别于普通管理员','2019-04-11 01:20:28','2019-04-11 01:20:28'),(4,'VIP','VIP享有特权','2019-04-12 02:02:53','2019-04-12 02:02:53');
/*!40000 ALTER TABLE `biz_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biz_role_privilege`
--

DROP TABLE IF EXISTS `biz_role_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biz_role_privilege` (
  `roleId` bigint(20) unsigned NOT NULL,
  `privilegeId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`roleId`,`privilegeId`),
  KEY `privilegeId` (`privilegeId`),
  CONSTRAINT `biz_role_privilege_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `biz_role` (`id`),
  CONSTRAINT `biz_role_privilege_ibfk_2` FOREIGN KEY (`privilegeId`) REFERENCES `biz_privilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biz_role_privilege`
--

LOCK TABLES `biz_role_privilege` WRITE;
/*!40000 ALTER TABLE `biz_role_privilege` DISABLE KEYS */;
INSERT INTO `biz_role_privilege` VALUES (2,1),(3,1),(3,2);
/*!40000 ALTER TABLE `biz_role_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biz_user_role`
--

DROP TABLE IF EXISTS `biz_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biz_user_role` (
  `userId` bigint(20) unsigned NOT NULL,
  `roleId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `biz_user_role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `usr_user` (`id`),
  CONSTRAINT `biz_user_role_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `biz_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biz_user_role`
--

LOCK TABLES `biz_user_role` WRITE;
/*!40000 ALTER TABLE `biz_user_role` DISABLE KEYS */;
INSERT INTO `biz_user_role` VALUES (1,1),(2,1),(3,1),(4,1),(2,2),(3,2),(3,3);
/*!40000 ALTER TABLE `biz_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usr_user`
--

DROP TABLE IF EXISTS `usr_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usr_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `gmtCreate` datetime DEFAULT NULL,
  `gmtModified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usr_user`
--

LOCK TABLES `usr_user` WRITE;
/*!40000 ALTER TABLE `usr_user` DISABLE KEYS */;
INSERT INTO `usr_user` VALUES (1,'tom','e10adc3949ba59abbe56e057f20f883e','Tomcat','2019-04-11 01:52:17','2019-04-11 01:52:17'),(2,'cat','e10adc3949ba59abbe56e057f20f883e','Tomcat','2019-04-11 01:52:27','2019-04-11 01:52:27'),(3,'admin','e10adc3949ba59abbe56e057f20f883e','Admin','2019-04-11 01:52:40','2019-04-11 01:52:40'),(4,'lily','e10adc3949ba59abbe56e057f20f883e','Lily','2019-04-11 01:58:15','2019-04-11 01:58:15'),(5,'paulluis','e10adc3949ba59abbe56e057f20f883e','pl','2019-04-12 02:05:07','2019-04-12 02:05:07');
/*!40000 ALTER TABLE `usr_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-12  2:22:49
