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
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_term`
--

LOCK TABLES `academic_term` WRITE;
/*!40000 ALTER TABLE `academic_term` DISABLE KEYS */;
INSERT INTO `academic_term` VALUES (1,'2021'),(2,'2022'),(3,'2023'),(4,'2024');
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
  `content` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `day_post` datetime DEFAULT NULL,
  `user_id` int NOT NULL,
  `outline_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_user1_idx` (`user_id`),
  KEY `fk_comment_outline1_idx` (`outline_id`),
  CONSTRAINT `fk_comment_outline1` FOREIGN KEY (`outline_id`) REFERENCES `outline` (`id`),
  CONSTRAINT `fk_comment_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'Great course!','2024-01-15 10:30:00',1,1),(2,'Very helpful.','2024-02-20 11:45:00',2,2),(3,'Needs more examples.','2024-03-12 09:00:00',3,3);
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
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `faculty_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_course_faculty_idx` (`faculty_id`),
  CONSTRAINT `fk_course_faculty` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Kỹ Thuật Lập Trình',1),(2,'Hệ Điều Hành',1),(3,'Hướng Đối Tượng',1),(4,'Ứng Dụng Web',1);
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
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation_method`
--

LOCK TABLES `evaluation_method` WRITE;
/*!40000 ALTER TABLE `evaluation_method` DISABLE KEYS */;
INSERT INTO `evaluation_method` VALUES (1,'Đánh giá quá trình'),(2,'Đánh giá giữa kỳ'),(3,'Đánh giá cuối kỳ');
/*!40000 ALTER TABLE `evaluation_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `dean` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Công nghệ thông tin','Trương HV'),(2,'Tài chính','Nguyễn Văn A'),(3,'Xã Hội','Lê Thị B');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outline`
--

DROP TABLE IF EXISTS `outline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outline` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `language` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `teching_method` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `knowledge` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `credit` float DEFAULT NULL,
  `policy` text CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci,
  `objectives` text CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_outline_user_idx` (`user_id`),
  KEY `fk_outline_course1_idx` (`course_id`),
  CONSTRAINT `fk_outline_course1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `fk_outline_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outline`
--

LOCK TABLES `outline` WRITE;
/*!40000 ALTER TABLE `outline` DISABLE KEYS */;
INSERT INTO `outline` VALUES (1,'2024-01-01 00:00:00','Vietnamese','Trực tiếp','Kiến thức ngành',3,'- Sinh viên tham gia đầy đủ các buổi học lý thuyết và thực hành.\n- Sinh viên tham gia đầy đủ các hoạt động học tập trên hệ thống LMS theo yêu cầu của GV.','- Nhận thức được tầm quan trọng của môn học.\n- Có khả năng tự học, tự trao dồi kiến thức.\n- Yêu thích các môn học lập trình và thích khám phá những bài toán khó.','Môn Kỹ Thuật Lập Trình trang bị cho sinh viên một số kiến thức tiếp theo của lập trình cấu trúc mà chưa được đề cập trong môn Cơ sở lập trình. Nội dung môn học bao gồm: mảng nhiều chiều, đệ qui, con trỏ, chuỗi ký tự, các kiểu dữ liệu tự tạo và các thao tác với tập tin. Ngôn ngữ lập trình được dùng để minh hoạ là C++.',13,1),(2,'2024-01-02 00:00:00','English','Kết hợp','Kiến thức ngành',4,'Policy 2','Objectives 2','Description 2',11,2),(3,'2024-01-03 00:00:00','French','Trực tuyến','Kiến thức ngành',2,'Policy 3','Objectives 3','Description 3',12,3);
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
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_outline_method_outline1_idx` (`outline_id`),
  KEY `fk_outline_method_evaluation_method1_idx` (`evaluation_method_id`),
  CONSTRAINT `fk_outline_method_evaluation_method1` FOREIGN KEY (`evaluation_method_id`) REFERENCES `evaluation_method` (`id`),
  CONSTRAINT `fk_outline_method_outline1` FOREIGN KEY (`outline_id`) REFERENCES `outline` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outline_method`
