-- MySQL dump 10.13  Distrib 8.1.0, for Win64 (x86_64)
--
-- Host: localhost    Database: compilecoursedb
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `academic_term`
--

DROP TABLE IF EXISTS `academic_term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `academic_term` (
  `id` int NOT NULL AUTO_INCREMENT,
  `year_tearm` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_term`
--

LOCK TABLES `academic_term` WRITE;
/*!40000 ALTER TABLE `academic_term` DISABLE KEYS */;
/*!40000 ALTER TABLE `academic_term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(45) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `day_post` datetime DEFAULT NULL,
  `user_id` int NOT NULL,
  `outline_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_user1_idx` (`user_id`),
  KEY `fk_comment_outline1_idx` (`outline_id`),
  CONSTRAINT `fk_comment_outline1` FOREIGN KEY (`outline_id`) REFERENCES `outline` (`id`),
  CONSTRAINT `fk_comment_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation_method`
--

DROP TABLE IF EXISTS `evaluation_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluation_method` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation_method`
--

LOCK TABLES `evaluation_method` WRITE;
/*!40000 ALTER TABLE `evaluation_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluation_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outline`
--

DROP TABLE IF EXISTS `outline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outline` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `language` varchar(45) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `teching_method` varchar(45) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `knowledge` varchar(45) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `credit` float DEFAULT NULL,
  `policy` varchar(45) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `user_id` int NOT NULL,
  `academic_term_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_outline_user_idx` (`user_id`),
  KEY `fk_outline_academic_term1_idx` (`academic_term_id`),
  KEY `fk_outline_course1_idx` (`course_id`),
  CONSTRAINT `fk_outline_academic_term1` FOREIGN KEY (`academic_term_id`) REFERENCES `academic_term` (`id`),
  CONSTRAINT `fk_outline_course1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `fk_outline_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outline`
--

LOCK TABLES `outline` WRITE;
/*!40000 ALTER TABLE `outline` DISABLE KEYS */;
/*!40000 ALTER TABLE `outline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outline_method`
--

DROP TABLE IF EXISTS `outline_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outline_method` (
  `id` int NOT NULL AUTO_INCREMENT,
  `weight` double DEFAULT NULL,
  `outline_id` int NOT NULL,
  `evaluation_method_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_outline_method_outline1_idx` (`outline_id`),
  KEY `fk_outline_method_evaluation_method1_idx` (`evaluation_method_id`),
  CONSTRAINT `fk_outline_method_evaluation_method1` FOREIGN KEY (`evaluation_method_id`) REFERENCES `evaluation_method` (`id`),
  CONSTRAINT `fk_outline_method_outline1` FOREIGN KEY (`outline_id`) REFERENCES `outline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outline_method`
--

LOCK TABLES `outline_method` WRITE;
/*!40000 ALTER TABLE `outline_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `outline_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `last_name` varchar(45) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `email` varchar(45) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `phone` varchar(45) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `username` varchar(45) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `password` varchar(100) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `avater` varchar(100) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `user_role` varchar(10) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-04 10:10:28
