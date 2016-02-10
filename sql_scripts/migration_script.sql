-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: bloodbank
-- Source Schemata: bloodbank
-- Created: Fri Nov 27 15:37:08 2015
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;;

-- ----------------------------------------------------------------------------
-- Schema bloodbank
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `bloodbank` ;
CREATE SCHEMA IF NOT EXISTS `bloodbank` ;

-- ----------------------------------------------------------------------------
-- Table bloodbank.profile
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bloodbank`.`profile` (
  `donorid` INT(50) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `dob` VARCHAR(10) NOT NULL ,
  `age` VARCHAR(3) NOT NULL ,
  `bloodgroup` VARCHAR(12) NOT NULL ,
  `gender` VARCHAR(15) NOT NULL ,
  `spousename` VARCHAR(100) NOT NULL ,
  `education` VARCHAR(50) NOT NULL ,
  `occupation` VARCHAR(50) NOT NULL ,
  `resdoornoandstreetorroad` VARCHAR(100) NOT NULL ,
  `resbuildingname` VARCHAR(50) NOT NULL ,
  `resarea` VARCHAR(100) NULL DEFAULT NULL ,
  `resvillageortownorcity` VARCHAR(45) NULL DEFAULT NULL ,
  `restaluk` VARCHAR(100) NULL DEFAULT NULL ,
  `resdistrict` VARCHAR(100) NULL DEFAULT NULL ,
  `respincode` VARCHAR(6) NOT NULL ,
  `resphone` VARCHAR(13) NOT NULL ,
  `resmobile` VARCHAR(13) NOT NULL ,
  `resemail` VARCHAR(254) NOT NULL ,
  `officedoornoandstreetorroad` VARCHAR(100) NOT NULL ,
  `officebuildingname` VARCHAR(50) NOT NULL ,
  `officearea` VARCHAR(100) NULL DEFAULT NULL ,
  `officevillageortownorcity` VARCHAR(45) NULL DEFAULT NULL ,
  `officetaluk` VARCHAR(100) NULL DEFAULT NULL ,
  `officedistrict` VARCHAR(100) NULL DEFAULT NULL ,
  `officepincode` VARCHAR(6) NOT NULL ,
  `officephone` VARCHAR(13) NOT NULL ,
  `officemobile` VARCHAR(13) NOT NULL ,
  `officeemail` VARCHAR(254) NOT NULL ,
  `dor` VARCHAR(10) NOT NULL ,
  `nsdod` VARCHAR(10) NOT NULL ,
  `donor_type` VARCHAR(10) NOT NULL ,
  `willl_bday` VARCHAR(10) NOT NULL ,
  `will_wed_day` VARCHAR(10) NOT NULL ,
  `will_oth_day` VARCHAR(10) NOT NULL ,
  `will_term` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`donorid`) )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;
SET FOREIGN_KEY_CHECKS = 1;;
