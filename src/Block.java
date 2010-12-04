import java.util.HashMap;
import java.util.Map;

/**
 * Block.java - Provides some way of making/editing blocks
 * 
 * @author James
 */
public class Block {
    /**
     * Type - Used to identify blocks
     */
    public enum Type {
        Air(0),
        Stone(1),
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
        Workbench(58),
        Crops(59),
        Soil(60),
        Furnace(61),
        BurningFurnace(62),
        SignPost(63),
        WoodDoor(64),
        Ladder(65),
        Rails(66),
        CobblestoneStairs(67),
        WallSign(68),
        Lever(69),
        StonePlate(70),
        IronDoor(71),
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
        Reed(83),
        Jukebox(84),
        Fence(85),
        Pumpkin(86),
        Netherstone(87),
        SlowSand(88),
        LightStone(89),
        Portal(90),
        JackOLantern(91)
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

        public int getType() {
            return id;
        }

        public static Type fromId(final int type) {
            return map.get(type);
        }
    }

    /**
     * Face - Used for what face of the block was clicked
     */
    public enum Face {

        /**
         * The top of the block
         */
        Top(1),
        /**
         * The bottom of the block
         */
        Bottom(0),
        /**
         * The left (Z-wise) of the block (Faces west)
         */
        Left(3),
        /**
         * The right (Z-wise) of the block (Faces east)
         */
        Right(2),
        /**
         * The front (X-wise) of the block (Faces south)
         */
        Front(5),
        /**
         * The back (X-wise) of the block (Faces north)
         */
        Back(4);
        private final int id;

        private Face(int id) {
            this.id = id;
        }

        /**
         * Returns a Face according to the specified ID
         * 
         * @param id
         *            id of face
         * @return face
         */
        public static Face fromId(final int id) {
            for (Face e : Face.values()) {
                if (e.id == id) {
                    return e;
                }
            }
            return null;
        }
    }
    private int type, x, y, z;
    private Face faceClicked;
    public Type blockType;
    private int status, data;

    /**
     * Create a block with no type, x, y or z.
     */
    public Block() {
    }

    /**
     * Creates a block of specified type
     * 
     * @param type
     */
    public Block(int type) {
        this.type = type;
        this.blockType = Type.fromId(type);
    }

    /**
     * Creates a block of specified type and specified x, y and z
     * 
     * @param type
     *            Type of block
     * @param x
     * @param y
     * @param z
     */
    public Block(int type, int x, int y, int z) {
        this.type = type;
        this.blockType = Type.fromId(type);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a block of specified type and specified x, y and z
     * 
     * @param type
     *            Type of block
     * @param x
     * @param y
     * @param z
     * @param data
     */
    public Block(int type, int x, int y, int z, int data) {
        this.type = type;
        this.blockType = Type.fromId(type);
        this.x = x;
        this.y = y;
        this.z = z;
        this.data = data;
    }

    /**
     * Type of block
     * 
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * Set type of block
     * 
     * @param type
     */
    public void setType(int type) {
        this.blockType = Type.fromId(type);
        this.type = type;
    }

    /**
     * Gets X location
     * 
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets X location
     * 
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets Y location
     * 
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets Y location
     * 
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets Z location
     * 
     * @return z
     */
    public int getZ() {
        return z;
    }

    /**
     * Sets Z location
     * 
     * @param z
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * If this block was clicked, this will return the face that was clicked.
     * 
     * @return face clicked
     */
    public Face getFaceClicked() {
        return faceClicked;
    }

    /**
     * Sets the face that was clicked
     * 
     * @param faceClicked
     *            face clicked
     */
    public void setFaceClicked(Face faceClicked) {
        this.faceClicked = faceClicked;
    }

    /**
     * Returns the destruction status of this block.
     * 
     * @return 0 = Started Digging, 1 = Digging, 2 = Stopped digging, 3 = Block
     *         broken. Note: You have to return true for onBlockDestroy for all
     *         of these (except 2) to prevent the block from being destroyed.
     *         Returning false just on block broken will not work. Another note
     *         is that 0 is called often, far less than 1 but is still called.
     *         Good for toggling something.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the current destruction status of this block.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Returns this block's data
     * 
     * @return
     */
    public int getData() {
        return data;
    }

    /**
     * Sets this block's data
     * 
     * @param data
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * Updates this block to the server.
     */
    public void update() {
        etc.getServer().setBlock(this);
    }

    /**
     * Returns the block at the given Face
     * 
     * @param face the block face of which to return
     * @return Block at the specified Face
     */
    public Block getFace(Face face) {
        if (face == null) return null;

        switch (face) {
            case Front:
                return getRelative(1, 0, 0);
            case Back:
                return getRelative(-1, 0, 0);
            case Top:
                return getRelative(0, 1, 0);
            case Bottom:
                return getRelative(0, -1, 0);
            case Left:
                return getRelative(0, 0, 1);
            case Right:
                return getRelative(0, 0, -1);
        }

        return null;
    }

    /**
     * Synchronises this Block with the server, abandoning all local
     * changes and refreshing the data with the current actual values
     */
    public void refresh() {
        type = etc.getServer().getBlockIdAt(x, y, z);
        data = etc.getServer().getBlockData(x, y, z);
        status = 0;
    }

    /**
     * Finds a Block relative to this Block
     * 
     * @param x amount to shift the x coordinate
     * @param y amount to shift the y coordinate
     * @param z amount to shift the z coordinate
     * 
     * @return Block at the requested location
     */
    public Block getRelative(int x, int y, int z) {
        return etc.getServer().getBlockAt(this.getX() + x, this.getY() + y, this.getZ() + z);
    }

    /**
     * Checks if this block is being powered through redstone
     * 
     * @return true if the block is being powered
     */
    public boolean isPowered() {
        return etc.getServer().isBlockPowered(this);
    }

    /**
     * Checks if this block is being indirectly powered through redstone
     *
     * @return true if the block is being indirectly powered
     */
    public boolean isIndirectlyPowered() {
        return etc.getServer().isBlockIndirectlyPowered(this);
    }

    /**
     * Returns a String value representing this Block
     * 
     * @return String representation of this block
     */
    @Override
    public String toString() {
        return String.format("Block[x=%d, y=%d, z=%d, type=%d]", x, y, z, type);
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
        final Block other = (Block) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.z != other.z) {
            return false;
        }
        return true;
    }

    /**
     * Returns a semi-unique hashcode for this block
     * 
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        hash = 97 * hash + this.z;
        return hash;
    }

}
