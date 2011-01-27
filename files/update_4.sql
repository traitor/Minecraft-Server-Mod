-- 
-- Commands can become larger than varchar(255),
-- we should change this to LONGTEXT
--  
ALTER TABLE `groups` MODIFY `commands` LONGTEXT NOT NULL DEFAULT '';
ALTER TABLE `users` MODIFY `commands` LONGTEXT NOT NULL DEFAULT '';

--
-- Let's update the items table.
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
INSERT INTO `items` VALUES ('air',0),('rock',1),('stone',1),('grass',2),('dirt',3),('cobblestone',4),('cobble',4),('wood',5),('sapling',6),('adminium',7),('bedrock',7),('water',8),('stillwater',9),('lava',10),('stilllava',11),('slava',11),('sand',12),('gravel',13),('goldore',14),('ironore',15),('coalore',16),('tree',17),('log',17),('leaves',18),('sponge',19),('glass',20),('cloth',35),('flower',37),('rose',38),('brownmushroom',39),('redmushroom',40),('gold',41),('goldblock',41),('iron',42),('ironblock',42),('doublestair',43),('stair',44),('step',44),('brickblock',45),('brickwall',45),('tnt',46),('bookshelf',47),('mossycobblestone',48),('mossy',48),('obsidian',49),('torch',50),('fire',51),('mobspawner',52),('woodstairs',53),('chest',54),('redstonewire',55),('diamondore',56),('diamondblock',57),('workbench',58),('crop',59),('crops',59),('soil',60),('furnace',61),('litfurnace',62),('signblock',63),('wooddoorblock',64),('ladder',65),('rails',66),('rail',66),('track',66),('tracks',66),('cobblestonestairs',67),('stairs',67),('signblocktop',68),('wallsign',68),('lever',69),('rockplate',70),('stoneplate',70),('irondoorblock',71),('woodplate',72),('redstoneore',73),('redstoneorealt',74),('redstonetorchoff',75),('redstonetorchon',76),('button',77),('snow',78),('ice',79),('snowblock',80),('cactus',81),('clayblock',82),('reedblock',83),('jukebox',84),('fence',85),('pumpkin',86),('netherstone',87),('slowsand',88),('lightstone',89),('portal',90),('jackolantern',91),('jacko',91),('ironshovel',256),('ironspade',256),('ironpickaxe',257),('ironpick',257),('ironaxe',258),('flintandsteel',259),('lighter',259),('apple',260),('bow',261),('arrow',262),('coal',263),('diamond',264),('ironbar',265),('goldbar',266),('ironsword',267),('woodsword',268),('woodshovel',269),('woodspade',269),('woodpickaxe',270),('wodpick',270),('woodaxe',271),('stonesword',272),('stoneshovel',273),('stonespade',273),('stonepickaxe',274),('stonepick',274),('stoneaxe',275),('diamondsword',276),('diamondshovel',277),('diamondspade',277),('diamondpickaxe',278),('diamondpick',278),('diamondaxe',279),('stick',280),('bowl',281),('bowlwithsoup',282),('soupbowl',282),('soup',282),('goldsword',283),('goldshovel',284),('goldspade',284),('goldpickaxe',285),('goldpick',285),('goldaxe',286),('string',287),('feather',288),('gunpowder',289),('woodhoe',290),('stonehoe',291),('ironhore',292),('diamondhoe',293),('goldhoe',294),('seeds',295),('wheat',296),('bread',297),('leatherhelmet',298),('leatherchestplate',299),('leatherpants',300),('leatherboots',301),('chainmailhelmet',302),('chainmailchestplate',303),('chainmailpants',304),('chainmailboots',305),('ironhelmet',306),('ironchestplate',307),('ironpants',308),('ironboots',309),('diamondhelmets',310),('diamondchestplate',311),('diamondpants',312),('diamondboots',313),('goldhelmet',314),('goldchestplate',315),('goldpants',316),('goldboots',317),('flint',318),('meat',319),('pork',319),('cookedmeat',320),('cookedpork',320),('painting',321),('paintings',321),('goldenapple',322),('sign',323),('wooddoor',324),('bucket',325),('waterbucket',326),('lavabucket',327),('minecart',328),('saddle',329),('irondoor',330),('redstonedust',331),('snowball',332),('boat',333),('leather',334),('milkbucket',335),('brick',336),('clay',337),('reed',338),('paper',339),('book',340),('slimeorb',341),('storageminecart',342),('poweredminecart',343),('eggs',344),('compass',345),('fishingrod',346),('watch',347),('lightstonedust',348),('lightdust',348),('rawfish',349),('fish',349),('cookedfish',350),('goldrecord',2256),('greenrecord',2257);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- We don't accept null
--
ALTER TABLE `groups`
  MODIFY `inheritedgroups` varchar(64) NOT NULL DEFAULT '',
  MODIFY `defaultgroup` tinyint(1) NOT NULL DEFAULT '0',
  MODIFY `canmodifyworld` tinyint(1) NOT NULL DEFAULT '1',
  MODIFY `ignoresrestrictions` tinyint(1) NOT NULL DEFAULT '0';
  
ALTER TABLE `kits`
  MODIFY `group` varchar(32) NOT NULL DEFAULT '';
  
ALTER TABLE `users`
  MODIFY `prefix` varchar(10) NOT NULL DEFAULT '',
  MODIFY `admin` tinyint(1) NOT NULL DEFAULT '0',
  MODIFY `canmodifyworld` tinyint(1) NOT NULL DEFAULT '1',
  MODIFY `ignoresrestrictions` tinyint(1) NOT NULL DEFAULT '0',
  MODIFY `ip` VARCHAR(128) NOT NULL DEFAULT '';
  
ALTER TABLE `warps`
  MODIFY `group` VARCHAR(64) NOT NULL DEFAULT '';
  
ALTER TABLE `homes`
  MODIFY `group` VARCHAR(64) NOT NULL DEFAULT '';