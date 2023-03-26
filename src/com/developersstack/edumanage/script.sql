-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.32 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table edu_manage.intake
CREATE TABLE IF NOT EXISTS `intake` (
  `intake_id` varchar(45) NOT NULL,
  `intake_name` varchar(45) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `intake_completeness` tinyint DEFAULT NULL,
  `program_code` varchar(45) NOT NULL,
  PRIMARY KEY (`intake_id`),
  KEY `fk_intake_program1_idx` (`program_code`),
  CONSTRAINT `fk_intake_program1` FOREIGN KEY (`program_code`) REFERENCES `program` (`program_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table edu_manage.intake: ~0 rows (approximately)

-- Dumping structure for table edu_manage.program
CREATE TABLE IF NOT EXISTS `program` (
  `program_code` varchar(45) NOT NULL,
  `program_name` varchar(45) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `teacher_code` varchar(45) NOT NULL,
  PRIMARY KEY (`program_code`),
  KEY `fk_program_teacher_idx` (`teacher_code`),
  CONSTRAINT `fk_program_teacher` FOREIGN KEY (`teacher_code`) REFERENCES `teacher` (`teacher_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table edu_manage.program: ~0 rows (approximately)

-- Dumping structure for table edu_manage.registration
CREATE TABLE IF NOT EXISTS `registration` (
  `register_id` varchar(45) NOT NULL,
  `register_date` date DEFAULT NULL,
  `payment_completeness` tinyint DEFAULT NULL,
  `student_id` varchar(45) NOT NULL,
  `program_code` varchar(45) NOT NULL,
  PRIMARY KEY (`register_id`),
  KEY `fk_registration_student1_idx` (`student_id`),
  KEY `fk_registration_program1_idx` (`program_code`),
  CONSTRAINT `fk_registration_program1` FOREIGN KEY (`program_code`) REFERENCES `program` (`program_code`),
  CONSTRAINT `fk_registration_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table edu_manage.registration: ~0 rows (approximately)

-- Dumping structure for table edu_manage.student
CREATE TABLE IF NOT EXISTS `student` (
  `student_id` varchar(45) NOT NULL,
  `full_name` varchar(45) DEFAULT NULL,
  `dob` date NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table edu_manage.student: ~0 rows (approximately)

-- Dumping structure for table edu_manage.teacher
CREATE TABLE IF NOT EXISTS `teacher` (
  `teacher_code` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`teacher_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table edu_manage.teacher: ~0 rows (approximately)

-- Dumping structure for table edu_manage.technology
CREATE TABLE IF NOT EXISTS `technology` (
  `tech_id` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `program_code` varchar(45) NOT NULL,
  PRIMARY KEY (`tech_id`),
  KEY `fk_technology_program1_idx` (`program_code`),
  CONSTRAINT `fk_technology_program1` FOREIGN KEY (`program_code`) REFERENCES `program` (`program_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table edu_manage.technology: ~0 rows (approximately)

-- Dumping structure for table edu_manage.user
CREATE TABLE IF NOT EXISTS `user` (
  `email` varchar(100) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `password` longblob,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table edu_manage.user: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
