import java.util.HashMap;
import java.util.Map;

/**
 * Item.java - Item stuff.
 * @author James
 */
public class Item {
    /**
     * Type - Used to identify items
     */
    public enum Type {
        Air (0),
        Stone (1),
        Grass(2),
        Dirt(3),
        Cobblestone(4),
        Wood(5),
        Sapling(6),
        Bedrock(7),
        Water(8),
        StationaryWater(9),
        Lava(10),
        StationaryLava(11),
        Sand(12),
        Gravel(13),
        GoldOre(14),
        IronOre(15),
        CoalOre(16),
        Log(17),
        Leaves(18),
        Sponge(19),
        Glass(20),
        Cloth(35),
        YellowFlower(37),
        RedRose(38),
        BrownMushroom(39),
        RedMushroom(40),
        GoldBlock(41),
        IronBlock(42),
        DoubleStep(43),
        Step(44),
        Brick(45),
        TNT(46),
        BookShelf(47),
        MossyCobblestone(48),
        Obsidian(49),
        Torch(50),
        Fire(51),
        MobSpawner(52),
        WoodStairs(53),
        Chest(54),
        RedstoneWire(55),
        DiamondOre(56),
        DiamondBlock(57),
        Workbench(58),
        Crops(59),
        Soil(60),
        Furnace(61),
        BurningFurnace(62),
        SignPost(63),
        WoodenDoor(64),
        Ladder(65),
        Rails(66),
        CobblestoneStairs(67),
        WallSign(68),
        Lever(69),
        StonePlate(70),
        IronDoorBlock(71),
        WoodPlate(72),
        RedstoneOre(73),
        GlowingRedstoneOre(74),
        RedstoneTorchOff(75),
        RedstoneTorchOn(76),
        StoneButton(77),
        Snow(78),
        Ice(79),
        SnowBlock(80),
        Cactus(81),
        Clay(82),
        ReedBlock(83),
        Jukebox(84),
        Fence(85),
        Pumpkin(86),
        Netherstone(87),
        SlowSand(88),
        LightStone(89),
        Portal(90),
        JackOLantern(91),

        IronSpade(256),
        IronPickaxe(257),
        IronAxe(258),
        FlintAndSteel(259),
        Apple(260),
        Bow(261),
        Arrow(262),
        Coal(263),
        Diamond(264),
        IronIngot(265),
        GoldIngot(266),
        IronSword(267),
        WoodSword(268),
        WoodSpade(269),
        WoodPickaxe(270),
        WoodAxe(271),
        StoneSword(272),
        StoneSpade(273),
        StonePickaxe(274),
        StoneAxe(275),
        DiamondSword(276),
        DiamondSpade(277),
        DiamondPickaxe(278),
        DiamondAxe(279),
        Stick(280),
        Bowl(281),
        MushroomSoup(282),
        GoldSword(283),
        GoldSpade(284),
        GoldPickaxe(285),
        GoldAxe(286),
        String(287),
        Feather(288),
        Gunpowder(289),
        WoodHoe(290),
        StoneHoe(291),
        IronHoe(292),
        DiamondHoe(293),
        GoldHoe(294),
        Seeds(295),
        Wheat(296),
        Bread(297),
        LeatherHelmet(298),
        LeatherChestplate(299),
        LeatherLeggings(300),
        LeatherBoots(301),
        ChainmailHelmet(302),
        ChainmailChestplate(303),
        ChainmailLeggings(304),
        ChainmailBoots(305),
        IronHelmet(306),
        IronChestplate(307),
        IronLeggings(308),
        IronBoots(309),
        DiamondHelmet(310),
        DiamondChestplate(311),
        DiamondLeggings(312),
        DiamondBoots(313),
        GoldHelmet(314),
        GoldChestplate(315),
        GoldLeggings(316),
        GoldBoots(317),
        Flint(318),
        Pork(319),
        GrilledPork(320),
        Painting(321),
        GoldenApple(322),
        Sign(323),
        WoodDoor(324),
        Bucket(325),
        WaterBucket(326),
        LavaBucket(327),
        Minecart(328),
        Saddle(329),
        IronDoor(330),
        RedStone(331),
        SnowBall(332),
        Boat(333),
        Leather(334),
        MilkBucket(335),
        ClayBrick(336),
        ClayBall(337),
        Reed(338),
        Paper(339),
        Book(340),
        SlimeBall(341),
        StorageMinecart(342),
        PoweredMinecart(343),
        Egg(344),
        Compass(345),
        FishingRod(346),
        Watch(347),
        LightstoneDust(348),
        RawFish(349),
        CookedFish(350),

        GoldRecord(2256),
        GreenRecord(2257)
        ;

        private int id;
        private static Map<Integer, Type> map;

        private Type(int id){
            this.id = id;
            add( id, this );
        }

        private static void add( int type, Type name ) {
            if (map == null) {
                map = new HashMap<Integer, Type>();
            }
            
            map.put(type, name);
        }

        public int getId() {
            return id;
        }

        public static Type fromId(final int id) {
            return map.get(id);
        }
    }

    private int itemId = 1, amount = 1, slot = -1, damage = 0;

    public Type itemType = Type.fromId(itemId);
    /**
     * Create an item with an id of 1 and amount of 1
     */
    public Item() {
    }

    /**
     * Creates an item with specified id and amount
     * @param itemId
     * @param amount
     */
    public Item(int itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
        this.itemType= Type.fromId(itemId);
    }

    /**
     * Creates an item with specified id, amount and slot
     * @param itemId
     * @param amount
     * @param slot
     */
    public Item(int itemId, int amount, int slot) {
        this.itemId = itemId;
        this.amount = amount;
        this.slot = slot;
        this.itemType = Type.fromId(itemId);
    }

    /**
     * Creates an item from the actual item class
     * @param hn
     */
    public Item(hn hn) {
        itemId = hn.c;
        amount = hn.a;
        damage = hn.d;
        this.itemType = Type.fromId(itemId);
    }

    /**
     * Returns the item id
     * @return item id
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets item id to specified id
     * @param itemId
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
        this.itemType = Type.fromId(itemId);
    }

    /**
     * Returns the amount
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Returns true if specified item id is a valid item id.
     * @param itemId
     * @return
     */
    public static boolean isValidItem(int itemId) {
        if (itemId < fl.c.length) {
            return fl.c[itemId] != null;
        }
        return false;
    }

    /**
     * Returns this item's current slot. -1 if no slot is specified
     * @return slot
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Sets this item's slot
     * @param slot
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * Returns this item's current damage. 0 if no damage is specified
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets this item's damage
     * @param damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Returns a String value representing this object
     * 
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return String.format("Item[id=%d, amount=%d, slot=%d]", itemId, amount, slot);
    }

    /**
     * Tests the given object to see if it equals this object
     * 
     * @param obj the object to test
     * @return true if the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.itemId != other.itemId) {
            return false;
        }
        if (this.amount != other.amount) {
            return false;
        }
        if (this.slot != other.slot) {
            return false;
        }
        return true;
    }

    /**
     * Returns a semi-unique hashcode for this object
     * 
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.itemId;
        hash = 97 * hash + this.amount;
        hash = 97 * hash + this.slot;
        return hash;
    }

    /**
     * Returns this item type
     * @return the item type
     */
    public Type getType(){
        return this.itemType;
    }

    /**
     * Set the item type
     * @param itemType the item type
     */
    public void setType(Type itemType){
        this.itemType = itemType;
        this.itemId = itemType.getId();
    }
}
