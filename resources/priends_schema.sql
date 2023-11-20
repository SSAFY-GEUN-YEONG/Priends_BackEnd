-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: priends
-- ------------------------------------------------------
-- Server version   8.0.34
DROP DATABASE IF EXISTS `priends`;
create database priends;
use priends;
 

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(500) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nickname` varchar(10) NOT NULL,
  `image` varchar(1000) NOT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `alarm` tinyint NOT NULL DEFAULT '0',
  `role` enum('ADMIN','MANAGER','USER') DEFAULT NULL,
  `social` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `boards`
--

DROP TABLE IF EXISTS `boards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boards` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` varchar(10000) NOT NULL,
  `hit` INT NOT NULL DEFAULT 0,
  `like` INT NOT NULL DEFAULT 0,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  `category` enum('NOTICE','FREE','QNA') NOT NULL,
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `boards_to_members_id_fk_idx` (`member_id`),
  CONSTRAINT `boards_to_members_id_fk` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) NOT NULL,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `board_id` bigint NOT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comments_to_boards_id_fk_idx` (`board_id`),
  KEY `comments_to_members_id_fk_idx` (`member_id`),
  CONSTRAINT `comments_to_boards_id_fk` FOREIGN KEY (`board_id`) REFERENCES `boards` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comments_to_members_id_fk` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `path`
--

DROP TABLE IF EXISTS `path`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `path` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `hit` INT NOT NULL DEFAULT 0,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  `is_end` tinyint NOT NULL DEFAULT '0',
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `path_to_members_id_fk_idx` (`member_id`),
  CONSTRAINT `path_to_members_id_fk` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `path_details`;
CREATE TABLE `priends`.`path_details` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `orders` INT NOT NULL,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `day` INT NOT NULL,
  `content_id` INT NOT NULL,
  `path_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`), 
  CONSTRAINT `path_details_to_attraction_info_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `priends`.`attraction_info` (`content_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `path_details_to_path_path_id_fk`
    FOREIGN KEY (`path_id`)
    REFERENCES `priends`.`path` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
DROP TABLE IF EXISTS `alarm`;
CREATE TABLE `alarm` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `createdAt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `content` varchar(80) NOT NULL,
  `read` tinyint NOT NULL DEFAULT '0',
  `from_member_id` BIGINT NOT NULL,
  `to_member_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`), 
  CONSTRAINT `alarm_to_members_from_id_fk`
    FOREIGN KEY (`from_member_id`)
    REFERENCES `priends`.`members` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `alarm_to_members_to_id_fk`
    FOREIGN KEY (`to_member_id`)
    REFERENCES `priends`.`members` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Dump completed on 2023-10-31 16:10:05 