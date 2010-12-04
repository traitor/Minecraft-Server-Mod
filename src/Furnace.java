/**
 * Furnace.java - Interface for furnaces
 * @author James
 */
public class Furnace extends ItemContainerBlock<dv> {

    /**
     * Creates a furnace interface
     * @param localay
     */
    public Furnace(dv furnace) {
        super(furnace);
    }

    /**
     * Returns the size of contents
     * @return size
     */
    public int getContentSize() {
        return 3;
    }

    /**
     * Returns a String value representing this object
     * 
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return String.format("Furnace[x=%d, y=%d, z=%d]", getX(), getY(), getZ());
    }
}
