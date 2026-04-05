CREATE DATABASE  IF NOT EXISTS `quan_ly_ngan_hang` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quan_ly_ngan_hang`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: quan_ly_ngan_hang
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `giao_dich`
--

DROP TABLE IF EXISTS `giao_dich`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giao_dich` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tai_khoan_id` int NOT NULL,
  `nhan_vien_id` int NOT NULL,
  `loai_giao_dich` varchar(50) DEFAULT NULL,
  `so_tien` decimal(15,2) NOT NULL,
  `ngay_giao_dich` datetime DEFAULT CURRENT_TIMESTAMP,
  `ghi_chu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tai_khoan_id` (`tai_khoan_id`),
  KEY `nhan_vien_id` (`nhan_vien_id`),
  CONSTRAINT `giao_dich_ibfk_1` FOREIGN KEY (`tai_khoan_id`) REFERENCES `tai_khoan` (`id`),
  CONSTRAINT `giao_dich_ibfk_2` FOREIGN KEY (`nhan_vien_id`) REFERENCES `nhan_vien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giao_dich`
--

LOCK TABLES `giao_dich` WRITE;
/*!40000 ALTER TABLE `giao_dich` DISABLE KEYS */;
INSERT INTO `giao_dich` VALUES (1,5,6,'NAP_TIEN',30000.00,'2026-04-04 20:16:21','Khach hang nap tien mat tai quay'),(2,5,6,'RUT_TIEN',100000.00,'2026-04-04 20:17:01','Khach hang rut tien mat tai quay'),(3,5,6,'CHUYEN_KHOAN',10000.00,'2026-04-04 20:17:41','Chuyen tien den STK 9952692939'),(4,4,6,'NHAN_TIEN',10000.00,'2026-04-04 20:17:41','Nhan tien tu STK 99783907');
/*!40000 ALTER TABLE `giao_dich` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khach_hang`
--

DROP TABLE IF EXISTS `khach_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khach_hang` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ho_ten` varchar(100) NOT NULL,
  `cccd` varchar(20) NOT NULL,
  `so_dien_thoai` varchar(15) DEFAULT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `ngay_tao` datetime DEFAULT CURRENT_TIMESTAMP,
  `nguoi_tao_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cccd` (`cccd`),
  UNIQUE KEY `so_dien_thoai` (`so_dien_thoai`),
  KEY `fk_khachhang_nhanvien` (`nguoi_tao_id`),
  CONSTRAINT `fk_khachhang_nhanvien` FOREIGN KEY (`nguoi_tao_id`) REFERENCES `nhan_vien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khach_hang`
--

LOCK TABLES `khach_hang` WRITE;
/*!40000 ALTER TABLE `khach_hang` DISABLE KEYS */;
INSERT INTO `khach_hang` VALUES (1,'Nguyen Van A','012345678912','0987654321','Ha Noi','2026-03-24 00:16:43',NULL),(2,'Luffy','0123456789','043141432','Ha Noi','2026-03-31 06:35:07',NULL),(4,'Nguyen Thanh Vinh','036204003095','0387735502','Ha Noi','2026-04-04 01:06:42',2),(5,'Khach Test 1','012345678999','0312312312','Ha Noi','2026-04-04 15:54:40',6),(6,'','','','','2026-04-04 16:08:09',6);
/*!40000 ALTER TABLE `khach_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhan_vien`
--

DROP TABLE IF EXISTS `nhan_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhan_vien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ho_ten` varchar(100) NOT NULL,
  `role` int NOT NULL,
  `trang_thai` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhan_vien`
--

LOCK TABLES `nhan_vien` WRITE;
/*!40000 ALTER TABLE `nhan_vien` DISABLE KEYS */;
INSERT INTO `nhan_vien` VALUES (1,'admin','admin123','Quan Tri He Thong',1,1),(2,'nhanvien001','vinh1205','Nguyen Van A',2,1),(3,'nhanvien002','123456','Nguyen Van B',2,0),(4,'nhanvien003','123456','Nguyen Van C',2,1),(5,'nhanvien005','vinh1205','Nguyen Van E',2,0),(6,'gdtest','passmoi123','Tran Van Test',2,1);
/*!40000 ALTER TABLE `nhan_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `so_tiet_kiem`
--

DROP TABLE IF EXISTS `so_tiet_kiem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `so_tiet_kiem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `khach_hang_id` int NOT NULL,
  `so_tien_goc` double NOT NULL,
  `ky_han` int NOT NULL,
  `lai_suat` double NOT NULL,
  `ngay_gui` datetime DEFAULT CURRENT_TIMESTAMP,
  `trang_thai` int DEFAULT '1',
  `nhan_vien_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `khach_hang_id` (`khach_hang_id`),
  KEY `fk_stk_nv` (`nhan_vien_id`),
  CONSTRAINT `fk_stk_nv` FOREIGN KEY (`nhan_vien_id`) REFERENCES `nhan_vien` (`id`),
  CONSTRAINT `so_tiet_kiem_ibfk_1` FOREIGN KEY (`khach_hang_id`) REFERENCES `khach_hang` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `so_tiet_kiem`
--

LOCK TABLES `so_tiet_kiem` WRITE;
/*!40000 ALTER TABLE `so_tiet_kiem` DISABLE KEYS */;
INSERT INTO `so_tiet_kiem` VALUES (1,5,20000000,6,0.05,'2026-04-04 16:03:09',0,NULL);
/*!40000 ALTER TABLE `so_tiet_kiem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tai_khoan`
--

DROP TABLE IF EXISTS `tai_khoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tai_khoan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `so_tai_khoan` varchar(20) NOT NULL,
  `so_du` decimal(15,2) DEFAULT '0.00',
  `khach_hang_id` int NOT NULL,
  `trang_thai` int DEFAULT '1',
  `ngay_mo` datetime DEFAULT CURRENT_TIMESTAMP,
  `nhan_vien_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `so_tai_khoan` (`so_tai_khoan`),
  KEY `khach_hang_id` (`khach_hang_id`),
  KEY `fk_tk_nv` (`nhan_vien_id`),
  CONSTRAINT `fk_tk_nv` FOREIGN KEY (`nhan_vien_id`) REFERENCES `nhan_vien` (`id`),
  CONSTRAINT `tai_khoan_ibfk_1` FOREIGN KEY (`khach_hang_id`) REFERENCES `khach_hang` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tai_khoan`
--

LOCK TABLES `tai_khoan` WRITE;
/*!40000 ALTER TABLE `tai_khoan` DISABLE KEYS */;
INSERT INTO `tai_khoan` VALUES (1,'TK999999',5000000.00,1,1,'2026-04-03 09:22:40',NULL),(2,'992844879',900000.00,1,1,'2026-04-03 09:31:49',NULL),(4,'9952692939',510000.00,4,1,'2026-04-04 01:09:07',NULL),(5,'99783907',990000.00,5,1,'2026-04-04 15:58:13',NULL),(6,'9939600482',20000.00,2,1,'2026-04-04 16:00:49',NULL),(7,'9914158456',0.00,5,1,'2026-04-04 16:16:12',NULL);
/*!40000 ALTER TABLE `tai_khoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-05  9:01:52
