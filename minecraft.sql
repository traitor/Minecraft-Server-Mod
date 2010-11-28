-- MySQL dump 10.13  Distrib 5.1.32, for Win32 (ia32)
--
-- Host: localhost    Database: minecraft
-- ------------------------------------------------------
-- Server version	5.1.32-community

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
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `groups` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `prefix` varchar(16) NOT NULL,
  `commands` varchar(256) DEFAULT '',
  `inheritedgroups` varchar(64) DEFAULT '',
  `defaultgroup` tinyint(1) DEFAULT '0',
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `canmodifyworld` tinyint(1) DEFAULT '1',
  `ignoresrestrictions` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'default','f','/help,/sethome,/home,/spawn,/me,/msg,/kit,/playerlist,/warp,/motd,/compass,/tell,/m,/who','',1,0,1,0),(2,'vip','a','','default',0,0,1,0),(3,'mods','b','/ban,/kick,/item,/tp,/tphere,/s,/i,/give','vip',0,0,1,1),(4,'admins','c','*','mods',0,1,1,1);
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homes`
--

DROP TABLE IF EXISTS `homes`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `homes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `x` double NOT NULL,
  `y` double NOT NULL,
  `z` double NOT NULL,
  `rotX` float NOT NULL,
  `rotY` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `homes`
--

LOCK TABLES `homes` WRITE;
/*!40000 ALTER TABLE `homes` DISABLE KEYS */;
/*!40000 ALTER TABLE `homes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `items` (
  `name` varchar(64) NOT NULL,
  `itemid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES ('adminium',7),('air',0),('apple',260),('arrow',262),('boat',333),('book',340),('bookshelf',47),('bow',261),('bowl',281),('bowlwithsoup',282),('bread',297),('brick',45),('brownmushroom',39),('bucket',325),('button',77),('cactus',81),('chainmailboots',305),('chainmailchestplate',303),('chainmailhelmet',302),('chainmailpants',304),('chest',54),('clay',337),('clayblock',82),('cloth',35),('coal',263),('coalore',16),('cobblestone',4),('cobblestonestairs',67),('cookedmeat',320),('crop',59),('diamond',264),('diamondaxe',279),('diamondblock',57),('diamondboots',313),('diamondchestplate',311),('diamondhelmet',310),('diamondhoe',293),('diamondore',56),('diamondpants',312),('diamondpickaxe',278),('diamondshovel',277),('diamondsword',276),('dirt',3),('doublestair',43),('egg',344),('feather',288),('fence',85),('fire',51),('flint',318),('flintandsteel',259),('flower',37),('furnace',61),('glass',20),('gold',41),('goldaxe',286),('goldbar',266),('goldboots',317),('goldchestplate',315),('goldenapple',322),('goldhelmet',314),('goldhoe',294),('goldore',14),('goldpants',316),('goldpickaxe',285),('goldshovel',284),('goldsword',283),('grass',2),('gravel',13),('gunpowder',289),('ice',79),('iron',42),('ironaxe',258),('ironbar',265),('ironboots',309),('ironchestplate',307),('irondoor',330),('irondoorblock',71),('ironhelmet',306),('ironhoe',292),('ironore',15),('ironpants',308),('ironpickaxe',257),('ironshovel',256),('ironsword',267),('jukebox',84),('ladder',65),('lava',10),('lavabucket',327),('leather',334),('leatherboots',301),('leatherchestplate',299),('leatherhelmet',298),('leatherpants',300),('leaves',18),('lever',69),('litfurnace',62),('meat',319),('milkbucket',335),('minecart',328),('mobspawner',52),('mossycobblestone',48),('obsidian',49),('painting',321),('paper',339),('poweredminecart',343),('rails',66),('redmushroom',40),('redstonedust',55),('redstoneore',73),('redstoneorealt',74),('redstonetorchoff',75),('redstonetorchon',76),('reed',338),('reedblock',83),('rock',1),('rockplate',70),('rose',38),('saddle',329),('sand',12),('sapling',6),('seeds',295),('sign',323),('signblock',63),('signblocktop',68),('slimeorb',341),('snow',78),('snowball',332),('snowblock',80),('soil',60),('sponge',19),('stair',44),('stick',280),('stilllava',11),('stillwater',9),('stoneaxe',275),('stonehoe',291),('stonepickaxe',274),('stoneshovel',273),('stonesword',272),('storageminecart',342),('string',287),('tnt',46),('torch',50),('tree',17),('water',8),('waterbucket',326),('wheat',296),('wood',5),('woodaxe',271),('wooddoor',324),('wooddoorblock',64),('woodhoe',290),('woodpickaxe',270),('woodplate',72),('woodshovel',269),('woodstairs',53),('woodsword',268),('workbench',58);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kits`
--

DROP TABLE IF EXISTS `kits`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `kits` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `items` varchar(512) NOT NULL,
  `delay` int(10) unsigned NOT NULL,
  `group` varchar(32) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `kits`
--

LOCK TABLES `kits` WRITE;
/*!40000 ALTER TABLE `kits` DISABLE KEYS */;
/*!40000 ALTER TABLE `kits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `groups` varchar(64) NOT NULL,
  `prefix` varchar(10) DEFAULT '',
  `commands` varchar(256) DEFAULT '',
  `admin` tinyint(1) DEFAULT '0',
  `canmodifyworld` tinyint(1) DEFAULT '1',
  `ignoresrestrictions` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `warps`
--

DROP TABLE IF EXISTS `warps`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `warps` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `x` double NOT NULL,
  `y` double NOT NULL,
  `z` double NOT NULL,
  `rotX` float NOT NULL,
  `rotY` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `warps`
--

LOCK TABLES `warps` WRITE;
/*!40000 ALTER TABLE `warps` DISABLE KEYS */;
/*!40000 ALTER TABLE `warps` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-08-27 17:58:30