--

LOCK TABLES `outline_method` WRITE;
/*!40000 ALTER TABLE `outline_method` DISABLE KEYS */;
INSERT INTO `outline_method` VALUES (1,20,1,1,'Điểm danh buổi học'),(2,30,1,2,'Kiểm tra trên máy'),(3,50,1,3,'Kiểm tra tự luận');
/*!40000 ALTER TABLE `outline_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outline_term`
--

DROP TABLE IF EXISTS `outline_term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outline_term` (
  `id` int NOT NULL AUTO_INCREMENT,
  `outline_id` int DEFAULT NULL,
  `academic_id` int DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_outline_detail_idx` (`outline_id`),
  KEY `fk_term_detail_idx` (`academic_id`),
  CONSTRAINT `fk_outline_detail` FOREIGN KEY (`outline_id`) REFERENCES `outline` (`id`),
  CONSTRAINT `fk_term_detail` FOREIGN KEY (`academic_id`) REFERENCES `academic_term` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outline_term`
--

LOCK TABLES `outline_term` WRITE;
/*!40000 ALTER TABLE `outline_term` DISABLE KEYS */;
INSERT INTO `outline_term` VALUES (1,1,1,'2024-01-01 00:00:00'),(2,2,2,'2024-01-02 00:00:00'),(3,3,3,'2024-01-03 00:00:00');
/*!40000 ALTER TABLE `outline_term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `last_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `user_role` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Hao','Phan Van','nva@example.com','0123456789','nva','password1',_binary '','avatar1.png','ROLE_student'),(2,'Huy','Truong Van','ttb@example.com','0987654321','ttb','password2',_binary '','avatar2.png','ROLE_teacher'),(3,'Binh','Do Thanh','lmc@example.com','0111222333','lmc','password3',_binary '','avatar3.png','ROLE_admin'),(10,'dat','do thanh','abc@gmail.com','1234567890','dtd','$2a$10$6VVKXKOa3yy0fY3XFjSXqu1lIHXA5UvSZbtF6VXZGAPgtBaBKrDYq',_binary '','https://res.cloudinary.com/dn0kj5rfm/image/upload/v1718897574/aj4zia4w4djjauln7hvn.jpg','ROLE_student'),(11,'Hao','Phan Van','pvh@gmail.com','1112223330','pvh','$2a$10$himyLuuEctzHOkSr03BVzuZMARe3unnf1P0mYA4Uf4Vj51bQh8ACi',_binary '','https://res.cloudinary.com/dn0kj5rfm/image/upload/v1718897838/rk3iwzlymvmjnncbcamc.png','ROLE_teacher'),(12,'Huy','Truong Van','tvh@gmail.com','9998887770','tvh','$2a$10$zZwpcWovt2nzQprdLpNHp.HQ4jbecuojQ7ogHhR0jyZiGV9hHfycO',_binary '','https://res.cloudinary.com/dn0kj5rfm/image/upload/v1718897887/ptmnbn7je4kyxv3gtzwz.png','ROLE_admin'),(13,'Binh','Do Thanh','dtb@gmail.com','4445556660','dtb','$2a$10$Ig7TYO74YKeYWZLGBxOi3OQFmlSMOAwkR0wS6cUW7wZLdFXrZdJWG',_binary '','https://res.cloudinary.com/dn0kj5rfm/image/upload/v1718899844/zt6ezl9k09pafspep0m9.png','ROLE_student'),(14,'Modric','kroos','123@gmail.com','4445556660','test123','$2a$10$.sutotebOyFaq1/3a/vj9.hldTpPcT1enhQkAovzqqsWsQNXZxUZ2',_binary '','https://res.cloudinary.com/dn0kj5rfm/image/upload/v1718900653/wjg1jxt89ekfgghiuijf.jpg','ROLE_student');
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

-- Dump completed on 2024-06-21 12:00:28
