
/**
 * Block.java - Provides some way of making/editing blocks
 * 
 * @author James
 */
public class Block {

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
