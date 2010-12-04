/**
 * Chest.java - Interface to chests.
 * @author James
 */
public class Chest extends ItemContainerBlock<ic> {
    
    /**
     * Creates a chest interface
     * @param localay
     */
    public Chest(ic chest) {
        super(chest);
    }

    /**
     * Returns the content size of this chest
     * @return
     */
    public int getContentSize() {
        return 27;
    }

    /**
     * Returns a String value representing this object
     * 
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return String.format("Chest[x=%d, y=%d, z=%d]", getX(), getY(), getZ());
    }
}
