/**
 * Block.java - Provides some way of making/editing blocks
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
        private Face(int id) { this.id = id; }

        /**
         * Returns a Face according to the specified ID
         * @param id id of face
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

    /**
     * If this block was clicked, this will return the face
     * that was clicked.
     * @return face clicked
     */
    public Face getFaceClicked() {
        return faceClicked;
    }

    /**
     * Sets the face that was clicked
     * @param faceClicked face clicked
     */
    public void setFaceClicked(Face faceClicked) {
        this.faceClicked = faceClicked;
    }
}
