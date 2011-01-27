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
  `commands` LONGTEXT DEFAULT '',
  `inheritedgroups` varchar(64) NOT NULL DEFAULT '',
  `defaultgroup` tinyint(1) NOT NULL DEFAULT '0',
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `canmodifyworld` tinyint(1) NOT NULL DEFAULT '1',
  `ignoresrestrictions` tinyint(1) NOT NULL DEFAULT '0',
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
  `group` VARCHAR(64) NOT NULL DEFAULT '',
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
INSERT INTO `items` VALUES 
('air',0),('rock',1),('stone',1),('grass',2),('dirt',3),('cobblestone',4),
('cobble',4),('wood',5),('sapling',6),('adminium',7),('bedrock',7),('water',8),
('stillwater',9),('lava',10),('stilllava',11),('slava',11),('sand',12),('gravel',13),
('goldore',14),('ironore',15),('coalore',16),('tree',17),('log',17),('leaves',18),
('sponge',19),('glass',20),('lapislazuliore', 21), ('lapislazuliblock', 22), 
('dispenser', 23), ('sandstone', 24), ('noteblock', 25), ('cloth',35),('flower',37),
('rose',38),('brownmushroom',39),
('redmushroom',40),('gold',41),('goldblock',41),('iron',42),('ironblock',42),
('doublestair',43),('stair',44),('step',44),('brickblock',45),('brickwall',45),
('tnt',46),('bookshelf',47),('mossycobblestone',48),('mossy',48),('obsidian',49),
('torch',50),('fire',51),('mobspawner',52),('woodstairs',53),('chest',54),('redstonewire',55),
('diamondore',56),('diamondblock',57),('workbench',58),('crop',59),('crops',59),('soil',60),
('furnace',61),('litfurnace',62),('signblock',63),('wooddoorblock',64),('ladder',65),('rails',66),
('rail',66),('track',66),('tracks',66),('cobblestonestairs',67),('stairs',67),('signblocktop',68),
('wallsign',68),('lever',69),('rockplate',70),('stoneplate',70),('irondoorblock',71),('woodplate',72),
('redstoneore',73),('redstoneorealt',74),('redstonetorchoff',75),('redstonetorchon',76),('button',77),
('snow',78),('ice',79),('snowblock',80),('cactus',81),('clayblock',82),('reedblock',83),('jukebox',84),
('fence',85),('pumpkin',86),('netherstone',87),('slowsand',88),('lightstone',89),('portal',90),
('jackolantern',91),('jacko',91),('cakeblock', 92),('ironshovel',256),('ironspade',256),('ironpickaxe',257),
('ironpick',257),('ironaxe',258),('flintandsteel',259),('lighter',259),('apple',260),('bow',261),
('arrow',262),('coal',263),('diamond',264),('ironbar',265),('goldbar',266),('ironsword',267),
('woodsword',268),('woodshovel',269),('woodspade',269),('woodpickaxe',270),('wodpick',270),
('woodaxe',271),('stonesword',272),('stoneshovel',273),('stonespade',273),('stonepickaxe',274),
('stonepick',274),('stoneaxe',275),('diamondsword',276),('diamondshovel',277),('diamondspade',277),
('diamondpickaxe',278),('diamondpick',278),('diamondaxe',279),('stick',280),('bowl',281),
('bowlwithsoup',282),('soupbowl',282),('soup',282),('goldsword',283),('goldshovel',284),
('goldspade',284),('goldpickaxe',285),('goldpick',285),('goldaxe',286),('string',287),('feather',288),
('gunpowder',289),('woodhoe',290),('stonehoe',291),('ironhore',292),('diamondhoe',293),('goldhoe',294),
('seeds',295),('wheat',296),('bread',297),('leatherhelmet',298),('leatherchestplate',299),('leatherpants',300),
('leatherboots',301),('chainmailhelmet',302),('chainmailchestplate',303),('chainmailpants',304),
('chainmailboots',305),('ironhelmet',306),('ironchestplate',307),('ironpants',308),('ironboots',309),
('diamondhelmets',310),('diamondchestplate',311),('diamondpants',312),('diamondboots',313),('goldhelmet',314),
('goldchestplate',315),('goldpants',316),('goldboots',317),('flint',318),('meat',319),('pork',319),
('cookedmeat',320),('cookedpork',320),('painting',321),('paintings',321),('goldenapple',322),('sign',323),
('wooddoor',324),('bucket',325),('waterbucket',326),('lavabucket',327),('minecart',328),('saddle',329),
('irondoor',330),('redstonedust',331),('snowball',332),('boat',333),('leather',334),('milkbucket',335),
('brick',336),('clay',337),('reed',338),('paper',339),('book',340),('slimeorb',341),('storageminecart',342),
('poweredminecart',343),('eggs',344),('compass',345),('fishingrod',346),('watch',347),('lightstonedust',348),
('lightdust',348),('rawfish',349),('fish',349),('cookefish',350),('dye', 351), ('bone', 352), ('sugar', 353), 
('cake', 354),('cookedfish',350),('goldrecord',2256),('greenrecord',2257);
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
  `group` varchar(32) NOT NULL DEFAULT '',
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
  `prefix` varchar(10) NOT NULL DEFAULT '',
  `commands` LONGTEXT DEFAULT '',
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `canmodifyworld` tinyint(1) NOT NULL DEFAULT '1',
  `ignoresrestrictions` tinyint(1) NOT NULL DEFAULT '0',
  `ip` VARCHAR(128) NOT NULL DEFAULT '',
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
  `group` VARCHAR(64) NOT NULL DEFAULT '',
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

CREATE TABLE  `whitelist` (
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE  `reservelist` (
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;