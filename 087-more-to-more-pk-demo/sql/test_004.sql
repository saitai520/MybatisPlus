-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: localhost    Database: test_004
-- ------------------------------------------------------
-- Server version	8.0.31

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
  `dept_id` bigint NOT NULL COMMENT '主键ID（全局唯一）',
  `dept_name` varchar(30) DEFAULT NULL COMMENT '部门名称',
  `staff` int DEFAULT NULL COMMENT '员工',
  `tel` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除（0:未删除；1:已删除）',
  `version` int DEFAULT '0' COMMENT '乐观锁',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='部门';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_dept`
--

LOCK TABLES `tb_dept` WRITE;
/*!40000 ALTER TABLE `tb_dept` DISABLE KEYS */;
INSERT INTO `tb_dept` VALUES (10,'Java',20,'88886666',_binary '\0',4,'2020-10-30 11:48:19','2021-05-24 15:11:17'),(11,'Mysql',12,'80802121',_binary '\0',0,'2020-10-30 11:48:44','2021-05-24 15:11:20'),(12,'Tomcat',12,'23231212',_binary '\0',0,'2020-10-30 11:48:44','2021-05-24 15:11:23'),(13,'Nginx',13,'7116201',_binary '\0',0,'2020-10-30 11:48:45','2021-05-24 15:11:26');
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
  `user_id` bigint NOT NULL COMMENT '主键ID（全局唯一）',
  `user_name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除（0:未删除；1:已删除）',
  `version` int DEFAULT '0' COMMENT '乐观锁',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  KEY `dept_id` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='书籍';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'Jone',1,'ab@c.c',10,_binary '\0',0,NULL,'2021-05-24 15:12:01'),(2,'Jack',3,'test2@baomidou.com',11,_binary '\0',0,NULL,'2021-05-24 15:12:04'),(3,'Billie',2,'test5@baomidou.com',12,_binary '\0',0,NULL,'2021-10-21 10:51:24'),(4,'didi',12,'test@qq.com',12,_binary '\0',0,'2021-06-05 19:22:46','2021-10-21 14:38:26');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-11 11:58:43
