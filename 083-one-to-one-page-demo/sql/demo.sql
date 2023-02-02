-- MySQL dump 10.13  Distrib 8.0.26, for macos11.3 (x86_64)
--
-- Host: localhost    Database: test28
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_dept`
--

DROP TABLE IF EXISTS `tb_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID（全局唯一）',
  `dept_name` varchar(30) DEFAULT NULL COMMENT '部门名称',
  `staff` int DEFAULT NULL COMMENT '员工',
  `tel` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除（0:未删除；1:已删除）',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COMMENT='部门';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_dept`
--

LOCK TABLES `tb_dept` WRITE;
/*!40000 ALTER TABLE `tb_dept` DISABLE KEYS */;
INSERT INTO `tb_dept` VALUES (10,'Java',20,'88886666',_binary '\0','2020-10-30 11:48:19','2021-05-24 15:11:17'),(11,'Mysql',12,'80802121',_binary '\0','2020-10-30 11:48:44','2021-05-24 15:11:20'),(12,'Tomcat',12,'23231212',_binary '\0','2020-10-30 11:48:44','2021-05-24 15:11:23'),(13,'Nginx',13,'7116201',_binary '\0','2020-10-30 11:48:45','2021-05-24 15:11:26');
/*!40000 ALTER TABLE `tb_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_stu_sub_relation`
--

DROP TABLE IF EXISTS `tb_stu_sub_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_stu_sub_relation` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `stu_id` int DEFAULT NULL COMMENT '学号ID',
  `sub_id` int DEFAULT NULL COMMENT '学生ID',
  `score` int DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`id`),
  KEY `stu_id` (`stu_id`),
  KEY `sub_id` (`sub_id`),
  CONSTRAINT `tb_stu_sub_relation_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `tb_student` (`stu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_stu_sub_relation_ibfk_2` FOREIGN KEY (`sub_id`) REFERENCES `tb_subject` (`sub_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='学生与课程关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_stu_sub_relation`
--

LOCK TABLES `tb_stu_sub_relation` WRITE;
/*!40000 ALTER TABLE `tb_stu_sub_relation` DISABLE KEYS */;
INSERT INTO `tb_stu_sub_relation` VALUES (1,1,1,88),(2,1,2,67),(3,2,1,82);
/*!40000 ALTER TABLE `tb_stu_sub_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_student`
--

DROP TABLE IF EXISTS `tb_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_student` (
  `stu_id` int NOT NULL AUTO_INCREMENT COMMENT '学号ID',
  `stu_name` varchar(30) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student`
--

LOCK TABLES `tb_student` WRITE;
/*!40000 ALTER TABLE `tb_student` DISABLE KEYS */;
INSERT INTO `tb_student` VALUES (1,'张三'),(2,'李四'),(3,'王五');
/*!40000 ALTER TABLE `tb_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_subject`
--

DROP TABLE IF EXISTS `tb_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_subject` (
  `sub_id` int NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `sub_name` varchar(30) DEFAULT NULL COMMENT '课程名',
  PRIMARY KEY (`sub_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_subject`
--

LOCK TABLES `tb_subject` WRITE;
/*!40000 ALTER TABLE `tb_subject` DISABLE KEYS */;
INSERT INTO `tb_subject` VALUES (1,'英语'),(2,'数学'),(3,'计算机'),(4,'操作系统'),(5,'数据库');
/*!40000 ALTER TABLE `tb_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID（全局唯一）',
  `user_name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除（0:未删除；1:已删除）',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  KEY `dept_id` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COMMENT='书籍';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'Jone',12,10,_binary '\0',NULL,'2022-11-05 16:44:22'),(2,'Jack',23,11,_binary '\0',NULL,'2022-11-05 16:44:24'),(3,'Billie',22,12,_binary '\0',NULL,'2022-11-05 16:44:27'),(4,'didi',12,12,_binary '\0','2021-06-05 19:22:46','2021-10-21 14:38:26'),(5,'滴滴',21,11,_binary '\0','2022-11-05 16:09:42','2022-11-05 16:11:28'),(6,'嗒嗒',18,12,_binary '\0','2022-11-05 16:10:48','2022-11-05 16:11:36');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `user_dept_view`
--

DROP TABLE IF EXISTS `user_dept_view`;
/*!50001 DROP VIEW IF EXISTS `user_dept_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `user_dept_view` AS SELECT 
 1 AS `user_id`,
 1 AS `user_name`,
 1 AS `age`,
 1 AS `dept_id`,
 1 AS `dept_name`,
 1 AS `staff`,
 1 AS `tel`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `user_dept_view`
--

/*!50001 DROP VIEW IF EXISTS `user_dept_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER */
/*!50001 VIEW `user_dept_view` AS select `a`.`user_id` AS `user_id`,`a`.`user_name` AS `user_name`,`a`.`age` AS `age`,`b`.`dept_id` AS `dept_id`,`b`.`dept_name` AS `dept_name`,`b`.`staff` AS `staff`,`b`.`tel` AS `tel` from (`tb_user` `a` left join `tb_dept` `b` on((`a`.`dept_id` = `b`.`dept_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-06 19:13:43
