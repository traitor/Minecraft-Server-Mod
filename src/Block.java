/**
 * Provides some way of making/editing blocks
 * @author James
 */
public class Block {
    private int type, x, y, z;

    /**
     * Create a block with a type or x, y and z.
     */
    public Block() { }

    /**
     * Creates a block of specified type
     * @param type
     */
    public Block(int type) { this.type = type; }

    /**
     * Creates a block of specified type and specified x, y and z
     * @param type Type of block
     * @param x
     * @param y
     * @param z
     */
    public Block(int type, int x, int y, int z) { this.type = type; this.x = x; this.y = y; this.z = z; };

    /**
     * Type of block
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * Set type of block
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Gets X location
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets X location
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets Y location
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets Y location
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets Z location
     * @return z
     */
    public int getZ() {
        return z;
    }

    /**
     * Sets Z location
     * @param z
     */
    public void setZ(int z) {
        this.z = z;
    }
}
