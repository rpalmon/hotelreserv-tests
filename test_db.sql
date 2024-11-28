-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: test_db
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `guestID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`guestID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (1,'testname','g@gm.com','123-444-5555','123 fake street'),(2,'nam','n@g.com','555','123'),(4,'4','4@4.com','444-444-4444','123-456-7890'),(5,'test5','5@5.com','555-555-5555','555 fake street'),(6,'Test Subject ','test@test.com','666-222-4444','321 fake street'),(7,'Jane Doe','jane.doe@gmail.fake','333-444-5555','jane doe street'),(8,'Jack','Daniels','224-5566','909 CSUN Ave.'),(9,'Niner Ninings','nine@nine.com','999-999-9999','999 Fake Nines');
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `resID` int NOT NULL AUTO_INCREMENT,
  `guestID` int NOT NULL,
  `roomID` int NOT NULL,
  `checkInDate` date NOT NULL,
  `checkOutDate` date NOT NULL,
  `totalCost` decimal(10,2) NOT NULL,
  `status` enum('AVAILABLE','BOOKED','CANCELLED','CHECKEDIN') DEFAULT NULL,
  `payment` tinyint(1) NOT NULL,
  PRIMARY KEY (`resID`),
  KEY `guestID` (`guestID`),
  KEY `roomID` (`roomID`),
  CONSTRAINT `Reservation_ibfk_1` FOREIGN KEY (`guestID`) REFERENCES `guest` (`guestID`),
  CONSTRAINT `Reservation_ibfk_2` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,2,2,'2024-01-01','2025-01-01',10.10,'CHECKEDIN',0),(3,2,2,'2024-10-10','2024-10-15',19.19,'CANCELLED',0),(4,5,5,'2024-11-25','2024-11-27',20.20,'BOOKED',0),(5,5,5,'2024-11-28','2024-11-29',2000.00,'BOOKED',1),(6,6,6,'2024-12-09','2024-12-26',666.66,'BOOKED',1),(7,7,7,'2025-11-11','2025-11-27',777.77,'BOOKED',1),(8,8,8,'2024-09-30','2024-11-13',88.88,'CANCELLED',0),(9,1,8,'2024-12-01','2024-12-10',99.99,'BOOKED',1),(10,5,3,'2025-12-01','2025-12-10',110.99,'BOOKED',1),(11,7,4,'2026-11-12','2026-11-28',1111.11,'BOOKED',0);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `roomID` int NOT NULL AUTO_INCREMENT,
  `roomNum` varchar(10) NOT NULL,
  `roomType` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `avail` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`roomID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (2,'2','DOUBLE',22.22,1),(3,'3','SUITE',3.00,1),(4,'4','DOUBLE',2020.00,1),(5,'8','DOUBLE',9.90,1),(6,'15','SUITE',10.10,1),(7,'203','SUITE',200.00,1),(8,'108','SINGLE',88.88,1);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `userPass` varchar(255) NOT NULL,
  `userEmail` varchar(100) NOT NULL,
  `userPhone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userEmail` (`userEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Alice Smith','password','alice.smith@example.com','123-456-7890'),(2,'Bob Johnson','password','bob.johnson@example.com','098-765-4321'),(3,'Carol Williams','password','carol.williams@example.com','555-123-4567'),(4,'admin','password','admin@example.com','111-222-3333'),(5,'admin2','password2','g@g.com','666-777-8888');
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

-- Dump completed on 2024-11-27 23:51:53
