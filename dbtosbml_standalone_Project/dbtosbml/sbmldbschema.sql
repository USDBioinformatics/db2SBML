-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: sbmldb2
-- ------------------------------------------------------
-- Server version	5.6.23-log

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
-- Table structure for table `bioelement`
--

DROP TABLE IF EXISTS `bioelement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bioelement` (
  `id` varchar(100) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `descr` varchar(200) DEFAULT NULL,
  `ref` varchar(200) DEFAULT NULL,
  `uri` varchar(200) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bioelement_has_expcond`
--

DROP TABLE IF EXISTS `bioelement_has_expcond`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bioelement_has_expcond` (
  `bioelement_id` varchar(100) NOT NULL,
  `expcond_id` varchar(100) NOT NULL,
  `ref` varchar(200) DEFAULT NULL,
  `value` double NOT NULL DEFAULT '0',
  `unit` double DEFAULT NULL,
  PRIMARY KEY (`bioelement_id`,`expcond_id`,`value`),
  KEY `fk_bioelement_has_expcond_expcond1_idx` (`expcond_id`),
  KEY `fk_bioelement_has_expcond_bioelement_idx` (`bioelement_id`),
  CONSTRAINT `fk_bioelement_has_expcond_bioelement` FOREIGN KEY (`bioelement_id`) REFERENCES `bioelement` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bioelement_has_expcond_expcond1` FOREIGN KEY (`expcond_id`) REFERENCES `expcond` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bioelement_has_model`
--

DROP TABLE IF EXISTS `bioelement_has_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bioelement_has_model` (
  `bioelement_id` varchar(100) NOT NULL,
  `model_id` varchar(100) NOT NULL,
  PRIMARY KEY (`bioelement_id`,`model_id`),
  KEY `fk_bioelement_has_model_model1_idx` (`model_id`),
  KEY `fk_bioelement_has_model_bioelement1_idx` (`bioelement_id`),
  CONSTRAINT `fk_bioelement_has_model_bioelement1` FOREIGN KEY (`bioelement_id`) REFERENCES `bioelement` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bioelement_has_model_model1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `compartment`
--

DROP TABLE IF EXISTS `compartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compartment` (
  `id` varchar(45) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `spacialDimensions` double DEFAULT NULL,
  `size` double DEFAULT NULL,
  `units` varchar(45) DEFAULT NULL,
  `constant` tinyint(1) DEFAULT NULL,
  `annotation` longtext,
  `model_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`model_id`),
  KEY `fk_compartment_model1_idx` (`model_id`),
  CONSTRAINT `fk_compartment_model1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `delay`
--

DROP TABLE IF EXISTS `delay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delay` (
  `math` longtext,
  `annotation` longtext,
  `event_id` varchar(45) NOT NULL,
  PRIMARY KEY (`event_id`),
  CONSTRAINT `fk_delay_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `useValuesFromTriggerTime` tinyint(1) DEFAULT NULL,
  `annotation` longtext,
  `model_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`model_id`),
  KEY `fk_event_model1_idx` (`model_id`),
  CONSTRAINT `fk_event_model1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `eventassignment`
--

DROP TABLE IF EXISTS `eventassignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventassignment` (
  `variable` varchar(45) DEFAULT NULL,
  `math` longtext,
  `annotation` longtext,
  `event_id` varchar(45) NOT NULL,
  PRIMARY KEY (`event_id`),
  CONSTRAINT `fk_eventassignment_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `expcond`
--

DROP TABLE IF EXISTS `expcond`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expcond` (
  `id` varchar(100) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `descr` varchar(200) DEFAULT NULL,
  `ref` varchar(200) DEFAULT NULL,
  `uri_efo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `functiondefinition`
--

DROP TABLE IF EXISTS `functiondefinition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `functiondefinition` (
  `id` varchar(100) NOT NULL,
  `xmlns` longtext,
  `annotation` longtext,
  `model_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`model_id`),
  KEY `fk_functiondefinition_model1_idx` (`model_id`),
  CONSTRAINT `fk_functiondefinition_model1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `initialassignment`
--

DROP TABLE IF EXISTS `initialassignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `initialassignment` (
  `symbol` varchar(45) NOT NULL,
  `math` longtext,
  `annotation` longtext,
  `model_id` varchar(100) NOT NULL,
  PRIMARY KEY (`symbol`,`model_id`),
  KEY `fk_initialassignment_model1_idx` (`model_id`),
  CONSTRAINT `fk_initialassignment_model1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `kineticlaw`
--

DROP TABLE IF EXISTS `kineticlaw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kineticlaw` (
  `kid` varchar(45) NOT NULL,
  `math` longtext,
  `annotation` longtext NOT NULL,
  `reaction_id` varchar(100) NOT NULL,
  PRIMARY KEY (`kid`,`reaction_id`),
  KEY `fk_kineticlaw_reaction1_idx` (`reaction_id`),
  CONSTRAINT `fk_kineticlaw_reaction1` FOREIGN KEY (`reaction_id`) REFERENCES `reaction` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `listofunitdefinitions`
--

DROP TABLE IF EXISTS `listofunitdefinitions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listofunitdefinitions` (
  `id` varchar(45) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `annotation` longtext,
  `model_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`model_id`),
  KEY `fk_listofunitdefinitions_model1_idx` (`model_id`),
  CONSTRAINT `fk_listofunitdefinitions_model1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `listofunits`
--

DROP TABLE IF EXISTS `listofunits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listofunits` (
  `kind` varchar(45) NOT NULL,
  `scale` int(11) DEFAULT NULL,
  `exponent` double DEFAULT NULL,
  `multiplier` double DEFAULT NULL,
  `annotation` longtext,
  `listofunitdefinitions_id` varchar(45) NOT NULL,
  PRIMARY KEY (`kind`,`listofunitdefinitions_id`),
  KEY `fk_listofunits_listofunitdefinitions1_idx` (`listofunitdefinitions_id`),
  CONSTRAINT `fk_listofunits_listofunitdefinitions1` FOREIGN KEY (`listofunitdefinitions_id`) REFERENCES `listofunitdefinitions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `localparameter`
--

DROP TABLE IF EXISTS `localparameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localparameter` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `units` int(11) DEFAULT NULL,
  `KineticLaw_math` longtext NOT NULL,
  `annotation` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `model` (
  `id` varchar(200) NOT NULL,
  `name` longtext,
  `notes` longtext,
  `substanceUnits` int(11) DEFAULT NULL,
  `timeUnits` int(11) DEFAULT NULL,
  `volumeUnits` int(11) DEFAULT NULL,
  `areaUnits` int(11) DEFAULT NULL,
  `lengthUnits` int(11) DEFAULT NULL,
  `extentUnits` int(11) DEFAULT NULL,
  `conversionFactor` int(11) DEFAULT NULL,
  `modelcol` varchar(45) DEFAULT NULL,
  `SBML_level` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `annotation` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `modifierspeciesreference`
--

DROP TABLE IF EXISTS `modifierspeciesreference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifierspeciesreference` (
  `species` varchar(45) NOT NULL,
  `sboTerm` varchar(45) DEFAULT NULL,
  `speciestype` varchar(45) DEFAULT NULL,
  `annotation` longtext,
  `reaction_id` varchar(100) NOT NULL,
  PRIMARY KEY (`species`,`reaction_id`),
  KEY `fk_modifierspeciesreference_reaction1_idx` (`reaction_id`),
  CONSTRAINT `fk_modifierspeciesreference_reaction1` FOREIGN KEY (`reaction_id`) REFERENCES `reaction` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parameter`
--

DROP TABLE IF EXISTS `parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parameter` (
  `id` varchar(100) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `units` varchar(45) DEFAULT NULL,
  `constant` tinyint(1) DEFAULT NULL,
  `annotation` longtext,
  `model_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`model_id`),
  KEY `fk_parameter_model1_idx` (`model_id`),
  CONSTRAINT `fk_parameter_model1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `priority`
--

DROP TABLE IF EXISTS `priority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `priority` (
  `math` longtext,
  `annotation` longtext,
  `event_id` varchar(45) NOT NULL,
  PRIMARY KEY (`event_id`),
  CONSTRAINT `fk_priority_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reaction`
--

DROP TABLE IF EXISTS `reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reaction` (
  `id` varchar(100) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `reversible` tinyint(1) DEFAULT NULL,
  `fast` tinyint(1) DEFAULT NULL,
  `compartment` varchar(45) DEFAULT NULL,
  `annotation` longtext NOT NULL,
  `model_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`model_id`),
  KEY `fk_reaction_model1_idx` (`model_id`),
  CONSTRAINT `fk_reaction_model1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rules`
--

DROP TABLE IF EXISTS `rules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rules` (
  `id` varchar(100) NOT NULL,
  `math` longtext,
  `ruletype` varchar(45) DEFAULT NULL,
  `variable` varchar(45) DEFAULT NULL,
  `annotation` longtext,
  `model_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`model_id`),
  KEY `fk_rules_model1_idx` (`model_id`),
  CONSTRAINT `fk_rules_model1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sbase`
--

DROP TABLE IF EXISTS `sbase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sbase` (
  `metaid` int(11) NOT NULL,
  `sboTerm` varchar(45) DEFAULT NULL,
  `Notes_xmlns` longtext NOT NULL,
  `Annotation_annotation` longtext NOT NULL,
  PRIMARY KEY (`metaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sbml`
--

DROP TABLE IF EXISTS `sbml`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sbml` (
  `xmlns` longtext,
  `level` int(11) NOT NULL,
  `SBase_metaid` int(11) DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sbmlconstraint`
--

DROP TABLE IF EXISTS `sbmlconstraint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sbmlconstraint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `math` longtext,
  `message` longtext,
  `annotation` longtext,
  `model_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`model_id`),
  KEY `fk_constraint_model1_idx` (`model_id`),
  CONSTRAINT `fk_constraint_model1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sbmltrigger`
--

DROP TABLE IF EXISTS `sbmltrigger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sbmltrigger` (
  `initialvalue` tinyint(1) DEFAULT NULL,
  `persistent` tinyint(1) DEFAULT NULL,
  `math` longtext,
  `annotation` longtext,
  `event_id` varchar(45) NOT NULL,
  PRIMARY KEY (`event_id`),
  CONSTRAINT `fk_trigger_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `simplespeciesreference`
--

DROP TABLE IF EXISTS `simplespeciesreference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `simplespeciesreference` (
  `species` varchar(45) NOT NULL,
  `sboTerm` varchar(45) DEFAULT NULL,
  `stoichiometry` double DEFAULT NULL,
  `speciestype` varchar(45) NOT NULL,
  `constant` tinyint(1) DEFAULT NULL,
  `annotation` longtext,
  `reaction_id` varchar(100) NOT NULL,
  PRIMARY KEY (`species`,`speciestype`,`reaction_id`),
  KEY `fk_simplespeciesreference_reaction1_idx` (`reaction_id`),
  CONSTRAINT `fk_simplespeciesreference_reaction1` FOREIGN KEY (`reaction_id`) REFERENCES `reaction` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `species` (
  `id` varchar(45) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `compartment` varchar(45) DEFAULT NULL,
  `initialAmount` double DEFAULT NULL,
  `initialConcentration` double DEFAULT NULL,
  `substanceUnits` varchar(45) DEFAULT NULL,
  `hasOnlySubstanceUnits` tinyint(1) DEFAULT NULL,
  `boundaryCondition` tinyint(1) DEFAULT NULL,
  `constant` tinyint(1) DEFAULT NULL,
  `conversionFactor` varchar(45) DEFAULT NULL,
  `annotation` longtext,
  `model_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`model_id`),
  KEY `fk_species_model1_idx` (`model_id`),
  CONSTRAINT `fk_species_model1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `speciestype`
--

DROP TABLE IF EXISTS `speciestype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `speciestype` (
  `id` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `reaction_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`reaction_id`),
  KEY `fk_speciestype_reaction1_idx` (`reaction_id`),
  CONSTRAINT `fk_speciestype_reaction1` FOREIGN KEY (`reaction_id`) REFERENCES `reaction` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit` (
  `kind` int(11) NOT NULL,
  `exponent` double DEFAULT NULL,
  `scale` int(11) DEFAULT NULL,
  `multiplier` double DEFAULT NULL,
  `ListOfUnits_Unit_kind` int(11) NOT NULL,
  `annotation` longtext,
  PRIMARY KEY (`kind`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unitdefinition`
--

DROP TABLE IF EXISTS `unitdefinition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unitdefinition` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `ListOfUnits_Unit_kind` int(11) NOT NULL,
  `annotation` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-04 11:13:51
